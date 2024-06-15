package com.capstone.h_buddy.ui.profil

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.capstone.h_buddy.R
import com.capstone.h_buddy.databinding.FragmentProfileBinding
import com.capstone.h_buddy.ui.welcome.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ProfileViewModel>()
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayUsername()
//        buttonNavigation()
        logoutUser()
        setupButton()
        setDarkMode()
    }

    private fun displayUsername() {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user != null){
            val username = user.displayName
            val photoUrl = user.photoUrl

            binding.tvNameProfile.text = username ?: "Nama Pengguna Tidak Tersedia"

            if(photoUrl != null){
                Glide.with(this)
                    .load(photoUrl)
                    .into(binding.ivProfilePicture)
            }else {
                binding.ivProfilePicture.setImageResource(R.drawable.ic_launcher_foreground)
            }
        }
    }

    private fun logoutUser() {
        auth = Firebase.auth
        val firebaseUser = auth.currentUser

        if (firebaseUser == null){
            startActivity(Intent(requireContext(), WelcomeActivity::class.java))
            requireActivity().finish()
            return
        }
    }

    private fun setupButton() {
        binding.apply {
            buttonAbout.setOnClickListener {
                startActivity(Intent(requireContext(), AboutActivity::class.java))
            }
            buttonLogout.setOnClickListener { signOut()}
        }
    }

    private fun signOut() {
        lifecycleScope.launch {
            val credentialManager = CredentialManager.create(requireActivity())
            auth.signOut()
            credentialManager.clearCredentialState(ClearCredentialStateRequest())
            startActivity(Intent(requireActivity(), WelcomeActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun setDarkMode() {
        val switch = binding.profileDarkmodeSwitch

        lifecycleScope.launch {
            viewModel.darkModeFlow.collect { isDarkMode ->
                switch.isChecked = isDarkMode
            }
        }

        switch.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                viewModel.startDarkMode(isChecked)

                delay(100)

                AppCompatDelegate.setDefaultNightMode(
                    if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
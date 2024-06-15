package com.capstone.h_buddy.ui.profil

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.capstone.h_buddy.R
import com.capstone.h_buddy.databinding.FragmentProfileBinding
import com.capstone.h_buddy.ui.favorite.FavoriteFragment
import com.capstone.h_buddy.ui.welcome.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        buttonNavigation()
        logoutUser()
        setupButton()

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




}
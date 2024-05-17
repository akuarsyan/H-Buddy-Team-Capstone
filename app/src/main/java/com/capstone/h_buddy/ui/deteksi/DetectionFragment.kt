package com.capstone.h_buddy.ui.deteksi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.capstone.h_buddy.databinding.FragmentDetectionBinding
import com.capstone.h_buddy.ui.deteksi.hasil.ResultActivity
import com.capstone.h_buddy.util.getImageUri

class DetectionFragment : Fragment() {
    private lateinit var binding: FragmentDetectionBinding
    private var currentImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetectionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonGallery.setOnClickListener { startGallery() }
        binding.buttonCamera.setOnClickListener { startCamera() }

        //sementara untuk preview
        binding.buttonDetection.setOnClickListener {
            val intent = Intent(this.requireContext(), ResultActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(this.requireContext())
        intentToCamera.launch(currentImageUri!!)
    }

    private val intentToCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun startGallery() {
        intentToGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val intentToGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No image selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Photo Picker", "showImage: $it")
            binding.ivDetectionPreview.setImageURI(it)
        }
    }


}
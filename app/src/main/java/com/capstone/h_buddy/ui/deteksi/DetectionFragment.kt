package com.capstone.h_buddy.ui.deteksi

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.capstone.h_buddy.data.api.detectionmodel.ClassificationResponse
import com.capstone.h_buddy.data.server.ApiConfig
import com.capstone.h_buddy.databinding.FragmentDetectionBinding
import com.capstone.h_buddy.ui.deteksi.hasil.ResultActivity
import com.capstone.h_buddy.utils.tools.getImageUri
import com.capstone.h_buddy.utils.tools.reduceImageFile
import com.capstone.h_buddy.utils.tools.uriToFile
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException

@AndroidEntryPoint
class DetectionFragment : Fragment() {
    private lateinit var binding: FragmentDetectionBinding
    private var currentImageUri: Uri? = null

    private val viewModel: DetectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonGallery.setOnClickListener { startGallery() }
        binding.buttonCamera.setOnClickListener { startCamera() }
        binding.cardPreview.setOnClickListener { startGallery() }

        binding.buttonDetection.setOnClickListener {
            uploadImage()
        }

        viewModel.classificationResponse.observe(viewLifecycleOwner, { response ->
            response?.let {
                showLoading(false)
                val intent = Intent(requireContext(), ResultActivity::class.java).apply {
                    putExtra("result", it.data.result)
                    putExtra("confidenceScore", it.data.confidenceScore.toString())
                    putExtra("description", it.data.description)
                    putExtra("binomial", it.data.binomial)
                    putExtra("benefit", it.data.benefit?.joinToString(", "))
                }
                startActivity(intent)
            }
        })
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
            binding.buttonDetection.isEnabled = true
        }
    }

    private fun uploadImage() {
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, requireContext()).reduceImageFile()
            Log.d("Image Classification File", "showImage: ${imageFile.path}")
            showLoading(true)
            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val multipartImageFile = MultipartBody.Part.createFormData(
                "image", //sebelumnya "photo"
                imageFile.name,
                requestImageFile
            )
            Log.d("Multipart Image File", "uploadImage: $multipartImageFile")
            viewModel.uploadImage(multipartImageFile)
        } ?: showToast("Please select an image first")
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarDetection.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}

package com.capstone.h_buddy.ui.beranda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.h_buddy.data.adapter.CarouselAdapter
import com.capstone.h_buddy.data.preference.CarouselModel
import com.capstone.h_buddy.R
import com.capstone.h_buddy.data.adapter.ArticleAdapter
import com.capstone.h_buddy.databinding.FragmentHomeBinding
import com.capstone.h_buddy.ui.ViewModelFactory
import com.capstone.h_buddy.utils.MyResponse
import com.capstone.h_buddy.utils.MyResponse.Status.*
import com.google.android.material.carousel.CarouselSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<CarouselModel>()
    private lateinit var carouselAdapter: CarouselAdapter

    // viewModel
    private val viewModel : HomeViewModel by viewModels()

    @Inject
    lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        // Carousel Layout
        // binding.carouselRecyclerView.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
        // Carousel Snap
        CarouselSnapHelper().attachToRecyclerView(binding.carouselRecyclerView)

        carouselAdapter = CarouselAdapter(list, requireContext())
        binding.carouselRecyclerView.adapter = carouselAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        observeArticlesData()
        viewModel.getAllNotes()

        if (list.isEmpty()) {
            list.add(CarouselModel(R.drawable.jahe, "Jahe"))
            list.add(CarouselModel(R.drawable.kencur, "Kencur"))
            list.add(CarouselModel(R.drawable.temulawak, "Temulawak"))
            list.add(CarouselModel(R.drawable.kunyit, "Kunyit"))
            list.add(CarouselModel(R.drawable.kayumanis, "Kayu Manis"))
            list.add(CarouselModel(R.drawable.lidahbuaya, "Lidah Buaya"))
            carouselAdapter.notifyDataSetChanged()
        }
    }

    private fun observeArticlesData() {
        viewModel.articleData.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                SUCCESS -> {
                    binding.progressBarArticle.visibility = View.GONE
                    response.data?.articles?.let { articleAdapter.setData(it) }
                }
                ERROR -> {
                    binding.progressBarArticle.visibility = View.GONE
                    Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                }
                LOADING -> {
                    binding.progressBarArticle.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setUpViews() {
        binding.rvArticle.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = articleAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

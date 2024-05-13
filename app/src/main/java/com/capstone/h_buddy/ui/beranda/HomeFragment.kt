package com.capstone.h_buddy.ui.beranda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.h_buddy.CarouselAdapter
import com.capstone.h_buddy.CarouselModel
import com.capstone.h_buddy.R
import com.capstone.h_buddy.databinding.FragmentHomeBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.CarouselStrategy
import com.google.android.material.carousel.HeroCarouselStrategy


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<CarouselModel>()
    private lateinit var carouselAdapter: CarouselAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        //Carousel Layout
//        binding.carouselRecyclerView.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
        //Carousel Snap
        CarouselSnapHelper().attachToRecyclerView(binding.carouselRecyclerView)

        carouselAdapter = CarouselAdapter(list, requireContext())
        binding.carouselRecyclerView.adapter = carouselAdapter
        list.add(CarouselModel(R.drawable.jahe, "Jahe"))
        list.add(CarouselModel(R.drawable.kencur, "Kencur"))
        list.add(CarouselModel(R.drawable.temulawak, "Temulawak"))
        list.add(CarouselModel(R.drawable.kunyit, "Kunyit"))
        list.add(CarouselModel(R.drawable.kayumanis, "Kayu Manis"))
        list.add(CarouselModel(R.drawable.lidahbuaya, "Lidah Buaya"))


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
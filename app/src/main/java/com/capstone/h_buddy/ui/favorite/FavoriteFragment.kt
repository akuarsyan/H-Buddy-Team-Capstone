package com.capstone.h_buddy.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.h_buddy.R
import com.capstone.h_buddy.data.adapter.FavoriteViewPagerAdapter
import com.capstone.h_buddy.databinding.FragmentFavoriteBinding
import com.capstone.h_buddy.ui.favorite.tabs.FavoriteArticleFragment
import com.capstone.h_buddy.ui.favorite.tabs.FavoriteDetectionFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriteViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTabLayout()
    }

    private fun setTabLayout() {
        adapter = FavoriteViewPagerAdapter(requireActivity())
        adapter.addFragment(FavoriteDetectionFragment(), "Deteksi", R.drawable.detection_icon_selector)
        adapter.addFragment(FavoriteArticleFragment(), "Artikel", R.drawable.article_icon_selector)

        val viewPager = binding.favoriteViewPager
        val tablayout = binding.favoriteTabLayout
        viewPager.adapter = adapter

        TabLayoutMediator(tablayout, viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
            tab.setIcon(adapter.getPageIcon(position))
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("FavoriteFragment", "onDestroyView: called")
        _binding = null
    }
}

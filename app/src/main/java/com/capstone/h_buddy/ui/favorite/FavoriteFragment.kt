package com.capstone.h_buddy.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.capstone.h_buddy.R
import com.capstone.h_buddy.databinding.FragmentFavoriteBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = binding.viewPager
        val tabs: TabLayout = binding.tabsLayout
        TabLayoutMediator(tabs, viewPager) { tabLayout, position ->
            tabLayout.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    companion object {
        private val TAB_TITLES = arrayOf(
            R.string.tab_text1,
            R.string.tab_text2
        )}
}
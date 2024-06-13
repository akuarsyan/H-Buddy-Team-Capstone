package com.capstone.h_buddy.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.h_buddy.R
import com.capstone.h_buddy.databinding.FragmentFavoriteBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

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
//        Log.d("FavoriteFragment", "setTabLayout: called")
//        val viewPager = binding.viewPager
//        val tabs: TabLayout = binding.favoriteTabLayout
//        TabLayoutMediator(tabs, viewPager) { tab, position ->
//            tab.text = resources.getString(TAB_TITLES[position])
//        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("FavoriteFragment", "onDestroyView: called")
        _binding = null
    }

    companion object {
        private val TAB_TITLES = arrayOf(
            R.string.tab_text1,
            R.string.tab_text2
        )
    }
}

package com.capstone.h_buddy.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        val fragment = DetectionArticleFragment()
        fragment.arguments = Bundle().apply {
            putInt(DetectionArticleFragment.ARG_POSITION, position +1)
        }
        return fragment
    }

    override fun getItemCount(): Int = 2
}
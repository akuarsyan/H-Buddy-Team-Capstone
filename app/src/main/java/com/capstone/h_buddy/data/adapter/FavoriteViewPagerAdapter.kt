package com.capstone.h_buddy.data.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FavoriteViewPagerAdapter(fragmentActivity: FragmentActivity) :
   FragmentStateAdapter(fragmentActivity) {
    private val fragmentList: ArrayList<Fragment> = ArrayList()
    private val fragmentTitleIcon: ArrayList<Pair<String, Int>> = ArrayList()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleIcon[position].first
    }

    fun getPageIcon(position: Int): Int {
        return fragmentTitleIcon[position].second
    }

    fun addFragment(fragment: Fragment, title: String, iconResId: Int) {
        fragmentList.add(fragment)
        fragmentTitleIcon.add(Pair(title, iconResId))
    }
}

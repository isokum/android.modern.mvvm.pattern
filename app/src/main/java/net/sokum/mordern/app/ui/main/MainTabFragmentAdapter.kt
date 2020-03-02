package net.sokum.mordern.app.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import net.sokum.mordern.app.ui.main.local.LocalUsersFragment
import net.sokum.mordern.app.ui.main.remote.RemoteUsersFragment


class MainTabFragmentAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            1 -> {
                LocalUsersFragment()
            }
            else -> {
                RemoteUsersFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            1 -> "Local"
            else -> "API"
        }
    }


    override fun getCount(): Int {
        return 2
    }

}
package com.example.panhe.noxintong.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.panhe.noxintong.R
import com.example.panhe.noxintong.fragment.HelpFragment
import com.example.panhe.noxintong.fragment.HomeFragment
import com.example.panhe.noxintong.fragment.MessageFragment
import com.example.panhe.noxintong.fragment.MyFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val fragmentList = arrayListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentList.add(HomeFragment())
        fragmentList.add(MessageFragment())
        fragmentList.add(HelpFragment())
        fragmentList.add(MyFragment())
        viewPager.adapter = PagerAdapter(supportFragmentManager)
        bottomBar.setViewPager(viewPager)

        bottomBar.setUnread(1,10)
    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(p: Int): Fragment {
            return fragmentList[p]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

    }
}

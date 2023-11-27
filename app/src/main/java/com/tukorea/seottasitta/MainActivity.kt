package com.tukorea.seottasitta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView


class MainActivity : AppCompatActivity() {
    var homeFragment: HomeFragment? = null
    var nearByFragment: NearByFragment? = null
    var moreFragment: MoreFragment? = null
    var myPageFragment: MyPageFragment? = null
    var settingFragment: SettingFragment? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homeFragment = HomeFragment()
        nearByFragment = NearByFragment()
        moreFragment = MoreFragment()
        myPageFragment = MyPageFragment()
        settingFragment = SettingFragment()

        supportFragmentManager.beginTransaction().replace(R.id.containers, homeFragment!!).commit()
        val navigationBarView = findViewById<NavigationBarView>(R.id.bottom_navigationview)
        navigationBarView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.containers,
                        homeFragment!!
                    ).commit()
                    return@OnItemSelectedListener true
                }
                R.id.nearBy -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.containers,
                        nearByFragment!!
                    ).commit()
                    return@OnItemSelectedListener true
                }
                R.id.more -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.containers,
                        moreFragment!!
                    ).commit()
                    return@OnItemSelectedListener true
                }
                R.id.myPage -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.containers,
                        myPageFragment!!
                    ).commit()
                    return@OnItemSelectedListener true
                }
                R.id.setting -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.containers,
                        settingFragment!!
                    ).commit()
                    return@OnItemSelectedListener true
                }

            }
            false
        })
    }
}
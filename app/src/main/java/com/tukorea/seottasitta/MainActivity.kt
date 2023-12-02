package com.tukorea.seottasitta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.tukorea.seottasitta.databinding.ActivityMainBinding
import android.animation.ObjectAnimator
import android.view.View
import android.widget.Toast

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var homeFragment: HomeFragment? = null
    var nearByFragment: NearByFragment? = null
    var moreFragment: MoreFragment? = null
    var myPageFragment: MyPageFragment? = null
    var settingFragment: SettingFragment? = null

    private lateinit var _binding: ActivityMainBinding
    private var isFabOpen = false // Fab 버튼 default는 닫혀있음

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        homeFragment = HomeFragment()
        nearByFragment = NearByFragment()
        moreFragment = MoreFragment()
        myPageFragment = MyPageFragment()
        settingFragment = SettingFragment()

        supportFragmentManager.beginTransaction().replace(R.id.containers, homeFragment!!).commit()
        val navigationBarView = findViewById<NavigationBarView>(R.id.bottom_navigationView)
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
        setFABClickEvt()
    }
    private fun setFABClickEvt(){
        // 플로팅 버튼 클릭시 애니메이션 동작 기능
        _binding.fabMain.setOnClickListener {
            toggleFab()
        }

        // 플로팅 버튼 클릭 이벤트 - 날씨확인
        _binding.fabWeatherInfo.setOnClickListener {
            val fragment = WeatherInfoFragment() // WeatherInfoFragment 인스턴스 생성
            val transaction = supportFragmentManager.beginTransaction() // FragmentTransaction 시작
            transaction.replace(R.id.containers, fragment) // containers 레이아웃을 WeatherInfoFragment로 교체
            transaction.commit() // FragmentTransaction 커밋
        }

        // 플로팅 버튼 클릭 이벤트 - 위치인증
        _binding.fabLocCheck.setOnClickListener {
            Toast.makeText(this,"location check : $isFabOpen", Toast.LENGTH_SHORT).show()
        }
        //플로팅 버튼 클릭 이벤트 - 카메라
        _binding.fabCamera.setOnClickListener {
            Toast.makeText(this,"on camera : $isFabOpen", Toast.LENGTH_SHORT).show()
        }
    }
    private fun toggleFab(){
        Toast.makeText(this,"메인 버튼 클릭!", Toast.LENGTH_SHORT).show()
        // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션
        if (isFabOpen) {
            ObjectAnimator.ofFloat(_binding.fabWeatherInfo, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(_binding.fabLocCheck, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(_binding.fabCamera, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(_binding.fabMain,  View.ROTATION,360f, 0f).apply { start() }
        } else { // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션
            ObjectAnimator.ofFloat(_binding.fabWeatherInfo, "translationY", -430f).apply { start() }
            ObjectAnimator.ofFloat(_binding.fabLocCheck, "translationY", -340f).apply { start() }
            ObjectAnimator.ofFloat(_binding.fabCamera, "translationY", -430f).apply { start() }
            ObjectAnimator.ofFloat(_binding.fabMain, View.ROTATION, 0f, 360f).apply { start() }
        }

        isFabOpen = !isFabOpen
    }
}
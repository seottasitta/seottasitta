package com.tukorea.seottasitta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        val faqBtn: Button = view.findViewById(R.id.faqBtn)
        val appInfoBtn: Button = view.findViewById(R.id.appInfoBtn)
        faqBtn.setOnClickListener {
            val fragment = FAQFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containers, fragment)
            transaction.commit()
        }
        appInfoBtn.setOnClickListener {
            val fragment = AppInfoFragment() // AppInfoFragment 인스턴스 생성
            val transaction = parentFragmentManager.beginTransaction() // FragmentTransaction 시작
            transaction.replace(R.id.containers, fragment) // containers 레이아웃을 AppInfoFragment로 교체
            transaction.commit() // FragmentTransaction 커밋
        }

        return view
    }
}

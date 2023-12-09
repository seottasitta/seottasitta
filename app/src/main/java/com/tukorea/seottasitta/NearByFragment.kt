package com.tukorea.seottasitta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Button
import android.widget.Toast

class NearByFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_near_by, container, false)
        val includedLayout1: View = view.findViewById(R.id.point1)
        val includedLayout2: View = view.findViewById(R.id.point2)
        val includedLayout3: View = view.findViewById(R.id.point3)
        val button1: ImageButton = includedLayout1.findViewById(R.id.loc_info_btn1)
        val button2: ImageButton = includedLayout2.findViewById(R.id.loc_info_btn2)
        val button3: ImageButton = includedLayout3.findViewById(R.id.loc_info_btn3)

        button1.setOnClickListener {
            Toast.makeText(activity, "1번 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show()
        }
        button2.setOnClickListener {
            Toast.makeText(activity, "2번 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show()
        }
        button3.setOnClickListener {
            Toast.makeText(activity, "3번 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}
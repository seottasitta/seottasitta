package com.tukorea.seottasitta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class LocCheckFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_loc_check, container, false)

        val locCheckBtn: Button = view.findViewById(R.id.loc_check_btn)
        locCheckBtn.setOnClickListener {
            Toast.makeText(context, "인증되었습니다", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}

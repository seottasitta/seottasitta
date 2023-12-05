package com.tukorea.seottasitta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import android.widget.ImageButton
import com.tukorea.seottasitta.R

class NearByFragment : Fragment() {

    private lateinit var includedSearchLocationBar1: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_near_by, container, false)
        includedSearchLocationBar1 = view.findViewById(R.id.included_search_location_bar1)

        val button = includedSearchLocationBar1.findViewById<ImageButton>(R.id.loc_info_btn1)
        button.setOnClickListener {
            findNavController().navigate(R.id.fragment_spot_info)
        }

        return view
    }
}
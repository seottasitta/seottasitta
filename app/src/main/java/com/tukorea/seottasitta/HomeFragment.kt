package com.tukorea.seottasitta

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.util.FusedLocationSource
import android.util.Log
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.CameraUpdate

class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private val LOCATION_PERMISSION_REQUEST_CODE = 1000
    private val TAG = "HomeFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_fragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_fragment, it).commit()
            }

        mapFragment.getMapAsync(this)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.NoFollow
        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            naverMap.locationTrackingMode = LocationTrackingMode.Follow
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        }

        // 시작 위치 설정
        val initialPosition = LatLng(37.33976, 126.7334) // 서울을 기준으로 한 초기 위치
        naverMap.moveCamera(CameraUpdate.scrollTo(initialPosition))

        // 갯골생태공원
        val gaetgol = Marker()
        gaetgol.position = LatLng(37.39115, 126.7808)
        gaetgol.icon = OverlayImage.fromResource(R.drawable.ic_map_pin)
        gaetgol.map = naverMap
        //한울공원
        val hanul = Marker()
        hanul.position = LatLng(37.35418, 126.7024)
        hanul.icon = OverlayImage.fromResource(R.drawable.ic_map_pin)
        hanul.map = naverMap
        //한울공원
        val oido = Marker()
        oido.position = LatLng(37.34565, 126.6875)
        oido.icon = OverlayImage.fromResource(R.drawable.ic_map_pin)
        oido.map = naverMap
        //한국공학대
        val tuk = Marker()
        tuk.position = LatLng(37.33976, 126.7334)
        tuk.icon = OverlayImage.fromResource(R.drawable.ic_map_pin)
        tuk.map = naverMap
        //거북섬
        val turtle = Marker()
        turtle.position = LatLng(37.32279, 126.6803)
        turtle.icon = OverlayImage.fromResource(R.drawable.ic_map_pin)
        turtle.map = naverMap
        //월곶포구
        val pogu = Marker()
        pogu.position = LatLng(37.38759, 126.7395)
        pogu.icon = OverlayImage.fromResource(R.drawable.ic_map_pin)
        pogu.map = naverMap
        //신세계 시흥프리미엄아울렛
        val sinsia = Marker()
        sinsia.position = LatLng(37.37935, 126.7371)
        sinsia.icon = OverlayImage.fromResource(R.drawable.ic_map_pin)
        sinsia.map = naverMap
        //한국공학대 2캠퍼스
        val tuk2 = Marker()
        tuk2.position = LatLng(37.32821, 126.6892)
        tuk2.icon = OverlayImage.fromResource(R.drawable.ic_map_pin)
        tuk2.map = naverMap
        Log.d(TAG, "onMapReady")
    }
}

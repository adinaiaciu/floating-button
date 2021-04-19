package com.example.buton

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Harta_1 : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    //    current location, set the camera on location
    private val LOCATION_PERMISSION_REQUEST=1
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    private fun getLocationAccess(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            mMap.isMyLocationEnabled=true
            getLocationUpdates()
            startLocationUpdates()

        }
        else
            ActivityCompat.requestPermissions(this, arrayOf (Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode==LOCATION_PERMISSION_REQUEST){
            if(grantResults.contains(PackageManager.PERMISSION_GRANTED)){
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                mMap.isMyLocationEnabled =true
            }
            else {
                Toast.makeText(this, "User has not granted location access permission", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_harta_1)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun getLocationUpdates() {
        locationRequest = LocationRequest()
        locationRequest.interval = 30000
        locationRequest.fastestInterval = 20000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult.locations.isNotEmpty()) {
                    val location = locationResult.lastLocation
                    if (location != null) {
                        val latLng = LatLng(location.latitude, location.longitude)
                        val markerOptions = MarkerOptions().position(latLng)
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                    }
                }
            }
        }
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
        )
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getLocationAccess()


        //places for recycling
        val bratianu = LatLng(44.434593587742924, 26.103121324271978)
        mMap.addMarker(MarkerOptions().position(bratianu).title("metal, aluminium, glass, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val voda = LatLng(44.43076073309459, 26.111370986433492)
        mMap.addMarker(MarkerOptions().position(voda).title("metal, aluminium, paper, cardboard, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val bratianu_unirii = LatLng(44.43139105114145, 26.103793204364802)
        mMap.addMarker(MarkerOptions().position(bratianu_unirii).title("metal, aluminium, paper, cardboard, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val titan_metrou = LatLng(44.42926587222399, 26.162318995340897)
        mMap.addMarker(MarkerOptions().position(titan_metrou).title("metal, aluminium, paper, cardboard, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val LiviuRebreanu = LatLng(44.42730124452252, 26.171982663585577)
        mMap.addMarker(MarkerOptions().position(LiviuRebreanu).title("metal, aluminium, plastic, glass, paper, cardboard, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val orhidee = LatLng(44.45398740860817, 26.0672841417036)
        mMap.addMarker(MarkerOptions().position(orhidee).title("glass, paper, cartboard, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val crangasi = LatLng(44.45357243791178, 26.047777741782465)
        mMap.addMarker(MarkerOptions().position(crangasi).title("metal, aluminium, paper, cardboard, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val virtutii= LatLng(44.45279790060152, 26.047376719018732)
        mMap.addMarker(MarkerOptions().position(virtutii).title("metal, aluminium, paper, cardboard, plastic, PET, glass, electronic devices, textiles").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val victoriei = LatLng(44.43077010131151, 26.097129363773313)
        mMap.addMarker(MarkerOptions().position(victoriei).title("metal, aluminium, glass, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val vlahuta = LatLng(44.42195652588831, 26.115898840697767)
        mMap.addMarker(MarkerOptions().position(vlahuta).title("metal, aluminium, paper, cardboard, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val NervaTraian = LatLng(44.42222603562662, 26.116160217936198)
        mMap.addMarker(MarkerOptions().position(NervaTraian).title("metal, aluminium, paper, cardboard, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val scoala_g_81 = LatLng(44.419761146871565, 26.115251168708873)
        mMap.addMarker(MarkerOptions().position(scoala_g_81).title("metal, aluminium, paper, cardboard, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val vlaicuVoda = LatLng(44.41954895902932, 26.121839440623013)
        mMap.addMarker(MarkerOptions().position(vlaicuVoda).title("metal, aluminium, paper, cardboard, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val vitan = LatLng(44.42117514228601, 26.124961717916833)
        mMap.addMarker(MarkerOptions().position(vitan).title("metal, aluminium, paper, cardboard, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val breaza = LatLng(44.4183023799602, 26.129074695072106)
        mMap.addMarker(MarkerOptions().position(breaza).title("metal, aluminium, paper, cardboard, glass, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val berceni = LatLng(44.398487707938045, 26.11001403990361)
        mMap.addMarker(MarkerOptions().position(berceni).title("metal, aluminium, paper, cardboard, glass, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val sunPlaza = LatLng(44.396339348664306, 26.12264081706699)
        mMap.addMarker(MarkerOptions().position(sunPlaza).title("metal, aluminium, plastic, PET, electronics").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val erou = LatLng(44.39141747002811, 26.11361238520107)
        mMap.addMarker(MarkerOptions().position(erou).title("plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val solstitiului = LatLng(44.37221472496015, 26.154440384512302)
        mMap.addMarker(MarkerOptions().position(solstitiului).title("metal, aluminium, paper, cardboard, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val mol13 = LatLng(44.42089947650124, 26.0649399402564)
        mMap.addMarker(MarkerOptions().position(mol13).title("metal, aluminium").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val timisoara33 = LatLng(44.42786133873826, 26.04054047101903)
        mMap.addMarker(MarkerOptions().position(timisoara33).title("metal, aluminium, paper, cardboard, glass, plastic, pet, electronics, textiles").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val timisoara8d = LatLng(44.42892599149183, 26.047254730829838)
        mMap.addMarker(MarkerOptions().position(timisoara8d).title("metal, aluminium, paper, cardboard, glass, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val drumulTaberei55 = LatLng(44.422824699265185, 26.028501140761655)
        mMap.addMarker(MarkerOptions().position(drumulTaberei55).title("metal, aluminium, paper, cardboard, glass, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val kauflandSebastian = LatLng(44.416188764690965, 26.070874103241113)
        mMap.addMarker(MarkerOptions().position(kauflandSebastian).title("metal, aluminium, paper, cardboard, glass, plastic, PET, electronics").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val carrefourSebastian = LatLng(44.4180704268858, 26.071776505120532)
        mMap.addMarker(MarkerOptions().position(carrefourSebastian).title("metal, aluminium, glass, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val marinescuConstantin = LatLng(44.42438499986422, 26.064258091065536)
        mMap.addMarker(MarkerOptions().position(marinescuConstantin).title("metal, aluminium, paper, cardboard, glass, platsic, PET, electronics").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val constantinZlatescu = LatLng(44.438681012611674, 26.133878483389918)
        mMap.addMarker(MarkerOptions().position(constantinZlatescu).title("metal, aluminium, paper, cardboard, glass, plastic, PET, electronics").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val macazului = LatLng(44.43954145373603, 26.161419713744625)
        mMap.addMarker(MarkerOptions().position(macazului).title("metal, aluminium, paper, cardboard, glass, plastic, PET, electronics, wood, tires, others").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val basarabia = LatLng(44.43242114581456, 26.15237311708785)
        mMap.addMarker(MarkerOptions().position(basarabia).title("glass").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val ionNedelcu = LatLng(44.41623141774364, 26.146693926309506)
        mMap.addMarker(MarkerOptions().position(ionNedelcu).title("metal, aluminium, paper, cardboard, glass, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val tomisDristor = LatLng(44.41694511310927, 26.145294497021066)
        mMap.addMarker(MarkerOptions().position(tomisDristor).title("metal, aluminium, paper, cardboard, glass, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val istriei = LatLng(44.4156490807117, 26.14251229287244)
        mMap.addMarker(MarkerOptions().position(istriei).title("paper, cardboard, glass, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val ramnicuValcea = LatLng(44.41591093349628, 26.140755523635438)
        mMap.addMarker(MarkerOptions().position(ramnicuValcea).title("metal, aluminium, paper, cardboard, glass, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        val birsescuAgatha = LatLng(44.4179730673866, 26.135220225308235)
        mMap.addMarker(MarkerOptions().position(birsescuAgatha).title("metal, aluminium, paper, cardboard, glass, plastic, PET").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))


    }
}
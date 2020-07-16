package com.croczi.mamaclean.ui.Main

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.croczi.mamaclean.MainActivity

import com.croczi.mamaclean.R
import com.croczi.mamaclean.data.auth.profile.myPackage.myPackages
import com.croczi.mamaclean.data.auth.profile.myPackegeItems.myPackegeItemRes
import com.croczi.mamaclean.data.auth.profile.myRequests.MyRequests
import com.croczi.mamaclean.data.auth.signUp.signUpResponse
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.packege.PackageRes
import com.croczi.mamaclean.data.packege.items.itemsInPackage
import com.croczi.mamaclean.network.myApi
import com.croczi.mamaclean.ui.Main.adapters.PackagesAdapter
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.calculateNoOfColumns
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.wakely.ui.auth.MainLesener
import com.mustafayusef.wakely.ui.auth.MainRepostary
import kotlinx.android.synthetic.main.main__fragment.*
import kotlinx.android.synthetic.main.progress.*

class main_Fragment : Fragment(),MainLesener , PermissionListener {
    val Fine_LOCAION=android.Manifest.permission.ACCESS_FINE_LOCATION
    val Cursu_Location=android.Manifest.permission.ACCESS_COARSE_LOCATION
    val Location_perrmision_Code=1234

    var ifLocationPerrmissionGranted=false
    var mMap: GoogleMap?=null
    var locationCall: LocationCallback?=null
    var fusedLocation: FusedLocationProviderClient?=null
    override fun OnSuccessGetMyPackages(it1: myPackages) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnSuccessGetRequest(it1: MyRequests) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSusessInsertPackage(s: String) {

    }

    override fun OnsuccessMyPackageItems(it1: myPackegeItemRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnSuccessBuyPackage(message: reSendRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnSuccessItems(message: itemsInPackage) {

    }

    override fun OnStart() {
        mainScroll?.isRefreshing=true
    }

    override fun OnSuccessSignIn(response: signUpResponse) {

    }

    override fun onFailer(message: String) {
        context?.toast(message)
        mainScroll?.isRefreshing=false
        noNetContainer?.visibility=View.GONE
    }

    override fun onFailerNet(message: String) {
        context?.toast(context?.resources!!.getString(R.string.noInternet))
        mainScroll?.isRefreshing=false
        noNetContainer?.visibility=View.VISIBLE
        mainPackageList?.visibility=View.GONE
    }

    override fun OnSuccessPackage(message: PackageRes) {
        mainPackageList?.visibility=View.VISIBLE
        mainScroll?.isRefreshing=false
        mainPackageList?.adapter=PackagesAdapter(context!!,message)
        noNetContainer?.visibility=View.GONE
        mainScroll?.setOnRefreshListener {
            viewModel?.getMyPackage(currentLocation!!.longitude
                ,currentLocation!!.latitude)
        }
    }

    companion object {
        fun newInstance() = main_Fragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main__fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val db= context?.let { databaseApp(it) }
        val repostary= MainRepostary(api!!,db!!)
        val factory= MainViewModelFactory(repostary)
        Dexter.withActivity(activity).withPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(this).check()
        viewModel = ViewModelProviders.of(this,factory).get(MainViewModel::class.java)
        viewModel?.Auth=this
        mainPackageList?.layoutManager= GridLayoutManager(context!!,
            calculateNoOfColumns(context!!,180f)
        )

        mainScroll?.setOnRefreshListener {
                if(currentLocation!=null){
                    viewModel?.getMyPackage(currentLocation!!.longitude
                        ,currentLocation!!.latitude)
                }else{
                    Dexter.withActivity(activity)
                        .withPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(this).check()
                }
        }
        retryBtn?.setOnClickListener {
            if(currentLocation!=null){
                viewModel?.getMyPackage(currentLocation!!.longitude
                    ,currentLocation!!.latitude)
            }else{
                Dexter.withActivity(activity)
                    .withPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(this).check()
            }
        }


      println("token    : "+MainActivity.cacheObj.token)


    }
    var currentLocation:android.location.Location?=null
    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        initLocation()
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
        token!!.continuePermissionRequest()
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        if(response!!.isPermanentlyDenied){
            var dilog= AlertDialog.Builder(context!!)
            dilog.setTitle("Perrmission denid")
                .setMessage("perrmission is permanintly denid go to the setting")
                .setNegativeButton("cancel",null)
                .setPositiveButton("Ok") { dialogInterface, i ->
                    var intent= Intent()
                    intent.action= Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    intent.data= Uri.fromParts("package",context!!.packageName,null)
                }
            dilog.show()
        }else{
           // context?.toast("Perrmisions is Denid")
        }
    }
     fun initLocation() {
      //  context?.toast("On location permmission ready")

        fusedLocation= LocationServices.getFusedLocationProviderClient(this?.requireActivity())

//        mMap!!.isMyLocationEnabled=true
//        mMap!!.uiSettings.isMyLocationButtonEnabled=true
        // check if GPS is enable or not
        var locationRequest= LocationRequest.create()
        locationRequest.interval=10000
        locationRequest.fastestInterval=5000
        locationRequest.priority= LocationRequest.PRIORITY_HIGH_ACCURACY

        var builder= LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

        var settingLocation= LocationServices.getSettingsClient(this.requireActivity())
        var task=settingLocation.checkLocationSettings(builder.build())
        task.addOnSuccessListener(this.requireActivity(), OnSuccessListener {
            getDiviceLocation()
        })

        task.addOnFailureListener(this.requireActivity(), OnFailureListener {
            if(it is ResolvableApiException){
                var resolv=it as ResolvableApiException
                try{
                    resolv.startResolutionForResult(this.requireActivity(),51)
                }catch (e: IntentSender.SendIntentException){
                    e.printStackTrace()
                }
            }
        })
    }

    fun getDiviceLocation(){
      //  context?.toast("getDiviceLocation ")

        try {
            fusedLocation!!.lastLocation!!.addOnCompleteListener {
                if (it.isSuccessful) {
               //     context?.toast("find location successfully")
                    currentLocation = it.result
                    if (currentLocation != null) {
                        viewModel?.getMyPackage(currentLocation!!.longitude,currentLocation!!.latitude)
                    }else{

                        var locationRequest = LocationRequest.create()
                        locationRequest.interval = 10000
                        locationRequest.fastestInterval = 5000
                        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                        locationCall= object : LocationCallback() {
                            override fun onLocationResult(locationResult: LocationResult?) {
                                super.onLocationResult(locationResult)
                                if (locationResult == null) {
                                    return
                                } else {
                                    currentLocation = locationResult.lastLocation
                                    viewModel?.getMyPackage(currentLocation!!.longitude
                                        ,currentLocation!!.latitude)
                                 //   moveCamera(LatLng(currentLocation!!.latitude, currentLocation!!.longitude), DEFAUL_ZOOM)
                                    fusedLocation!!.removeLocationUpdates(locationCall)
                                }
                            }
                        }
                        fusedLocation!!.requestLocationUpdates(locationRequest, locationCall, null)

                    }
                } else {
                    context?.toast("unable to find current location")
                }

            }

        }catch (e:SecurityException){
            context?.toast("security exeption")
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==51){
            if(resultCode== Activity.RESULT_OK){
                getDiviceLocation()
            }

        }
    }

    @Override
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        ifLocationPerrmissionGranted=false
        when(requestCode){
            Location_perrmision_Code->{
                if(grantResults.size>0){
                    for(i in 0 until  grantResults.size){
                        if(grantResults[i]!= PackageManager.PERMISSION_GRANTED){
                            ifLocationPerrmissionGranted=false
                            return
                        }
                    }
                    ifLocationPerrmissionGranted=true
                    //inisialize map
                    initLocation()

                }
            }
        }
    }
}

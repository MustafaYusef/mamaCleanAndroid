package com.croczi.mamaclean.ui.Auth.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.croczi.mamaclean.MainActivity

import com.croczi.mamaclean.R
import com.croczi.mamaclean.data.auth.changePasswordRes
import com.croczi.mamaclean.data.auth.profile.myPackage.myPackages
import com.croczi.mamaclean.data.auth.profile.profile
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.citys.cities
import com.croczi.mamaclean.network.myApi
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.wakely.ui.auth.AuthRepostary
import com.mustafayusef.wakely.ui.auth.ProfileLesener
import kotlinx.android.synthetic.main.fragment_change_password.*

class changePassword : Fragment(),ProfileLesener {
    override fun OnSuccessProfile(message: profile) {
    }

    override fun OnSuccessMyPackage(message: myPackages) {
    }

    override fun OnSuccessEditeProfile(message: reSendRes) {
    }

    override fun OnSuccessUpdatePhoto(message: reSendRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun OnSuccessChangePass(message: changePasswordRes) {
        context?.toast(context?.resources!!.getString(R.string.changePassSuccess))
        changePassBtn?.visibility=View.VISIBLE
        view?.findNavController()?.navigate(R.id.profileFragment)
    }

    override fun onSusessDeleteCartPackage(s: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSusessDeleteCartOrder(s: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnStart() {
        changePassBtn?.visibility=View.GONE
    }

    override fun onFailer(message: String) {
        context?.toast(context?.resources!!.getString(R.string.somThing))
        changePassBtn?.visibility=View.VISIBLE
    }

    override fun onFailerNet(message: String) {
        context?.toast(context?.resources!!.getString(R.string.noInternet))
        changePassBtn?.visibility=View.VISIBLE
    }

    override fun OnSuccessGetCity(message: cities) {

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }
    private lateinit var viewModel: ProfileViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkIntercepter = context?.let { networkIntercepter(it) }
        val api = networkIntercepter?.let { myApi(it) }
        val db= context?.let { databaseApp(it) }

        val repostary = AuthRepostary(api!!, db!!)
        val factory = profileViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java)
        viewModel?.Auth = this

        changePassBtn?.setOnClickListener {
            if(!oldPassword?.text.toString().isNullOrEmpty()&& !newPassword?.text .toString().isNullOrEmpty()){
                viewModel?.changePassword(MainActivity.cacheObj.token,
                    oldPassword?.text.toString(),newPassword?.text.toString())

            }else{
                context?.toast(context?.resources!!.getString(R.string.completeField))
            }
        }
    }
}

package com.croczi.mamaclean.ui.Auth.profile

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
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
import kotlinx.android.synthetic.main.pop_dilog.view.*
import kotlinx.android.synthetic.main.profile_fragment.*
import java.util.*

class profileFragment : Fragment(),ProfileLesener {
    override fun OnSuccessEditeProfile(message: reSendRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnSuccessUpdatePhoto(message: reSendRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnSuccessMyPackage(message: myPackages) {
    }

    override fun OnSuccessChangePass(message: changePasswordRes) {
    }

    override fun onSusessDeleteCartPackage(s: String) {
        MainActivity.cacheObj.token=""

        view?.findNavController()?.navigate(R.id.login)
        malert?.dismiss()
        progProf?.visibility=View.GONE
    }

    override fun onSusessDeleteCartOrder(s: String) {
        viewModel?.DeleteAllCartPackage()
    }


    override fun OnStart() {
        progProf?.visibility=View.VISIBLE
    }


    override fun onFailer(message: String) {
        context?.toast(context?.resources!!.getString(R.string.somThing))
        progProf?.visibility=View.GONE
        if(message.toInt()==401){
            view?.findNavController()?.navigate(R.id.login)
            context?.toast(context?.resources!!.getString(R.string.NotUthorize))
        }
    }

    override fun onFailerNet(message: String) {
        progProf?.visibility=View.GONE
        context?.toast(context?.resources!!.getString(R.string.noInternet))
    }

    override fun OnSuccessGetCity(message: cities) {

    }

    var proPar:profile?=null
    override fun OnSuccessProfile(message: profile) {
        progProf?.visibility=View.GONE
        proPar=message
        userNameProfile?.text=message.data.profile.name
        phoneProfile?.text=message.data.profile.phone
        locProfile?.text=message.data.profile.city
        MainActivity.cacheObj.resetCode=message.data.profile.reset_Code
        profileImage?.let {it1->
            context?.let {
                Glide.with(it).load(MainActivity.constant.base+message.data.profile.photo)
                    .placeholder(R.drawable.logo).apply(RequestOptions.bitmapTransform(RoundedCorners(25)))
                    .into( it1)
            }
        }
        editeProfile?.setOnClickListener {
            var bundle=Bundle()

            bundle.putString("name",proPar!!.data.profile.name)
            bundle.putString("city",proPar!!.data.profile.city)
            view?.findNavController()?.navigate(R.id.editeProfile2,bundle)

        }

    }

    companion object {
        fun newInstance() = profileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }
    var dview: View ?=null
    var builder:AlertDialog.Builder?=null
    var malert:AlertDialog?=null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       // println("tokennnnnnnnnn   "+MainActivity.cacheObj.token)
        if(Locale.getDefault().getLanguage()=="ar"){
            myPackage?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_chevron_left_black_24dp,
                0,0,0)
            myRequest?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_chevron_left_black_24dp,
                0,0,0)
            editeProfile?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_chevron_left_black_24dp,
                0,0,0)
            changePassBt?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_chevron_left_black_24dp,
                0,0,0)
        }

        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val db= context?.let { databaseApp(it) }
        val repostary= AuthRepostary(api!!,db!!)
        val factory=profileViewModelFactory (repostary)

        viewModel = ViewModelProviders.of(this,factory).get(ProfileViewModel::class.java)
        viewModel?.Auth=this

        if(MainActivity.cacheObj.token==""){
            view?.findNavController()?.navigate(R.id.login)
        }else{
            viewModel?.getProfile(MainActivity.cacheObj.token)
        }
        logoutProfile?.setOnClickListener {
             dview = View.inflate(context,R.layout.pop_dilog, null)
            malert?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
             builder=context?.let { AlertDialog.Builder(it).setView(dview) }
             malert= builder?.show()
            malert?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dview?.descPart?.text= context?.resources!!.getString(R.string.wantLogOut)
            dview!!.conform?.setOnClickListener {
                viewModel.DeleteAllCartCategoryOrder()

            }

            }

        myPackage?.setOnClickListener {
            var bundle=Bundle()
            bundle.putBoolean("flage",false)
            view?.findNavController()?.navigate(R.id.ordersFragment,bundle)
        }
        changePassBt?.setOnClickListener {
            view?.findNavController()?.navigate(R.id.changePassword)
        }
        myRequest?.setOnClickListener {
            view?.findNavController()?.navigate(R.id.myRequest_fragment)
        }
        }






}

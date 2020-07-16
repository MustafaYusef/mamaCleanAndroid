package com.croczi.mamaclean.ui.Auth

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.croczi.mamaclean.MainActivity

import com.croczi.mamaclean.R
import com.croczi.mamaclean.data.auth.signUp.signUpResponse
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.auth.verification.verficationRes
import com.croczi.mamaclean.data.citys.City
import com.croczi.mamaclean.data.citys.cities
import com.croczi.mamaclean.network.myApi
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.wakely.ui.auth.AuthLesener
import com.mustafayusef.wakely.ui.auth.AuthRepostary
import com.onesignal.OneSignal
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.location_dailog.view.*

class signUp : Fragment(),AuthLesener {


    override fun OnSuccessSendCode(message: reSendRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnSuccessVerefication(it1: verficationRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnSuccessResetPassword(it1: reSendRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun OnStart() {
        SignInBt?.visibility=View.GONE
    }

    override fun OnSuccesSignIn(response: signUpResponse) {
        println("token  :"+response.data.token)
        if(!response.data.isActive){
            var bundel=Bundle()
            bundel.putString("token",response.data.token)
            bundel.putString("phone",ccpSign.fullNumberWithPlus.toString()+phoneNumSign?.text.toString())
            view?.findNavController()?.navigate(R.id.activateUser,bundel)
        }else{
            MainActivity.cacheObj.token=response.data.token
            context?.toast(response.message)
            view?.findNavController()?.navigate(R.id.profileFragment)


        }
        SignInBt?.visibility=View.VISIBLE
    }

    override fun onFailer(message: String) {
        SignInBt?.visibility=View.VISIBLE
        context?.toast(message)
    }

    override fun onFailerNet(message: String) {
        SignInBt?.visibility=View.VISIBLE
        context?.toast(context?.resources!!.getString(R.string.noInternet))
    }

    override fun OnSuccessGetCity(message: cities) {

        cities.clear()
        resCity= message.data.cities
        for(i in message.data.cities){
            cities.add(i.name)

        }
        ShowCity(cities as ArrayList<String>)
        SignInBt?.visibility=View.VISIBLE
    }



    companion object {
        fun newInstance() = Login()
    }

    private lateinit var viewModel: LoginViewModel
    var cities:MutableList<String> = arrayListOf()
    var resCity:List<City>?=null
    var cityId:Int=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val db= context?.let { databaseApp(it) }
        val repostary= AuthRepostary(api!!, db!!)
        val factory= AuthViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this,factory).get(LoginViewModel::class.java)
        viewModel?.Auth=this



        LocationSign?.setOnClickListener {
            viewModel?.getCity()

        }
        SignInBt?.setOnClickListener {
            val playerId:String= OneSignal.getPermissionSubscriptionState().subscriptionStatus.userId
            if(!nameSign?.text.toString().isNullOrEmpty()
                ||!phoneNumSign?.text.toString().isNullOrEmpty()
                ||!passSignUp?.text.toString().isNullOrEmpty()||
                !passSignUpconfirm?.text.toString().isNullOrEmpty()
                ||!LocationSign?.text.toString().isNullOrEmpty()){
                if(phoneNumSign?.text.toString().length==10){
                    if (passSignUp?.text.toString().equals(passSignUpconfirm?.text.toString())){
                        viewModel.SignIn(nameSign?.text.toString(),
                            ccpSign.fullNumberWithPlus.toString()+phoneNumSign?.text.toString() ,passSignUp?.text.toString(),cityId,playerId)
                    }else{
                        context?.toast(context?.resources!!.getString(R.string.confirmPassNotSame))
                    }
                }else{
                    context?.toast(context?.resources!!.getString(R.string.phoneComplet))
                }
            }else{
                context?.toast(context?.resources!!.getString(R.string.completeField))
            }
        }

    }
    fun ShowCity(
        array: ArrayList<String>
    ){

        val dview: View = layoutInflater.inflate(R.layout.location_dailog, null)
        val builder = context?.let { AlertDialog.Builder(it).setView(dview) }
        val malert= builder?.show()
        malert?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dview.filterTitle.text="Add"
        dview.filterPicker.minValue = 0
        dview.filterPicker.maxValue = array.size-1
        dview.filterPicker.wrapSelectorWheel = true
        dview.filterPicker.displayedValues = array.toTypedArray()
        var index=0

        dview.filterPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            //
            //Display the newly selected number to text view
            index=newVal
            // println(country +"   cooodkl,dl")
        }
        dview.applayFilter.setOnClickListener {

            //mileFilter.text=selectMile
            LocationSign.setText(resCity?.get(index)?.name)
            cityId= resCity?.get(index)?.id!!

            malert?.dismiss()
        }
//        dview.closeDf.setOnClickListener {
//            malert?.dismiss()
//
//        }

    }
}

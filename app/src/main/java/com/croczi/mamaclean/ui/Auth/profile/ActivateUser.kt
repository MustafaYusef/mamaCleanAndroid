package com.croczi.mamaclean.ui.Auth.profile

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.croczi.mamaclean.MainActivity

import com.croczi.mamaclean.R
import com.croczi.mamaclean.data.auth.signUp.signUpResponse
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.auth.verification.verficationRes
import com.croczi.mamaclean.data.citys.cities
import com.croczi.mamaclean.network.myApi
import com.croczi.mamaclean.ui.Auth.AuthViewModelFactory
import com.croczi.mamaclean.ui.Auth.LoginViewModel
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.wakely.ui.auth.AuthLesener
import com.mustafayusef.wakely.ui.auth.AuthRepostary
import kotlinx.android.synthetic.main.fragment_activate_user.*


class ActivateUser : Fragment(),AuthLesener {
    override fun OnSuccesSignIn(response: signUpResponse) {

    }

    override fun OnSuccessSendCode(message: reSendRes) {
        context?.toast(context?.resources!!.getString(R.string.sendCode))
        activateBtn?.visibility=View.VISIBLE
    }

    override fun OnSuccessVerefication(it1: verficationRes) {

        context?.toast(context?.resources!!.getString(R.string.activateAccount))
            MainActivity.cacheObj.token=arguments!!.getString("token")!!
        view?.findNavController()?.popBackStack(R.id.activateUser,true)
        view?.findNavController()?.popBackStack(R.id.login,true)
            view?.findNavController()?.navigate(R.id.profileFragment)
        activateBtn?.visibility=View.VISIBLE
        }



    override fun OnSuccessResetPassword(it1: reSendRes) {

    }

    override fun OnStart() {
        activateBtn?.visibility=View.GONE
    }

    override fun onFailer(message: String) {
        context?.toast(context?.resources!!.getString(R.string.somThing))
        activateBtn?.visibility=View.VISIBLE
    }

    override fun onFailerNet(message: String) {
        context?.toast(context?.resources!!.getString(R.string.noInternet))
        activateBtn?.visibility=View.VISIBLE
    }

    override fun OnSuccessGetCity(message: cities) {
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activate_user, container, false)
    }
    private lateinit var viewModel: LoginViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkIntercepter = context?.let { networkIntercepter(it) }
        val api = networkIntercepter?.let { myApi(it) }
        val db= context?.let { databaseApp(it) }
        val repostary = AuthRepostary(api!!, db!!)
        val factory = AuthViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        viewModel?.Auth = this
        arguments!!.getString("phone")?.let { viewModel?.ActivateUser(it)}

        if(arguments?.getBoolean("flage",false)!!){

        }
        val timer = object: CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countDown?.text="${context?.resources!!.getString(R.string.pressResendAfter)}\n"+(millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                ReSendCode?.setOnClickListener {
                    arguments!!.getString("phone")?.let { viewModel?.ActivateUser(it)}
                }
            }
        }
        timer.start()

        activateBtn?.setOnClickListener {
            if(!confirmCode?.text.toString().isNullOrEmpty()){
                if(arguments?.getBoolean("flage",false)!!){
                    arguments!!.getString("phone")?.let { it1 ->
                        var bundel=Bundle()
                        bundel.putString("phone",it1)
                        bundel.putString("code",confirmCode?.text.toString())
                        view?.findNavController()?.navigate(R.id.resetPassword,bundel)
                    }
                }else{
                    arguments!!.getString("phone")?.let { it1 ->
                        viewModel?.VerivecationUser(confirmCode?.text.toString(),
                            it1
                        )
                    }
                }
                    }






        }
    }
}

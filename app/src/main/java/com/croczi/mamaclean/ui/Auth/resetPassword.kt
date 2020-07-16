package com.croczi.mamaclean.ui.Auth

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.croczi.mamaclean.R
import com.croczi.mamaclean.data.auth.signUp.signUpResponse
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.auth.verification.verficationRes
import com.croczi.mamaclean.data.citys.cities
import com.croczi.mamaclean.network.myApi
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.wakely.ui.auth.AuthLesener
import com.mustafayusef.wakely.ui.auth.AuthRepostary
import kotlinx.android.synthetic.main.reset_password_fragment.*

class resetPassword : Fragment(), AuthLesener {

    companion object {
        fun newInstance() = resetPassword()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reset_password_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkIntercepter = context?.let { networkIntercepter(it) }
        val api = networkIntercepter?.let { myApi(it) }
        val db= context?.let { databaseApp(it) }
        val repostary = AuthRepostary(api!!, db!!)
        val factory = AuthViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        viewModel?.Auth = this


        sendResetPass?.setOnClickListener {
            if(!newResetPassword?.text.toString().isNullOrEmpty()){
                viewModel?.resetPassword(newResetPassword?.text.toString()
                    ,arguments!!.getString("phone")!!,arguments!!.getString("code")!!)
            }
        }

    }

    override fun OnStart() {
        sendResetPass?.visibility=View.GONE
    }

    override fun OnSuccesSignIn(response: signUpResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailer(message: String) {
        context?.toast(message)
        sendResetPass?.visibility=View.VISIBLE
    }

    override fun onFailerNet(message: String) {
        context?.toast(context?.resources!!.getString(R.string.noInternet))
        sendResetPass?.visibility=View.VISIBLE
    }

    override fun OnSuccessGetCity(message: cities) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnSuccessSendCode(message: reSendRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnSuccessVerefication(it1: verficationRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnSuccessResetPassword(it1: reSendRes) {
        context?.toast(context?.resources!!.getString(R.string.changePassSuccess))

        sendResetPass?.visibility=View.VISIBLE
        view?.findNavController()?.navigate(R.id.login)
    }

}

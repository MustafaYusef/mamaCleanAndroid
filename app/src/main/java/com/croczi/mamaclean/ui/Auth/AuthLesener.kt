package com.mustafayusef.wakely.ui.auth

import com.croczi.mamaclean.data.auth.signUp.signUpResponse
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.auth.verification.verficationRes
import com.croczi.mamaclean.data.citys.cities


interface AuthLesener {
    fun OnStart()
    fun OnSuccesSignIn(response:signUpResponse)
    fun onFailer(message:String)
    fun onFailerNet(message:String)
   fun OnSuccessGetCity(message:cities)



    fun OnSuccessSendCode(message: reSendRes)
     fun OnSuccessVerefication(it1: verficationRes)
     fun OnSuccessResetPassword(it1: reSendRes)
}
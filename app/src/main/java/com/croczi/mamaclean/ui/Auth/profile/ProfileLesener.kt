package com.mustafayusef.wakely.ui.auth

import com.croczi.mamaclean.data.auth.changePasswordRes
import com.croczi.mamaclean.data.auth.profile.myPackage.myPackages
import com.croczi.mamaclean.data.auth.profile.profile
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.citys.cities


interface ProfileLesener {
    fun OnStart()
    fun onFailer(message:String)
    fun onFailerNet(message:String)
   fun OnSuccessGetCity(message:cities)
    fun OnSuccessProfile(message: profile)
    fun OnSuccessMyPackage(message: myPackages)
    fun OnSuccessEditeProfile(message: reSendRes)
    fun OnSuccessUpdatePhoto(message: reSendRes)

    fun OnSuccessChangePass(message: changePasswordRes)
     fun onSusessDeleteCartPackage(s: String)
     fun onSusessDeleteCartOrder(s: String)


}
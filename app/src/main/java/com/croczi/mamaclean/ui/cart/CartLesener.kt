package com.mustafayusef.wakely.ui.auth

import com.croczi.mamaclean.data.auth.profile.myPackage.myPackages
import com.croczi.mamaclean.data.auth.profile.myRequests.MyRequests
import com.croczi.mamaclean.data.auth.signUp.signUpResponse
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.packege.PackageRes
import com.croczi.mamaclean.data.packege.items.itemsInPackage
import com.mustafayusef.sharay.database.entitis.PackageOrder
import com.mustafayusef.sharay.database.entitis.catagoryOrder


interface CartLesener {
    fun OnStart()

    fun onFailer(message:String)
    fun onFailerNet(message:String)


     fun onSusessDeletePackage(s: String)
     fun onSusessgetCartPackage(it: List<PackageOrder>)
     fun onSusessDeleteCartPackage(s: String)

    fun onSusessDeleteOrder(s: String)
    fun onSusessgetCartOrder(it: List<catagoryOrder>)
    fun onSusessDeleteCartOrder(s: String)
     fun onSuccessBuyOrderCategory(it1: reSendRes)
     fun onSussessBuyPackage(it1: reSendRes)
}
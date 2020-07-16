package com.mustafayusef.wakely.ui.auth

import com.croczi.mamaclean.data.auth.profile.myPackage.myPackages
import com.croczi.mamaclean.data.auth.profile.myPackegeItems.myPackegeItemRes
import com.croczi.mamaclean.data.auth.profile.myRequests.MyRequests
import com.croczi.mamaclean.data.auth.signUp.signUpResponse
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.packege.PackageRes
import com.croczi.mamaclean.data.packege.items.itemsInPackage
import com.mustafayusef.sharay.database.entitis.PackageOrder


interface MainLesener {
    fun OnStart()
    fun OnSuccessSignIn(response:signUpResponse)
    fun onFailer(message:String)
    fun onFailerNet(message:String)

    fun OnSuccessItems(message: itemsInPackage)
    fun OnSuccessPackage(message: PackageRes)
    fun OnSuccessBuyPackage(message: reSendRes)
     fun OnSuccessGetMyPackages(it1: myPackages)

    fun OnSuccessGetRequest(it1: MyRequests)
     fun onSusessInsertPackage(s: String)
     fun OnsuccessMyPackageItems(it1: myPackegeItemRes)


}
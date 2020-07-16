package com.mustafayusef.wakely.ui.auth

import com.croczi.mamaclean.data.auth.profile.myPackage.myPackages
import com.croczi.mamaclean.data.auth.profile.myRequests.MyRequests
import com.croczi.mamaclean.data.auth.signUp.signUpResponse
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.orders.categoryOrder.categoryOrderDetails.categoryOrderDetailsRes
import com.croczi.mamaclean.data.orders.categoryOrder.categoryOrdersRes.categoryOrderRes
import com.croczi.mamaclean.data.orders.packageOrder.packageOrderDetails.packageOrderDetailsRes
import com.croczi.mamaclean.data.orders.packageOrder.packageOrderRes.packageOrderRes
import com.croczi.mamaclean.data.packege.PackageRes
import com.croczi.mamaclean.data.packege.items.itemsInPackage
import com.mustafayusef.sharay.database.entitis.PackageOrder
import com.mustafayusef.sharay.database.entitis.catagoryOrder


interface OrderLesener {
    fun OnStart()

    fun onFailer(message:String)
    fun onFailerNet(message:String)
     fun onSussessGetNormalOrder(it1: categoryOrderRes)
     fun onSussessGetNormalOrderDetails(it1: categoryOrderDetailsRes)
     fun onSussessCancelNormalOrder(it1: reSendRes)
     fun onSussessGetPackageOrder(it1: packageOrderRes)
     fun onSussessGetPackageOrderDetails(it1: packageOrderDetailsRes)
     fun onSussessCancelPackageOrder(it1: reSendRes)


}
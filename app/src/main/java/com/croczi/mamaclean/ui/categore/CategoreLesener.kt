package com.mustafayusef.wakely.ui.auth

import com.croczi.mamaclean.data.auth.profile.profile
import com.croczi.mamaclean.data.auth.signUp.signUpResponse
import com.croczi.mamaclean.data.categore.categoreRes
import com.croczi.mamaclean.data.categore.categoryItems.categoryItemsRes
import com.croczi.mamaclean.data.citys.cities
import com.croczi.mamaclean.data.packege.PackageRes
import com.croczi.mamaclean.data.packege.items.itemsInPackage


interface CategoreLesener {
    fun OnStart()

    fun OnSuccessCategore(message:categoreRes)
    fun OnSuccessCategoreItems(message:categoryItemsRes)
    fun onFailer(message:String)
    fun onFailerNet(message:String)
     fun onSusessInsertcategoryOrder(s: String)

}
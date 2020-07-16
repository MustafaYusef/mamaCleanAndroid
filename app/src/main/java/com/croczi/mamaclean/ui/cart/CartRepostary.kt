package com.mustafayusef.wakely.ui.auth


import com.croczi.mamaclean.data.auth.profile.myPackage.myPackages
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.categore.categoreRes
import com.croczi.mamaclean.data.categore.categoryItems.categoryItemsRes
import com.croczi.mamaclean.data.packege.PackageRes
import com.croczi.mamaclean.data.packege.items.itemsInPackage
import com.croczi.mamaclean.network.myApi
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.sharay.database.entitis.PackageOrder
import com.mustafayusef.sharay.database.entitis.catagoryOrder

import com.mustafayusef.wakely.utils.SafeApiRequest

class CartRepostary(val api: myApi, val db:databaseApp): SafeApiRequest() {
//Package
    suspend fun getAllPackageOrder():List<PackageOrder>{
        return db.Car_Dao().getAllPackageOrder()
    }
    suspend fun deleteAllPackageOrder(){
        return db.Car_Dao().deleteAllPackageOrder()
    }

    suspend fun deletePackOredrById(id:Int):Int{
        return db.Car_Dao().deletePackOredrById(id)
    }

    //category
    suspend fun getAllCategoryOrder():List<catagoryOrder>{
        return db.Car_Dao().getAllCategoryOrder()
    }
    suspend fun deleteAllCategoryOrder(){
        return db.Car_Dao().deleteAllCategoryOrder()
    }

    suspend fun deleteCatOredrById(id:Int):Int{
        return db.Car_Dao().deleteCatOrderById(id)
    }

    suspend fun BuyOrderCategory(token:String,lat:Double,lon:Double, price: Int,
             type: String,itemsCount: Int,itemsIds:List<Int>
    ,counts:List< Int>,
    types:List< String>): reSendRes {
        return SafeRequest {
            api.MakeOrder(token,lat,lon,
                price, type,itemsCount,itemsIds,counts,types)
        }
    }

    suspend fun BuyPackageOrder(token:String,lat:Double,lon:Double
                                ,packageId: Int,itemsCount: Int,itemsIds:List<Int>
                                 ,counts:List< Int>,
                                newCounts:List< Int>): reSendRes {
        return SafeRequest {
            api.PackageOrder(token,lat,lon
                ,packageId,itemsCount,itemsIds
            ,counts,
            newCounts)
        }
    }
}



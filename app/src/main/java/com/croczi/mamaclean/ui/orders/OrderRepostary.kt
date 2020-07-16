package com.mustafayusef.wakely.ui.auth


import com.croczi.mamaclean.data.auth.profile.myPackage.myPackages
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.categore.categoreRes
import com.croczi.mamaclean.data.categore.categoryItems.categoryItemsRes
import com.croczi.mamaclean.data.orders.categoryOrder.categoryOrderDetails.categoryOrderDetailsRes
import com.croczi.mamaclean.data.orders.categoryOrder.categoryOrdersRes.categoryOrderRes
import com.croczi.mamaclean.data.orders.packageOrder.packageOrderDetails.packageOrderDetailsRes
import com.croczi.mamaclean.data.orders.packageOrder.packageOrderRes.packageOrderRes
import com.croczi.mamaclean.data.packege.PackageRes
import com.croczi.mamaclean.data.packege.items.itemsInPackage
import com.croczi.mamaclean.network.myApi
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.sharay.database.entitis.PackageOrder
import com.mustafayusef.sharay.database.entitis.catagoryOrder

import com.mustafayusef.wakely.utils.SafeApiRequest

class OrderRepostary(val api: myApi): SafeApiRequest() {

/// ordere
    suspend fun getNormalOrder(token:String): categoryOrderRes {
        return SafeRequest {
            api.getCategoryOrders(token)
        }
    }
    suspend fun getCategoryOrderDetails(token:String,id:Int): categoryOrderDetailsRes {
        return SafeRequest {
            api.getCategoryOrderDetails(token,id)
        }
    }
    suspend fun cancelCategoryOrder(token:String,id:Int): reSendRes {
        return SafeRequest {
            api.CancelOrder(token,id)
        }
    }



/// package
    suspend fun getPackageOrder(token:String): packageOrderRes {
        return SafeRequest {
            api.getPackageOrders(token)
        }
    }
    suspend fun getPackageOrderDetails(token:String,id:Int): packageOrderDetailsRes {
        return SafeRequest {
            api.getPackageOrdersDetails(token,id)
        }
    }
    suspend fun cancelPackageOrder(token:String,id:Int): reSendRes {
        return SafeRequest {
            api.CancelPackageOrder(token,id)
        }
    }

}



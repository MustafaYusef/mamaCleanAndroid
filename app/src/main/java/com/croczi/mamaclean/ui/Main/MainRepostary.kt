package com.mustafayusef.wakely.ui.auth


import com.croczi.mamaclean.data.auth.profile.myPackage.myPackages
import com.croczi.mamaclean.data.auth.profile.myPackegeItems.myPackegeItemRes
import com.croczi.mamaclean.data.auth.profile.myRequests.MyRequests
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.packege.PackageRes
import com.croczi.mamaclean.data.packege.items.itemsInPackage
import com.croczi.mamaclean.network.myApi
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.sharay.database.entitis.PackageOrder

import com.mustafayusef.wakely.utils.SafeApiRequest

class MainRepostary(val api: myApi,val db: databaseApp): SafeApiRequest() {

    suspend fun getMyPackage(lon:Double,lat:Double): PackageRes {

        return SafeRequest {
            api.getPackages(lon,lat)
        }
    }
    suspend fun getPackageMain(token:String,lon:Double,lat:Double): myPackages {

        return SafeRequest {
            api.getMyPackage(token,lon,lat)
        }
    }

    suspend fun getMyRequestBuyPackage(token:String): MyRequests {

        return SafeRequest {
            api.getBuyPackageRequest(token)
        }
    }
    suspend fun getItems(id:Int): itemsInPackage {

        return SafeRequest {
            api.getPackageItems(id)
        }
    }
    suspend fun getMyPackageItems(id:Int): myPackegeItemRes {

        return SafeRequest {
            api.getUserPackageItems(id)
        }
    }
    suspend fun BuyPackage(token:String,id:Int): reSendRes {

        return SafeRequest {
            api.BuyPacage(token,id)
        }
    }
    suspend fun InsertPackageOrder(item:PackageOrder):Long{
        return db.Car_Dao().insertPackageOrder(item)
    }

}
//    suspend fun getCategore(token:String,compId:String):categoreResponse{
//
//        return SafeRequest{
//            api.GetCategore(token,compId)
//        }}
//    suspend fun AddUser(token:String,name:String,password:String,phone:String,role:Int):AddUserRes{
//        var body=addUserBody(name=name,password = password,phone = phone,role =1)
//
//        return SafeRequest{
//            api.AddUser(token,body)
//        }}
//
//    suspend fun AddShops(token:String,title:String, phone:String, provinceId:String
//                         , cityId:String, nearLocation:String,
//                         imagesBodyRequest: MultipartBody.Part, id:String):AddUserRes{
//       var provinceId=RequestBody.create(MediaType.parse("text/plain"),provinceId)
//        var  cityId = RequestBody.create(MediaType.parse("text/plain"),cityId)
//        var nearLocation = RequestBody.create(MediaType.parse("text/plain"),nearLocation)
//        var tit=RequestBody.create(MediaType.parse("text/plain"),title)
//        var phon=RequestBody.create(MediaType.parse("text/plain"),phone)
//        var id1=RequestBody.create(MediaType.parse("text/plain"),id)
//        return SafeRequest{
//            api.AddShops(token, tit,phon,provinceId,cityId,nearLocation,imagesBodyRequest,id1)
//        }}
//
//    suspend fun GetProv():provRes{
//        return SafeRequest{
//            api.GetProv()
//        }}
//
//    suspend fun GetCity(id:String):provRes{
//        return SafeRequest{
//            api.GetCity(id)
//        }}
//
//    suspend fun Profile(token:String): profile {
//        return SafeRequest{
//            api.Profile(token)
//        }}
//
//    suspend fun Update(token:String,name:String,title:String,phone: String,image:MultipartBody.Part)
//            : loginResponse {
//        var name1=RequestBody.create(MediaType.parse("text/plain"),name)
//        var title1=RequestBody.create(MediaType.parse("text/plain"),title)
//        var phone1=RequestBody.create(MediaType.parse("text/plain"),phone)
//
//        return SafeRequest{
//            api.Update(token,name1,title1,phone1,image)
//        }}
//
//
//}
//data class addUserBody(
//    var name:String,
//    var password:String,
//    var phone:String,
//    var role:Int
//
//)
//data class LogIn(
//    var phone:String,
//    var password:String,
//    var playerId:String
//
//)
//data class location(
//    var provinceId: RequestBody,
//    var cityId:RequestBody,
//    var nearLocation:RequestBody
//
//)

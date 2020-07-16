package com.mustafayusef.wakely.ui.auth


import com.croczi.mamaclean.data.auth.changePasswordRes
import com.croczi.mamaclean.data.auth.profile.myPackage.myPackages
import com.croczi.mamaclean.data.auth.profile.profile
import com.croczi.mamaclean.data.auth.signUp.signUpResponse
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.auth.verification.verficationRes
import com.croczi.mamaclean.data.citys.cities
import com.croczi.mamaclean.network.myApi
import com.mustafayusef.sharay.database.databaseApp

import com.mustafayusef.wakely.utils.SafeApiRequest
import okhttp3.MultipartBody

class AuthRepostary(
    val api: myApi,
    val db: databaseApp
): SafeApiRequest() {
    suspend fun SignIn(name: String, phone: String, password: String, cityId: Int,PlayerId:String): signUpResponse {

        return SafeRequest {
            api.SignUp(name, phone, password, cityId,PlayerId)
        }
    }

    suspend fun LogIn(phone: String, password: String,playerId:String): signUpResponse {

        return SafeRequest {
            api.LogIn(phone, password,playerId)
        }
    }

    suspend fun getCity(): cities {

        return SafeRequest {
            api.getCity()
        }
    }
    suspend fun getProfile(token:String): profile {

        return SafeRequest {
            api.getProfile(token)
        }
    }
    suspend fun getMyPackage(token:String,lon:Double,lat:Double): myPackages {

        return SafeRequest {
            api.getMyPackage(token,lon,lat)
        }
    }
    suspend fun ActivateUser(phone:String): reSendRes {

        return SafeRequest {
            api.reSendCode(phone)
        }
    }

    suspend fun updateProfile(token:String,name:String,city:Int?): reSendRes {

        return SafeRequest {
            api.UpdateProfile(token,name,city)
        }
    }
    suspend fun updatePhoto(token:String,photo:MultipartBody.Part?): reSendRes {

        return SafeRequest {
            api.updateProfilePhoto(token,photo!!)
        }
    }

    suspend fun changePass(token:String,old:String,new:String): changePasswordRes {

        return SafeRequest {
            api.changePassword(token,old,new)
        }
    }
    suspend fun VerivecationUser(code:String,phone:String): verficationRes {

        return SafeRequest {
            api.verficationUser (code,phone)
        }
    }
    suspend fun reSetPassdword(password:String,phone:String,code:String): reSendRes {

        return SafeRequest {
            api.reSetPassword(password,code,phone)
        }
    }
    suspend fun deleteAllCategoryOrder(){
        return db.Car_Dao().deleteAllCategoryOrder()
    }
    suspend fun deleteAllPackageOrder(){
        return db.Car_Dao().deleteAllPackageOrder()
    }
}



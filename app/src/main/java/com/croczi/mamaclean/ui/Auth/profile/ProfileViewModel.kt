package com.croczi.mamaclean.ui.Auth.profile

import android.accounts.NetworkErrorException
import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.wakely.ui.auth.AuthLesener
import com.mustafayusef.wakely.ui.auth.AuthRepostary
import com.mustafayusef.wakely.ui.auth.ProfileLesener
import com.mustafayusef.wakely.utils.corurtins
import okhttp3.MultipartBody
import okhttp3.internal.http2.StreamResetException
import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

class ProfileViewModel(val repostary: AuthRepostary) : ViewModel() {
    var Auth: ProfileLesener?=null

    fun DeleteAllCartCategoryOrder() {
        Auth?.OnStart()
        corurtins.main {
            try {
                repostary.deleteAllCategoryOrder()

                Auth?.onSusessDeleteCartOrder("Success")


            } catch (e: ApiExaptions) {
                e.message?.let { Auth?.onFailer(it) }
            }
        }
    }
    fun DeleteAllCartPackage() {
        Auth?.OnStart()
        corurtins.main {
            try {
                repostary.deleteAllPackageOrder()

                Auth?.onSusessDeleteCartPackage("Success")


            } catch (e: ApiExaptions) {
                e.message?.let { Auth?.onFailer(it) }
            }
        }
    }
    fun getProfile(token:String){
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.getProfile(token)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessProfile(it1)
                }
            }catch (e: ApiExaptions){
                e.message?.let { Auth?.onFailer(it) }

            }catch (e: noInternetExeption){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SocketTimeoutException){
                e.message?.let { Auth?.onFailerNet(it) }}
            catch (e: SocketException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ProtocolException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ConnectException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SSLHandshakeException){
                e.message?.let { Auth?.onFailerNet(it)}
            }catch (e: StreamResetException){
                e.message?.let { Auth?.onFailerNet(it)}
            }catch (e:NetworkErrorException){
                e.message?.let { Auth?.onFailerNet(it)}
            }
        }
    }

    fun getmyPackage(token:String,lon:Double,lat:Double){
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.getMyPackage(token,lon,lat)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessMyPackage(it1)
                }
            }catch (e: ApiExaptions){
                e.message?.let { Auth?.onFailer(it) }

            }catch (e: noInternetExeption){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SocketTimeoutException){
                e.message?.let { Auth?.onFailerNet(it) }}
            catch (e: SocketException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ProtocolException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ConnectException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SSLHandshakeException){
                e.message?.let { Auth?.onFailerNet(it)}
            }catch (e: StreamResetException){
                e.message?.let { Auth?.onFailerNet(it)}
            }catch (e:NetworkErrorException){
                e.message?.let { Auth?.onFailerNet(it)}
            }
        }
    }

    fun UpdateProfile(token:String,name:String,city:Int?){
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.updateProfile(token,name,city)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessEditeProfile(it1)
                }


            }catch (e: ApiExaptions){
                e.message?.let { Auth?.onFailer(it) }

            }catch (e: noInternetExeption){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SocketTimeoutException){
                e.message?.let { Auth?.onFailerNet(it) }}
            catch (e: SocketException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ProtocolException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ConnectException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SSLHandshakeException){
                e.message?.let { Auth?.onFailerNet(it)}
            }catch (e: StreamResetException){
                e.message?.let { Auth?.onFailerNet(it)}
            }catch (e:NetworkErrorException){
                e.message?.let { Auth?.onFailerNet(it)}
            }
        }
    }
    fun UpdatePhoto(token:String,photo:MultipartBody.Part?){
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.updatePhoto(token,photo)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessUpdatePhoto(it1)
                }
            }catch (e: ApiExaptions){
                e.message?.let { Auth?.onFailer(it) }

            }catch (e: noInternetExeption){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SocketTimeoutException){
                e.message?.let { Auth?.onFailerNet(it) }}
            catch (e: SocketException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ProtocolException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ConnectException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SSLHandshakeException){
                e.message?.let { Auth?.onFailerNet(it)}
            }catch (e: StreamResetException){
                e.message?.let { Auth?.onFailerNet(it)}
            }catch (e:NetworkErrorException){
                e.message?.let { Auth?.onFailerNet(it)}
            }
        }
    }
    fun changePassword(token:String,old:String,new:String){
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.changePass(token,old,new)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessChangePass(it1)
                }
            }catch (e: ApiExaptions){
                e.message?.let { Auth?.onFailer(it) }

            }catch (e: noInternetExeption){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SocketTimeoutException){
                e.message?.let { Auth?.onFailerNet(it) }}
            catch (e: SocketException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ProtocolException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ConnectException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SSLHandshakeException){
                e.message?.let { Auth?.onFailerNet(it)}
            }catch (e: StreamResetException){
                e.message?.let { Auth?.onFailerNet(it)}
            }catch (e:NetworkErrorException){
                e.message?.let { Auth?.onFailerNet(it)}
            }
        }
    }

    fun getCity(){
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.getCity()
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessGetCity(it1)
                }
            }catch (e: ApiExaptions){
                e.message?.let { Auth?.onFailer(it) }

            }catch (e: noInternetExeption){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SocketTimeoutException){
                e.message?.let { Auth?.onFailerNet(it) }}
            catch (e: SocketException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ProtocolException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ConnectException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SSLHandshakeException){
                e.message?.let { Auth?.onFailerNet(it)}
            }catch (e: StreamResetException){
                e.message?.let { Auth?.onFailerNet(it)}
            }catch (e: NetworkErrorException){
                e.message?.let { Auth?.onFailerNet(it)}
            }
        }
    }
}
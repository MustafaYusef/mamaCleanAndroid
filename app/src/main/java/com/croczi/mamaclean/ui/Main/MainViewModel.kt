package com.croczi.mamaclean.ui.Main

import android.accounts.NetworkErrorException
import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.sharay.database.entitis.PackageOrder
import com.mustafayusef.wakely.ui.auth.MainLesener
import com.mustafayusef.wakely.ui.auth.MainRepostary
import com.mustafayusef.wakely.utils.corurtins
import okhttp3.internal.http2.StreamResetException
import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

class MainViewModel(var repostary: MainRepostary) : ViewModel() {
    var Auth: MainLesener?=null
    fun getMyPackage(lon: Double, lat: Double){
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.getMyPackage(lon,lat)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessPackage(it1)
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

    fun getItems(id:Int){
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.getItems(id)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessItems(it1)
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
    fun getMyPackages(token:String,lan:Double,lon: Double){
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.getPackageMain(token,lon,lan)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessGetMyPackages(it1)
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
    fun getMyRequestBuyPackage(token:String){
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.getMyRequestBuyPackage(token)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessGetRequest(it1)
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


    fun BuyPackage(token:String,id:Int){
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.BuyPackage(token,id)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessBuyPackage(it1)
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
    fun getMyPackageItems(id:Int){
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.getMyPackageItems (id)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnsuccessMyPackageItems(it1)
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

    fun insertPackageOrder(car: PackageOrder?) {
        Auth?.OnStart()
        corurtins.main {
            try {
                val NumbersResponse = car?.let { repostary.InsertPackageOrder(car) }
                NumbersResponse?.let {
                    Auth?.onSusessInsertPackage("success")
                }

            } catch (e: ApiExaptions) {
                e.message?.let { Auth?.onFailer(it) }

            }
        }
    }

}

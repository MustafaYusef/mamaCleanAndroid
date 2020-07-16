package com.croczi.mamaclean.ui.orders

import androidx.lifecycle.ViewModel
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.orders.categoryOrder.categoryOrderDetails.categoryOrderDetailsRes
import com.croczi.mamaclean.data.orders.categoryOrder.categoryOrdersRes.categoryOrderRes
import com.croczi.mamaclean.data.orders.packageOrder.packageOrderDetails.packageOrderDetailsRes
import com.croczi.mamaclean.data.orders.packageOrder.packageOrderRes.packageOrderRes
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.wakely.ui.auth.CartLesener
import com.mustafayusef.wakely.ui.auth.OrderLesener
import com.mustafayusef.wakely.ui.auth.OrderRepostary
import com.mustafayusef.wakely.utils.corurtins
import okhttp3.internal.http2.StreamResetException
import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

class OrderViewModel(val repostary: OrderRepostary) : ViewModel() {
    var Auth: OrderLesener?=null
    /// ordere
     fun getNormalOrder(token:String) {
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.getNormalOrder(token)
                CarsDetailsResponse ?.let {it1->

                    Auth?.onSussessGetNormalOrder(it1)
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
            }
        }

    }
     fun getCategoryOrderDetails(token:String,id:Int) {
         Auth?.OnStart()
         corurtins.main {
             try {
                 val CarsDetailsResponse=repostary.getCategoryOrderDetails(
                     token,id)
                 CarsDetailsResponse ?.let {it1->

                     Auth?.onSussessGetNormalOrderDetails(it1)
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
             }
         }
    }
     fun cancelCategoryOrder(token:String,id:Int) {
         Auth?.OnStart()
         corurtins.main {
             try {
                 val CarsDetailsResponse=repostary.cancelCategoryOrder(token,id)
                 CarsDetailsResponse ?.let {it1->

                     Auth?.onSussessCancelNormalOrder(it1)
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
             }
         }
    }



    /// package
     fun getPackageOrder(token:String) {
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.getPackageOrder(token)
                CarsDetailsResponse ?.let {it1->

                    Auth?.onSussessGetPackageOrder(it1)
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
            }
        }
    }
     fun getPackageOrderDetails(token:String,id:Int) {
         Auth?.OnStart()
         corurtins.main {
             try {
                 val CarsDetailsResponse=
                     repostary.getPackageOrderDetails(token,id)
                 CarsDetailsResponse ?.let {it1->

                     Auth?.onSussessGetPackageOrderDetails(it1)
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
             }
         }
    }
     fun cancelPackageOrder(token:String,id:Int) {
         Auth?.OnStart()
         corurtins.main {
             try {
                 val CarsDetailsResponse=repostary.cancelPackageOrder(token,id)
                 CarsDetailsResponse ?.let {it1->

                     Auth?.onSussessCancelPackageOrder(it1)
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
             }

         }
    }

}

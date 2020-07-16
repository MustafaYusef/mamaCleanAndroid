package com.croczi.mamaclean.ui.cart

import android.accounts.NetworkErrorException
import androidx.lifecycle.ViewModel
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.wakely.ui.auth.CartLesener
import com.mustafayusef.wakely.ui.auth.CartRepostary
import com.mustafayusef.wakely.utils.corurtins
import kotlinx.coroutines.CoroutineScope
import okhttp3.internal.http2.StreamResetException
import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

class CartViewModel(val repostary:CartRepostary) : ViewModel() {
    var Auth: CartLesener?=null
    fun getAllCartPackage() {
        Auth?.OnStart()
        corurtins.main {
            try {
                val NumbersResponse = repostary.getAllPackageOrder()
                NumbersResponse?.let {
                    Auth?.onSusessgetCartPackage(it)
                }

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

    fun DeleteByIdCartPackage(id:Int) {
        Auth?.OnStart()
        corurtins.main {
            try {
                repostary.deletePackOredrById(id)

                Auth?.onSusessDeleteCartPackage("Success delete by id")


            } catch (e: ApiExaptions) {
                e.message?.let { Auth?.onFailer(it) }
            }
        }
    }


    fun getAllCartCategoryOrder() {
        Auth?.OnStart()
        corurtins.main {
            try {
                val NumbersResponse = repostary.getAllCategoryOrder()
                NumbersResponse?.let {
                    Auth?.onSusessgetCartOrder(it)
                }

            } catch (e: ApiExaptions) {
                e.message?.let { Auth?.onFailer(it) }

            }
        }
    }
    fun DeleteAllCartCategoryOrder() {
        Auth?.OnStart()
        corurtins.main {
            try {
                repostary.deleteAllCategoryOrder()

                Auth?.onSusessDeleteCartPackage("Success")


            } catch (e: ApiExaptions) {
                e.message?.let { Auth?.onFailer(it) }
            }
        }
    }

    fun DeleteByIdCartCategoryOrder(id:Int) {
        Auth?.OnStart()
        corurtins.main {
            try {

                repostary.deleteCatOredrById(id)

                Auth?.onSusessDeleteCartOrder("Success delete category by id")


            } catch (e: ApiExaptions) {
                e.message?.let { Auth?.onFailer(it) }
            }
        }
    }


     fun BuyOrderCategory(token:String,lat:Double,lon:Double, price: Int,
                                 type: String,itemsCount: Int,itemsIds:List<Int>
                                 ,counts:List< Int>,
                                 types:List< String>) {
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.BuyOrderCategory(
                    token,lat,lon,
                    price, type,itemsCount,itemsIds,counts,types
                )
                CarsDetailsResponse ?.let {it1->

                    Auth?.onSuccessBuyOrderCategory(it1)
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
        }}

    fun BuyPackageOrder(token:String,lat:Double,lon:Double
                        ,packageId: Int,itemsCount: Int,itemsIds:List<Int>
                        ,counts:List< Int>,
                        newCounts:List< Int>) {
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.BuyPackageOrder(
                    token,lat,lon
                    ,packageId,itemsCount,itemsIds
                    ,counts,
                    newCounts
                )
                CarsDetailsResponse ?.let {it1->

                    Auth?.onSussessBuyPackage(it1)
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
        }}
}

package com.croczi.mamaclean.ui.categore

import android.accounts.NetworkErrorException
import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.sharay.database.entitis.PackageOrder
import com.mustafayusef.sharay.database.entitis.catagoryOrder
import com.mustafayusef.wakely.ui.auth.CategoreLesener
import com.mustafayusef.wakely.ui.auth.CategoreRepostary
import com.mustafayusef.wakely.ui.auth.MainLesener
import com.mustafayusef.wakely.utils.corurtins
import okhttp3.internal.http2.StreamResetException
import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

class CategoreViewModel(val repostary: CategoreRepostary) : ViewModel() {
    var Auth: CategoreLesener?=null
    fun getCategore(){
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.getCategore()
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessCategore(it1)
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

    fun getCategoreItems(id:Int,lon:Double,lat:Double){
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.getItemsCategore(id,lon,lat)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessCategoreItems(it1)
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
    fun insertCategoryOrder(car: catagoryOrder?) {
        Auth?.OnStart()
        corurtins.main {
            try {
                val NumbersResponse = car?.let { repostary.InsertCategoryOrder(car) }
                NumbersResponse?.let {
                    Auth?.onSusessInsertcategoryOrder("success")
                }

            } catch (e: ApiExaptions) {
                e.message?.let { Auth?.onFailer(it) }

            }
        }
    }


}

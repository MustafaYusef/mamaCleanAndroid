package com.croczi.mamaclean.ui.orders

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.croczi.mamaclean.MainActivity

import com.croczi.mamaclean.R
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.orders.categoryOrder.categoryOrderDetails.categoryOrderDetailsRes
import com.croczi.mamaclean.data.orders.categoryOrder.categoryOrdersRes.Order
import com.croczi.mamaclean.data.orders.categoryOrder.categoryOrdersRes.categoryOrderRes
import com.croczi.mamaclean.data.orders.packageOrder.packageOrderDetails.packageOrderDetailsRes
import com.croczi.mamaclean.data.orders.packageOrder.packageOrderRes.PackageOrder
import com.croczi.mamaclean.data.orders.packageOrder.packageOrderRes.packageOrderRes
import com.croczi.mamaclean.network.myApi
import com.croczi.mamaclean.ui.orders.adapters.DetailsOrderAdapter
import com.croczi.mamaclean.ui.orders.adapters.DetailsPackageOrderAdapter
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.wakely.ui.auth.OrderLesener
import com.mustafayusef.wakely.ui.auth.OrderRepostary
import kotlinx.android.synthetic.main.orders_details_fragment.*
import kotlinx.android.synthetic.main.progress.*

class orderDetailsFragment : Fragment(),OrderLesener {

    companion object {
        fun newInstance() = orderDetailsFragment()
    }

    private lateinit var viewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.orders_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val db= context?.let { databaseApp(it) }
        val repostary= OrderRepostary(api!!)
        val factory= OrderViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this,factory).get(OrderViewModel::class.java)
        viewModel?.Auth=this
        ordersDetailsList?.layoutManager=LinearLayoutManager(context!!)
        if(arguments?.getBoolean("flage")!!){
            var order: Order= arguments?.getSerializable("order") as Order
            if(order.status=="watting"){
                statusOrderDetails.text=context?.resources!!.getString(R.string.pending)
                reciveDateOrder?.text=order.receivedAt
            }else if(order.status=="driver_clinet"){
                statusOrderDetails.text=context?.resources!!.getString(R.string.accepted)
                statusOrderDetails?.setTextColor(context!!.resources.getColor(R.color.green) )
                reciveDateOrder?.text=order.receivedAt
            }else if(order.status=="with MOMClean"){
                statusOrderDetails.text=context?.resources!!.getString(R.string.inOficce)
                reciveDateOrder?.text=order.receivedAt
            }else if(order.status=="cleaning"){
                statusOrderDetails.text=context?.resources!!.getString(R.string.proccess)
                reciveDateOrder?.text=order.receivedAt
            }
            else if(order.status=="cancelled"){
                statusOrderDetails.text=context?.resources!!.getString(R.string.refused)
                statusOrderDetails?.setTextColor(context!!.resources.getColor(R.color.red) )
                reciveDateOrder?.text=order.receivedAt

            }else{
                statusOrderDetails.text=context?.resources!!.getString(R.string.deleverd)
              statusOrderDetails?.setTextColor(context!!.resources.getColor(R.color.green) )
                reciveDateOrder?.text=order.receivedAt
            }

            viewModel?.getCategoryOrderDetails(MainActivity.cacheObj.token,order.id)
        }else{
            var orderPackage: PackageOrder = arguments?.getSerializable("orderPackage") as PackageOrder
            if(orderPackage.status=="watting"){
                statusOrderDetails.text=context?.resources!!.getString(R.string.pending)
                reciveDateOrder?.text=orderPackage.receivedAt
            }else if(orderPackage.status=="driver_clinet"){
                statusOrderDetails.text=context?.resources!!.getString(R.string.accepted)
                statusOrderDetails?.setTextColor(context!!.resources.getColor(R.color.green) )
                reciveDateOrder?.text=orderPackage.receivedAt
            }else if(orderPackage.status=="with MOMClean"){
                statusOrderDetails.text=context?.resources!!.getString(R.string.inOficce)
                reciveDateOrder?.text=orderPackage.receivedAt
            }else if(orderPackage.status=="cleaning"){
                statusOrderDetails.text=context?.resources!!.getString(R.string.proccess)
                reciveDateOrder?.text=orderPackage.receivedAt
            }
            else if(orderPackage.status=="cancelled"){
                statusOrderDetails.text=context?.resources!!.getString(R.string.refused)
                statusOrderDetails?.setTextColor(context!!.resources.getColor(R.color.red) )
                reciveDateOrder?.text=orderPackage.receivedAt

            }else{
                statusOrderDetails.text=context?.resources!!.getString(R.string.deleverd)
                statusOrderDetails?.setTextColor(context!!.resources.getColor(R.color.green) )
                reciveDateOrder?.text=orderPackage.receivedAt
            }

               viewModel?.getPackageOrderDetails(MainActivity.cacheObj.token,
                   orderPackage.id)
        }

      retryBtn?.setOnClickListener {
          if(arguments?.getBoolean("flage")!!){
              var order: Order= arguments?.getSerializable("order") as Order
              if(order.status=="watting"){
                  statusOrderDetails.text=context?.resources!!.getString(R.string.pending)
                  reciveDateOrder?.text=order.receivedAt
              }else if(order.status=="driver_clinet"){
                  statusOrderDetails.text=context?.resources!!.getString(R.string.accepted)
                  statusOrderDetails?.setTextColor(context!!.resources.getColor(R.color.green) )
                  reciveDateOrder?.text=order.receivedAt
              }else if(order.status=="with MOMClean"){
                  statusOrderDetails.text=context?.resources!!.getString(R.string.inOficce)
                  reciveDateOrder?.text=order.receivedAt
              }else if(order.status=="cleaning"){
                  statusOrderDetails.text=context?.resources!!.getString(R.string.proccess)
                  reciveDateOrder?.text=order.receivedAt
              }
              else if(order.status=="cancelled"){
                  statusOrderDetails.text=context?.resources!!.getString(R.string.refused)
                  statusOrderDetails?.setTextColor(context!!.resources.getColor(R.color.red) )
                  reciveDateOrder?.text=order.receivedAt

              }else{
                  statusOrderDetails.text=context?.resources!!.getString(R.string.deleverd)
                  statusOrderDetails?.setTextColor(context!!.resources.getColor(R.color.green) )
                  reciveDateOrder?.text=order.receivedAt
              }

              viewModel?.getCategoryOrderDetails(MainActivity.cacheObj.token,order.id)
          }else{
              var orderPackage: PackageOrder = arguments?.getSerializable("orderPackage") as PackageOrder
              if(orderPackage.status=="watting"){
                  statusOrderDetails.text=context?.resources!!.getString(R.string.pending)
                  reciveDateOrder?.text=orderPackage.receivedAt
              }else if(orderPackage.status=="driver_clinet"){
                  statusOrderDetails.text=context?.resources!!.getString(R.string.accepted)
                  statusOrderDetails?.setTextColor(context!!.resources.getColor(R.color.green) )
                  reciveDateOrder?.text=orderPackage.receivedAt
              }else if(orderPackage.status=="with MOMClean"){
                  statusOrderDetails.text=context?.resources!!.getString(R.string.inOficce)
                  reciveDateOrder?.text=orderPackage.receivedAt
              }else if(orderPackage.status=="cleaning"){
                  statusOrderDetails.text=context?.resources!!.getString(R.string.proccess)
                  reciveDateOrder?.text=orderPackage.receivedAt
              }
              else if(orderPackage.status=="cancelled"){
                  statusOrderDetails.text=context?.resources!!.getString(R.string.refused)
                  statusOrderDetails?.setTextColor(context!!.resources.getColor(R.color.red) )
                  reciveDateOrder?.text=orderPackage.receivedAt

              }else{
                  statusOrderDetails.text=context?.resources!!.getString(R.string.deleverd)
                  statusOrderDetails?.setTextColor(context!!.resources.getColor(R.color.green) )
                  reciveDateOrder?.text=orderPackage.receivedAt
              }

              viewModel?.getPackageOrderDetails(MainActivity.cacheObj.token,
                  orderPackage.id)
          }
      }
    }
    override fun onSussessGetNormalOrderDetails(it1: categoryOrderDetailsRes) {
        noNetContainer?.visibility=View.GONE
        ordersDetailsList?.visibility=View.VISIBLE
        ordersDetailsList?.adapter=DetailsOrderAdapter(context!!,it1)
        OrdersDetailsScroll?.isRefreshing=false
    }
    override fun onSussessGetPackageOrderDetails(it1: packageOrderDetailsRes) {
        noNetContainer?.visibility=View.GONE
        ordersDetailsList?.visibility=View.VISIBLE
        ordersDetailsList?.adapter= DetailsPackageOrderAdapter(context!!,it1)
        OrdersDetailsScroll?.isRefreshing=false
    }
    override fun OnStart() {
        OrdersDetailsScroll?.isRefreshing=true
    }

    override fun onFailer(message: String) {
        context?.toast(context?.resources!!.getString(R.string.somThing))
        OrdersDetailsScroll?.isRefreshing=false
        noNetContainer?.visibility=View.GONE
        if(message.toInt()==401){
            view?.findNavController()?.navigate(R.id.login)
            context?.toast(context?.resources!!.getString(R.string.NotUthorize))
        }
    }

    override fun onFailerNet(message: String) {
        context?.toast(context?.resources!!.getString(R.string.noInternet))
        OrdersDetailsScroll?.isRefreshing=false
        noNetContainer?.visibility=View.VISIBLE
        ordersDetailsList?.visibility=View.GONE
    }

    override fun onSussessGetNormalOrder(it1: categoryOrderRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun onSussessCancelNormalOrder(it1: reSendRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSussessGetPackageOrder(it1: packageOrderRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun onSussessCancelPackageOrder(it1: reSendRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

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
import com.croczi.mamaclean.data.orders.categoryOrder.categoryOrdersRes.categoryOrderRes
import com.croczi.mamaclean.data.orders.packageOrder.packageOrderDetails.packageOrderDetailsRes
import com.croczi.mamaclean.data.orders.packageOrder.packageOrderRes.packageOrderRes
import com.croczi.mamaclean.network.myApi
import com.croczi.mamaclean.ui.cart.CartViewModel
import com.croczi.mamaclean.ui.cart.CartViewModelFactory
import com.croczi.mamaclean.ui.orders.adapters.ItemsPackageOrderAdapter
import com.croczi.mamaclean.ui.orders.adapters.ItemsnormalOrderAdapter
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.wakely.ui.auth.CartRepostary
import com.mustafayusef.wakely.ui.auth.OrderLesener
import com.mustafayusef.wakely.ui.auth.OrderRepostary
import kotlinx.android.synthetic.main.orders_fragment.*
import kotlinx.android.synthetic.main.progress.*

class ordersFragment : Fragment(),OrderLesener {

    companion object {
        fun newInstance() = ordersFragment()
    }

    private lateinit var viewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.orders_fragment, container, false)
    }

    var flage=true
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }

        val repostary= OrderRepostary(api!!)
        val factory= OrderViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this,factory).get(OrderViewModel::class.java)
        viewModel?.Auth=this
        ordersList?.layoutManager=LinearLayoutManager(context!!)

        if(arguments!!.getBoolean("flage",false)){
            flage=false
            packageOrder?.setBackgroundResource(R.drawable.round_btn4)

            packageOrder?.setTextColor(context!!.resources.getColor(R.color.white))

            normalOrder?.setBackgroundResource(R.drawable.back_text)

            normalOrder?.setTextColor(context!!.resources.getColor(R.color.darkgray))
            viewModel?.getPackageOrder(MainActivity.cacheObj.token)
        }else{
            viewModel?.getNormalOrder(MainActivity.cacheObj.token)
        }

       retryBtn?.setOnClickListener {
           if(arguments!!.getBoolean("flage",false)){
               flage=false
               packageOrder?.setBackgroundResource(R.drawable.round_btn4)

               packageOrder?.setTextColor(context!!.resources.getColor(R.color.white))

               normalOrder?.setBackgroundResource(R.drawable.back_text)

               normalOrder?.setTextColor(context!!.resources.getColor(R.color.darkgray))
               viewModel?.getPackageOrder(MainActivity.cacheObj.token)
           }else{
               viewModel?.getNormalOrder(MainActivity.cacheObj.token)
           }
       }
        OrdersScroll?.setOnRefreshListener {
            if(arguments!!.getBoolean("flage",false)){
                flage=false
                packageOrder?.setBackgroundResource(R.drawable.round_btn4)

                packageOrder?.setTextColor(context!!.resources.getColor(R.color.white))

                normalOrder?.setBackgroundResource(R.drawable.back_text)

                normalOrder?.setTextColor(context!!.resources.getColor(R.color.darkgray))
                viewModel?.getPackageOrder(MainActivity.cacheObj.token)
            }else{
                viewModel?.getNormalOrder(MainActivity.cacheObj.token)
            }
        }
        normalOrder?.setOnClickListener {
            flage=true
            normalOrder?.setBackgroundResource(R.drawable.round_btn4)

            normalOrder?.setTextColor(context!!.resources.getColor(R.color.white))

            packageOrder?.setBackgroundResource(R.drawable.back_text)

            packageOrder?.setTextColor(context!!.resources.getColor(R.color.darkgray))
            viewModel?.getNormalOrder(MainActivity.cacheObj.token)
        }
        packageOrder?.setOnClickListener {
            flage=false
            packageOrder?.setBackgroundResource(R.drawable.round_btn4)

            packageOrder?.setTextColor(context!!.resources.getColor(R.color.white))

            normalOrder?.setBackgroundResource(R.drawable.back_text)

            normalOrder?.setTextColor(context!!.resources.getColor(R.color.darkgray))
            viewModel?.getPackageOrder(MainActivity.cacheObj.token)
        }

    }
    override fun onSussessGetNormalOrder(it1: categoryOrderRes) {
        ordersList?.visibility=View.VISIBLE
        ordersList?.adapter=ItemsnormalOrderAdapter(context!!,it1,viewModel)
        OrdersScroll?.isRefreshing=false
        noNetContainer?.visibility=View.GONE
    }

    override fun onSussessGetPackageOrder(it1: packageOrderRes) {
        ordersList?.visibility=View.VISIBLE
       ordersList?.adapter=ItemsPackageOrderAdapter(context!!,it1,viewModel)
        OrdersScroll?.isRefreshing=false
        noNetContainer?.visibility=View.GONE
    }
    override fun OnStart() {
        OrdersScroll?.isRefreshing=true
    }

    override fun onFailer(message: String) {
        context?.toast(context?.resources!!.getString(R.string.somThing))
        OrdersScroll?.isRefreshing=false
        ordersList?.visibility=View.INVISIBLE
        noNetContainer?.visibility=View.GONE
        if(message.toInt()==401){
            view?.findNavController()?.navigate(R.id.login)
            context?.toast(context?.resources!!.getString(R.string.NotUthorize))
        }
    }

    override fun onFailerNet(message: String) {
        OrdersScroll?.isRefreshing=false
        context?.toast(context?.resources!!.getString(R.string.proccess))
        noNetContainer?.visibility=View.VISIBLE
        ordersList?.visibility=View.GONE
    }



    override fun onSussessGetNormalOrderDetails(it1: categoryOrderDetailsRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSussessCancelNormalOrder(it1: reSendRes) {
        context?.toast(context?.resources!!.getString(R.string.cancelOrder))
        OrdersScroll?.isRefreshing=false
        viewModel?.getNormalOrder(MainActivity.cacheObj.token)
    }



    override fun onSussessGetPackageOrderDetails(it1: packageOrderDetailsRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSussessCancelPackageOrder(it1: reSendRes) {
        context?.toast(context?.resources!!.getString(R.string.cancelOrder))
        OrdersScroll?.isRefreshing=false
        viewModel?.getPackageOrder(MainActivity.cacheObj.token)
    }

}

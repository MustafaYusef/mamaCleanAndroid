package com.croczi.mamaclean.ui.cart

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
import com.croczi.mamaclean.network.myApi
import com.croczi.mamaclean.ui.cart.adapters.ItemsCartAdapter
import com.croczi.mamaclean.ui.cart.adapters.PackageCartAdapter
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.sharay.database.entitis.PackageOrder
import com.mustafayusef.sharay.database.entitis.catagoryOrder
import com.mustafayusef.wakely.ui.auth.CartLesener
import com.mustafayusef.wakely.ui.auth.CartRepostary
import kotlinx.android.synthetic.main.cart_fragment.*
import java.io.Serializable

class cartFragment : Fragment(),CartLesener {

    companion object {
        fun newInstance() = cartFragment()
    }

    private lateinit var viewModel: CartViewModel
   data class myCart(
       var myList:List<catagoryOrder>?=null

   ):Serializable

    data class myPackageCart(
        var myList:List<PackageOrder>?=null

    ):Serializable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cart_fragment, container, false)
    }

    var flage=true
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val db= context?.let { databaseApp(it) }
        val repostary= CartRepostary(api!!,db!!)
        val factory= CartViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this,factory).get(CartViewModel::class.java)
        viewModel?.Auth=this

        ItemsListCart?.layoutManager=LinearLayoutManager(context!!)
        viewModel?.getAllCartCategoryOrder()

        println("token    :"+MainActivity.cacheObj.token)

      normalCart?.setOnClickListener {
          flage=true
              normalCart?.setBackgroundResource(R.drawable.round_btn4)

          normalCart?.setTextColor(context!!.resources.getColor(R.color.white))

          packageCart?.setBackgroundResource(R.drawable.back_text)

          packageCart?.setTextColor(context!!.resources.getColor(R.color.darkgray))
          viewModel?.getAllCartCategoryOrder()
      }
        packageCart?.setOnClickListener {
            flage=false
            packageCart?.setBackgroundResource(R.drawable.round_btn4)

            packageCart?.setTextColor(context!!.resources.getColor(R.color.white))

            normalCart?.setBackgroundResource(R.drawable.back_text)

            normalCart?.setTextColor(context!!.resources.getColor(R.color.darkgray))
            viewModel?.getAllCartPackage()
        }

        itemsCartScroll?.setOnRefreshListener {
            if(flage){
                viewModel?.getAllCartCategoryOrder()
            }else{
                viewModel?.getAllCartPackage()
            }
        }
        BuyCartBtn?.setOnClickListener {
//            viewModel?.buyCart()
            var bundel=Bundle()
            if(flage){
                bundel.putSerializable("myCart",myCartItems)
            }else{
                bundel.putSerializable("myPackageCart",PackageCart)
            }

            bundel?.putBoolean("flage",flage)
           view?.findNavController()?.navigate(R.id.mapFragment,bundel)
        }
    }

    override fun OnStart() {
        itemsCartScroll?.isRefreshing=true
    }

    override fun onFailer(message: String) {
        itemsCartScroll?.isRefreshing=false

    }

    override fun onFailerNet(message: String) {
        itemsCartScroll?.isRefreshing=false
    }

    override fun onSusessDeletePackage(s: String) {

    }
    var PackageCart=myPackageCart()
    override fun onSusessgetCartPackage(it: List<PackageOrder>) {
        priceCart?.visibility=View.GONE
        ItemsListCart?.adapter= PackageCartAdapter(context!!, it, viewModel)
        PackageCart.myList=it
        var sum=0
        if(!it.isNullOrEmpty()){
            for(i in it){
                sum+= i.price!!
            }
            BuyCartBtn?.visibility=View.VISIBLE
        }else{
            MainActivity.cacheObj.packageId=0
            BuyCartBtn?.visibility=View.GONE
        }

        itemsCartScroll?.isRefreshing=false
    }

    override fun onSusessDeleteCartPackage(s: String) {
        context?.toast(context?.resources!!.getString(R.string.deleteFromCart))
        viewModel?.getAllCartPackage()
        itemsCartScroll?.isRefreshing=false
    }

    override fun onSusessDeleteOrder(s: String) {

    }
  var myCartItems=myCart()
    override fun onSusessgetCartOrder(it: List<catagoryOrder>) {
        priceCart?.visibility=View.VISIBLE
        ItemsListCart?.adapter=
            ItemsCartAdapter(
                context!!,
                it,
                viewModel
            )
        myCartItems.myList=it
        var sum=0
        if(!it.isNullOrEmpty()){
            for(i in it){
                sum+= i.price!!
            }
            priceCart?.text="${context!!.resources.getString(R.string.totalPrice)}"+sum
            BuyCartBtn?.visibility=View.VISIBLE
            priceCart?.visibility=View.VISIBLE
        }else{
            BuyCartBtn?.visibility=View.GONE
            priceCart?.visibility=View.GONE
        }



        itemsCartScroll?.isRefreshing=false
    }

    override fun onSusessDeleteCartOrder(s: String) {
        //context?.toast("sucess delete")
        viewModel?.getAllCartCategoryOrder()
        itemsCartScroll?.isRefreshing=false
    }

    override fun onSuccessBuyOrderCategory(it1: reSendRes) {

    }

    override fun onSussessBuyPackage(it1: reSendRes) {

    }

}

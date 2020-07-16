package com.croczi.mamaclean.ui.orders.adapters


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.croczi.mamaclean.MainActivity
import com.croczi.mamaclean.R
import com.croczi.mamaclean.data.orders.packageOrder.packageOrderRes.packageOrderRes
import com.croczi.mamaclean.ui.cart.CartViewModel
import com.croczi.mamaclean.ui.orders.OrderViewModel

import com.mustafayusef.sharay.database.entitis.catagoryOrder
import kotlinx.android.synthetic.main.items_cart_card.view.*
import kotlinx.android.synthetic.main.order_package_card.view.*
import kotlinx.android.synthetic.main.pop_dilog.view.*
import java.text.SimpleDateFormat
import java.util.*


class ItemsPackageOrderAdapter(
    val context: Context,
    val response: packageOrderRes,
    val viewModel: OrderViewModel
) : RecyclerView.Adapter<ItemsPackageOrderAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.order_package_card ,parent,false)

        return CustomViewHolder(
            cardItem
        )


    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return response.res.data.PackageOrders.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
      //  holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))
        holder.view. priceOrder?.visibility=View.GONE
        var data=response.res.data.PackageOrders .get (position)

        var dateLast=data.createAt !!.substring(0,10)+" "+data.createAt!!.substring(11,19)
        //val toyBornTime = "2014-06-18 12:56:50"
        val dateFormat = SimpleDateFormat(
            "MM/dd/yyyy HH:mm:ss"
        )
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
        var oldDate = dateFormat.parse(dateLast)
        println("ddddddddddddddddddddddddddddddddddd")
        println(oldDate)

//        var dt = Date()
//        val c = Calendar.getInstance()
//        c.time = dt
//        c.add(Calendar.DATE, 1)
//        dt = c.time
        val c = Calendar.getInstance()
        c.time = oldDate
        //c.roll(Calendar.HOUR_OF_DAY, 12)
        oldDate = c.time

        val current = Date()
        println(current)

        val diff = current.time - oldDate.time
        val seconds = (diff / 1000 ).toInt()
        val minutes = (seconds / 60) .toInt()
        val hours = (minutes / 60) .toInt()
        val days:Int = (hours / 24) .toInt()
        println("diffrent  "+days)

        if(days>0){
            holder.view.titleOrder?.text="${context?.resources!!.getString(R.string.befor)} $days ${context?.resources!!.getString(R.string.Day)} "
        }else if(hours>0){
            holder.view.titleOrder?.text="${context?.resources!!.getString(R.string.befor)} $hours ${context?.resources!!.getString(R.string.hour)} "
        }else if (minutes>0){
            holder.view.titleOrder?.text="${context?.resources!!.getString(R.string.befor)} $minutes ${context?.resources!!.getString(R.string.minut)} "
        }
        holder.view.priceOrder?.visibility=View.GONE
        if(data.status=="watting"){
            holder.view.CancelOrder?.visibility=View.VISIBLE
            holder.view.statusOrder?.setBackgroundResource(R.drawable.wait_btn)
            holder.view.statusOrder?.setTextColor(context.resources.getColor(R.color.colorAccent) )
            holder.view.statusOrder.text=context?.resources!!.getString(R.string.pending)
        }else if(data.status=="cancelled"){
            holder.view.CancelOrder?.visibility=View.GONE
            holder.view.statusOrder.text=context?.resources!!.getString(R.string.refused)
            holder.view.statusOrder?.setBackgroundResource(R.drawable.refuse_btn)
            holder.view.statusOrder?.setTextColor(context.resources.getColor(R.color.red))

        }else{
            holder.view.CancelOrder?.visibility=View.GONE
            holder.view.statusOrder.text=context?.resources!!.getString(R.string.accepted)
            holder.view.statusOrder?.setBackgroundResource(R.drawable.accept_btn)
            holder.view.statusOrder?.setTextColor(context.resources.getColor(R.color.green) )
        }

        holder.view.dateOrder?.text=data.createAt.split(" ").get(0)
        holder.view.itemCount?.text=context?.resources!!.getString(R.string.num)+data.itemsCount.toString()
        holder.view.detailsOrder?.setOnClickListener {
            var bundel= Bundle()
            bundel.putSerializable("orderPackage",data)
            bundel.putBoolean("flage",false)
            holder.view.findNavController().navigate(R.id.orderDetailsFragment,bundel)
        }

        holder.view.CancelOrder?.setOnClickListener {
            val dview: View = View.inflate(context,R.layout.pop_dilog, null)
            val builder = context?.let { AlertDialog.Builder(it).setView(dview) }
            val malert= builder?.show()
            malert?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dview?.descPart?.text=context?.resources!!.getString(R.string.wantCancelOrder)
            dview.conform?.setOnClickListener {
                viewModel?.cancelPackageOrder(MainActivity.cacheObj.token,data.id)
                malert?.dismiss()
            }

        }


//        else{
//            var data=response.data.packageItems.packageItems .get(position-1)
//            holder.view. titlePackageItem.text=data.item.name_ar
//            holder.view.WashPrice.text=data.item.wash_price .toString()+" IQD"
//            holder.view.ironWashPrice.text=data.item.dw_price .toString()+" IQD"
//            Glide.with(context).load(MainActivity.constant.base+data.item.photo)
//                .placeholder(R.drawable.laundry)
//                .into( holder.view.ItemeImage)
//
//            holder.view.selectItem?.setOnClickListener {
//                showBottomSheetPackage(context,data.count,viewModel,data,response.data.packageItems.id)
//            }
//        }
        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))



//        holder.view.purPackage.setOnClickListener {
//            holder.view.findNavController().navigate(R.id.mapFragment)
//        }

//        Glide.with(context).load("http://api.alwakiel.com/storage/images/"+
//                data.productPrices[0].image)
//            .into(  holder.view.circleImageViewProd)
//        holder.view.setOnClickListener {
//            var bundel=Bundle()
//            bundel.putSerializable("product",data)
//            holder.view.findNavController().navigate(R.id.productDetails,bundel)
//        }

//        Glide.with(context).load("http://api.centralmarketiq.com/"+carsP.image+".png").into(holder.view?.numImage)

    }



    class CustomViewHolder(val view : View) : RecyclerView.ViewHolder(view){
//           var OnNotlesener:OnNoteLisener
//        override fun onClick(view: View?) {
//           onNoteLisener.onNoteClick(layoutPosition)
//        }
//
//        init {
//            this.OnNotlesener=onNoteLisener
//         view.setOnClickListener(this)
//
//        }


    }






}


package com.croczi.mamaclean.ui.Main.adapters


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.croczi.mamaclean.MainActivity
import com.croczi.mamaclean.R
import com.croczi.mamaclean.data.auth.profile.myPackage.myPackages
import kotlinx.android.synthetic.main.items_card.view.*
import kotlinx.android.synthetic.main.package_card.view.*
import kotlinx.android.synthetic.main.package_card.view.containerCar
import kotlinx.android.synthetic.main.request_card.view.*
import java.util.*


class myPackagesAdapter(
    val context: Context,
    val response: myPackages
) : RecyclerView.Adapter<myPackagesAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.package_card ,parent,false)

        return  CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return response.data.packages.size

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
       var data=response.data.packages.get(position)
        if(Locale.getDefault().getLanguage()=="ar") {
            holder.view. titlePackage.text=data.packageDetails .name_ar
        }else{
            holder.view. titlePackage.text=data.packageDetails .name_en
        }

        holder.view.visitPackage.text=data.packageDetails.visits_count .toString()
        holder.view.pricePackage.text=data.expireAt
        holder.view.expire?.text=data.packageDetails.days.toString()+context?.resources!!.getString(R.string.Day)
        if(!data.same_Area){
            holder.view.packageState?.visibility=View.VISIBLE
        }else{
            holder.view.packageState?.visibility=View.GONE
        }

        if(!data.is_Active){
            holder.view.packageState?.visibility=View.VISIBLE
            holder.view.packageState?.text=context?.resources!!.getString(R.string.Expire)
            holder.view.packageState?.setBackgroundResource(R.drawable.round_red_btn4)
        }else{
            holder.view.packageState?.visibility=View.GONE
        }
        Glide.with(context).load(MainActivity.constant.base+data.packageDetails.file)
            .placeholder(R.drawable.image6).apply(RequestOptions.bitmapTransform(RoundedCorners(25)))
            .into( holder.view.packageImage)
//        holder.view.packageImage?.setImageResource(R.drawable.image6)
//        holder.view.purPackage.setOnClickListener {
//            if(isAvailable(context)){
//                var intent=Intent(context!!,mapFragment::class.java)
//                context.startActivity(intent)
//            }


        holder.view.setOnClickListener{
            if(data.is_Active){
                var bundel=Bundle()
                bundel.putBoolean("flage",true)
                bundel.putSerializable("data",data)
                bundel.putInt("id",data.user_packageId)
                holder.view.findNavController().navigate(R.id.itemsFragment,bundel)
            }

        }
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


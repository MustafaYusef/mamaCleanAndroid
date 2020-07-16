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
import com.croczi.mamaclean.data.packege.PackageRes
import kotlinx.android.synthetic.main.package_card.view.*
import java.util.*


class PackagesAdapter(
    val context: Context,
    val response: PackageRes
) : RecyclerView.Adapter<PackagesAdapter.CustomViewHolder>(){
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
            holder.view. titlePackage.text=data.name_ar
        }else{
            holder.view. titlePackage.text=data.name_en
        }

        holder.view.expire?.text=data.days.toString()+context?.resources!!.getString(R.string.Day)
        holder.view.pricePackage.text=data.price.toString()+" "+data.currency
        holder.view.visitPackage.text=data.visits_count .toString()
        holder.view.packageState?.visibility=View.GONE
        Glide.with(context).load(MainActivity.constant.base+data.file)
            .placeholder(R.drawable.image1).apply(RequestOptions.bitmapTransform(RoundedCorners(25)))
            .into( holder.view.packageImage)
//        holder.view.purPackage.setOnClickListener {
//            if(isAvailable(context)){
//                var intent=Intent(context!!,mapFragment::class.java)
//                context.startActivity(intent)
//            }
//
//        }
        holder.view.setOnClickListener{
            var bundel=Bundle()
            bundel.putSerializable("id",data.id)
            bundel.putBoolean("flage",false)
            holder.view.findNavController().navigate(R.id.itemsFragment,bundel)
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


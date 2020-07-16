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
import com.croczi.mamaclean.data.auth.profile.myRequests.MyRequests
import kotlinx.android.synthetic.main.package_card.view.*
import kotlinx.android.synthetic.main.package_card.view.containerCar
import kotlinx.android.synthetic.main.package_card.view.pricePackage
import kotlinx.android.synthetic.main.package_card.view.titlePackage
import kotlinx.android.synthetic.main.package_card.view.visitPackage
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.request_card.view.*
import java.util.*


class myRequestsAdapter(
    val context: Context,
    val response: MyRequests
) : RecyclerView.Adapter<myRequestsAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.request_card ,parent,false)

        return  CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return response.data.myRequest .size

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
       var data=response.data.myRequest .get(position)
        if(Locale.getDefault().getLanguage()=="ar") {
            holder.view. titlePackageReq.text=data.`package` .name_ar
        }else{
            holder.view. titlePackageReq.text=data.`package` .name_en
        }

        if(data.status=="panding"){
            holder.view.statusRequest.text=context?.resources!!.getString(R.string.pending)
        }else if(data.status=="accepted"){
            holder.view.statusRequest.text=context?.resources!!.getString(R.string.accepted)
            holder.view.statusRequest?.setBackgroundResource(R.drawable.accept_btn)
            holder.view.statusRequest?.setTextColor(context.resources.getColor(R.color.green) )
        }else{
            holder.view.statusRequest.text=context?.resources!!.getString(R.string.refused)
            holder.view.statusRequest?.setBackgroundResource(R.drawable.refuse_btn)
            holder.view.statusRequest?.setTextColor(context.resources.getColor(R.color.red) )
        }


        holder.view. pricePackageRequest.text=data.`package`.price.toString()+" "+data.`package`.currency

        context.let {
            Glide.with(it!!).load(MainActivity.constant.base+data.`package`.file)
                .placeholder(R.drawable.tshirt).apply(RequestOptions.bitmapTransform(RoundedCorners(25)))
                .into( holder.view.imagePackReq)
        }

        holder.view.dateReq.text=data.createAt.split(" ").get(0)
       // holder.view.visitPackageReq.text=data.`package`.visits_count.toString()
     //   holder.view.pricePackageReq.text=data.`package`.price.toString()+" "+data.`package`.currency
//        if(){
//
//        }else{
//
//        }


//        holder.view.purPackage.setOnClickListener {
//            if(isAvailable(context)){
//                var intent=Intent(context!!,mapFragment::class.java)
//                context.startActivity(intent)
//            }

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


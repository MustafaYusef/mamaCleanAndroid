package com.croczi.mamaclean.ui.categore.adapters


import android.content.Context
import android.content.res.Resources
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
import com.croczi.mamaclean.data.categore.categoreRes
import kotlinx.android.synthetic.main.categore_card.view.*
import kotlinx.android.synthetic.main.categore_fragment.view.*
import kotlinx.android.synthetic.main.items_cart_card.view.*
import kotlinx.android.synthetic.main.package_card.view.*
import kotlinx.android.synthetic.main.profile_fragment.*
import java.util.*


class CategoreAdapter(
    val context: Context,
    val response: categoreRes
) : RecyclerView.Adapter<CategoreAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.categore_card ,parent,false)

        return CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return response.data.categories .size

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.containerCategoryCard?.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

       var lang= Resources.getSystem().getConfiguration().locale.getLanguage();
        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
       var data=response.data.categories .get(position)
        if(Locale.getDefault().getLanguage()=="ar"){
            holder.view. titleCategore.text=data.name_ar
        }else{
            holder.view. titleCategore.text=data.name_en
        }

        context?.let {
            Glide.with(it).load(MainActivity.constant.base+data.file)
                .placeholder(R.drawable.image3).apply(RequestOptions.bitmapTransform(RoundedCorners(25)))
                .into( holder.view.categoryImage!!)
        }

        holder.view.setOnClickListener{
            var bundel=Bundle()
            bundel.putSerializable("id",data.id)
            holder.view.findNavController().navigate(R.id.categoreItemsFragment,bundel)
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


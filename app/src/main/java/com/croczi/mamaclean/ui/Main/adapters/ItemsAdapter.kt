package com.croczi.mamaclean.ui.Main.adapters


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.croczi.mamaclean.MainActivity
import com.croczi.mamaclean.R
import com.croczi.mamaclean.data.packege.items.itemsInPackage
import com.croczi.mamaclean.ui.Main.MainViewModel

import com.mustafayusef.holidaymaster.utils.showBottomSheetPackage
import kotlinx.android.synthetic.main.items_card.view.*
import kotlinx.android.synthetic.main.package_ditails.view.*
import java.util.*


class ItemsAdapter(
    val context: Context,
    val response: itemsInPackage,
    val viewModel: MainViewModel,
   var boolean: Boolean
) : RecyclerView.Adapter<ItemsAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)
    if(viewType==0){
        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.package_ditails ,parent,false)

        return  CustomViewHolder(cardItem)
    } else{
        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.items_card ,parent,false)

        return  CustomViewHolder(cardItem)
    }

    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return response.data.packageItems.packageItems .size+1

    }

    override fun getItemViewType(position: Int): Int {
        if(position==0){
            return 0
        }
        else{
            return 1
        }
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
      //  holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))
        holder.view.line?.visibility=View.GONE
        holder.view. priceLayout?.visibility=View.GONE
        if(position==0){
           if(Locale.getDefault().getLanguage()=="ar") {
               holder.view. packageDes?.text=response.data.packageItems.description_ar
           }else{
               holder.view. packageDes?.text=response.data.packageItems.description_en
           }

            holder.view. visitCount?.text=response.data.packageItems.visits_count.toString()
            holder.view. ExpirePackage?.text=response.data.packageItems.days.toString()+
                    context?.resources!!.getString(R.string.Day)
        }
        else{
            var data=response.data.packageItems.packageItems .get(position-1)
            if(Locale.getDefault().getLanguage()=="ar") {
                holder.view. titlePackageItem.text=data.item.name_ar
            }else{
                holder.view. titlePackageItem.text=data.item.name_en
            }

            holder.view.countItems?.text=context?.resources!!.getString(R.string.num)+data.count
            Glide.with(context).load(MainActivity.constant.base+data.item.photo)
                .placeholder(R.drawable.image2).apply(RequestOptions.bitmapTransform(RoundedCorners(25)))
                .into( holder.view.ItemeImage)

                holder.view.selectItem?.visibility=View.GONE


        }
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


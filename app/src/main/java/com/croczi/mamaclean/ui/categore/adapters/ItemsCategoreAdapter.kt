package com.croczi.mamaclean.ui.categore.adapters


import android.content.Context
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
import com.croczi.mamaclean.data.categore.categoryItems.categoryItemsRes
import com.croczi.mamaclean.ui.categore.CategoreViewModel
import com.mustafayusef.holidaymaster.utils.showBottomSheet
import kotlinx.android.synthetic.main.items_card.view.*
import kotlinx.android.synthetic.main.package_card.view.containerCar
import java.util.*


class ItemsCategoreAdapter(
    val context: Context,
    val response: categoryItemsRes,
    val viewModel: CategoreViewModel
) : RecyclerView.Adapter<ItemsCategoreAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.items_card ,parent,false)

        return CustomViewHolder(
            cardItem
        )
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return response.data.items .size

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
       var data=response.data.items .get(position)
        if(Locale.getDefault().getLanguage()=="ar"){
            holder.view. titlePackageItem.text=data.name_ar
        }else{
            holder.view. titlePackageItem.text=data.name_en
        }

        holder.view.countItems.visibility=View.GONE
        holder.view.WashPrice.text=data.wash_price.toString()+" "+data.currency
        holder.view.ironWashPrice.text=data.dw_price .toString()+" "+data.currency
        Glide.with(context).load(MainActivity.constant.base+data.photo)
            .placeholder(R.drawable.image2).apply(RequestOptions.bitmapTransform(RoundedCorners(25)))
            .into( holder.view.ItemeImage)
        holder.view.selectItem?.setOnClickListener {
            if (MainActivity.cacheObj.token==""){
                holder.view.findNavController().navigate(R.id.login)
            }else{
                showBottomSheet(context,viewModel,data)
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


package com.croczi.mamaclean.ui.cart.adapters


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.croczi.mamaclean.MainActivity
import com.croczi.mamaclean.R
import com.croczi.mamaclean.ui.cart.CartViewModel

import com.mustafayusef.sharay.database.entitis.catagoryOrder
import kotlinx.android.synthetic.main.items_cart_card.view.*
import kotlinx.android.synthetic.main.pop_dilog.view.*
import java.util.*


class ItemsCartAdapter(
    val context: Context,
    val response: List<catagoryOrder>,
    val viewModel: CartViewModel
) : RecyclerView.Adapter<ItemsCartAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.items_cart_card ,parent,false)

        return CustomViewHolder(
            cardItem
        )


    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return response.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
      //  holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        var data=response.get(position)
       if(Locale.getDefault().getLanguage()=="ar"){
           holder.view.titleCartItem.text=data.name
       }else{
           holder.view.titleCartItem.text=data.name_en
       }


        Glide.with(context).load(MainActivity.constant.base+data.image)
                .placeholder(R.drawable.image2).apply(RequestOptions.bitmapTransform(RoundedCorners(25)))
                .into(  holder.view.ItemeCartImage)


        holder.view.itemCartCount.text=context.resources!!.getString(R.string.num)+data.count.toString()
        holder.view.ItemCartPrice.text=data.price.toString()+data.currency
        if(data.type=="wash"){
            holder.view. itemcartType.text=context.resources!!.getString(R.string.iron)
            holder.view. imageTypeCart.setImageResource(R.drawable.ic_ironing )
        }else{
            holder.view. itemcartType.text=context.resources!!.getString(R.string.ironWach)
            holder.view. imageTypeCart.setImageResource(R.drawable.ic_local_laundry_service_black_24dp )
        }

        holder.view.deleteFromCart?.setOnClickListener {
            val dview: View = View.inflate(context,R.layout.pop_dilog, null)
            val builder = context?.let { AlertDialog.Builder(it).setView(dview) }
            val malert= builder?.show()
            malert?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dview?.descPart?.text=context.resources!!.getString(R.string.deleteFromCart)
            dview.conform?.setOnClickListener {
                data.id?.let { it1 -> viewModel.DeleteByIdCartCategoryOrder(it1) }
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


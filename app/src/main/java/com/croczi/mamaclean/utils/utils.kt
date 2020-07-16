package com.mustafayusef.holidaymaster.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.croczi.mamaclean.MainActivity
import com.croczi.mamaclean.R
import com.croczi.mamaclean.data.auth.profile.myPackegeItems.package_user_detailsId
import com.croczi.mamaclean.data.categore.categoryItems.Item
import com.croczi.mamaclean.ui.Main.MainViewModel
import com.croczi.mamaclean.ui.categore.CategoreViewModel
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.material.snackbar.Snackbar
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.sharay.database.entitis.PackageOrder
import com.mustafayusef.sharay.database.entitis.catagoryOrder
import kotlinx.android.synthetic.main.add_item_bottom.view.*
import kotlinx.android.synthetic.main.add_item_bottom.view.ironBtn
import kotlinx.android.synthetic.main.add_item_bottom.view.ironText
import kotlinx.android.synthetic.main.add_item_bottom2.view.*
import kotlinx.coroutines.*

fun Context.toast(message:String){
    Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
}
fun View.snackbar(message:String){
    Snackbar.make(this,message,Snackbar.LENGTH_LONG).also {snackbar->
        snackbar.setAction("OK"){
           snackbar.dismiss()
        }
    }.show()

}
fun calculateNoOfColumns(context: Context, columnWidthDp: Float): Int { // For example columnWidthdp=180
    val displayMetrics = context.resources.displayMetrics
    val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
    return (screenWidthDp / columnWidthDp + 0.5).toInt()
}
fun isAvailable(context: Context):Boolean{
    var avilable= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
    if(avilable== ConnectionResult.SUCCESS){
        return true
        // user can show map
    }else if(GoogleApiAvailability.getInstance().isUserResolvableError(avilable)){
        var dilog= GoogleApiAvailability.getInstance().getErrorDialog(context as Activity?,avilable,9001)
        dilog.show()
    }else{
        context?.toast("cant show the map")
    }
    return false
}

fun showBottomSheet(
    context: Context,
  viewModel: CategoreViewModel,
    item: Item
){
    val view =View.inflate(context,R.layout.add_item_bottom, null)

//    val display =activity ?.windowManager?.defaultDisplay
//    val size = Point()
//    display?.getSize(size)
//    val width = size.x
//    val height = size.y


    //view.minimumHeight=600
    val mBottomSheetDialog = Dialog(
        context!!,
        R.style.MaterialDialogSheet
    )
    mBottomSheetDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    mBottomSheetDialog.setContentView(view)
    mBottomSheetDialog.setCancelable(true)
    mBottomSheetDialog.window!!.setLayout(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    mBottomSheetDialog.window!!.setGravity(Gravity.BOTTOM)

    mBottomSheetDialog.show()
    var flage=true
     view.ironBtn.setOnClickListener {
         view.ironAndWachBtn.setBackgroundResource(R.drawable.wait_btn)
         view.imageIronAndWach.setImageResource(R.drawable.ic_local_laundry_service_black_24dp)
         view.ironAndWachText.setTextColor(context.resources.getColor(R.color.black) )
         view.ironWashPrice.setTextColor(context.resources.getColor(R.color.colorAccent) )


         view.ironBtn.setBackgroundResource(R.drawable.round_btn4)
         view.imageIron.setImageResource(R.drawable.ic_ironing_white)
         view.ironText.setTextColor(context.resources.getColor(R.color.white) )
         view.WashPrice.setTextColor(context.resources.getColor(R.color.white) )
         flage=false

     }
    view.WashPrice.text=item.wash_price.toString()+" "+item.currency
    view.ironWashPrice.text=item.dw_price .toString()+" "+item.currency
    view.ironAndWachBtn.setOnClickListener {
        view.ironBtn.setBackgroundResource(R.drawable.wait_btn)
        view.imageIron.setImageResource(R.drawable.ic_ironing)
        view.ironText.setTextColor(context.resources.getColor(R.color.black) )
        view.WashPrice.setTextColor(context.resources.getColor(R.color.colorAccent) )

        view.ironAndWachBtn.setBackgroundResource(R.drawable.round_btn4)
        view.imageIronAndWach.setImageResource(R.drawable.ic_local_laundry_service_white_24dp)
        view.ironAndWachText.setTextColor(context.resources.getColor(R.color.white) )
        view.ironWashPrice.setTextColor(context.resources.getColor(R.color.white) )
        flage=true
    }

    var count:Int=1
    view?.itemCountText?.text=count.toString()
    view.plusCountBtn?.setOnClickListener {
            count++
            view?.itemCountText?.text=count.toString()

    }
    view.subCountBtn?.setOnClickListener {
        if(count>1){
            count--
            view?.itemCountText?.text=count.toString()
        }
    }
    view.addItemToCartBtn?.setOnClickListener {

        if(flage){
            var cart=catagoryOrder( Item_id=item.id,
                count=count,
                type = "dry and wash",
                name=item.name_ar,
                name_en = item.name_en,
                price=item.dw_price*count,
                currency=item.currency,
                image=item.photo)
           doJop(viewModel,context,cart)



        }else{
            var cart=catagoryOrder( Item_id=item.id,
                count=count,
                type = "wash",
                name=item.name_ar,
                name_en = item.name_en,
                price=item.wash_price*count,
                currency=item.currency,
                image=item.photo)
            doJop(viewModel,context,cart)

        }


        mBottomSheetDialog.dismiss()
    }


    }
var flage:Boolean?=null
fun doJop(
    viewModel: CategoreViewModel,
    context: Context,
    cart: catagoryOrder
){
    var db=databaseApp(context)

   CoroutineScope(Dispatchers.Main).launch {
      var data= db.Car_Dao().getAllCategoryOrder()
       if(data.isEmpty()){
           viewModel.insertCategoryOrder(cart)
       }else{
           var flage=false
           for(i in 0 until data!!.size){
              if(data!![i].Item_id==cart.Item_id && data!![i].type ==cart.type) {
                  flage=true
               }
           }
           if(!flage){
               viewModel.insertCategoryOrder(cart)
           }
       }
   }



    }


// suspend fun getList( id:Int, type:String, viewModel: CategoreViewModel,context:Context,cart){
//    var db=databaseApp(context)
//    var flage:Boolean?=null
//    var data:List<catagoryOrder>?=null
//
//         return withContext(Dispatchers.Main) {
//            db.Car_Dao().getAllCategoryOrder()
//        }
//         for(i in 0 until data!!.size){
//             if(data!![i].Item_id==id && data!![i].type ==type){
//
//                 break
//             }else {
//                 flage=  true
//             }
//         }
//
//
//}
fun showBottomSheetPackage(
    context: Context,
    countItem: Int,
    viewModel: MainViewModel,
    data: package_user_detailsId,
    userPackageid: Int
){
    val view =View.inflate(context,R.layout.add_item_bottom2, null)

//    val display =activity ?.windowManager?.defaultDisplay
//    val size = Point()
//    display?.getSize(size)
//    val width = size.x
//    val height = size.y


    //view.minimumHeight=600
    val mBottomSheetDialog = Dialog(
        context!!,
        R.style.MaterialDialogSheet
    )
    mBottomSheetDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    mBottomSheetDialog.setContentView(view)
    mBottomSheetDialog.setCancelable(true)
    mBottomSheetDialog.window!!.setLayout(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    mBottomSheetDialog.window!!.setGravity(Gravity.BOTTOM)

    mBottomSheetDialog.show()

    view?.pricePackageItem?.text=(data.count-1).toString()
    var count:Int=1
   var count2=data.count-1
    view?.itemCountPackageText?.text=count.toString()
    view.plusCountPackageBtn?.setOnClickListener {
        if(count<data.count){
            count++
            count2--
            view?.pricePackageItem?.text=count2.toString()
            view?.itemCountPackageText?.text=count.toString()
        }
    }
    view.subCountPackageBtn?.setOnClickListener {
        if(count>1){
            count--
            count2++
            view?.pricePackageItem?.text=count2.toString()
            view?.itemCountPackageText?.text=count.toString()
        }
    }
    view.addItemToCartPackageBtn?.setOnClickListener {
        if(MainActivity.cacheObj.packageId==0 || MainActivity.cacheObj.packageId==userPackageid ){
            var cart=PackageOrder( Item_id=data.package_user_detailsId,
                count=count, newCount=data.count-count,
                name=data.item.name_ar,
                name_en = data.item.name_en,
                price=data.item.dw_price*count,
                image=data.item.photo)
            MainActivity.cacheObj.packageId=userPackageid
            viewModel.insertPackageOrder(cart)
            mBottomSheetDialog.dismiss()
        }else{
            context?.toast(context?.resources!!.getString(R.string.diffPackage))
            mBottomSheetDialog.dismiss()
        }

    }
}



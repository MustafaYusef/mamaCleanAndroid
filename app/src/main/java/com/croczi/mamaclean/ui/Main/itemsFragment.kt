package com.croczi.mamaclean.ui.Main

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.croczi.mamaclean.MainActivity

import com.croczi.mamaclean.R
import com.croczi.mamaclean.data.auth.profile.myPackage.Package
import com.croczi.mamaclean.data.auth.profile.myPackage.myPackages
import com.croczi.mamaclean.data.auth.profile.myPackegeItems.myPackegeItemRes
import com.croczi.mamaclean.data.auth.profile.myRequests.MyRequests
import com.croczi.mamaclean.data.auth.signUp.signUpResponse
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.packege.PackageRes
import com.croczi.mamaclean.data.packege.items.itemsInPackage
import com.croczi.mamaclean.network.myApi
import com.croczi.mamaclean.ui.Main.adapters.ItemsAdapter
import com.croczi.mamaclean.ui.Main.adapters.MyPackageItemsAdapter
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.wakely.ui.auth.MainLesener
import com.mustafayusef.wakely.ui.auth.MainRepostary
import kotlinx.android.synthetic.main.package_details_list.*
import kotlinx.android.synthetic.main.pop_dilog.view.*
import kotlinx.android.synthetic.main.progress.*


class itemsFragment : Fragment(),MainLesener {
    override fun OnSuccessGetMyPackages(it1: myPackages) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnSuccessGetRequest(it1: MyRequests) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSusessInsertPackage(s: String) {
        context?.toast(context?.resources!!.getString(R.string.addToCart))
        itemsScrollPackage?.isRefreshing=false
    }

    override fun OnsuccessMyPackageItems(it1: myPackegeItemRes) {
        itemsScrollPackage?.isRefreshing=false
        ItemsListPackage?.layoutManager= LinearLayoutManager(context!!)
        if(!it1.data.Package_Items .isEmpty()){
            ItemsListPackage?.adapter= MyPackageItemsAdapter (context!!,it1,viewModel
                ,arguments?.getBoolean("flage",false)!!
                ,arguments!!.getSerializable("data") as Package)
        }

    }

    override fun OnSuccessBuyPackage(message: reSendRes) {
        itemsScrollPackage?.isRefreshing=false
        context?.toast(context?.resources!!.getString(R.string.BuyPackaege))
        view?.findNavController()?.navigate(R.id.myRequest_fragment)
    }

    override fun OnSuccessItems(message: itemsInPackage) {
        noNetContainer?.visibility=View.GONE
        itemsScrollPackage?.isRefreshing=false
        ItemsListPackage?.visibility=View.VISIBLE
        ItemsListPackage?.layoutManager= LinearLayoutManager(context!!)
        ItemsListPackage?.adapter= ItemsAdapter (context!!,message,viewModel
            ,arguments?.getBoolean("flage",false)!!)
        pricePackage?.text=context?.resources!!.getString(R.string.packPrice)+ message.data.packageItems.price.toString()+" "+message.data.packageItems.currency
    init(message.data.packageItems.id)
    }

    override fun OnStart() {
        itemsScrollPackage?.isRefreshing=true
    }

    override fun OnSuccessSignIn(response: signUpResponse) {
    }

    override fun onFailer(message: String) {
        context?.toast(message)
        itemsScrollPackage?.isRefreshing=false
        noNetContainer?.visibility=View.GONE
    }

    override fun onFailerNet(message: String) {
        itemsScrollPackage?.isRefreshing=false
        context?.toast(context?.resources!!.getString(R.string.noInternet))
        noNetContainer?.visibility=View.VISIBLE
        ItemsListPackage?.visibility=View.GONE
    }

    override fun OnSuccessPackage(message: PackageRes) {

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.package_details_list, container, false)
    }
    private lateinit var viewModel: MainViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val db= context?.let { databaseApp(it) }
        val repostary= MainRepostary(api!!,db!!)
        val factory= MainViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this,factory).get(MainViewModel::class.java)
        viewModel?.Auth=this

        itemsScrollPackage?.setOnRefreshListener {
            if(arguments?.getBoolean("flage",false)!!){
                conMy?.visibility=View.GONE
                var id:Int= arguments?.getInt("id")!!
                viewModel?.getMyPackageItems(id)
            }else{
                conMy?.visibility=View.VISIBLE
                var id:Int= arguments?.getInt("id")!!
                viewModel?.getItems(id)

            }
        }
        retryBtn?.setOnClickListener {
            if(arguments?.getBoolean("flage",false)!!){
                conMy?.visibility=View.GONE
                var id:Int= arguments?.getInt("id")!!
                viewModel?.getMyPackageItems(id)
            }else{
                conMy?.visibility=View.VISIBLE
                var id:Int= arguments?.getInt("id")!!
                viewModel?.getItems(id)

            }
        }
        if(arguments?.getBoolean("flage",false)!!){
            conMy?.visibility=View.GONE
            var id:Int= arguments?.getInt("id")!!
            viewModel?.getMyPackageItems(id)
        }else{
            conMy?.visibility=View.VISIBLE
            var id:Int= arguments?.getInt("id")!!
            viewModel?.getItems(id)

        }




    }
fun init(id: Int) {
    BuyPackBtn?.setOnClickListener {
        if(MainActivity.cacheObj.token!=""){
            val dview: View = View.inflate(context,R.layout.pop_dilog, null)
            val builder = context?.let { AlertDialog.Builder(it).setView(dview) }
            val malert= builder?.show()
            malert?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dview?.descPart?.text=context?.resources!!.getString(R.string.wantBuyPack)
            dview.conform?.setOnClickListener {
                viewModel?.BuyPackage(MainActivity.cacheObj.token,id)
                malert?.dismiss()
            }
        }else{
            view?.findNavController()?.navigate(R.id.login)
        }


    }
}
}

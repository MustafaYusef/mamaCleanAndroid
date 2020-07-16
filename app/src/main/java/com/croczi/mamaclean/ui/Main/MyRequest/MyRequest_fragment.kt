package com.croczi.mamaclean.ui.Main.MyRequest

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.croczi.mamaclean.MainActivity

import com.croczi.mamaclean.R
import com.croczi.mamaclean.data.auth.profile.myPackage.myPackages
import com.croczi.mamaclean.data.auth.profile.myPackegeItems.myPackegeItemRes
import com.croczi.mamaclean.data.auth.profile.myRequests.MyRequests
import com.croczi.mamaclean.data.auth.signUp.signUpResponse
import com.croczi.mamaclean.data.auth.verification.reSendCode.reSendRes
import com.croczi.mamaclean.data.packege.PackageRes
import com.croczi.mamaclean.data.packege.items.itemsInPackage
import com.croczi.mamaclean.network.myApi
import com.croczi.mamaclean.ui.Main.MainViewModel
import com.croczi.mamaclean.ui.Main.MainViewModelFactory
import com.croczi.mamaclean.ui.Main.adapters.myPackagesAdapter
import com.croczi.mamaclean.ui.Main.adapters.myRequestsAdapter
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.calculateNoOfColumns
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.wakely.ui.auth.MainLesener
import com.mustafayusef.wakely.ui.auth.MainRepostary
import kotlinx.android.synthetic.main.main__fragment.*
import kotlinx.android.synthetic.main.progress.*

class MyRequest_fragment : Fragment(),MainLesener {
    override fun OnSuccessGetMyPackages(it1: myPackages) {

    }

    override fun OnSuccessGetRequest(it1: MyRequests) {
        mainScroll?.isRefreshing=false
        mainPackageList?.visibility=View.VISIBLE
        mainPackageList?.adapter= myRequestsAdapter(context!!,it1)
        println("my packages     "+it1)
        noNetContainer?.visibility=View.GONE
    }

    override fun onSusessInsertPackage(s: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnsuccessMyPackageItems(it1: myPackegeItemRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnSuccessBuyPackage(message: reSendRes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnSuccessItems(message: itemsInPackage) {

    }

    override fun OnStart() {
        mainScroll?.isRefreshing=true
    }

    override fun OnSuccessSignIn(response: signUpResponse) {

    }

    override fun onFailer(message: String) {
        context?.toast(context?.resources!!.getString(R.string.somThing))
        mainScroll?.isRefreshing=false
        noNetContainer?.visibility=View.GONE
        if(message.toInt()==401){
            view?.findNavController()?.navigate(R.id.login)
            context?.toast(context?.resources!!.getString(R.string.NotUthorize))
        }
    }

    override fun onFailerNet(message: String) {
        context?.toast(context?.resources!!.getString(R.string.noInternet))
        mainScroll?.isRefreshing=false
        noNetContainer?.visibility=View.VISIBLE
        mainPackageList?.visibility=View.GONE
    }

    override fun OnSuccessPackage(message: PackageRes) {

    }

    companion object {
        fun newInstance() = MyRequest_fragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main__fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val db= context?.let { databaseApp(it) }
        val repostary= MainRepostary(api!!,db!!)
        val factory= MainViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this,factory).get(MainViewModel::class.java)
        viewModel?.Auth=this
        mainPackageList?.layoutManager=LinearLayoutManager(context!!)
        viewModel?.getMyRequestBuyPackage(MainActivity.cacheObj.token)

        mainScroll?.setOnRefreshListener {
            viewModel?.getMyRequestBuyPackage(MainActivity.cacheObj.token)
        }
        retryBtn?.setOnClickListener {
            viewModel?.getMyRequestBuyPackage(MainActivity.cacheObj.token)
        }
    }

}

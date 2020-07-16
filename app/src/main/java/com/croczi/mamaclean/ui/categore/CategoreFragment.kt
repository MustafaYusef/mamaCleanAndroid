package com.croczi.mamaclean.ui.categore

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.croczi.mamaclean.R
import com.croczi.mamaclean.data.categore.categoreRes
import com.croczi.mamaclean.data.categore.categoryItems.categoryItemsRes
import com.croczi.mamaclean.network.myApi
import com.croczi.mamaclean.ui.Main.MainViewModelFactory
import com.croczi.mamaclean.ui.categore.adapters.CategoreAdapter
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.calculateNoOfColumns
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.database.databaseApp
import com.mustafayusef.wakely.ui.auth.CategoreLesener
import com.mustafayusef.wakely.ui.auth.CategoreRepostary
import com.mustafayusef.wakely.ui.auth.MainRepostary
import kotlinx.android.synthetic.main.categore_fragment.*
import kotlinx.android.synthetic.main.progress.*

class CategoreFragment : Fragment(),CategoreLesener {
    override fun OnSuccessCategoreItems(message: categoryItemsRes) {

    }

    override fun OnStart() {
        categoryScroll?.isRefreshing=true
    }

    override fun OnSuccessCategore(message: categoreRes) {
        categoreList?.visibility=View.VISIBLE
        categoryScroll?.isRefreshing=false
        categoreList?.layoutManager= GridLayoutManager(context!!,
            calculateNoOfColumns(context!!,180f)
        )
        categoreList?.adapter=
            CategoreAdapter(context!!, message)
        categoryScroll?.setOnRefreshListener {

            viewModel?.getCategore()
        }
        noNetContainer?.visibility=View.GONE
    }

    override fun onFailer(message: String) {
        context?.toast(message)
        categoryScroll?.isRefreshing=false
        noNetContainer?.visibility=View.GONE

    }

    override fun onFailerNet(message: String) {
        categoryScroll?.isRefreshing=false
        context?.toast(context?.resources!!.getString(R.string.noInternet))
        noNetContainer?.visibility=View.VISIBLE
        categoreList?.visibility=View.GONE
    }

    override fun onSusessInsertcategoryOrder(s: String) {

    }

    companion object {
        fun newInstance() = CategoreFragment()
    }

    private lateinit var viewModel: CategoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.categore_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val db= context?.let { databaseApp(it) }
        val repostary= CategoreRepostary(api!!,db!!)
        val factory= CategoreViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this,factory).get(CategoreViewModel::class.java)
        viewModel?.Auth=this

        viewModel?.getCategore()
        retryBtn?.setOnClickListener {

            viewModel?.getCategore()
        }
    }

}

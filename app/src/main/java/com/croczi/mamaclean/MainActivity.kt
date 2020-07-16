package com.croczi.mamaclean

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.KotprefModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    object cacheObj : KotprefModel() {

        var token by stringPref("")
        var resetCode by stringPref("")
        var packageId by intPref(0)
    }
  object constant{
      val base:String="https://api.maamclean.com/files/"
  }





    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Kotpref.init(this)

        navController= Navigation.findNavController(this,R.id.navHost)
        bottomNav.setupWithNavController(navController)


        loginBtn?.setOnClickListener {
            loginBtn?.setBackgroundResource(R.drawable.round_white)

            loginBtn.setTextColor(this!!.resources.getColor(R.color.colorPrimaryDark1))

            signInBtn?.setBackgroundResource(R.drawable.round_white_corner)

            signInBtn.setTextColor(this!!.resources.getColor(R.color.white))
            navController?.navigate(R.id.login)
        }
        signInBtn?.setOnClickListener {
            signInBtn?.setBackgroundResource(R.drawable.round_white)

            signInBtn.setTextColor(this!!.resources.getColor(R.color.colorPrimaryDark1))

            loginBtn?.setBackgroundResource(R.drawable.round_white_corner)

            loginBtn.setTextColor(this!!.resources.getColor(R.color.white))
            navController?.navigate(R.id.signUp)
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if(navController.currentDestination?.id==R.id.login
                ||navController.currentDestination?.id==R.id.signUp
                ||navController.currentDestination?.id==R.id.sendCode
                ||navController.currentDestination?.id==R.id.resetPassword
                ||navController.currentDestination?.id==R.id.activateUser
                ||navController.currentDestination?.id==R.id.mapFragment
                ||navController.currentDestination?.id==R.id.changePassword){
                bottomNav?.visibility = View.GONE
                ToolBar?.visibility = View.GONE

            }else if(navController.currentDestination?.id==R.id.profileFragment||navController.currentDestination?.id==R.id.editeProfile2){
                ToolBar?.visibility = View.GONE
                bottomNav?.visibility = View.VISIBLE
                if (MainActivity.cacheObj.token==""){
                    navController?.navigate(R.id.login)
                }
            }
            else{  bottomNav?.visibility = View.VISIBLE
                ToolBar?.visibility = View.VISIBLE
            }
                if(navController.currentDestination?.id==R.id.main_Fragment){
                    titleMain?.text=this?.resources.getString(R.string.offers)
                }else if(navController.currentDestination?.id==R.id.ordersFragment){
                    titleMain?.text=this?.resources.getString(R.string.orders)
                }else if(navController.currentDestination?.id==R.id.cartFragment){
                    titleMain?.text=this?.resources.getString(R.string.Cart)
                    if(cacheObj.token=="")
                    navController?.navigate(R.id.login)
                }else if(navController.currentDestination?.id==R.id.categoreFragment){
                    titleMain?.text=this?.resources.getString(R.string.Main)
                }else if(navController.currentDestination?.id==R.id.itemsFragment){
                    titleMain?.text=this?.resources.getString(R.string.offersDetails)

                }else if(navController.currentDestination?.id==R.id.categoreItemsFragment){
                    titleMain?.text=this?.resources.getString(R.string.SecDetails)
                }else if(navController.currentDestination?.id==R.id.myPackage_fragment){
                    titleMain?.text=this?.resources.getString(R.string.myPackage)
                    if(cacheObj.token=="")
                    navController?.navigate(R.id.login)
                }else if(navController.currentDestination?.id==R.id.myRequest_fragment){
                    titleMain?.text=this?.resources.getString(R.string.requests)
                }else if(navController.currentDestination?.id==R.id.orderDetailsFragment) {
                    titleMain?.text=this?.resources.getString(R.string.OrdersDetails)
                }

                 if(navController.currentDestination?.id==R.id.login ||
                    navController.currentDestination?.id==R.id.signUp){
                    tol2?.visibility=View.VISIBLE
                }else{
                     tol2?.visibility=View.GONE
                 }
            if(navController.currentDestination?.id==R.id.login){
                loginBtn?.setBackgroundResource(R.drawable.round_white)

                loginBtn.setTextColor(this!!.resources.getColor(R.color.colorPrimaryDark1))

                signInBtn?.setBackgroundResource(R.drawable.round_white_corner)

                signInBtn.setTextColor(this!!.resources.getColor(R.color.white))

            }
        }

    }

    override fun onBackPressed() {

        if (navController.currentDestination?.id == R.id.login||
            navController.currentDestination?.id == R.id.categoreFragment) {
            finish()

            // do nothing
        }
        else {
            super.onBackPressed()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }
}

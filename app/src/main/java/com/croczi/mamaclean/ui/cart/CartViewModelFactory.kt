package com.croczi.mamaclean.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mustafayusef.wakely.ui.auth.CartRepostary
import com.mustafayusef.wakely.ui.auth.MainRepostary


class CartViewModelFactory(val repostary: CartRepostary) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CartViewModel(repostary) as T
    }
}
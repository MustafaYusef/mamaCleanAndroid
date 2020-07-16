package com.croczi.mamaclean.ui.Main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mustafayusef.wakely.ui.auth.MainRepostary


class MainViewModelFactory(val repostary: MainRepostary) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repostary) as T
    }
}
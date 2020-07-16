package com.croczi.mamaclean.ui.categore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mustafayusef.wakely.ui.auth.CategoreRepostary


class CategoreViewModelFactory(val repostary: CategoreRepostary) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoreViewModel(repostary) as T
    }
}
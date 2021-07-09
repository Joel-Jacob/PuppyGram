package com.example.puppygram.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.puppygram.models.Items
import com.example.puppygram.network.FlickerRepository
import com.example.puppygram.utils.Constants.PUPPY_TAG
import io.reactivex.disposables.CompositeDisposable

class PuppyGramViewModel : ViewModel() {
    private val flickerRepository = FlickerRepository()
    private val compositeDisposable = CompositeDisposable()

    private val _puppyList = MutableLiveData<MutableList<Items>>()
    val puppyList: LiveData<MutableList<Items>>
        get() = _puppyList

    private val _showErrorToast = MutableLiveData<Boolean>()
    val showErrorToast: LiveData<Boolean>
        get() = _showErrorToast

    /**
     * Method to make API call to get Puppies
     * */
    fun getPuppiesHelper() {
        compositeDisposable.add(
            flickerRepository.getPuppies(PUPPY_TAG)
                .subscribe ({
                    setPuppyList(it.items)
                },{
                    setShowErrorToast(true)
                })
        )
    }

    private fun setPuppyList(list: MutableList<Items>){
        _puppyList.value = list
    }

    private fun setShowErrorToast(value: Boolean){
        _showErrorToast.value = value
    }

    fun disposeCompositeDisposable(){
        compositeDisposable.dispose()
    }
}
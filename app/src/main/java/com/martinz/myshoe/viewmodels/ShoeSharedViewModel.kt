package com.martinz.myshoe.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martinz.myshoe.R
import com.martinz.myshoe.model.Shoes
import com.martinz.myshoe.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ShoeSharedViewModel : ViewModel() {

    var shoes = Shoes()

    private val _shoesLiveData =  MutableLiveData<MutableList<Shoes>>()
    val shoesLiveData : LiveData<MutableList<Shoes>> = _shoesLiveData

    private val _uiEventChannel = Channel<UiEvent>()
    val uiEventChannel : Flow<UiEvent> = _uiEventChannel.receiveAsFlow()


    var isLoading : MutableLiveData<Boolean> = MutableLiveData(false)

    val shoesPicList = listOf(
        R.drawable.shoe1,
        R.drawable.shoe2,
        R.drawable.shoe3,
        R.drawable.shoe4,
        R.drawable.shoe5,

        )

    val list = mutableListOf<Shoes>(
        Shoes(shoesName = "Black High Converse" , shoesCompany = "Converse" , shoesSize = 36 , shoesDescription = "New Converse Shoes", shoesPicture = shoesPicList.get(0)),
        Shoes(shoesName = "Baby Blue Converse" , shoesCompany = "Converse" , shoesSize = 42 , shoesDescription = "New Converse Shoes", shoesPicture = shoesPicList.get(1)),
        Shoes(shoesName = "Red Converse" , shoesCompany = "Converse" , shoesSize = 33 , shoesDescription = "New Converse Shoes", shoesPicture = shoesPicList.get(2)),
        Shoes(shoesName = "Sand Yellow Converse" , shoesCompany = "Converse" , shoesSize = 38, shoesDescription = "New Converse Shoes", shoesPicture = shoesPicList.get(3)),
        Shoes(shoesName = "Off-white Converse" , shoesCompany = "Converse" , shoesSize = 43 , shoesDescription = "New Converse Shoes", shoesPicture = shoesPicList.get(4)),

    )


    init {
        viewModelScope.launch {
            isLoading.postValue(true)
            delay(1000L)
            isLoading.postValue(false)
            _shoesLiveData.postValue(list)
        }
    }





    fun addShoe() {
        viewModelScope.launch {
            if (shoes == Shoes()) {
                _uiEventChannel.send(UiEvent.ShowToast("Please Add At Least One Filed Of Data"))
            } else {
                val finalShoes = shoes.copy(
                    shoesPicture = shoesPicList.random()
                )
                list.add(finalShoes)
                _shoesLiveData.postValue(list)
                shoes = Shoes()
                _uiEventChannel.send(UiEvent.NavigateUp)

            }
        }

    }





}
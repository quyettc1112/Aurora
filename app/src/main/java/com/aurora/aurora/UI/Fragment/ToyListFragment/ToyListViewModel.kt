package com.aurora.aurora.UI.Fragment.ToyListFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aurora.aurora.Model.ToyModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ToyListViewModel @Inject constructor() : ViewModel() {
    private val _fullToyList = mutableListOf<ToyModel>()
    private val _currentToyList = MutableLiveData<List<ToyModel>?>()
    val currentToyList: MutableLiveData<List<ToyModel>?> get() = _currentToyList

    var currentPopularLiveData = MutableLiveData<Int>()
    val currentSearchLiveData = MutableLiveData<String>()
    init {
        currentPopularLiveData.value = 2
        currentSearchLiveData.value = ""
    }

    fun setToyList(toyList: List<ToyModel>) {
        _fullToyList.clear()
        _fullToyList.addAll(toyList)
        _currentToyList.value = _fullToyList
    }

    fun setCurrentPopular(id: Int) {
        currentPopularLiveData.value = id
    }
    fun setCurrentSearchValue(query: String) {
        currentSearchLiveData.value = query
    }


    fun filterToyList(query: String) {
        val filteredList = _fullToyList.filter {
            val matchesQuery = it.toyName.contains(query, ignoreCase = true)
            val matchesTypePopular = when (currentPopularLiveData.value) {
                0 -> true // Nếu currentPopularLiveData.value == 0 thì không lọc theo typePopular
                else -> it.typePoppular == currentPopularLiveData.value
            }
            matchesQuery && matchesTypePopular
        }
        _currentToyList.value = filteredList
    }

}
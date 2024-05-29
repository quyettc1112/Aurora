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

    fun setToyList(toyList: List<ToyModel>) {
        _fullToyList.clear()
        _fullToyList.addAll(toyList)
        _currentToyList.value = _fullToyList
    }

    fun filterToyList(query: String) {
        val filteredList = if (query.isEmpty()) {
            _fullToyList
        } else {
            _fullToyList.filter { it.toyName.contains(query, ignoreCase = true) }
        }
        _currentToyList.value = filteredList
    }
}
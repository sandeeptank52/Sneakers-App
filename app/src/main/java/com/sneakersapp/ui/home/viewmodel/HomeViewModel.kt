package com.sneakersapp.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sneakersapp.data.models.Product
import com.sneakersapp.domain.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ProductsRepository) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> get() = _errorMsg

    init {
        fetchProducts()
    }

    private fun fetchProducts() {

        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val response = repository.getProducts()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _products.postValue(response.body())
                } else {
                    _errorMsg.postValue(response.message())
                }
                _isLoading.postValue(false)
            }
        }
    }
}
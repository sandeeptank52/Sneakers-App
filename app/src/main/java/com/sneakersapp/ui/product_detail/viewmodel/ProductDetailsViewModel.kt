package com.sneakersapp.ui.product_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sneakersapp.data.models.Product
import com.sneakersapp.domain.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private val repository: ProductsRepository) :
    ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> get() = _product
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _navigateToCart = MutableLiveData<Boolean>()
    val navigateToCart: LiveData<Boolean> get() = _navigateToCart
    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> get() = _errorMsg


    fun fetchProduct(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val response = repository.getProductDetails(id)
            if (response.isSuccessful) {
                _product.postValue(response.body())
            } else {
                _errorMsg.postValue(response.message())
            }
            _isLoading.postValue(false)
        }
    }

    fun addProductToCart() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            _product.value?.let {
                val response = repository.addProductToCart(it)
                if (response.isSuccessful) {
                    _navigateToCart.postValue(true)
                } else {
                    _errorMsg.postValue(response.message())
                }
            }
            _isLoading.postValue(false)
        }
    }
}
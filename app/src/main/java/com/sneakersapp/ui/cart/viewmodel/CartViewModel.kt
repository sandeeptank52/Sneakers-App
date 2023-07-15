package com.sneakersapp.ui.cart.viewmodel

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
class CartViewModel @Inject constructor(private val repository: ProductsRepository) : ViewModel() {

    private val _subTotalText = MutableLiveData<String>()
    val subTotalText: LiveData<String> get() = _subTotalText
    private val _taxText = MutableLiveData<String>()
    val taxText: LiveData<String> get() = _taxText
    private val _totalAmount = MutableLiveData<String>()
    val totalAmount: LiveData<String> get() = _totalAmount


    private val _cartProducts = MutableLiveData<List<Product>>()
    val cartProducts: LiveData<List<Product>> get() = _cartProducts
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> get() = _errorMsg

    init {
        fetchCartProducts()
    }

    private fun fetchCartProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val response = repository.getCartProducts()
            if (response.isSuccessful) {
                _cartProducts.postValue(response.body())
            } else {
                _errorMsg.postValue(response.message())
            }
            _isLoading.postValue(false)
        }
    }

    fun updateUIData(cartProducts: List<Product>?) {
        cartProducts?.let { list ->
            val subTotal = list.sumOf { it.retailPrice ?: 0 }
            _subTotalText.postValue("Subtotal : $${subTotal}")
            _taxText.postValue("Taxes and Charges : $40")
            _totalAmount.postValue("$${subTotal + 40}")
        }
    }

    fun removeProductFromCart(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val response = repository.removeProductFromCart(product)
            if (response.isSuccessful) {
                fetchCartProducts()
            } else {
                _errorMsg.postValue(response.message())
            }
            _isLoading.postValue(false)
        }
    }
}
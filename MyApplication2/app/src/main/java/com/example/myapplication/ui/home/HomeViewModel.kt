package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.obj.Products
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeViewModel : ViewModel() {

    val products = MutableLiveData<ArrayList<Products>>()

    fun getDataProduct() {
        Firebase.firestore.collection("products").get().addOnSuccessListener { documents ->
            documents?.let{
                val list = ArrayList<Products>()
                for (product in it) {
                    list.add(product.toObject(Products::class.java))
                }
                products.value = list
            }
        }

    }
}
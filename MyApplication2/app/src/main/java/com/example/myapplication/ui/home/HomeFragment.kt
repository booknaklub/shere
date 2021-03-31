package com.example.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.recycleview.ProductRecyclerViewAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_home)*/
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)
        val productViewAdapter = ProductRecyclerViewAdapter()
        recyclerView.adapter = productViewAdapter

        recyclerView.layoutManager = LinearLayoutManager(context)
        /*homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        homeViewModel.products.observe(viewLifecycleOwner, Observer {
            for (product in it) {
                Log.d("Prodict", product.name)
            }
            productViewAdapter.setProducts(it)
            productViewAdapter.notifyDataSetChanged()
        })
        homeViewModel.getDataProduct()
        return root
    }
}
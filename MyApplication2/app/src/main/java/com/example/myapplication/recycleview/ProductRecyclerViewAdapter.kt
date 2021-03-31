package com.example.myapplication.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.obj.Products

class ProductRecyclerViewAdapter:
    RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    val product = ArrayList<Products>()

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val price: TextView

        init {
            name = view.findViewById(R.id.name)
            price = view.findViewById(R.id.price)
        }

    }

    fun setProducts(product: ArrayList<Products>) {
        this.product.addAll(product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.name.text = product[position].name
        holder.price.text = product[position].price.toString()
    }

    override fun getItemCount(): Int = product.size
}
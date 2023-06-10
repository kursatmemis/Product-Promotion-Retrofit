package com.kursatmemis.productpromotion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.kursatmemis.productpromotion.adapters.ProductAdapter
import com.kursatmemis.productpromotion.models.ProductElement

class DetailActivity : AppCompatActivity() {

    private lateinit var productListView: ListView
    private var products = mutableListOf<ProductElement>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        productListView = findViewById(R.id.productListView)
        val adapter = ProductAdapter(this, products)
        productListView.adapter = adapter

        val item = ProductsActivity.clickedItem
        if (item != null) {
            products.add(item)
            adapter.notifyDataSetChanged()
            Log.d("mkmmkm", "null degil")
        } else {
            Log.d("mkmmkm", "null")
        }

    }
}
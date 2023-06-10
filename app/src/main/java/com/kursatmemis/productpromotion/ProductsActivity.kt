package com.kursatmemis.productpromotion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.kursatmemis.productpromotion.configs.ApiClient
import com.kursatmemis.productpromotion.models.Product
import com.kursatmemis.productpromotion.models.ProductElement
import com.kursatmemis.productpromotion.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsActivity : AppCompatActivity() {

    private lateinit var productsListView: ListView
    private lateinit var searchEditText: EditText
    private var originalProducts = mutableListOf<ProductElement>()
    private var filteredProducts = mutableListOf<ProductElement>()

    companion object{
        var clickedItem: ProductElement? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        productsListView = findViewById(R.id.productsListView)
        searchEditText = findViewById(R.id.searchEditText)
        welcome()

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            originalProducts
        )
        productsListView.adapter = adapter

        searchEditText.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                adapter.filter.filter(p0)
            }

        })

        productsListView.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this@ProductsActivity, DetailActivity::class.java)
            clickedItem = originalProducts[i]
            startActivity(intent)
        }

        val dummyService = ApiClient.getClient().create(DummyService::class.java)
        dummyService.getProducts().enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                val products = response.body()?.products
                if (products != null) {
                    for (product in products) {
                        originalProducts.add(product)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Log.d("isNull", true.toString())
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.d("onFailure-ProductsActivity", t.toString())
            }

        })


    }

    private fun welcome() {
        val firstname = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        Toast.makeText(this@ProductsActivity, "Welcome $firstname $lastName", Toast.LENGTH_SHORT)
            .show()
    }
}
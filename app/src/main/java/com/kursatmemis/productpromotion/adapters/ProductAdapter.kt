package com.kursatmemis.productpromotion.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kursatmemis.productpromotion.R
import com.kursatmemis.productpromotion.models.ProductElement

class ProductAdapter(val context: AppCompatActivity, val products: List<ProductElement>) :
    ArrayAdapter<ProductElement>(context, R.layout.custom_product_view, products) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val customProductView = inflater.inflate(R.layout.custom_product_view, null)

        val idTextView = customProductView.findViewById<TextView>(R.id.idTextView)
        val titleTextView = customProductView.findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = customProductView.findViewById<TextView>(R.id.descriptionTextView)
        val priceTextView = customProductView.findViewById<TextView>(R.id.priceTextView)
        val discountPercentageTextView =
            customProductView.findViewById<TextView>(R.id.discountPercentageTextView)
        val ratingTextView = customProductView.findViewById<TextView>(R.id.ratingTextView)
        val stockTextView = customProductView.findViewById<TextView>(R.id.stockTextView)
        val brandTextView = customProductView.findViewById<TextView>(R.id.brandTextView)
        val categoryTextView = customProductView.findViewById<TextView>(R.id.categoryTextView)
        val productImageView = customProductView.findViewById<ImageView>(R.id.productImageView)

        idTextView.text = products[position].id.toString()
        titleTextView.text = products[position].title
        descriptionTextView.text = products[position].description
        priceTextView.text = products[position].price.toString()
        discountPercentageTextView.text = products[position].discountPercentage.toString()
        ratingTextView.text = products[position].rating.toString()
        stockTextView.text = products[position].stock.toString()
        brandTextView.text = products[position].brand
        categoryTextView.text = products[position].category
        Glide.with(context).load(products[position].images[0]).into(productImageView)

        return customProductView
    }

}
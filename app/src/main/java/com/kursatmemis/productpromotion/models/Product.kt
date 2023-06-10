package com.kursatmemis.productpromotion.models

data class Product (
    val products: List<ProductElement>,
    val total: Long,
    val skip: Long,
    val limit: Long
)

data class ProductElement (
    val id: Long,
    val title: String,
    val description: String,
    val price: Long,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Long,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
) {
    override fun toString(): String {
        return title
    }
}



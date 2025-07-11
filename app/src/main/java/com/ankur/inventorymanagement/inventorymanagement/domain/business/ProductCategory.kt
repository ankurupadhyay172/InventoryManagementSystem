package com.ankur.inventorymanagement.inventorymanagement.domain.business


data class ProductCategory(var productCategoryId:Int,
                           var productCategoryName:String,
                           var price:Double,
                           var products:MutableList<Product> = mutableListOf()){
    fun addProduct(product: Product){
        products.add(product)
    }

    fun removeProductItems(count:Int){
        for (i in 1..count){
            products.removeAt(0)
        }
    }
}
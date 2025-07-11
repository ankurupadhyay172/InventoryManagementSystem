package com.ankur.inventorymanagement.inventorymanagement.domain.business


data class Inventory(var productCategoryList: MutableList<ProductCategory> = mutableListOf()){

    fun addCategory(productCategoryId:Int,productCategoryName:String,price:Double){
        val category = ProductCategory(productCategoryId,productCategoryName,price)
        productCategoryList.add(category)
    }

    fun addCategoryProduct(product: Product, productCategoryId: Int){
        for (category in productCategoryList){
            if (category.productCategoryId==productCategoryId){
                category.addProduct(product)
            }
        }
    }

    fun removeItems(productCategoryIdVsCount:MutableMap<Int,Int>){
        for ((key,value ) in productCategoryIdVsCount){
            val category = getProductCategoryFromId(key)
            category?.removeProductItems(value)
        }
    }

    fun getProductCategoryFromId(productCategoryId: Int): ProductCategory?{
        for (category in productCategoryList){
            if (category.productCategoryId==productCategoryId){
                return category
            }
        }
        return null
    }

    fun printInventory(){
        for(category in productCategoryList){
            println("${category.productCategoryName} = ${category.products.size}")
        }
    }

    fun getAvailableProductsList(productCategoryId: Int):Int{
        for (category in productCategoryList){
            if (category.productCategoryId==productCategoryId)
            return category.products.size
        }
        return 0
    }

}
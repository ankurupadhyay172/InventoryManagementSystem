package com.ankur.inventorymanagement.inventorymanagement.domain.business

data class Warehouse(var address: Address, var inventory: Inventory){

    constructor() : this(
        address = Address(),
        inventory = Inventory()
    )

    fun removeItemsFromInventory(productCategoryIdVsCountMap: MutableMap<Int,Int>){
        inventory.removeItems(productCategoryIdVsCountMap)
    }

    fun addItemsToInventory(productCategoryIdVsCountMap: MutableMap<Int,Int>){

    }

    fun printWarehouse(){
        inventory.printInventory()
    }

    fun getAvailableProductsCount(productCategoryId:Int):Int{
        return inventory.getAvailableProductsList(productCategoryId)
    }


    fun getCategoryById(categoryId: Int): ProductCategory {
        return inventory.productCategoryList.first { it.productCategoryId == categoryId }
    }
}
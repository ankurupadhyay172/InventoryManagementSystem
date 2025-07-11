package com.ankur.inventorymanagement.inventorymanagement.data.repository

import com.ankur.inventorymanagement.inventorymanagement.domain.business.Inventory
import com.ankur.inventorymanagement.inventorymanagement.domain.business.Product
import com.ankur.inventorymanagement.inventorymanagement.domain.business.Warehouse
import com.ankur.inventorymanagement.inventorymanagement.domain.repository.InventoryRepository
import javax.inject.Inject

class InventoryRepositoryImpl @Inject constructor(): InventoryRepository {
    override fun addWarehouseProducts(warehouse: Warehouse): Warehouse {
        if (warehouse.inventory.productCategoryList.isNotEmpty()) return warehouse

        val inventory = Inventory()

        // 🔹 Each Category IS a Product Name
        inventory.addCategory(1, "Pepsi 500ml", 50.0)
        inventory.addCategory(2, "Coca-Cola 1L", 60.0)
        inventory.addCategory(3, "Sprite Can", 45.0)
        inventory.addCategory(4, "Maggi Noodles", 15.0)
        inventory.addCategory(5, "Lays Classic", 10.0)
        inventory.addCategory(6, "Kurkure Masala", 12.0)
        inventory.addCategory(7, "Amul Milk 1L", 50.0)
        inventory.addCategory(8, "Mother Dairy Curd", 30.0)
        inventory.addCategory(9, "Amul Cheese Cubes", 70.0)
        inventory.addCategory(10, "Dove Shampoo 200ml", 120.0)
        inventory.addCategory(11, "Colgate Toothpaste", 45.0)
        inventory.addCategory(12, "Lifebuoy Soap", 25.0)
        inventory.addCategory(13, "Harpic Toilet Cleaner", 90.0)
        inventory.addCategory(14, "Vim Bar", 10.0)
        inventory.addCategory(15, "Dettol Floor Cleaner", 130.0)

        // 🔸 Now adding units — one Product object per unit
        repeat(3) { inventory.addCategoryProduct(Product(1, "Pepsi 500ml"), 1) }
        repeat(3)  { inventory.addCategoryProduct(Product(2, "Coca-Cola 1L"), 2) }
        repeat(3) { inventory.addCategoryProduct(Product(3, "Sprite Can"), 3) }
        repeat(3) { inventory.addCategoryProduct(Product(4, "Maggi Noodles"), 4) }
        repeat(4) { inventory.addCategoryProduct(Product(5, "Lays Classic"), 5) }
        repeat(3) { inventory.addCategoryProduct(Product(6, "Kurkure Masala"), 6) }
        repeat(10) { inventory.addCategoryProduct(Product(7, "Amul Milk 1L"), 7) }
        repeat(15) { inventory.addCategoryProduct(Product(8, "Mother Dairy Curd"), 8) }
        repeat(6)  { inventory.addCategoryProduct(Product(9, "Amul Cheese Cubes"), 9) }
        repeat(10) { inventory.addCategoryProduct(Product(10, "Dove Shampoo"), 10) }
        repeat(14) { inventory.addCategoryProduct(Product(11, "Colgate"), 11) }
        repeat(30) { inventory.addCategoryProduct(Product(12, "Lifebuoy Soap"), 12) }
        repeat(8)  { inventory.addCategoryProduct(Product(13, "Harpic"), 13) }
        repeat(20) { inventory.addCategoryProduct(Product(14, "Vim Bar"), 14) }
        repeat(5)  { inventory.addCategoryProduct(Product(15, "Dettol Cleaner"), 15) }

        warehouse.inventory = inventory
        return warehouse
    }


}
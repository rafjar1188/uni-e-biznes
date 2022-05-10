package bloczek.pl.service

import bloczek.pl.enums.Category
import bloczek.pl.enums.Subcategory
import bloczek.pl.model.Product
import bloczek.pl.repository.ProductRepository

class ProductService(private val productRepo: ProductRepository) {

    suspend fun getProducts(
        category: Category? = null,
        subcategory: Subcategory? = null,
        brandId: Int? = null
    ): List<Product> {
        val products = productRepo.getProducts(category, subcategory, brandId)
        return products
    }

    suspend fun getById(id: Int): Product? = productRepo.getById(id)

}

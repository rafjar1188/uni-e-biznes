package bloczek.pl.repository

import bloczek.pl.db.DatabaseFactory.dbQuery
import bloczek.pl.enums.Category
import bloczek.pl.enums.Subcategory
import bloczek.pl.model.Brand
import bloczek.pl.model.Brands
import bloczek.pl.model.Product
import bloczek.pl.model.Products
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ProductRepository {

    suspend fun getProducts(
        category: Category? = null,
        subcategory: Subcategory? = null,
        brandId: Int? = null
    ) = dbQuery {
        val query = Products.join(
            Brands,
            JoinType.LEFT,
            additionalConstraint = {Products.brandId eq Brands.id}
        ).selectAll()

        category?.let {
            query.andWhere { Products.category eq it }
        }
        subcategory?.let {
            query.andWhere { Products.subcategory eq it }
        }
        brandId?.let {
            query.andWhere { Products.brandId eq it }
        }

        query.map { mapProduct(it) }
    }

    suspend fun getById(id: Int) = dbQuery {
        Products
            .join(
                Brands,
                JoinType.LEFT,
                additionalConstraint = {Products.brandId eq Brands.id}
            )
            .select(Products.id eq id)
            .map {
                mapProduct(it)
            }.elementAtOrNull(0)
    }

    private fun mapProduct(row: ResultRow) = Product(
        id = row[Products.id],
        name = row[Products.name],
        price = row[Products.price].toDouble(),
        description = row[Products.description],
        brand = Brand(id = row[Brands.id], name = row[Brands.name]),
        category = row[Products.category],
        subcategory = row[Products.subcategory]
    )
}

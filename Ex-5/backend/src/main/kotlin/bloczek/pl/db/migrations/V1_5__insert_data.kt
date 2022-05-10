package bloczek.pl.db.migrations

import bloczek.pl.enums.Category
import bloczek.pl.enums.Subcategory
import bloczek.pl.model.Brands
import bloczek.pl.model.Products
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.math.BigDecimal

class V1_5__insert_data : BaseJavaMigration(){
    override fun migrate(context: Context?) {
        transaction {
            Brands.insert { it[name] = "Brand A" }
            Brands.insert { it[name] = "Brand B" }
            Brands.insert { it[name] = "Brand C" }

            val brandId = Brands.selectAll().map { it[Brands.id] }[0]

            Products.insert {
                it[name] = "Product A"
                it[description] = "Lorem ipsum"
                it[price] = BigDecimal(153)
                it[Products.brandId] = brandId
                it[category] = Category.SWEATSHIRTS
                it[subcategory] = Subcategory.HOODIE
            }

            Products.insert {
                it[name] = "Product B"
                it[description] = "Lorem ipsum"
                it[price] = BigDecimal(1167)
                it[Products.brandId] = brandId
                it[category] = Category.SWEATSHIRTS
                it[subcategory] = Subcategory.HOODIE
            }

            Products.insert {
                it[name] = "Product C"
                it[description] = "Lorem ipsum"
                it[price] = BigDecimal(56)
                it[Products.brandId] = brandId
                it[category] = Category.SWEATSHIRTS
                it[subcategory] = Subcategory.HOODIE
            }

            Products.insert {
                it[name] = "Product D"
                it[description] = "Lorem ipsum"
                it[price] = BigDecimal(999)
                it[Products.brandId] = brandId
                it[category] = Category.SWEATSHIRTS
                it[subcategory] = Subcategory.HOODIE
            }

        }
    }
}
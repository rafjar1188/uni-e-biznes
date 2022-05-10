package bloczek.pl.model

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Brands : Table() {
    val id: Column<Int> = integer("brand_id").autoIncrement()
    val name: Column<String> = varchar("name", 255)

    override val primaryKey = PrimaryKey(id, name = "PK_Brands_Id")
}


data class Brand(
    val id: Int,
    val name: String
)

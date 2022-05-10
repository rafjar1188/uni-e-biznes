package bloczek.pl.db.migrations

import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.transactions.transaction

class V1_2__create_subcategories : BaseJavaMigration() {
    override fun migrate(context: Context?) {
        transaction {
            exec("CREATE TYPE Subcategory AS ENUM ('HOODIE' ,'CRAWNECK')")
        }
    }
}
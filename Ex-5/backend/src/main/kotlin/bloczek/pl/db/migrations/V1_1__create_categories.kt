package bloczek.pl.db.migrations

import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.transactions.transaction

class V1_1__create_categories : BaseJavaMigration() {
    override fun migrate(context: Context?) {
        transaction {
            exec("CREATE TYPE Category AS ENUM ('T_SHIRTS', 'SWEATSHIRTS', 'TROUSERS', 'ACCESSORIES')")
        }
    }
}

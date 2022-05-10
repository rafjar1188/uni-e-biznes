package bloczek.pl.controller

import bloczek.pl.dto.CategoryDto
import bloczek.pl.dto.SubcategoryDto
import bloczek.pl.enums.Category
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/categories")
class CategoriesRoutes

fun Route.categoriesRoutes() {
    get<CategoriesRoutes> {
        val categories = Category.values().map {
            CategoryDto(
                it,
                it.title,
                it.getSubcategories().map { it2 ->
                    SubcategoryDto(
                        it2,
                        it2.title
                    )
                }
            )
        }

        call.respond(categories)
    }
}

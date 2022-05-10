package bloczek.pl

import bloczek.pl.controller.categoriesRoutes
import bloczek.pl.controller.productsRoutes
import bloczek.pl.db.DatabaseFactory
import bloczek.pl.utils.diModule
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.serialization.gson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.context.startKoin

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.mainModule() {
    startKoin {
        modules(diModule)
    }
    install(ContentNegotiation) {
        gson()
        json()
    }
    install(Resources)

    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowNonSimpleContentTypes = true
        allowCredentials = true
        allowSameOrigin = true
        allowHost("*", listOf("http", "https")) // frontendHost might be "*"
    }

    DatabaseFactory.connectAndMigrate(environment.config)

    install(Routing) {
        productsRoutes()
        categoriesRoutes()
    }
//    routing {
//        route("/products") {
//            get {
//                call.respond(listOf(
//                    Product(1, "Product A", category = Category.SWEATSHIRTS),
//                    Product(2, "Product B", category = Category.SWEATSHIRTS),
//                    Product(3, "Product C", category = Category.SWEATSHIRTS),
//                    Product(4, "Product D", category = Category.SWEATSHIRTS)
//                ))
//            }
//        }
//    }
}

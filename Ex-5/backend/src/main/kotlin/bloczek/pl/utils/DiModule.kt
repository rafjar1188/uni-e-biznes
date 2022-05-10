package bloczek.pl.utils

import bloczek.pl.repository.ProductRepository
import bloczek.pl.service.ProductService
import org.koin.dsl.module

val diModule = module {
    single { ProductService(get()) }
    single { ProductRepository() }
//    single { HelloServiceImpl(get()) } // get() Will resolve bloczek.pl.HelloRepository
//    single { HelloRepository() }
}

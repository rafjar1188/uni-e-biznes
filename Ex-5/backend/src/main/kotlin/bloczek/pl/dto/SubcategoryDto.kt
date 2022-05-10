package bloczek.pl.dto

import bloczek.pl.enums.Subcategory

data class SubcategoryDto(
    val name: Subcategory,
    val title: String
)

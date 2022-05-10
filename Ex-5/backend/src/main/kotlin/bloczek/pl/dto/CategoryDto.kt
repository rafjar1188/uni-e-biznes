package bloczek.pl.dto

import bloczek.pl.enums.Category

data class CategoryDto(
    val name: Category,
    val title: String,
    val subcategories: List<SubcategoryDto>
)

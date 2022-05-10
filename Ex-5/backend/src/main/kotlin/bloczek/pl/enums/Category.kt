package bloczek.pl.enums

enum class Category(
    val title: String
) {
    T_SHIRTS("T-shirty") {
        override fun getSubcategories(): List<Subcategory> =
            listOf(Subcategory.HOODIE, Subcategory.CRAWNECK)
    },
    SWEATSHIRTS("Bluzy") {
        override fun getSubcategories(): List<Subcategory> =
            listOf(Subcategory.HOODIE, Subcategory.CRAWNECK)
    },
    TROUSERS("Spodnie") {
        override fun getSubcategories(): List<Subcategory> = listOf()
    },
    ACCESSORIES("Akcesoria") {
        override fun getSubcategories(): List<Subcategory> = listOf()
    };

    abstract fun getSubcategories(): List<Subcategory>
}

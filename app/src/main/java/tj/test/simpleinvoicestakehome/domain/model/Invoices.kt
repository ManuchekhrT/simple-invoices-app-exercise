package tj.test.simpleinvoicestakehome.domain.model


data class Invoice(
    val id: String,
    val date: String,
    val totalPrice: Int,
    val items: List<InvoiceDetail> = emptyList()
) : java.io.Serializable

data class InvoiceDetail(
    val id: String,
    val name: String,
    val quantity: Int,
    val priceInCents: Int
) : java.io.Serializable
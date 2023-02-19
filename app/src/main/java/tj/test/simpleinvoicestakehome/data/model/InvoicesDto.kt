package tj.test.simpleinvoicestakehome.data.model

import com.google.gson.annotations.SerializedName

class InvoicesTotalDto(
    @SerializedName("items")
    val items: List<InvoicesDto> = emptyList()
)

class InvoicesDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("items")
    val items: List<InvoiceDto> = emptyList()
)

class InvoiceDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("priceinCents")
    val priceInCents: Int
)
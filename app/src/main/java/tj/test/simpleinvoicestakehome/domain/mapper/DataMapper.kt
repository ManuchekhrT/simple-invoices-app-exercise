package tj.test.simpleinvoicestakehome.domain.mapper

import tj.test.simpleinvoicestakehome.data.model.InvoiceDto
import tj.test.simpleinvoicestakehome.data.model.InvoicesDto
import tj.test.simpleinvoicestakehome.domain.model.Invoice
import tj.test.simpleinvoicestakehome.domain.model.InvoiceDetail

fun List<InvoicesDto>.transformToInvoicesList(): List<Invoice> {
    return this.map { dto ->
        Invoice(
            id = dto.id,
            date = dto.date,
            totalPrice = dto.items.totalSumOfInvoices(),
            items = dto.items.transformToInvoiceList()
        )
    }
}

fun List<InvoiceDto>.totalSumOfInvoices(): Int {
    return this.sumOf { it.priceInCents }
}

fun List<InvoiceDto>.transformToInvoiceList(): List<InvoiceDetail> {
    return this.map {
        InvoiceDetail(
            it.id,
            it.name,
            it.quantity,
            it.priceInCents
        )
    }
}
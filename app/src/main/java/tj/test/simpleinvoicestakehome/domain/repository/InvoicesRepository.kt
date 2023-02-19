package tj.test.simpleinvoicestakehome.domain.repository

import tj.test.simpleinvoicestakehome.utils.Invoices
import tj.test.simpleinvoicestakehome.utils.ResponseWrapper

interface InvoicesRepository {
    suspend fun fetchInvoices() : Invoices
    suspend fun fetchInvoicesEmpty(): Invoices
    suspend fun fetchInvoicesMalformed(): ResponseWrapper<Invoices>
}
package tj.test.simpleinvoicestakehome.data.remote.datasources

import retrofit2.Response
import tj.test.simpleinvoicestakehome.data.model.InvoicesDto
import tj.test.simpleinvoicestakehome.data.model.InvoicesTotalDto

interface InvoicesRemoteDataSource {
    suspend fun fetchInvoices(): List<InvoicesDto>
    suspend fun fetchInvoicesEmpty(): List<InvoicesDto>
    suspend fun fetchInvoicesMalformed(): Response<InvoicesTotalDto>
}
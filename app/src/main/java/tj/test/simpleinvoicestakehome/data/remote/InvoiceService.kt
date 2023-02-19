package tj.test.simpleinvoicestakehome.data.remote

import retrofit2.Response
import retrofit2.http.GET
import tj.test.simpleinvoicestakehome.data.model.InvoicesDto
import tj.test.simpleinvoicestakehome.data.model.InvoicesTotalDto

interface InvoiceService {
    companion object {
        const val BASE_API = "https://storage.googleapis.com/xmm-homework/"
    }

    @GET("invoices.json")
    suspend fun fetchInvoices(): InvoicesTotalDto

    @GET("invoices_empty.json")
    suspend fun fetchInvoicesEmpty(): InvoicesTotalDto

    @GET("invoices_malformed.json")
    suspend fun fetchInvoicesMalformed(): Response<InvoicesTotalDto>
}
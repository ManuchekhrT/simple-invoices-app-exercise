package tj.test.simpleinvoicestakehome.data.remote.datasources

import retrofit2.Response
import tj.test.simpleinvoicestakehome.data.model.InvoicesDto
import tj.test.simpleinvoicestakehome.data.model.InvoicesTotalDto
import tj.test.simpleinvoicestakehome.data.remote.InvoiceService
import javax.inject.Inject

class InvoicesRemoteDataSourceImpl @Inject constructor(private val invoiceService: InvoiceService) :
    InvoicesRemoteDataSource {

    override suspend fun fetchInvoices(): List<InvoicesDto> {
        return invoiceService.fetchInvoices().items
    }

    override suspend fun fetchInvoicesEmpty(): List<InvoicesDto> {
        return invoiceService.fetchInvoicesEmpty().items
    }

    override suspend fun fetchInvoicesMalformed(): Response<InvoicesTotalDto> {
        return invoiceService.fetchInvoicesMalformed()
    }
}
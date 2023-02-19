package tj.test.simpleinvoicestakehome.data.remote.repository

import tj.test.simpleinvoicestakehome.data.remote.datasources.InvoicesRemoteDataSource
import tj.test.simpleinvoicestakehome.domain.mapper.transformToInvoicesList
import tj.test.simpleinvoicestakehome.domain.repository.InvoicesRepository
import tj.test.simpleinvoicestakehome.utils.Invoices
import tj.test.simpleinvoicestakehome.utils.ResponseWrapper
import javax.inject.Inject

class InvoicesRepositoryImpl @Inject constructor(
    private val dataSource: InvoicesRemoteDataSource
) : InvoicesRepository {

    override suspend fun fetchInvoices(): Invoices {
        return dataSource.fetchInvoices().transformToInvoicesList()
    }

    override suspend fun fetchInvoicesEmpty(): Invoices {
        return dataSource.fetchInvoicesEmpty().transformToInvoicesList()
    }

    override suspend fun fetchInvoicesMalformed(): ResponseWrapper<Invoices> {
        val response = dataSource.fetchInvoicesMalformed()
        return if (response.isSuccessful) {
            ResponseWrapper.Success(response.body()?.items?.transformToInvoicesList() ?: emptyList())
        } else {
            ResponseWrapper.Error(response.code(), response.errorBody()?.string())
        }
    }
}
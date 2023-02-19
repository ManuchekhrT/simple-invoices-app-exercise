package tj.test.simpleinvoicestakehome.domain.usecases

import kotlinx.coroutines.flow.*
import tj.test.simpleinvoicestakehome.domain.repository.InvoicesRepository
import tj.test.simpleinvoicestakehome.utils.FlowUseCase
import tj.test.simpleinvoicestakehome.utils.Invoices
import tj.test.simpleinvoicestakehome.utils.ResponseWrapper
import javax.inject.Inject

interface InvoicesUseCase : FlowUseCase<Unit, Invoices>

class GetInvoicesUseCase @Inject constructor(
    private val repository: InvoicesRepository
) : InvoicesUseCase {

    override fun execute(param: Unit): Flow<ResponseWrapper<Invoices>> = flow {
        emit(ResponseWrapper.Success(repository.fetchInvoices()))
    }
}
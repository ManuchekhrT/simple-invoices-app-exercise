package tj.test.simpleinvoicestakehome.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tj.test.simpleinvoicestakehome.domain.repository.InvoicesRepository
import tj.test.simpleinvoicestakehome.utils.FlowUseCase
import tj.test.simpleinvoicestakehome.utils.Invoices
import tj.test.simpleinvoicestakehome.utils.ResponseWrapper
import javax.inject.Inject

interface InvoicesEmptyUseCase : FlowUseCase<Unit, Invoices>

class GetInvoicesEmptyUseCase @Inject constructor(
    private val repository: InvoicesRepository
) : InvoicesEmptyUseCase {

    override fun execute(param: Unit): Flow<ResponseWrapper<Invoices>> = flow {
        emit(ResponseWrapper.Success(repository.fetchInvoicesEmpty()))
    }
}
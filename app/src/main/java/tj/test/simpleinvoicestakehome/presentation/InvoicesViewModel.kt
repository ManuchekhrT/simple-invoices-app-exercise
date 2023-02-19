package tj.test.simpleinvoicestakehome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import tj.test.simpleinvoicestakehome.domain.usecases.GetInvoicesEmptyUseCase
import tj.test.simpleinvoicestakehome.domain.usecases.GetInvoicesMalformedUseCase
import tj.test.simpleinvoicestakehome.domain.usecases.GetInvoicesUseCase
import tj.test.simpleinvoicestakehome.utils.Invoices
import tj.test.simpleinvoicestakehome.utils.ResponseWrapper
import javax.inject.Inject

@HiltViewModel
class InvoicesViewModel @Inject constructor(
    private val getInvoicesUseCase: GetInvoicesUseCase,
    private val getInvoicesEmptyUseCase: GetInvoicesEmptyUseCase,
    private val getInvoicesMalformedUseCase: GetInvoicesMalformedUseCase
) : ViewModel() {

    private val _invoices = MutableStateFlow<ResponseWrapper<Invoices>>(ResponseWrapper.Loading)
    val invoices = _invoices.asStateFlow()

    fun fetchInvoices() {
        viewModelScope.launch {
            getInvoicesUseCase(Unit)
                .onStart {
                    _invoices.value = ResponseWrapper.Loading
                }
                .catch {
                    _invoices.value = ResponseWrapper.Error(message = it.localizedMessage)
                }
                .collectLatest {
                    when (it) {
                        is ResponseWrapper.Success -> _invoices.value =
                            ResponseWrapper.Success(data = it.data)
                        is ResponseWrapper.Error -> _invoices.value =
                            ResponseWrapper.Error(message = it.message)
                        else -> _invoices.value = ResponseWrapper.Loading
                    }
                }
        }
    }

    fun fetchInvoicesEmpty() {
        viewModelScope.launch {
            getInvoicesEmptyUseCase(Unit)
                .onStart {
                    _invoices.value = ResponseWrapper.Loading
                }
                .catch {
                    _invoices.value = ResponseWrapper.Error(message = it.localizedMessage)
                }
                .collectLatest {
                    when (it) {
                        is ResponseWrapper.Success ->
                            _invoices.value = ResponseWrapper.Success(data = it.data)
                        is ResponseWrapper.Error ->
                            _invoices.value = ResponseWrapper.Error(message = it.message)
                        else ->
                            _invoices.value = ResponseWrapper.Loading
                    }
                }
        }
    }

    fun fetchInvoicesMalformed() {
        viewModelScope.launch {
            getInvoicesMalformedUseCase(Unit)
                .onStart {
                    _invoices.value = ResponseWrapper.Loading
                }
                .catch {
                    if (it is HttpException) {
                        _invoices.value = ResponseWrapper.Error(
                            code = it.code(),
                            message = it.localizedMessage
                        )
                    }
                }
                .collectLatest {
                    when (it) {
                        is ResponseWrapper.Success ->
                            _invoices.value = ResponseWrapper.Success(data = it.data)
                        is ResponseWrapper.Error ->
                            _invoices.value = ResponseWrapper.Error(code = it.code, message = it.message)
                        else ->
                            _invoices.value = ResponseWrapper.Loading
                    }
                }
        }
    }
}
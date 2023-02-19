package tj.test.simpleinvoicestakehome.utils

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import tj.test.simpleinvoicestakehome.R
import tj.test.simpleinvoicestakehome.domain.model.Invoice
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

typealias Invoices = List<Invoice>

const val DEF_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"

fun String.convertToFormat(defPattern: String = DEF_PATTERN): String {
    val inputFormat = SimpleDateFormat(defPattern, Locale.getDefault())
    val date = inputFormat.parse(this)
    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    date?.let {
        return outputFormat.format(it)
    }

    return ""
}

fun Int.convertToDollar(): String {
    val dollarValue = this / 100.0
    return String.format("%.2f", dollarValue)
}

fun Context.showDialog(title: String? = null, message: String? = null){
    MaterialAlertDialogBuilder(this).apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton(this@showDialog.getString(R.string.action_ok)) { dialog, _ ->
            dialog.dismiss()
        }
        setCancelable(false)
        show()
    }
}

inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getSerializable(key) as? T
}

inline fun <reified T : Serializable> Intent.serializable(key: String) = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
}

@Suppress("InjectDispatcher")
interface FlowUseCase<in Input, Output> {
    /**
     * Executes the flow on Dispatchers.IO and wraps exceptions inside it into Result
     */
    operator fun invoke(param: Input): Flow<ResponseWrapper<Output>> =
        execute(param)
            .catch { e -> emit(ResponseWrapper.Error(message = e.localizedMessage)) }
            .flowOn(Dispatchers.IO)

    fun execute(param: Input): Flow<ResponseWrapper<Output>>
}

fun View.show() {
    this.isVisible = true
}

fun View.hide() {
    this.isVisible = false
}
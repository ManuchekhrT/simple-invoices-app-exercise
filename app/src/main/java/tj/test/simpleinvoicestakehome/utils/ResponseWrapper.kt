package tj.test.simpleinvoicestakehome.utils

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val data: T?) : ResponseWrapper<T>()
    data class Error(val code: Int? = null, val message: String? = null) : ResponseWrapper<Nothing>()
    object Loading : ResponseWrapper<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[message=$message]"
            Loading  -> "Loading"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val ResponseWrapper<*>.succeeded
    get() = this is ResponseWrapper.Success && data != null

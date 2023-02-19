package tj.test.simpleinvoicestakehome.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import tj.test.simpleinvoicestakehome.R
import tj.test.simpleinvoicestakehome.databinding.ItemInvoiceDetailBinding
import tj.test.simpleinvoicestakehome.domain.model.InvoiceDetail

class InvoiceDetailItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding =
        ItemInvoiceDetailBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    fun setItem(item: InvoiceDetail) {
        with(binding) {
            tvName.text = item.name
            tvQuantity.text = String.format(
                context.getString(R.string.invoice_quantity),
                item.quantity.toString()
            )
            tvPrice.text = String.format(
                context.getString(R.string.invoice_price, item.priceInCents.toString()),
                item.priceInCents.toString()
            )
        }
    }
}
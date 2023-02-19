package tj.test.simpleinvoicestakehome.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import tj.test.simpleinvoicestakehome.R
import tj.test.simpleinvoicestakehome.databinding.ItemInvoiceBinding
import tj.test.simpleinvoicestakehome.domain.model.Invoice
import tj.test.simpleinvoicestakehome.utils.convertToDollar
import tj.test.simpleinvoicestakehome.utils.convertToFormat

class InvoiceItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding =
        ItemInvoiceBinding.inflate(LayoutInflater.from(context), this)

    private val invoiceAdapter = InvoiceDetailAdapter()

    init {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    fun setItem(item: Invoice, itemClickListener: (Invoice) -> Unit) {
        with(binding) {
            rvItems.adapter = invoiceAdapter
            invoiceAdapter.submitList(item.items)
            tvInvoiceDate.text = String.format(
                context.getString(R.string.invoice_date),
                item.date.convertToFormat()
            )
            tvInvoiceTotalPrice.text = String.format(
                context.getString(R.string.invoice_total_price),
                item.totalPrice.toString(),
                item.totalPrice.convertToDollar()
            )
            btnMore.setOnClickListener {
                itemClickListener.invoke(item)
            }
        }
    }
}
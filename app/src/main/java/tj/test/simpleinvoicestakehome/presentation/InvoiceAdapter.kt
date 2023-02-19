package tj.test.simpleinvoicestakehome.presentation

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tj.test.simpleinvoicestakehome.domain.model.Invoice

class InvoiceAdapter(private val itemClickListener: (Invoice) -> Unit) : ListAdapter<Invoice, InvoiceAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(InvoiceItemView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        (holder.itemView as InvoiceItemView).setItem(item, itemClickListener)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Invoice>() {
            override fun areItemsTheSame(
                oldItem: Invoice,
                newItem: Invoice
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Invoice,
                newItem: Invoice
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
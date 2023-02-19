package tj.test.simpleinvoicestakehome.presentation

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tj.test.simpleinvoicestakehome.domain.model.InvoiceDetail

class InvoiceDetailAdapter : ListAdapter<InvoiceDetail, InvoiceDetailAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(InvoiceDetailItemView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        (holder.itemView as InvoiceDetailItemView).setItem(item)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<InvoiceDetail>() {
            override fun areItemsTheSame(
                oldItem: InvoiceDetail,
                newItem: InvoiceDetail
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: InvoiceDetail,
                newItem: InvoiceDetail
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
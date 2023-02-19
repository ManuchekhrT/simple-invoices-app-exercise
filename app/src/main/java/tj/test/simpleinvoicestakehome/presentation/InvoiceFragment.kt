package tj.test.simpleinvoicestakehome.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import tj.test.simpleinvoicestakehome.R
import tj.test.simpleinvoicestakehome.databinding.FragmentInvoiceBinding
import tj.test.simpleinvoicestakehome.databinding.ItemInvoiceBinding
import tj.test.simpleinvoicestakehome.domain.model.Invoice
import tj.test.simpleinvoicestakehome.utils.convertToDollar
import tj.test.simpleinvoicestakehome.utils.convertToFormat
import tj.test.simpleinvoicestakehome.utils.hide
import tj.test.simpleinvoicestakehome.utils.serializable

@AndroidEntryPoint
class InvoiceFragment : Fragment() {

    private var _itemVoiceBinging: ItemInvoiceBinding? = null
    private val itemVoiceBinging get() = checkNotNull(_itemVoiceBinging)
    private var _binding: FragmentInvoiceBinding? = null
    private val binding get() = checkNotNull(_binding)

    private val invoiceAdapter = InvoiceDetailAdapter()
    private var invoice: Invoice? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            invoice = it.serializable(ARG_INVOICE) as Invoice?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInvoiceBinding.inflate(inflater, container, false)
        _itemVoiceBinging = ItemInvoiceBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(itemVoiceBinging) {
        btnMore.hide()
        rvItems.adapter = invoiceAdapter
        invoiceAdapter.submitList(invoice?.items)
        tvInvoiceDate.text = String.format(
            requireContext().getString(R.string.invoice_date),
            invoice?.date?.convertToFormat()
        )
        tvInvoiceTotalPrice.text = String.format(
            requireContext().getString(R.string.invoice_total_price),
            invoice?.totalPrice.toString(),
            invoice?.totalPrice?.convertToDollar()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_INVOICE = "arg_invoice"
    }
}
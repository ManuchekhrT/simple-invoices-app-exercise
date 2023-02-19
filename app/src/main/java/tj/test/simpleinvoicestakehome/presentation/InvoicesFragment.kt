package tj.test.simpleinvoicestakehome.presentation

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tj.test.simpleinvoicestakehome.R
import tj.test.simpleinvoicestakehome.databinding.FragmentInvoicesBinding
import tj.test.simpleinvoicestakehome.domain.model.Invoice
import tj.test.simpleinvoicestakehome.utils.ResponseWrapper
import tj.test.simpleinvoicestakehome.utils.hide
import tj.test.simpleinvoicestakehome.utils.show
import tj.test.simpleinvoicestakehome.utils.showDialog

@AndroidEntryPoint
class InvoicesFragment : Fragment() {

    private var _binding: FragmentInvoicesBinding? = null
    private val binding get() = checkNotNull(_binding)

    private val viewModel: InvoicesViewModel by viewModels()

    private lateinit var invoiceAdapter: InvoiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInvoicesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchInvoices()
        initView()
        initObservers()
        setupMenu()
    }

    private fun fetchInvoices() {
        viewModel.fetchInvoices()
    }

    private fun initView() = with(binding) {
        invoiceAdapter = InvoiceAdapter {
            onInvoiceItemClickListener(it)
        }
        rvInvoices.adapter = invoiceAdapter
    }

    private fun onInvoiceItemClickListener(item: Invoice) {
        findNavController().navigate(
            R.id.action_InvoicesFragment_to_InvoiceFragment,
            bundleOf(InvoiceFragment.ARG_INVOICE to item)
        )
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.invoices.collectLatest {
                    when (it) {
                        is ResponseWrapper.Loading -> {
                            binding.pbLoading.show()
                            binding.rvInvoices.hide()
                        }
                        is ResponseWrapper.Success -> {
                            binding.pbLoading.hide()
                            binding.rvInvoices.show()
                            if (it.data.isNullOrEmpty()) {
                                requireContext().showDialog(
                                    title = resources.getString(R.string.empty_list)
                                )
                            } else {
                                invoiceAdapter.submitList(it.data)
                            }
                        }
                        is ResponseWrapper.Error -> {
                            binding.pbLoading.hide()
                            binding.rvInvoices.hide()
                            requireContext().showDialog(
                                title = resources.getString(
                                    R.string.unexpected_error,
                                    it.code?.toString() ?: ""
                                ),
                                message = it.message
                            )
                        }
                    }
                }
            }
        }
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_update -> {
                        viewModel.fetchInvoices()
                        true
                    }
                    R.id.action_full_invoices -> {
                        viewModel.fetchInvoices()
                        true
                    }
                    R.id.action_empty_invoices -> {
                        viewModel.fetchInvoicesEmpty()
                        true
                    }
                    R.id.action_malformed_invoices -> {
                        viewModel.fetchInvoicesMalformed()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
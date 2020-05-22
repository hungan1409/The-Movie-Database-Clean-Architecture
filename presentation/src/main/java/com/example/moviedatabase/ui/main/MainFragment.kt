package com.example.moviedatabase.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.moviedatabase.BR
import com.example.moviedatabase.R
import com.example.moviedatabase.base.BaseFragment
import com.example.moviedatabase.databinding.FragmentMainBinding
import com.example.moviedatabase.util.autoCleared

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_main

    override val viewModel: MainViewModel by viewModels { viewModelFactory }

    private var mainAdapter by autoCleared<MainAdapter>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        subscribeUI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MainAdapter() { item ->
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToContributorFragment(
                    item
                )
            )
        }
        this.mainAdapter = adapter

        with(viewDataBinding) {
            listRepo.adapter = mainAdapter
        }
    }

    private fun subscribeUI() = with(viewModel) {
        data.observe(viewLifecycleOwner) {
            mainAdapter.submitList(it)
        }

        loading.observe(viewLifecycleOwner) { loading ->
            viewDataBinding.loading.visibility = if (loading) View.VISIBLE else View.GONE
        }
    }
}

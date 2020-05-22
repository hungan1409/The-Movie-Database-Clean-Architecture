package com.example.moviedatabase.ui.contributor

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.example.moviedatabase.BR
import com.example.moviedatabase.R
import com.example.moviedatabase.base.BaseFragment
import com.example.moviedatabase.databinding.FragmentContributorBinding
import com.example.moviedatabase.util.autoCleared

class ContributorFragment : BaseFragment<FragmentContributorBinding, ContributorViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel: ContributorViewModel by viewModels { viewModelFactory }

    override val layoutId: Int = R.layout.fragment_contributor

    private val args by navArgs<ContributorFragmentArgs>()

    private var contributorAdapter by autoCleared<ContributorAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.repoItem.value = args.repo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ContributorAdapter { contribute ->
        }
        this.contributorAdapter = adapter

        with(viewDataBinding) {
            listContributor.adapter = contributorAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUI()
    }

    private fun subscribeUI() = with(viewModel) {
        getContributions().observe(viewLifecycleOwner) { contributions ->
            contributorAdapter.submitList(contributions)
        }
    }
}

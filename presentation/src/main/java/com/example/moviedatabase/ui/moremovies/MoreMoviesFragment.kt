package com.example.moviedatabase.ui.moremovies

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.moviedatabase.R
import com.example.moviedatabase.base.BaseFragment
import com.example.moviedatabase.databinding.FragmentMoreMoviesBinding

class MoreMoviesFragment : BaseFragment<FragmentMoreMoviesBinding, MoreMoviesViewModel>() {
    override val layoutId: Int = R.layout.fragment_movie_detail

    override val viewModel: MoreMoviesViewModel by viewModels { viewModelFactory }

    private val args: MoreMoviesFragment by navArgs()
}
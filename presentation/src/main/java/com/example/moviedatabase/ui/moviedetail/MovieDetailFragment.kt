package com.example.moviedatabase.ui.moviedetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.moviedatabase.R
import com.example.moviedatabase.base.BaseFragment
import com.example.moviedatabase.databinding.FragmentMovieDetailBinding
import com.example.moviedatabase.extension.singleClickListener
import com.example.moviedatabase.ui.moviedetail.adapter.MovieCreditAdapter
import com.example.moviedatabase.ui.moviedetail.adapter.MovieVideoAdapter

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>() {

    private var movieVideoAdapter: MovieVideoAdapter? = null
    private var movieCreditAdapter: MovieCreditAdapter? = null

    override val layoutId: Int = R.layout.fragment_movie_detail

    override val viewModel: MovieDetailViewModel by viewModels { viewModelFactory }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            movieVideosItem.observe(viewLifecycleOwner, Observer { movieVideosItem ->
                movieVideosItem.results?.let { movieVideoAdapter?.submitList(it) }
            })

            movieCreditsItem.observe(viewLifecycleOwner, Observer { movieCreditsItem ->
                movieCreditsItem.castItem?.let { movieCreditAdapter?.submitList(it) }
            })

            getMovieDetail(419704)
            getMovieVideos(419704)
            getMovieCredits(419704)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.apply {
            layoutInformation.buttonBack.singleClickListener({
                findNavController().navigateUp()
            })

            layoutYourRate.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
                viewModel?.rating?.value = rating
            }

            movieVideoAdapter = MovieVideoAdapter()
            layoutVideo.recyclerVideo.adapter = movieVideoAdapter

            movieCreditAdapter = MovieCreditAdapter()
            layoutSeriesCast.recyclerSeriesCast.adapter = movieCreditAdapter
        }
    }
}

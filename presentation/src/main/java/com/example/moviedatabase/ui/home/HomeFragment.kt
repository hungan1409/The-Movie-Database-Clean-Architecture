package com.example.moviedatabase.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.moviedatabase.R
import com.example.moviedatabase.base.BaseFragment
import com.example.moviedatabase.databinding.FragmentHomeBinding
import com.example.moviedatabase.ui.home.adapter.PopularAdapter
import com.example.moviedatabase.ui.home.adapter.TopRatedAdapter
import com.example.moviedatabase.ui.home.adapter.UpcomingAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override val layoutId: Int = R.layout.fragment_home

    private var popularsAdapter: PopularAdapter? = null
    private var topRatedAdapter: TopRatedAdapter? = null
    private var upcomingAdapter: UpcomingAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularsAdapter = PopularAdapter()
        topRatedAdapter = TopRatedAdapter()
        upcomingAdapter = UpcomingAdapter()
        viewModel.apply {
            moviesPopulars.observe(viewLifecycleOwner, Observer {
                popularsAdapter?.submitList(it)
            })

            moviesTopRated.observe(viewLifecycleOwner, Observer {
                topRatedAdapter?.submitList(it)
            })

            moviesUpcoming.observe(viewLifecycleOwner, Observer {
                upcomingAdapter?.submitList(it)
            })

            moviesGenres.observe(viewLifecycleOwner, Observer {

            })

            moviesRecommendations.observe(viewLifecycleOwner, Observer {

            })
            viewDataBinding.apply {
                recyclerPopular.adapter = popularsAdapter
                recyclerTopRated.adapter = topRatedAdapter
                recyclerUpcoming.adapter = upcomingAdapter
            }
            getMovieListPopular(1)
            getMovieListTopRated(1)
            getMovieListUpcoming(1)
        }
    }
}

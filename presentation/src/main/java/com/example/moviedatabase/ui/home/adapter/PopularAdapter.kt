package com.example.moviedatabase.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.example.moviedatabase.R
import com.example.moviedatabase.base.BaseRecyclerAdapter
import com.example.moviedatabase.databinding.ItemMovieBinding
import com.example.moviedatabase.model.MovieItem

class PopularAdapter : BaseRecyclerAdapter<MovieItem>(

    callBack = object : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MovieItem,
            newItem: MovieItem
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }) {
    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding {
        return DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie, parent, false
        )
    }

    override fun bind(binding: ViewDataBinding, movieItem: MovieItem) {
        if (binding is ItemMovieBinding) {
            binding.item = movieItem
        }
    }
}
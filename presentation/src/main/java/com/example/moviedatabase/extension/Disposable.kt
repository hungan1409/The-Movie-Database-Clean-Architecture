package com.example.moviedatabase.extension

import com.example.moviedatabase.base.BaseViewModel
import io.reactivex.disposables.Disposable

fun Disposable.add(viewModel: BaseViewModel) {
    viewModel.addDisposable(this)
}
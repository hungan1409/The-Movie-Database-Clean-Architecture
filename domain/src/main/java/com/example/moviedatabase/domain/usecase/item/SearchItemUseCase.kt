package com.example.moviedatabase.domain.usecase.item

import com.example.moviedatabase.domain.model.Item
import com.example.moviedatabase.domain.repository.ItemRepository
import com.example.moviedatabase.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

open class SearchItemUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) : UseCase<SearchItemUseCase.Params, Single<List<Item>>>() {
    override fun createObservable(params: Params?): Single<List<Item>> {
        params?.let {
            return itemRepository.searchItems(
                query = params.query,
                page = params.pageNumber
            )
        }

        return Single.error(Throwable(""))
    }

    override fun onCleared() {
    }

    data class Params(val query: String, val pageNumber: Int? = 1)
}

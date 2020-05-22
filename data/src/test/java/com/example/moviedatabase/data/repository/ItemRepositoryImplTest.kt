package com.example.moviedatabase.data.repository

import com.example.moviedatabase.data.ItemRepositoryImpl
import com.example.moviedatabase.data.createItemEntity
import com.example.moviedatabase.data.model.ItemEntityMapper
import com.example.moviedatabase.data.model.OwnerEntityMapper
import com.example.moviedatabase.data.remote.api.ItemApi
import com.example.moviedatabase.data.remote.response.SearchRepoResponse
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ItemRepositoryImplTest {
    private lateinit var itemRepositoryImpl: ItemRepositoryImpl
    private val ownerEntityMapper = OwnerEntityMapper()
    private val itemEntityMapper = ItemEntityMapper(ownerEntityMapper)
    private val itemApi = mock(ItemApi::class.java)

    @Before
    fun setup() {
        itemRepositoryImpl = ItemRepositoryImpl(itemApi, itemEntityMapper)
    }

    @Test
    fun searchComplete() {
        val query = anyString()
        val page = anyInt()

        val searchRepoResponse = SearchRepoResponse(total = 1, items = listOf(createItemEntity()))
        `when`(itemApi.searchRepos(query, page)).thenReturn(Single.just(searchRepoResponse))
        itemRepositoryImpl.searchItems(query, page).test().assertComplete()
    }

    @Test
    fun searchItems() {
        val query = anyString()
        val page = anyInt()

        val searchRepoResponse = SearchRepoResponse(total = 1, items = listOf(createItemEntity()))
        `when`(itemApi.searchRepos(query, page)).thenReturn(Single.just(searchRepoResponse))

        val test = itemRepositoryImpl.searchItems(query, page).test()
        test.assertValue { items ->
            items == searchRepoResponse.items.map { itemEntityMapper.mapToDomain(it) }
        }
    }

    @Test
    fun searchReturnNull() {
        val query = "query"
        val page = 1
        val errorMessage = "Error"
        `when`(itemApi.searchRepos(query, page)).thenReturn(Single.error(Throwable(errorMessage)))

        val test = itemRepositoryImpl.searchItems(query, page).test()
        test.assertError { it.message == errorMessage }
    }
}
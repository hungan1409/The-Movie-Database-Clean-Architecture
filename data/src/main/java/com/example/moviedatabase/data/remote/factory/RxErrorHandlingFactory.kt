package com.example.moviedatabase.data.remote.factory

import com.example.moviedatabase.data.remote.exception.RetrofitException
import io.reactivex.*
import io.reactivex.functions.Function
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type


/**
 * RxErrorHandlingFactory for RxJava
 */
class RxErrorHandlingFactory : CallAdapter.Factory() {

    private val instance = RxJava2CallAdapterFactory.createAsync()

    @Suppress("UNCHECKED_CAST")
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? = RxCallAdapterWrapper(
        instance.get(returnType, annotations, retrofit) as CallAdapter<Any, Any>, retrofit
    )
}

class RxCallAdapterWrapper<R>(
    private val wrapped: CallAdapter<R, Any>,
    private val retrofit: Retrofit
) : CallAdapter<R, Any> {

    override fun responseType(): Type = wrapped.responseType()

    override fun adapt(call: Call<R>): Any {
        return when (val result = wrapped.adapt(call)) {
            is Single<*> -> {
                result.onErrorResumeNext(Function<Throwable, SingleSource<Nothing>> { throwable ->
                    Single.error(convertToRetrofitException(throwable))
                })
            }

            is Observable<*> -> {
                result.onErrorResumeNext(Function<Throwable, ObservableSource<Nothing>> { throwable ->
                    Observable.error(convertToRetrofitException(throwable))
                })
            }

            is Completable -> {
                result.onErrorResumeNext { throwable ->
                    Completable.error(convertToRetrofitException(throwable))
                }
            }

            is Flowable<*> -> {
                result.onErrorResumeNext(Function<Throwable, Flowable<Nothing>> { throwable ->
                    Flowable.error(convertToRetrofitException(throwable))
                })
            }

            is Maybe<*> -> {
                result.onErrorResumeNext(Function<Throwable, Maybe<Nothing>> { throwable ->
                    Maybe.error(convertToRetrofitException(throwable))
                })
            }

            else -> result
        }
    }

    private fun convertToRetrofitException(throwable: Throwable): RetrofitException =
        when (throwable) {
            is RetrofitException -> throwable

            is IOException -> RetrofitException.networkError(throwable)

            is HttpException -> {
                val response = throwable.response()
                val url = response.raw().request().url().toString()

                if (response?.errorBody() != null) {
                    RetrofitException.httpErrorWithObject(
                        url = url,
                        response = response,
                        retrofit = retrofit
                    )
                } else {
                    RetrofitException.httpError(
                        url = url,
                        response = response,
                        retrofit = retrofit
                    )
                }
            }

            else -> RetrofitException.unexpectedError(throwable)
        }
}





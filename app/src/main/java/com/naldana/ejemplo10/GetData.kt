package com.naldana.ejemplo10
import retrofit2.Retrofit
import retrofit2.http.GET
import java.util.*

interface GetData {

    @GET ("prices?key=5a15fd67")
    fun getData() :Observable<List<RetroCrypto>>
}
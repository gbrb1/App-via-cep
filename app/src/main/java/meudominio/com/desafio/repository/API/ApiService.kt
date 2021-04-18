package meudominio.com.desafio.repository.API

import retrofit2.Response
import retrofit2.http.*

interface ApiService {


    @GET("26525045/json")

    suspend fun getEndereco(
        @Query("cep") cep : String,
    ): Response<ApiDataResponse>


}
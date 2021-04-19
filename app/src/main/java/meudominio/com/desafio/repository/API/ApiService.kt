package meudominio.com.desafio.repository.API

import retrofit2.Response
import retrofit2.http.*

interface ApiService {


    @GET("{cep}/json")

    suspend fun getEndereco(
            @Path("cep") cep : String,
    ): Response<ApiDataResponse>


}
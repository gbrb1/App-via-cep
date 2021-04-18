package meudominio.com.desafio.repository

//import meudominio.com.desafio.repository.API.ApiDataResponse
import meudominio.com.desafio.repository.API.RestApi

class CepRepository(private val restApi : RestApi) {



    suspend fun getEndereco(cep : String): String? {
        val res = restApi.getApiService().getEndereco(cep)

        when(res.code()) {
            200, 204 -> {

                //val response = ApiDataResponse(res.body()?.logradouro, res.body()?.bairro, res.body()?.localidade)

                return res.body()?.localidade
            }
            400 -> throw Exception("Erro ao fazer requisição.")
            401 -> throw Exception("Usuário não autenticado.")
            403 -> throw Exception("Sem permissão.")
            500 -> throw Exception("Erro 500")
            else -> throw Exception("Erro HTTP ${res.code()}.")
        }
    }

}
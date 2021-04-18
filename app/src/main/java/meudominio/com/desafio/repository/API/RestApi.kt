package meudominio.com.desafio.repository.API

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

class RestApi {

    private fun getClient(): OkHttpClient.Builder {
        val clienteHttp = OkHttpClient.Builder()

        clienteHttp.addInterceptor { chain ->
            val pedido =
                chain.request().newBuilder()
                    .build()
            chain.proceed(pedido)
        }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
        return clienteHttp
    }

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .client(getClient().build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    fun getApiService() = retrofit.create(ApiService::class.java)
}
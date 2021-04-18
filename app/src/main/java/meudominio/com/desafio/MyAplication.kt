package meudominio.com.desafio

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.FlowPreview
import meudominio.com.desafio.utils.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import meudominio.com.desafio.utils.repositoryModule
import meudominio.com.desafio.utils.viewModelModulo

val myModule = listOf<Module>(repositoryModule, apiModule, viewModelModulo)

class MyApplication: Application() {

    private lateinit var intentService : Intent

    companion object {
        lateinit var instance: MyApplication
        var temInternet: Boolean = true
        var executando = true
        var tempoSleeper : Long = 100000 // 1 minuto
    }

    init {
        instance = this
        when("dev"){
            "dev" -> tempoSleeper = 5000 // 5 segundos
        }
    }



    @FlowPreview
    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            androidContext(this@MyApplication)
            modules(myModule)
        }
        val connectivityManager = this.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                executando = false
                temInternet = false
                //record wi-fi disconnect event
            }
            override fun onUnavailable() {
                executando = false
                temInternet = false
            }
            override fun onLosing(network: Network, maxMsToLive: Int) {
                executando = false
                temInternet = false
            }
            override fun onAvailable(network: Network) {
                executando = true
                temInternet = true
//                startService(intentService)
            }
        }

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }
}
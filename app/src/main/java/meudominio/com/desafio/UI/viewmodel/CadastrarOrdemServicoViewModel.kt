package meudominio.com.desafio.UI.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import meudominio.com.desafio.common.viewmodel.Response
import java.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meudominio.com.desafio.repository.CepRepository

class CadastrarOrdemServicoViewModell(val repository : CepRepository) : ViewModel() {

    private val response = MutableLiveData<Response>()



    fun getEndereco(cep : String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val responseApi =  repository.getEndereco(cep)
                response.postValue(Response.success(responseApi))
            } catch (t: Throwable) {
                response.postValue(Response.error(t))
            }
        }
    }

    fun response(): MutableLiveData<Response> {
        return response
    }



}
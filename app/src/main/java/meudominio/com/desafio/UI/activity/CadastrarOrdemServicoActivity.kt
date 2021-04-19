package meudominio.com.desafio.UI.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_cadastrar_ordem_servico.*
import meudominio.com.desafio.R
import meudominio.com.desafio.UI.viewmodel.CadastrarOrdemServicoViewModell
import meudominio.com.desafio.common.viewmodel.Response
import meudominio.com.desafio.common.viewmodel.Status
import meudominio.com.desafio.repository.API.ApiDataResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastrarOrdemServicoActivity : AppCompatActivity() {

    val viewModel : CadastrarOrdemServicoViewModell by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_ordem_servico)

        botao_cep.setOnClickListener{

            val cep = edit_text_cep.text.toString()

            if(cep.isEmpty()){
                Toast.makeText(applicationContext,"Insira um CEP",Toast.LENGTH_SHORT).show()
            } else  {

                viewModel.getEndereco(cep)
            }
        }

        viewModel.response().observe(this, Observer { response -> processResponse(response) })
    }

    fun processResponse(response: Response) {

        val a = response
        println(a)

        when (response.status) {
            Status.LOADING -> println("status.loading")
            Status.SUCCESS -> responseSuccess(response.data)
            Status.ERROR -> responseFailure(response.error)
            else -> throw Exception("Process response")
        }
    }

    private fun responseFailure(error: Throwable?) {

        Toast.makeText(applicationContext,"Erro ao consultar CEP",Toast.LENGTH_SHORT).show()

    }

    private fun responseSuccess(address: Any?) {

       address as ApiDataResponse

        edit_text_endereco.setText(address.logradouro.toString())
        edit_text_bairro.setText(address.bairro.toString())
        edit_text_cidade.setText(address.localidade.toString())
    }




}
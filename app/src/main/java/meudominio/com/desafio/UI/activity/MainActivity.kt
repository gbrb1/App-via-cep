package meudominio.com.desafio.UI.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import meudominio.com.desafio.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sem_ordem.visibility = View.GONE

        adicionar_ordem_servico.setOnClickListener{
            intent = Intent(applicationContext, CadastrarOrdemServicoActivity::class.java)
            startActivity(intent)
        }
    }


}
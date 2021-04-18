package meudominio.com.desafio.utils


import kotlinx.coroutines.FlowPreview
import meudominio.com.desafio.UI.viewmodel.CadastrarOrdemServicoViewModell
import meudominio.com.desafio.repository.API.RestApi
import meudominio.com.desafio.repository.CepRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
val repositoryModule = module{

    single {CepRepository(get()) }
}

val apiModule = module {

    single { RestApi() }
}

@FlowPreview
val viewModelModulo = module {

    viewModel { CadastrarOrdemServicoViewModell( get() ) }
//val utilsModel = module{
    //single { Utils() }
}
package br.com.carvalho.demoaacretrofit.ui.mainscreen

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.carvalho.demoaacretrofit.entities.EnderecoResponse
import br.com.carvalho.demoaacretrofit.repositories.EnderecoRepository
import br.com.carvalho.demoaacretrofit.repositories.EnderecoRespositoryImpl

class MainViewModel : ViewModel() {
    val isLoading : MutableLiveData<Boolean> = MutableLiveData()
    private val enderecoRepository: EnderecoRepository
    private val mapiResponse: MediatorLiveData<EnderecoResponse> = MediatorLiveData()

    val apiResponse : LiveData<EnderecoResponse>
        get() = mapiResponse

    init {
        enderecoRepository = EnderecoRespositoryImpl()
    }
  fun pesquisarEndereco(cep: String): LiveData<EnderecoResponse>{
      isLoading.postValue(true)
      mapiResponse.addSource(enderecoRepository.buscarEndereco(cep)){
          apiResponse -> mapiResponse.value = apiResponse
          isLoading.postValue(false)
      }
    return  mapiResponse
  }
}

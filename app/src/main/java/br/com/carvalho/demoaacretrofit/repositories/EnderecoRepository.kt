package br.com.carvalho.demoaacretrofit.repositories

import android.arch.lifecycle.LiveData
import br.com.carvalho.demoaacretrofit.entities.EnderecoResponse

interface EnderecoRepository {

    fun buscarEndereco(cep: String): LiveData<EnderecoResponse>
}
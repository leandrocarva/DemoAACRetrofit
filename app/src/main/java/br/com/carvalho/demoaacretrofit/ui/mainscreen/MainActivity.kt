package br.com.carvalho.demoaacretrofit.ui.mainscreen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.carvalho.demoaacretrofit.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        btPesquisar.setOnClickListener{
            mainViewModel.pesquisarEndereco(etCEP.text.toString())
        }

        mainViewModel.apiResponse.observe(this, Observer{
            apiResponse ->
            if (apiResponse?.erro == null) {
                Log.i("TAG","SUCESSO")
                tvResultado.text = "Endereço: ${apiResponse?.endereco?.logradouro}\n" +
                        "Complemento: ${apiResponse?.endereco?.complemento}\n" +
                        "Bairro: ${apiResponse?.endereco?.bairro}\n" +
                        "Cidade: ${apiResponse?.endereco?.localidade}\n" +
                        "UF: ${apiResponse?.endereco?.uf}"
            } else {
                Log.i("TAG", "ERRO: ${apiResponse.erro}")
            }
        })
    }
}

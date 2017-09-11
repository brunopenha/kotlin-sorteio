package br.nom.penha.bruno.sorteio

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import java.util.*

class PrincipalActivity : AppCompatActivity() {


    var numeros = arrayOf(QuantidadeSorteado(0, 0.0, 0.0f),
                          QuantidadeSorteado(1, 0.0, 0.0f),
                          QuantidadeSorteado(2, 0.0, 0.0f),
                          QuantidadeSorteado(3, 0.0, 0.0f),
                          QuantidadeSorteado(4, 0.0, 0.0f),
                          QuantidadeSorteado(5, 0.0, 0.0f),
                          QuantidadeSorteado(6, 0.0, 0.0f),
                          QuantidadeSorteado(7, 0.0, 0.0f),
                          QuantidadeSorteado(8, 0.0, 0.0f),
                          QuantidadeSorteado(9, 0.0, 0.0f),
                          QuantidadeSorteado(10,0.0, 0.0f))

    var quantidades : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
    }

    fun sortearNumero(view: View){

        var texto = findViewById<TextView>(R.id.textoSorteio) as TextView
        var textoMaisSorteado = findViewById<TextView>(R.id.textoMaisSorteado) as TextView
        var numeroSorteado = Random().nextInt(numeros.size)

        numeros[numeroSorteado].quantidade = numeros[numeroSorteado].quantidade.inc()

        var num = numeros[numeroSorteado].numero
        var qtd = numeros[numeroSorteado].quantidade


        texto.setText("Número sorteado é: $num ele foi sorteado ${qtd.toInt()} ")

        quantidades = quantidades.inc()

        var maiorPercentual: Float = 0f
        var maisSortados: MutableList<Int> = mutableListOf<Int>()

        Log.i("Botao","***************************")
        for (i in 0..numeros.size - 1) {
            var qtd = numeros[i].quantidade
            var percentual : Float = (( qtd.div(quantidades))).toFloat() * 100
            numeros[i].percentual = percentual
            Log.i("Botao", "Numeros sorteados ${numeros[i].numero} foi soteado em  ${percentual} %")
            if(maiorPercentual <= percentual){
                maiorPercentual = percentual

                var j: Int = 0
                while (j < maisSortados.size){
                    if(numeros[maisSortados[j]].percentual < maiorPercentual){
                        maisSortados.remove(maisSortados[j])
                        j = 0
                    }else{
                        ++j
                    }


                }
                maisSortados.add(numeros[i].numero)

            }
        }
        Log.i("Botao","***************************")

        var maisSorteado : String = "E o numero mais sorteado é o ${maisSortados.toList()} com a probabilidade de ${maiorPercentual}% em ${quantidades} sorteios"
        Log.i("Botao",maisSorteado)
        textoMaisSorteado.setText(maisSorteado)
        Log.i("Botao","***************************")


    }
}

class QuantidadeSorteado (var numeroParam: Int, var quantidadeParam:Double, var percentualParam: Float){

    var numero: Int = numeroParam
    var quantidade: Double = quantidadeParam
    var percentual: Float = percentualParam

}

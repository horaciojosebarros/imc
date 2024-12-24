package com.example.app20241224

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@SuppressLint("WrongViewCast")
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textViewTitle)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val button: Button = findViewById(R.id.buttonSubmit)

        button.setOnClickListener {
            buttonAction()
        }
    }

    private fun buttonAction() {
        try {
            val pesoEditText = findViewById<EditText>(R.id.editTextPeso)
            val pesoString = pesoEditText.text.toString()
            val peso = pesoString.toInt()

            val heightEditText = findViewById<EditText>(R.id.editTextHeight)
            val alturaString = heightEditText.text.toString()
            val altura = alturaString.toDouble()

            val resultTextView: TextView = findViewById(R.id.editTextView)
            var laudo = "";

            if (altura > 0) {
                // Calculando o IMC
                val imc = peso / (altura * altura)

                Log.e("Imc", imc.toString())

                /* Exibindo o resultado
                Peso normal: IMC entre 18,5 e 24,9
                Sobrepeso: IMC entre 25,0 e 29,9
                Obesidade grau 1 (moderada): IMC entre 30,0 e 34,9
                Obesidade grau 2 (severa): IMC entre 35,0 e 39,9
                Obesidade grau 3 (mórbida): IMC acima de 40,0*/
                if (imc < 18.5) {
                    laudo = "Abaixo do peso "
                } else if (imc < 25) {
                    laudo = "Peso Normal "
                } else if (imc < 30) {
                    laudo = "Sobrepeso "
                } else if (imc < 35) {
                    laudo = "Obesidade grau 1 (moderada) "
                } else if (imc < 40) {
                    laudo = "Obesidade grau 2 (severa) "
                } else if (imc >= 40) {
                    laudo = "Obesidade grau 3 (mórbida) "

                }
                resultTextView.text = "Seu IMC é: %.2f".format(imc) + " --> " + laudo
                Log.e("Result", resultTextView.text.toString())

            } else {
                resultTextView.text = "Por favor, insira valores válidos."
            }
        } catch (e: Exception) {
            e.stackTrace
            Log.e("Erro", e.message.toString())


        }
    }
}
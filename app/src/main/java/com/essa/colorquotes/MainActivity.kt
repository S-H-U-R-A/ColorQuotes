package com.essa.colorquotes

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        randomQuote(mainLayout);
    }

    public fun randomQuote(view: View){
        //Se crea la instamcia de la clase generadora de citas
        //Esta fue remplazada por un objeto Singleton
        //val quoteGenerator: QuoteGenerator = QuoteGenerator();
        //Ahora se obtiene una cita
        val quote = QuoteGenerator.getQuote();
        //Se cambia el valor de los textView por el retornado de la cita aleatoria
        quoteTextView.text = quote.text;
        autorTextView.text = quote.author;
        mainLayout.background = getRandomColor();
        newQuoteButton.background = mainLayout.background;
    }

    //El metodo obtiene un color de la carpeta res y retorna un objeto color
    private fun getRandomColor(): ColorDrawable{
        val rainbow = resources.getIntArray(R.array.rainbow)
        return ColorDrawable(rainbow.random())
    }


}

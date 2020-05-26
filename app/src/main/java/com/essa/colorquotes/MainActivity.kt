package com.essa.colorquotes

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Esto suele hacerse para identificar los errores que uno determine de cual activity o archivo vienen
    //this.javaClass.simpleName nos retorna el nombre de la clase
    private  val TAG = this.javaClass.simpleName

    private var quoteAuthor:String? = null
    private var quoteText: String? = null

    private var color: Int? = null

    companion object{
        private const val  QUOTE_AUTHOR: String = "QUOTE_AUTHOR";
        private const val  QUOTE_TEXT: String = "QUOTE_TEXT";
        private const val  QUOTE_COLOR: String = "QUOTE_COLOR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        randomQuote(mainLayout);
        //En este metodo onCreate() tambien podemos intentar recuperar data que este en el bundle
        /*if(savedInstanceState != null){
            this.quoteAuthor = savedInstanceState?.getString("QUOTE_AUTHOR") //?: "No hay data"
            this.quoteText = savedInstanceState?.getString("QUOTE_TEXT");
            this.updateUi();
        }*/
        //Este metodo usando let comprueba que el objeto no sea nulo funciona perfectamente
        /*savedInstanceState?.let {
            this.quoteAuthor = savedInstanceState?.getString(QUOTE_AUTHOR) //?: "No hay data"
            this.quoteText = savedInstanceState?.getString(QUOTE_TEXT);
            this.color = savedInstanceState.getInt(QUOTE_COLOR);
            this.updateUi();
        }*/

    }

    //Este metodo lo que hace es guardar el estado de la app y recibe
    //un objeto bundle en el cual se guarda la info
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(QUOTE_AUTHOR, this.quoteAuthor)
        outState.putString(QUOTE_TEXT, this.quoteText)
        this.color?.let { outState.putInt(QUOTE_COLOR, it) }
        //Un parcelable es como meter una clase kotlin en una cajita para
        //que despues el compilador sepa como sacarla
    }

    //Con este metodo se restaura los valores que previamente se guardaron
    //con el metodo anterior
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        this.quoteAuthor = savedInstanceState.getString(QUOTE_AUTHOR);
        this.quoteText = savedInstanceState.getString(QUOTE_TEXT);
        this.color = savedInstanceState.getInt(QUOTE_COLOR)
        this.updateUi();
    }

    public fun randomQuote(view: View){
        //Se crea la instamcia de la clase generadora de citas
        //Esta fue remplazada por un objeto Singleton
        //val quoteGenerator: QuoteGenerator = QuoteGenerator();
        //Ahora se obtiene una cita
        val quote   = QuoteGenerator.getQuote();
        this.quoteAuthor = quote.author
        this.quoteText   = quote.text
        this.color = getRandomColor()
        //Se llama la funcion de actualizar la UI
        //Esto se hace por situaciones como que al girar la pantalla se pierden
        //los datos que se tienen y se resetea la app
        this.updateUi()
    }

    private fun updateUi(){
        //Se cambia el valor de los textView por el retornado de la cita aleatoria
        quoteTextView.text = this.quoteText
        autorTextView.text = this.quoteAuthor
        color?.let{
            mainLayout.setBackgroundColor(it)
            newQuoteButton.setBackgroundColor(it)
        }
    }

    //El metodo obtiene un color de la carpeta res y retorna un objeto color
    private fun getRandomColor(): Int{
        val rainbow = resources.getIntArray(R.array.rainbow)
        //Ejemplo Usar LOGS
        Log.e(TAG, "Funcion")
        return rainbow.random()
    }


}

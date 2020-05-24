package com.essa.colorquotes

// Se crea un singleton, es decir la clase solo se crea una vez
object QuoteGenerator {
    private val quoteList = listOf<Quote>(
        Quote("Somos nuestras desiciones", "A. Dumbledore"),
        Quote("Stay hungry, stay foolfish", "S. Jobs"),
        Quote("Ojo por ojo y el mundo quedara ciego", "M. Gandhi"),
        Quote("El mundo esta lleno de cosas obvias que nunca nadie observa", "S. Holmes"),
        Quote("La imaginación es mas importante que el conocimiento", "A. Esistein")
    );

    public fun getQuote(): Quote = quoteList.random();

}
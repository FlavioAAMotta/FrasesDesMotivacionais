package com.example.frasesmotivacionais

import kotlin.random.Random

data class Phrase(val description: String, val categoryId: Int)

class Mock {
    private val all = 1
    private val happy = 2
    private val sunny = 3

    private val myListPhrase = listOf<Phrase>(
        Phrase("Não sabendo que era impossível, foi lá e descobriu", sunny),
        Phrase("Primeira frase", happy),
        Phrase("Segunda frase", happy),
        Phrase("Terceira frase", sunny)
    )

    fun getPhrase(value: Int): String {

        val filtered = myListPhrase.filter{it.categoryId == value || value == all}
        val index = Random.nextInt(filtered.size)
        return filtered[index].description
    }
}
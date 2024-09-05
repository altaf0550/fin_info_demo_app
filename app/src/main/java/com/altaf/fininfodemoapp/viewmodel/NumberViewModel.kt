package com.altaf.fininfodemoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.altaf.fininfodemoapp.model.NumberItem
import kotlin.math.sqrt

class NumberViewModel : ViewModel() {

    private val _numbers = MutableLiveData<List<NumberItem>>()
    val numbers: LiveData<List<NumberItem>> get() = _numbers

    private val rules = listOf("Odd", "Even", "Prime", "Fibonacci")

    init {
        generateNumbers()
    }

    private fun generateNumbers() {
        _numbers.value = (1..100).map { NumberItem(it) }
    }

    fun updateHighlighting(rule: String) {
        val currentNumbers = _numbers.value?.toMutableList() ?: return
        currentNumbers.forEach { it.isHighlighted = false }

        when (rule) {
            "Odd" -> currentNumbers.forEach { if (it.value % 2 != 0) it.isHighlighted = true }
            "Even" -> currentNumbers.forEach { if (it.value % 2 == 0) it.isHighlighted = true }
            "Prime" -> currentNumbers.forEach { if (isPrime(it.value)) it.isHighlighted = true }
            "Fibonacci" -> {
                val fibonacciNumbers = generateFibonacci()
                currentNumbers.forEach {
                    if (fibonacciNumbers.contains(it.value)) it.isHighlighted = true
                }
            }
        }

        _numbers.value = currentNumbers
    }

    private fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        for (i in 2..sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }

    private fun generateFibonacci(): Set<Int> {
        val limit = 100
        val fibSet = mutableSetOf(0, 1)
        var a = 0
        var b = 1
        while (b <= limit) {
            val next = a + b
            a = b
            b = next
            fibSet.add(b)
        }
        return fibSet
    }

    fun getRules() = rules
}

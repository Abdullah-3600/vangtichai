package com.example.vangtichai

object NoteCalculator {
    val DENOMINATIONS = listOf(500, 100, 50, 20, 10, 5, 2, 1)

    fun calculateChange(amount: Long): Map<Int, Long> {
        val result = LinkedHashMap<Int, Long>()
        var remaining = amount
        for (note in DENOMINATIONS) {
            val count = remaining / note
            result[note] = count
            remaining %= note
        }
        return result
    }
}

package com.example.vangtichai

import org.junit.Assert.assertEquals
import org.junit.Test

class NoteCalculatorTest {

    @Test
    fun calculateChange_for234Taka_returnsCorrectNoteBreakdown() {
        val result = NoteCalculator.calculateChange(234L)

        assertEquals(0L, result[500])
        assertEquals(2L, result[100])
        assertEquals(0L, result[50])
        assertEquals(1L, result[20])
        assertEquals(1L, result[10])
        assertEquals(0L, result[5])
        assertEquals(2L, result[2])
        assertEquals(0L, result[1])
    }

    @Test
    fun calculateChange_for0Taka_returnsAllZero() {
        val result = NoteCalculator.calculateChange(0L)

        for (denom in NoteCalculator.DENOMINATIONS) {
            assertEquals(0L, result[denom])
        }
    }

    @Test
    fun calculateChange_for688Taka_returnsCorrectNoteBreakdown() {
        val result = NoteCalculator.calculateChange(688L)

        assertEquals(1L, result[500]) // 500
        assertEquals(1L, result[100]) // 100 -> 600
        assertEquals(1L, result[50])  // 50 -> 650
        assertEquals(1L, result[20])  // 20 -> 670
        assertEquals(1L, result[10])  // 10 -> 680
        assertEquals(1L, result[5])   // 5 -> 685
        assertEquals(1L, result[2])   // 2 -> 687
        assertEquals(1L, result[1])   // 1 -> 688
    }
}

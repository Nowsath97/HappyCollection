package com.example.happycollection

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class UtilsTest {

    @Test
    fun checkPrice() {
        val validPrice = Utils().getValidPrice(100.0)
        assertEquals(100.0, validPrice)

        val inValidPrice = Utils().getValidPrice(-100.0)
        assertEquals(false, inValidPrice)
    }

    @Test
    fun checkRatingCount() {
        val zeroCount = Utils().getValidCount(0)
        assertEquals(false, zeroCount)

        val inValidCount = Utils().getValidCount(-10)
        assertEquals(false, inValidCount)

        val validCount = Utils().getValidCount(120)
        assertEquals(120, validCount)
    }

    @Test
    fun checkRating() {
        val validRating = Utils().getRating(4.2F)
        assertEquals(4.2F, validRating)

        val inValidRating = Utils().getRating(6.2F)
        assertEquals(5.0F, inValidRating)
    }
}
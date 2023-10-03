package com.example.fahrenheitcelsius

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

class TempConverter{
    fun FahrentoC(Progress: Int): Double {
        return ((Progress - 32) * 5.0/9.0)
    }
    fun CelsitoF(Progress: Int): Double {
        return ((Progress * 9.0/5.0) + 32)
    }
}
class MainActivityTest{
    private lateinit var converter: TempConverter
    @BeforeEach
    fun setUp(){
        converter = TempConverter()
    }
    @Test
    fun testFtoC(){
        val cVal = converter.FahrentoC(32)
        assertEquals(0.0, cVal, 0.01)
    }

    @Test
    fun testCtoF(){
        val fVal = converter.CelsitoF(0)
        assertEquals(32.0, fVal, 0.01)
    }
}
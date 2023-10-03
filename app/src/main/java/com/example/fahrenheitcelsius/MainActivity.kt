package com.example.fahrenheitcelsius

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var Fbar: SeekBar
    private lateinit var FCounting: TextView
    private lateinit var Cbar: SeekBar
    private lateinit var CCounting: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Fbar = findViewById<SeekBar>(R.id.FBar)
        FCounting = findViewById<TextView>(R.id.FCount)

        Cbar = findViewById<SeekBar>(R.id.CBar)
        CCounting = findViewById<TextView>(R.id.CCount)

        Fbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean){
                val celsiusValue = FahrentoC(progress)

                    Cbar.progress = celsiusValue.toInt()
                    CCounting.text = "%.2f".format(celsiusValue)
                    FCounting.text = "%.2f".format(progress.toDouble())
            }
            override fun onStartTrackingTouch(seek: SeekBar) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
                val CelVal = CCounting.text.toString().toDouble()
                checkTemperature(CelVal)
                if (Fbar.progress < 32){
                    Fbar.progress = 32
                    FCounting.text = "32.00"
                    Cbar.progress = 0
                    CCounting.text = "0.00"
                }
            }
        })

        Cbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean){
                val fahrenheitValue = CelsitoF(progress)
                Fbar.progress = fahrenheitValue.toInt()
                FCounting.text = "%.2f".format(fahrenheitValue.toDouble())
                CCounting.text = "%.2f".format(progress.toDouble())
            }
            override fun onStartTrackingTouch(seek: SeekBar) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
                val CelVal = CCounting.text.toString().toDouble()
                checkTemperature(CelVal)
            }
        })

    }
    fun checkTemperature(celsiusValue: Double){
        // For some reason this is still going off for any value <5.
        if (celsiusValue <= 20){
            showSnackbarCold()
        } else {
            showSnackbarHot()
        }
    }
    private fun showSnackbarCold() {
        Snackbar.make(findViewById(android.R.id.content), "I wish it were warmer", Snackbar.LENGTH_SHORT).show()
    }
    private fun showSnackbarHot() {
        Snackbar.make(findViewById(android.R.id.content), "I wish it were colder", Snackbar.LENGTH_SHORT).show()
    }

    fun FahrentoC(Progress: Int): Double {
        return ((Progress - 32) * 5.0/9.0)
    }
    fun CelsitoF(Progress: Int): Double {
        return ((Progress * 9.0/5.0) + 32)
    }
}
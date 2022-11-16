package com.example.iot_delta

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import pl.droidsonroids.gif.GifImageView
import java.io.IOError
import java.io.IOException

class LigthActivity : AppCompatActivity(), SensorEventListener {

    var sensors: Sensor? = null
    var sensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ligth)

        val display: GifImageView = findViewById(R.id.display_img)
        display.visibility = View.INVISIBLE

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensors = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
    }


    override fun onResume(){
        super.onResume()
        sensorManager!!.registerListener(this,sensors,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause(){
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    var isRunning = false

    override fun onSensorChanged(event: SensorEvent?) {
        try{
            if(event!!.values[0] < 30 && isRunning == false){
                isRunning = true
                val display: GifImageView = findViewById(R.id.display_img)
                display.visibility = View.VISIBLE
            }
            else{
                isRunning = false
                val display: GifImageView = findViewById(R.id.display_img)
                display.visibility = View.INVISIBLE
            }
        }catch (e: IOException){

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }
}
package com.example.iot_delta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.iot_delta.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnScanner.setOnClickListener{initScanner()}

        /*val buttonClick = findViewById<Button>(R.id.btnSensorLuz)
        buttonClick.setOnClickListener {
            val intent = Intent(this, LigthActivity::class.java)
            startActivity(intent)
        }*/

    }

    private fun initScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        //integrator.setPrompt("Sigue aprendiendo en CursoKotlin.com")
        //integrator.setTorchEnabled(true)
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null){
            if(result.contents == null){
                Toast.makeText(this,"Cancelado",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"El valor escaneado es: ${result.contents}",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, NavegadorActivity::class.java)
                intent.putExtra("direccion", result.contents.toString())
                startActivity(intent)
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}
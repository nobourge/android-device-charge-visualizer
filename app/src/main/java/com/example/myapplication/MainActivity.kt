package com.example.myapplication

import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.os.BatteryManager
import android.widget.Button
import android.widget.Toast

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity()
{


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Intent to check the actions on battery
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
            applicationContext.registerReceiver(null, ifilter)
        }

        // isCharging if true indicates charging is ongoing and vice-versa
        val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
        val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
                || status == BatteryManager.BATTERY_STATUS_FULL

        // Call battery manager service
        val bm = applicationContext.getSystemService(BATTERY_SERVICE) as BatteryManager

        // Get the battery percentage and store it in a INT variable
        val batLevel:Int = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        if(batLevel==100 && isCharging)
        {
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        }

        // Declare button, that will show battery percentage when clicked
        val btn = findViewById<Button>(R.id.showBtn)
        btn.setOnClickListener{
            // Display whatever the state in the form of a Toast
            if(isCharging) {
                Toast.makeText(applicationContext, "$batLevel% and Charging", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext,"$batLevel% and Not Charging", Toast.LENGTH_LONG).show()
            }

        }
    }
    /** Called when the user taps the Send button */
    fun sendMessage(view: View)
    {
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }



}

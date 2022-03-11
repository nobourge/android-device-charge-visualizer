package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView

class DisplayMessageActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        /**
        // Load a background for the current screen from a drawable resource
        window.setBackgroundDrawableResource(R.drawable.my_background_image)

// Set the Activity title by getting a string from the Resources object, because
//  this method requires a CharSequence rather than a resource ID
        window.setTitle(resources.getText(R.string.main_title))

// Load a custom layout for the current screen
        setContentView(R.layout.main_screen)

// Set a slide in animation by getting an Animation from the Resources object
        flipper.setInAnimation(AnimationUtils.loadAnimation(this,
            R.anim.hyperspace_in))

// Set the text on a TextView object using a resource ID
        val msgTextView = findViewById(R.id.msg) as TextView
        msgTextView.setText(R.string.hello_message)
*/



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.textView).apply {
            text = message
        }

        AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump).also { hyperspaceJumpAnimation ->
            findViewById<TextView>(R.id.textView).startAnimation(hyperspaceJumpAnimation)
        }
    }

}
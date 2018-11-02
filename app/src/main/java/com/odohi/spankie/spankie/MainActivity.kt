package com.odohi.spankie.spankie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val will make the variable immutable, I'm guessing that's kotlin's idea of constant
        val initialTextViewTranslationY = textView_progress.translationY

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView_progress.text = progress.toString()

                val translationDistance = (initialTextViewTranslationY +
                         progress * resources.getDimension(R.dimen.text_anim_step) * -1)

                textView_progress.animate().translationY(translationDistance)

                if (!fromUser)
                    textView_progress.animate()
                        .setDuration(500)
                        .rotationBy(360f)
                        .translationY(initialTextViewTranslationY)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        button_reset.setOnClickListener { _ ->
            if (seekBar.progress != 0) {
                seekBar.progress = 0
            } else {
                val toastText = "Progress is zero(0)"
                val toastDuration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, toastText, toastDuration)
                toast.show()
            }
        }
    }
}

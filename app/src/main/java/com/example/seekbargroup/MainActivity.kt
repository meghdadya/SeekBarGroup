package com.example.seekbargroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import com.meghdadya.seekbargroup.SeekBarGroup


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnAddSteps = findViewById<Button>(R.id.btn_add_steps)
        val seekBarGroup = findViewById<SeekBarGroup>(R.id.seekBar_Group)
        btnAddSteps.setOnClickListener {
            seekBarGroup.addSeekBarView(SeekBar(this))
        }
    }
}
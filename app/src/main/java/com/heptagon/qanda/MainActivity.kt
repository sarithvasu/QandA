package com.heptagon.qanda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heptagon.qanda.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }
}
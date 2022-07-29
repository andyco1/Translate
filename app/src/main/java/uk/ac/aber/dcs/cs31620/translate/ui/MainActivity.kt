package uk.ac.aber.dcs.cs31620.translate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uk.ac.aber.dcs.cs31620.translate.R
import uk.ac.aber.dcs.cs31620.translate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }
}
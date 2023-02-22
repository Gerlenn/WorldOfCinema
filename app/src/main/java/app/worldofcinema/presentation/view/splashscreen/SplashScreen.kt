package app.worldofcinema.presentation.view.splashscreen

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import app.worldofcinema.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
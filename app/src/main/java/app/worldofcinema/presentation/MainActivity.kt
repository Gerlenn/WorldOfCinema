package app.worldofcinema.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import app.worldofcinema.R
import app.worldofcinema.databinding.ActivityMainBinding
import app.worldofcinema.presentation.view.auth.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        actionBar?.setDisplayHomeAsUpEnabled(false)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, LoginFragment())
            .commit()
    }
}
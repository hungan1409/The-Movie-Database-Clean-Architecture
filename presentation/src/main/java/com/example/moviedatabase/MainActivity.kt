package com.example.moviedatabase

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.moviedatabase.base.BaseActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)
    }
}
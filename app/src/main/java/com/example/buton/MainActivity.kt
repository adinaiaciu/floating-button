package com.example.buton

import androidx.appcompat.app.ActionBar
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*





class MainActivity : AppCompatActivity() {



    lateinit var toolbar: ActionBar

    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.miMap -> {
                        toolbar.title = "Map"
                        //                val mapFragment = Harta_1()
                        //                openFragment(mapFragment)
                        run { startActivity(Intent(this, Harta_1::class.java)) }

                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.miFeed -> {

                        toolbar.title = "Feed"
                        //run { startActivity(Intent(this, feed::class.java)) }

                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.miProfile -> {
                        toolbar.title = "Profile"

                        run { startActivity(Intent(this, Login::class.java)) }

                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }

    //    private fun openFragment(fragment: Fragment) {
    //        val transaction = supportFragmentManager.beginTransaction()
    //        transaction.replace(R.id.container, fragment)
    //        transaction.addToBackStack(null)
    //        transaction.commit()
    //    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigation.background = null
        fab.setOnClickListener({startActivity(Intent(this, feed::class.java)) })
        bottomNavigationView.menu.getItem (1).isEnabled=false
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
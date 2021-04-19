
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
import kotlinx.android.synthetic.main.activity_feed3.*
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
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigation.background = null
        fab.setOnClickListener({startActivity(Intent(this, Feed_3::class.java)) })
        bottomNavigationView.menu.getItem (1).isEnabled=false
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}



//package com.example.buton
//
//import android.content.Context
//import android.content.Intent
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.graphics.drawable.BitmapDrawable
//import android.os.Bundle
//import android.provider.MediaStore
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import android.widget.Toast
//import androidx.appcompat.app.ActionBar
//import androidx.appcompat.app.AppCompatActivity
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//import com.google.firebase.storage.FirebaseStorage
//import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.activity_feed3.*
//import kotlinx.android.synthetic.main.activity_feed3.*
//import kotlinx.android.synthetic.main.activity_login.*
//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.add_ticket.view.*
//import kotlinx.android.synthetic.main.posts.view.*
//import java.io.ByteArrayOutputStream
//import java.text.SimpleDateFormat
//import java.util.*
//import kotlin.collections.ArrayList
//import kotlin.collections.HashMap
//
//
//
//
//class MainActivity : AppCompatActivity() {
//
//
//
//    lateinit var toolbar: ActionBar
//
//    private val mOnNavigationItemSelectedListener =
//            BottomNavigationView.OnNavigationItemSelectedListener { item ->
//                when (item.itemId) {
//                    R.id.miMap -> {
//                        toolbar.title = "Map"
//                        //                val mapFragment = Harta_1()
//                        //                openFragment(mapFragment)
//                        run { startActivity(Intent(this, Harta_1::class.java)) }
//
//                        return@OnNavigationItemSelectedListener true
//                    }
//                    R.id.miFeed -> {
//
//                        toolbar.title = "Feed"
//                        //run { startActivity(Intent(this, feed::class.java)) }
//
//                        return@OnNavigationItemSelectedListener true
//                    }
//                    R.id.miProfile -> {
//                        toolbar.title = "Profile"
//
//                        run { startActivity(Intent(this, Login::class.java)) }
//
//                        return@OnNavigationItemSelectedListener true
//                    }
//                }
//                false
//            }
//
//    //    private fun openFragment(fragment: Fragment) {
//    //        val transaction = supportFragmentManager.beginTransaction()
//    //        transaction.replace(R.id.container, fragment)
//    //        transaction.addToBackStack(null)
//    //        transaction.commit()
//    //    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        supportActionBar?.hide()
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        toolbar = supportActionBar!!
//        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigationView)
//        bottomNavigation.background = null
//        main_button.setOnClickListener{startActivity(Intent(this, Feed_3::class.java)) }
//        bottomNavigationView.menu.getItem (1).isEnabled=false
//        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
////        buttton.setOnClickListener{startActivity(Intent(this, feed2::class.java)) }
//    }
//}
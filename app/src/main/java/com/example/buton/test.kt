package com.example.buton

import android.app.SearchManager

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask


import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem

import android.widget.SearchView
import android.widget.Toast




import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_test.*

class test : AppCompatActivity() {
    var ListTweets=ArrayList<Ticket>()
    var adapter:MyTweetAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        ListTweets.add(Ticket("0", "him", "url", "add"))
        ListTweets.add(Ticket("0", "him", "url", "clara"))
        ListTweets.add(Ticket("0", "him", "url", "clara"))
        ListTweets.add(Ticket("0", "him", "url", "clara"))

        adapter=MyTweetAdapter(this, ListTweets)
        lvTweets.adapter=adapter
    }
        inner class MyTweetAdapter: BaseAdapter {
        var listNotesAdapter = ArrayList<Ticket>()
        var context: Context? = null

        constructor(context: Context, listNotesAdapter: ArrayList<Ticket>) : super() {
            this.listNotesAdapter = listNotesAdapter
            this.context = context
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            var mytweet = listNotesAdapter[p0]

            if (mytweet.postPersonUID.equals("add")) {
                var myView = layoutInflater.inflate(R.layout.add_ticket, null)
                //load add ticket
                //TODO: work
                return myView
            } else {
                var myView = layoutInflater.inflate(R.layout.posts, null)
                //load tweet ticket
                //TODO: work
                return myView
            }


        }

        override fun getItem(p0: Int): Any {
            return listNotesAdapter[p0]
        }


        override fun getItem(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return listNotesAdapter.size
        }
}}
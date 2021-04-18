package com.example.buton

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_ticket.view.*

class feed : AppCompatActivity() {
    var ListTweets = ArrayList<Ticket>()
    var adpater: MyTweetAdpater? = null
    var myemail:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        var b: Bundle? =intent.extras
        myemail= b?.getString("email")
        ListTweets.add(Ticket("0", "him", "url", "add"))
        ListTweets.add(Ticket("0", "him", "url", "clara"))
        ListTweets.add(Ticket("0", "him", "url", "clara"))
        ListTweets.add(Ticket("0", "him", "url", "clara"))

        adpater = MyTweetAdpater(this, ListTweets)
        lvTweets.adapter = adpater
    }

    inner class MyTweetAdpater : BaseAdapter {
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

                myView.file.setOnClickListener(View.OnClickListener {

                })
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

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {

            return listNotesAdapter.size

        }
    }

    val PICK_IMAGE_CODE = 123
    fun loadImage() {

        var intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(intent, PICK_IMAGE_CODE)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == PICK_IMAGE_CODE && data != null && resultCode == RESULT_OK) {
//
//            val selectedImage = data.data
//            val filePathColum = arrayOf(MediaStore.Images.Media.DATA)
//            val cursor = contentResolver.query(selectedImage!!, filePathColum, null, null, null)
//            cursor!!.moveToFirst()
//            val coulomIndex = cursor!!.getColumnIndex(filePathColum[0])
//            val picturePath = cursor!!.getString(coulomIndex)
//            cursor!!.close()
//            UploadImage(BitmapFactory.decodeFile(picturePath))
//        }
//
//    }

//    fun UploadImage (bitmap:Bitmap){
//        val storage=FirebaseStorage.getInstance()
//        val storgaRef=storage.getReferenceFromUrl("gs://twitterwebservice-b75b6.appspot.com")
//        val df= SimpleDateFormat("ddMMyyHHmmss")
//        val dataobj= Date()
//        val imagePath= SaveSettings.userID + "."+ df.format(dataobj)+ ".jpg"
//        val ImageRef=storgaRef.child("imagePost/"+imagePath
//    }
}
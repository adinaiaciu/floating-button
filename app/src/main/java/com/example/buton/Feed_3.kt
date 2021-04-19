package com.example.buton

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_feed3.*
import kotlinx.android.synthetic.main.activity_feed3.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_ticket.view.*
import kotlinx.android.synthetic.main.posts.view.*
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Feed_3 : AppCompatActivity() {

    private var database= FirebaseDatabase.getInstance()
    private var myRef=database.reference



//
//    lateinit var toolbar: ActionBar
//
//    private val mOnNavigationItemSelectedListener =
//        BottomNavigationView.OnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.miMap -> {
//                    toolbar.title = "Map"
//                    //                val mapFragment = Harta_1()
//                    //                openFragment(mapFragment)
//                    run { startActivity(Intent(this, Harta_1::class.java)) }
//
//                    return@OnNavigationItemSelectedListener true
//                }
//                R.id.miFeed -> {
//
//                    toolbar.title = "Feed"
//                    //run { startActivity(Intent(this, feed::class.java)) }
//
//                    return@OnNavigationItemSelectedListener true
//                }
//                R.id.miProfile -> {
//                    toolbar.title = "Profile"
//
//                    run { startActivity(Intent(this, Login::class.java)) }
//
//                    return@OnNavigationItemSelectedListener true
//                }
//            }
//            false
//        }
//






    var ListTweets = ArrayList<Ticket>()
    var adpater: MyTweetAdpater? = null
    var myemail:String?=null
    var UserUID:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {



//        toolbar = supportActionBar!!
//        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigationView)
//        bottomNavigation.background = null
////        feed_button.setOnClickListener{startActivity(Intent(this, Feed_3::class.java)) }
//        bottomNavigationView_feed.menu.getItem (1).isEnabled=false
//        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)



        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed3)

        var b: Bundle? =intent.extras
        myemail= b?.getString("email")
        UserUID=b?.getString("uid")

        ListTweets.add(Ticket("0", "him", "url", "add"))


        ListTweets.add(Ticket("2", "Tihs is an organic age-defending oil powered by Argan Oil and hemp seed oil for strong, resilient, glowing skin that bounces back. Non-psychoactive, THC-free, and CBD-free. https://www.crueltyfreekitty.com/product/skin-dope-argan-oil-hemp-seed-oil/",
            "https://www.crueltyfreekitty.com/wp-content/uploads/2020/09/Skin-Dope-Argan-Oil-Hemp-Seed-Oil.jpg", "ReciClarity"))
        ListTweets.add(Ticket("3", "VIVAIA is a great place to buy shoes", "https://cdnimg.vivaiacollection.com/VA/image/Banner/20200617_959/VIVAIA_SustainableShoes_Flats_Margot_Model01.jpg", "ReciClarity"))
        ListTweets.add(Ticket("4", "Kinship is cruelty-free", "https://www.crueltyfreekitty.com/wp-content/uploads/2021/04/kinship.jpg", "ReciClarity"))
        ListTweets.add(Ticket("5", "Oily, environmental stress, workout heavy hair\n" +
                "\n" +
                "Hot yoga have your hair beyond a dry shampoo fix? Don’t sweat it! Heavy daily workouts mean daily washing which can be hard on hair. Formulated for those who have to wash and condition their hair daily due to harsh environmental conditions, heavy sweat, or just plain oily locks. Gentle, everyday formula with watermelon seed extract and blood orange!\n" +
                "\n" +
                "100% VEGAN. 0% PRETENTIOUS.\n" +
                "Paraben free, Sulfate free, Mineral oil free, Petrolatum free, Formaldehyde free, Cruelty free", "https://acure.com/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/b/r/bright-scrub.jpg", "ReciClarity"))
        ListTweets.add(Ticket("6", "All natural, fragrance-free, no artificial colors. Sun Shield is an unscented, full-spectrum UVA/UVB sun defense that offers powerful and effective coverage for both your face and body. The high-performance blend of antioxidants, including Vitamin E and Aloe, soothe skin, encourage its natural defense, and help to minimize the signs of sun-induced irritation.", "https://images.squarespace-cdn.com/content/v1/5442b6cce4b0cf00d1a3bef2/1590013096860-DUK2RZCOE8XHQ5HPWUG7/ke17ZwdGBToddI8pDm48kMh3mVmBaCAeGwqCLG3iONRZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZamWLI2zvYWH8K3-s_4yszcp2ryTI0HqTOaaUohrI8PIarJWwnumkapRz_nmTYj1dpaH2rx--_BA62nv3IYPJxMKMshLAGzx4R3EDFOm1kBS/Cruelty-Free+Sunscreen+-+MyChelle+Dermaceuticals?format=500w", "ReciClarity"))
        ListTweets.add(Ticket("7", "What5 do you think about JLo Beauty?", "https://www.crueltyfreekitty.com/wp-content/uploads/2021/02/jlo-beauty.jpg", "ReciClarity"))
        ListTweets.add(Ticket("1", "Dezi Skin is now cruelty-free ", "https://www.crueltyfreekitty.com/wp-content/uploads/2021/04/dezi.jpg", "ReciClarity"))

        adpater = MyTweetAdpater(this, ListTweets)
        lvTweets_feed.adapter = adpater
        LoadPost()
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
                    loadImage()

                })
                myView.iv_send.setOnClickListener(View.OnClickListener {
                    //upload to server
                    myRef.child("posts").push().setValue(
                        PostInfo(UserUID!!,
                            myView.etPost.text.toString(), DownloadURL!!))
                    myView.etPost.setText("")
                })

                return myView
            } else {
                var myView = layoutInflater.inflate(R.layout.posts, null)
                myView.txt_tweet.setText(mytweet.postText)
                myView.txtUserName.setText(mytweet.postPersonUID)
//                myView.tweet_picture.setImageURI(mytweet.postImageURL)
                Picasso.get().load(mytweet.postImageURL).into(myView.tweet_picture);
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


    val PICK_IMAGE_CODE=123

    fun loadImage(){
        var intent=Intent(Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==PICK_IMAGE_CODE && data!=null && resultCode==RESULT_OK){
            val selectedImage=data.data
            val filePathColum=arrayOf(MediaStore.Images.Media.DATA)
            val cursor= contentResolver.query(selectedImage!!, filePathColum,null,null,null)
            //cursor!!.moveToFirst()
            //am adaugat !! la selectedImage
            if (cursor != null) {
                cursor.moveToFirst()
            }
            //initial cursor.moveToFirst()

            //val coulomIndex= cursor.getColumnIndex(filePathColum[0])
            val coulomIndex=cursor!!.getColumnIndex(filePathColum[0])
            val picturePAth= cursor.getString(coulomIndex)
            //val picturePAth=cursor.getString(coulomIndex)
//            if (cursor != null) {
//                cursor.close()
//            }
            cursor.close()
            UploadImage(BitmapFactory.decodeFile(picturePAth))
        }
    }

    var DownloadURL:String?=null

    fun UploadImage(bitmap:Bitmap){

        val storage= FirebaseStorage.getInstance()
        val storageRef=storage.getReferenceFromUrl("gs://reciclarity1.appspot.com")
        val df= SimpleDateFormat("ddMMyyHHmmss")
        val dataobj= Date()
        val imagePAth=SplitString(myemail!!) +  "." + df.format(dataobj)+".jpg"
        val ImageRef=storageRef .child("imagePost/"+imagePAth)
        val baos= ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data =baos.toByteArray()
        val uploadTask=ImageRef.putBytes(data)
        uploadTask.addOnFailureListener{
            Toast.makeText(applicationContext,"Fail to upload", Toast.LENGTH_LONG).show()
        } .addOnSuccessListener{taskSnapshot ->
            DownloadURL= taskSnapshot.getMetadata()!!.getReference()!!.getDownloadUrl().toString()

        }
    }
    fun SplitString(email:String):String{
        val split=email.split("@")
        return split[0]
    }

    fun LoadPost(){
        myRef.child("posts")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    try {
                        ListTweets.clear()
                        ListTweets.add(Ticket("0", "him", "url", "add"))

                        var td=dataSnapshot!!.value as  HashMap<String, Any>
                        for(key in td.keys){
                            var post= td[key] as  HashMap<String, Any>
                            ListTweets.add(Ticket(key,
                                post["text"] as String,
                                post["postImage"] as String,
                                post["userUID"] as String))
                        }
                        adpater!!.notifyDataSetChanged()
                    }catch (ex:Exception){}
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }
}

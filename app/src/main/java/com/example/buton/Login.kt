package com.example.buton

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*



class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        buttonSignUp.setOnClickListener {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            if (editTextEmail.text.toString().isNullOrEmpty() || editTextPassword.text.toString()
                    .isNullOrEmpty())
                textViewResponse.text = "Email Address or Password is not provided"
            else {
                auth.createUserWithEmailAndPassword(
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            textViewResponse.text =
                                "Sign Up successfull. Email and Password created"
                            val user = auth.currentUser
                            //updateUI(user)
                        } else {
                            textViewResponse.text = "Sign Up Failed"
                            //updateUI(null)
                        }
                    }
            }
            fun updateUI(currentUser: FirebaseUser?) {

            }
        }


        imagePerson.setOnClickListener(View.OnClickListener {
            checkPermission()
        })
    }


    val READIMAGE:Int=234
    fun checkPermission(){

        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){

                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), READIMAGE)
                return
            }
        }
        loadImage()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){
            READIMAGE->{
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    loadImage()
                }else{
                    Toast.makeText(this, "Cannot access your images", Toast.LENGTH_LONG).show()
                }
            }
            else->super.onRequestPermissionsResult(requestCode, permissions, grantResults)
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
                //cursor.moveToFirst()
                cursor.moveToFirst()
            }
            //initial cursor.moveToFirst()

            //val coulomIndex= cursor.getColumnIndex(filePathColum[0])
            val coulomIndex=cursor!!.getColumnIndex(filePathColum[0])
            val picturePAth= cursor.getString(coulomIndex)
            //val picturePAth=cursor.getString(coulomIndex)
            if (cursor != null) {
                cursor.close()
            }
            //cursor.close()
            imagePerson.setImageBitmap(BitmapFactory.decodeFile(picturePAth))
        }
    }

    fun butLogIn(view:View) {

    }
}
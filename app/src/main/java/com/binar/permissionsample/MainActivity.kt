package com.binar.permissionsample

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private lateinit var loadImageButton: Button
    private lateinit var imageView: ImageView
    private lateinit var requestButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadImageButton = findViewById(R.id.load_image_button)
        imageView = findViewById(R.id.image_view)
        requestButton = findViewById(R.id.request_permission_button)

        loadImageButton.setOnClickListener{
            click()
        }
           requestButton.setOnClickListener{
               requestPermissionLocation()
           }
       }

    private fun click()
    {
        Glide.with(this)
            .load("https://1.bp.blogspot.com/-tjxThEa2wZk/XT5Fea1JqaI/AAAAAAAAC2U/qh03Mzu1dxAzXeGAtxLlR4f8EJoaFZsLQCLcBGAs/s1600/Gambar%2BIlustrasi%2BMalam.jpg")
            .circleCrop()
            .into(imageView)
    }


    @SuppressLint("MissingPermission")
   private fun getLongLat()
    {
        val locationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val location : Location? = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    }

    private fun requestLocationPermission() {
        requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),201)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            201 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION
                ) {
                    Toast.makeText(this, "Permissions for location permitted", Toast.LENGTH_LONG)
                        .show()
                    getLongLat()
                } else {
                    Toast.makeText(this, "permissions for location denied", Toast.LENGTH_LONG)
                        .show()
                }
            }
            else -> {
                Toast.makeText(this, "the request code doesnt match", Toast.LENGTH_LONG).show()

            }
        }
    }

         private fun requestPermissionLocation()
        {
            val permissionCheck = checkSelfPermission(ACCESS_FINE_LOCATION)
            if (permissionCheck == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this,"Permission Granted", Toast.LENGTH_LONG).show()
                getLongLat()
            }
            else
            {
                Toast.makeText( this,"Permission Denied", Toast.LENGTH_LONG).show()
                requestLocationPermission()
            }
        }
}
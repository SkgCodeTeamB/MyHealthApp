package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class HomePage : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.home_page -> Toast.makeText(applicationContext,"Clicked Home Page",Toast.LENGTH_SHORT).show()
                R.id.back -> Toast.makeText(applicationContext,"Clicked Back",Toast.LENGTH_SHORT).show()
                R.id.home_page -> Toast.makeText(applicationContext,"Clicked Home Page",Toast.LENGTH_SHORT).show()
                R.id.appointment -> Toast.makeText(applicationContext,"Clicked Appointment",Toast.LENGTH_SHORT).show()
                R.id.diagnoses -> Toast.makeText(applicationContext,"Clicked Diagnoses",Toast.LENGTH_SHORT).show()
                R.id.prescriptions -> Toast.makeText(applicationContext,"Clicked Prescriptions",Toast.LENGTH_SHORT).show()
                R.id.vaccines -> Toast.makeText(applicationContext,"Clicked Vaccines",Toast.LENGTH_SHORT).show()
                R.id.personal_data -> Toast.makeText(applicationContext,"Clicked Personal Data",Toast.LENGTH_SHORT).show()
                R.id.logOut -> Toast.makeText(applicationContext,"Clicked LogOut",Toast.LENGTH_SHORT).show()
            }

            true
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}
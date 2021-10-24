package com.example.app

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.app.Fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView



class HomePage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var drawer: DrawerLayout? = null
    private var bottomNavigationView: BottomNavigationView? = null
    private val homePageFragment: HomePageFragment = HomePageFragment.newInstance()
    private val personalDataFragment: PersonalDataFragment = PersonalDataFragment.newInstance()
    private val diagnosesFragment: DiagnosesFragment = DiagnosesFragment.newInstance()
    private val appointmentFragment: AppointmentFragment = AppointmentFragment.newInstance()
    private val prescriptionFragment: PrescriptionFragment = PrescriptionFragment.newInstance()
    private val vaccinesFragment: VaccinesFragment = VaccinesFragment.newInstance()
    private val makeAnAppointmentFragment: MakeAnAppointmentFragment = MakeAnAppointmentFragment.newInstance()

    var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_page)
        id = intent.getStringExtra("_ID").toString()

        val bundle = Bundle()
        bundle.putString("_id", id)
        homePageFragment.arguments = bundle
        personalDataFragment.arguments = bundle
        diagnosesFragment.arguments = bundle
        appointmentFragment.arguments = bundle
        prescriptionFragment.arguments = bundle
        vaccinesFragment.arguments = bundle


/*
        val token = getIntent().getStringExtra("token")
        val txtMarios: TextView? = findViewById(R.id.textViewMarios);
        txtMarios?.setText(token)
        setContentView(R.layout.fragment_homepage)

        val txtMarios: TextView? = findViewById(R.id.textViewMarios);
        val intent = intent

        if (intent.extras != null) {
            val passedUsername = intent.getStringExtra("_id")
            txtMarios?.setText("Welcome $passedUsername")
        }

*/

        val drawer : DrawerLayout = findViewById(R.id.drawer_layout)
        //val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val menuRight = findViewById<ImageButton>(R.id.leftRight)
        menuRight.setOnClickListener { v : View? ->
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START)
            } else {
                drawer.openDrawer(GravityCompat.START)
            }
        }



        val bottomNavigationView : BottomNavigationView = findViewById(R.id.navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener{ item: MenuItem ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.back -> { onBackPressed()}
                R.id.home_page2 -> selectedFragment = homePageFragment
                R.id.personal_data2 -> selectedFragment = personalDataFragment

            }
            val transaction = supportFragmentManager.beginTransaction()
            if (selectedFragment != null) {
                transaction.replace(R.id.f_container, selectedFragment)
                transaction.commit()
            }
            true
        }


        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        navigationView.setNavigationItemSelectedListener { item: MenuItem ->
            drawer.closeDrawers()
            var selectedFragment2: Fragment? = null
            val ft = supportFragmentManager.beginTransaction()
            when (item.itemId) {
                R.id.home_page -> {
                    bottomNavigationView.selectedItemId = R.id.home_page2
                    ft.replace(R.id.f_container, homePageFragment)
                }

                R.id.appointment -> selectedFragment2 = appointmentFragment

                R.id.diagnoses -> selectedFragment2 = diagnosesFragment

                R.id.prescriptions -> selectedFragment2 = prescriptionFragment

                R.id.vaccines -> selectedFragment2 = vaccinesFragment

                R.id.personal_data -> {
                    bottomNavigationView.selectedItemId = R.id.personal_data2
                    ft.replace(R.id.f_container, personalDataFragment)
                }

                R.id.logOut -> {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    Toast.makeText(this@HomePage, "Your Logout is successful.", Toast.LENGTH_SHORT).show()}
            }
            val transaction = supportFragmentManager.beginTransaction()
            if (selectedFragment2 != null) {
                transaction.replace(R.id.f_container, selectedFragment2)
                transaction.commit()
            }
            true

        }


        //Manually displaying the first fragment - one time only
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.f_container, homePageFragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }



}

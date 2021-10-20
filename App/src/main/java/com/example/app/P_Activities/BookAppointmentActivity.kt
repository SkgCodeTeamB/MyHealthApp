package com.example.dummyappointmentsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.app.P_API.ApiInterface
import com.example.app.R
import com.example.dummyappointmentsapp.Models.*
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class BookAppointmentActivity : AppCompatActivity() {

    var fieldSpinner: Spinner? = null
    var fieldsNameList = arrayListOf<String>()
    var fieldAdapter: ArrayAdapter<String>? = null
    var doctorSpinner: Spinner? = null
    var doctorsList = arrayListOf<String>()
    var doctorAdapter: ArrayAdapter<String>? = null
    var dateSpinner: Spinner? = null
    var datesList = arrayListOf<String>()
    var dateAdapter: ArrayAdapter<String>? = null
    var timeSpinner: Spinner? = null
    var timesList = arrayListOf<String>()
    var timeAdapter: ArrayAdapter<String>? = null
    var bookButton: Button? = null

    var doctorsIDList = arrayListOf<String>()

    var user_id = ""
    var doctor_id = ""
    var date = ""
    var time = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)
        contentInit()
        populateFieldSpinner()
    }

    private fun contentInit() {
        fieldSpinner = findViewById(R.id.fieldSpinner)
        doctorSpinner = findViewById(R.id.doctorSpinner)
        dateSpinner = findViewById(R.id.dateSpinner)
        timeSpinner = findViewById(R.id.timeSpinner)
        bookButton = findViewById(R.id.bookButton)
        user_id = "6166f4778ba0b351dec0d1ab" //get logged user's _id
        fieldsNameList.add("Select Medical Field")
        doctorsList.add("Select Doctor")


        datesList.add("Select Date")
        val format = SimpleDateFormat("dd/M/yyyy")
        val currentDate = format.format(Date())
        val c = Calendar.getInstance()
        for (i in 1..15){
            c.time = format.parse(currentDate)
            c.add(Calendar.DATE, i) // number of days to add
            var dt = format.format(c.time) // dt is now the new date
            datesList.add(dt)
        }

        timesListInit()
    }

    private fun timesListInit() {
        timesList.clear()
        timesList.add("Select Time")
        timesList.addAll(listOf("8:00 - 9:00", "9:00 - 10:00", "10:00 - 11:00",
            "11:00 - 12:00", "12:00 - 13:00", "17:00 - 18:00",
            "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00"))
    }

    private fun populateFieldSpinner() {

        val apiInterface = ApiInterface.create().getFields()
        apiInterface.enqueue( object : retrofit2.Callback<List<Field>> {

            override fun onResponse(call: Call<List<Field>>?, response: Response<List<Field>>?) {

                if(response?.body() != null){
                    for (i in response.body()!!.indices)
                        fieldsNameList.add(response.body()!![i].name)

                    fieldAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, fieldsNameList)
                    fieldSpinner?.adapter = fieldAdapter
                }

                fieldSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        bookButton?.isEnabled = false
                        if (position == 0) {}
                        else{
                            fieldSpinner?.isEnabled = false
                            populateDoctorSpinner(fieldsNameList[position])
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<Field>>?, t: Throwable?) {
                println(t)
                Toast.makeText(this@BookAppointmentActivity, "Communication Problem", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun populateDoctorSpinner(selectedField: String) {

        val apiInterface = ApiInterface.create().getDoctors(selectedField)
        apiInterface.enqueue( object : retrofit2.Callback<List<Doctor>> {
            override fun onResponse(call: Call<List<Doctor>>?, response: Response<List<Doctor>>?) {

                if(response?.body() != null){
                    for (i in response.body()!!.indices) {
                        doctorsList.add(response.body()!![i].name + " " + response.body()!![i].surname)
                        doctorsIDList.add(response.body()!![i]._id)
                    }

                    doctorAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, doctorsList)
                    doctorSpinner?.adapter = doctorAdapter
                }

                doctorSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        bookButton?.isEnabled = false
                        if (position == 0) {}
                        else{
                            doctor_id = doctorsIDList[position-1]
                            doctorSpinner?.isEnabled = false
                            populateDateSpinner()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<Doctor>>?, t: Throwable?) {
                println(t)
                Toast.makeText(this@BookAppointmentActivity, "Communication Problem", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun populateDateSpinner() {
        dateAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, datesList)
        dateSpinner?.adapter = dateAdapter

        dateSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                bookButton?.isEnabled = false
                if (position == 0) {}
                else{
                    date = datesList[position]
                    timesListInit()
                    //dateSpinner?.isEnabled = false
                    populateTimeSpinner()
                }
            }
        }
    }

    private fun populateTimeSpinner() {
        val info = Info(doctor_id, date)
        val apiInterface = ApiInterface.create().getBookedSlots(info)
        apiInterface.enqueue( object : retrofit2.Callback<List<TimeSlot>> {
            override fun onResponse(call: Call<List<TimeSlot>>?, response: Response<List<TimeSlot>>?) {

                if(response?.body() != null){
                    for (i in response.body()!!.indices) {
                        timesList.remove(response.body()!![i].time)
                    }

                    timeAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, timesList)
                    timeSpinner?.adapter = timeAdapter
                }

                timeSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        bookButton?.isEnabled = false
                        if (position == 0) {}
                        else{
                            time = timesList[position]
                            //timeSpinner?.isEnabled = false
                            readyToBook()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<TimeSlot>>?, t: Throwable?) {
                println(t)
                Toast.makeText(this@BookAppointmentActivity, "Communication Problem", Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun readyToBook() {

        bookButton?.isEnabled = true
        bookButton?.setOnClickListener {

            val newAppointment = Appointment(user_id, doctor_id, date, time)

            val apiInterface = ApiInterface.create().addBooking(newAppointment)

            apiInterface.enqueue( object : retrofit2.Callback<Appointment> {
                override fun onResponse(call: Call<Appointment>?, response: Response<Appointment>?) {

                    if(response?.body() != null){
                        Toast.makeText(this@BookAppointmentActivity, "Your new Appointment is booked", Toast.LENGTH_SHORT).show()
                        onBackPressed()
                    }
                }
                override fun onFailure(call: Call<Appointment>?, t: Throwable?) {
                    println(t)
                    Toast.makeText(this@BookAppointmentActivity, "Communication Problem", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
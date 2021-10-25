package com.example.app.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.app.ApiInterface
import com.example.app.R
import com.example.dummyappointmentsapp.Models.User
import retrofit2.Call
import retrofit2.Response

class PersonalDataFragment : Fragment() {

    var user_id: String? = null
    var userInfo: User? = null

    var textId: TextView? = null
    var textName: TextView? = null
    var textSurname: TextView? = null
    var textEmail: TextView? = null
    var textPhone: TextView? = null
    var textBirthday: TextView? = null
    var textBloodtype: TextView? = null
    var textAmka: TextView? = null
    var textFamilydoctor: TextView? = null
    var textAddress: TextView? = null
    var textCity: TextView? = null
    var textPostalcode: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personaldata, container, false)
    }

    companion object {
        fun newInstance(): PersonalDataFragment {
            return PersonalDataFragment()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        user_id = arguments?.getString("_id").toString()
        getPersonalData()
    }

    private fun getPersonalData() {

        val apiInterface = ApiInterface.create().getPersonalData(user_id)
        apiInterface.enqueue( object : retrofit2.Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>?) {

                if(response?.body() != null){
                    for (i in response.body()!!.indices) {
                        userInfo = User(response.body()!![i]._id, response.body()!![i].name, response.body()!![i].surname,
                                        response.body()!![i].email, response.body()!![i].phone, response.body()!![i].birthday,
                                        response.body()!![i].bloodtype, response.body()!![i].amka, response.body()!![i].familydoctor,
                                        response.body()!![i].address, response.body()!![i].city, response.body()!![i].postalcode)
                    }
                    showPersonalData()
                }
            }
            override fun onFailure(call: Call<List<User>>?, t: Throwable?) {
                println(t)
                Toast.makeText(activity, "Communication Problem", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showPersonalData() {
        /*textId = view?.findViewById(com.example.app.R.id."COMPONENT_NAME")
        textName = view?.findViewById(com.example.app.R.id."COMPONENT_NAME")
        textSurname = view?.findViewById(com.example.app.R.id."COMPONENT_NAME")
        textEmail = view?.findViewById(com.example.app.R.id."COMPONENT_NAME")
        textPhone = view?.findViewById(com.example.app.R.id."COMPONENT_NAME")
        textBirthday = view?.findViewById(com.example.app.R.id."COMPONENT_NAME")
        textBloodtype = view?.findViewById(com.example.app.R.id."COMPONENT_NAME")
        textAmka = view?.findViewById(com.example.app.R.id."COMPONENT_NAME")
        textFamilydoctor = view?.findViewById(com.example.app.R.id."COMPONENT_NAME")
        textAddress = view?.findViewById(com.example.app.R.id."COMPONENT_NAME")
        textCity = view?.findViewById(com.example.app.R.id."COMPONENT_NAME")
        textPostalcode = view?.findViewById(com.example.app.R.id."COMPONENT_NAME")*/


        val userId = userInfo?._id
        val userName = userInfo?.name
        val userSurname = userInfo?.surname
        val userEmail = userInfo?.email
        val userPhone = userInfo?.phone
        val userBirthday = userInfo?.birthday
        val userBloodtype = userInfo?.bloodtype
        val userAmka = userInfo?.amka
        val userFamilydoctor = userInfo?.familydoctor
        val userAddress = userInfo?.address
        val userCity = userInfo?.city
        val userPostalcode = userInfo?.postalcode

        textId?.text = userId
        textName?.text = userName
        textSurname?.text = userSurname
        textEmail?.text = userEmail
        textPhone?.text = userPhone
        textBirthday?.text = userBirthday
        textBloodtype?.text = userBloodtype
        textAmka?.text = userAmka
        textFamilydoctor?.text = userFamilydoctor
        textAddress?.text = userAddress
        textCity?.text = userCity
        textPostalcode?.text = userPostalcode
    }
}
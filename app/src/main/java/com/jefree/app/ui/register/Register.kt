package com.jefree.app.ui.register

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import com.jefree.app.R
import com.jefree.app.common.toast
import com.jefree.app.ui.login.Login
import com.jefree.app.ui.register.adapter.CustomeSpinnerAdapterRegister
import com.jefree.app.ui.register.adapter.UtilityRegister
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.view.*

class Register : AppCompatActivity(), RegisterView {

    lateinit var registerTitle: Spinner
    lateinit var registerIdValue: String
    lateinit var registerCodeValue: String
    lateinit var registerNameValue: String
    private var showPassword = false
    private var showConfirmPassword = false

    override fun OnError(message: String) {
        toast(message)
    }

    private val presenter = registerPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainLayout = layoutInflater.inflate(R.layout.activity_register, null)
        setContentView(mainLayout)
        registerTitle = mainLayout.sp_register_title


//        setContentView(R.layout.activity_register)
        btnBack()

//        spinnerAdapter()

        SetupCountryPicker()
        setupListener()
    }

    private fun btnBack() {
        //actionbar
        setSupportActionBar(toolbar_register)
        supportActionBar?.title = "Daftar"
        //set back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun SetupCountryPicker() {
        //the countryPickerData holds a list of objects of the class ExPickerItem.
        val countryPickerData = UtilityRegister.CountryDataSourceRegist
        Log.d("xxx", countryPickerData.toString())
        val pickerAdapter =
            CustomeSpinnerAdapterRegister(this@Register, R.layout.spinner_item_layout_register, countryPickerData)
        registerTitle.setAdapter(pickerAdapter)

        //have the first item by-default selected
        registerTitle.setSelection(1)
        registerIdValue = countryPickerData.get(0).id!!
        registerCodeValue = countryPickerData.get(0).code!!
        registerNameValue = countryPickerData.get(0).name!!

        //Adding a listener to the custom spinner when an item is selected from the spinner
        registerTitle.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                registerIdValue = countryPickerData.get(i).id!!
                registerCodeValue = countryPickerData.get(i).code!!
                registerNameValue = countryPickerData.get(i).name!!

//                Toast.makeText(this@Register, "You selected $registerNameValue with id $registerIdValue and code $registerCodeValue", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                //handle when no item selected
            }
        }
    }

    private fun setupListener() {
        btn_register_daftar.setOnClickListener {
            register()
        }

        toggle_show_password.setOnClickListener {
            showPassword()
        }

        toggle_show_confirm_password.setOnClickListener {
            showConfirmPassword()
        }
    }

    private fun showPassword() {
        if (!showPassword) {
            showPassword = true
            et_register_password.transformationMethod = null
            if (et_register_password.text.length >= 0) {
                et_register_password.setSelection(et_register_password.text.length)
                toggle_show_password.setImageDrawable(resources.getDrawable(R.drawable.ic_eye_hide))
            }
        } else {
            showPassword = false
            et_register_password.transformationMethod = PasswordTransformationMethod()
            if (et_register_password.text.length >= 0){
                et_register_password.setSelection(et_register_password.text.length)
                toggle_show_password.setImageDrawable(resources.getDrawable(R.drawable.ic_show_password))
            }
        }
    }

    private fun showConfirmPassword() {
        if (!showConfirmPassword) {
            showConfirmPassword= true
            et_register_password_confirmation.transformationMethod = null
            if (et_register_password_confirmation.text.length >= 0) {
                et_register_password_confirmation.setSelection(et_register_password_confirmation.text.length)
                toggle_show_confirm_password.setImageDrawable(resources.getDrawable(R.drawable.ic_eye_hide))
            }
        } else {
            showConfirmPassword= false
            et_register_password_confirmation.transformationMethod = PasswordTransformationMethod()
            if (et_register_password_confirmation.text.length >= 0){
                et_register_password_confirmation.setSelection(et_register_password_confirmation.text.length)
                toggle_show_confirm_password.setImageDrawable(resources.getDrawable(R.drawable.ic_show_password))
            }
        }
    }

    private fun register() {
        validateForm()
       //  alert()
    }

    private fun validateForm() {
        val nama = et_register_nama.text.toString()
        val phone = et_register_nomor.text.toString()
        val pass = et_register_password.text.toString()
        val pass_con = et_register_password_confirmation.text.toString()
        if (nama.isBlank() || phone.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        } else if ((pass_con != pass)) {
            toast("pastikan password sama")
            return
        } else {
            val map = mutableMapOf<String, String>()
            map["name"] = nama
            map["phone"] = phone
            map["password"] = pass
            map["title"] = registerNameValue
            map["password_confirmation"] = pass_con
            presenter.addUser(map)

        }
    }

    override fun register(Status: Boolean, message: String) {
        toast(message)
        if (Status) {
            finish()
        }
    }

    override fun showProgresRegister(show: Boolean) {
        if (show) {
            progressBar_register?.visibility = View.VISIBLE

        } else
            progressBar_register?.visibility = View.GONE
    }


//    private fun alert() {
//        // Initialize a new instance of
//        val builder = AlertDialog.Builder(this@Register)
//        // Set the alert dialog title
//        builder.setTitle("Konfirmasi")
//        // Display a message on alert dialog
//        builder.setMessage("Apakah data yang diinputkan sudah benar?")
//        // Set a positive button and its click listener on alert dialog
//        builder.setPositiveButton("Ya") { dialog, which ->
//            // Do something when user press the positive button
//            Toast.makeText(applicationContext, "User sedang diproses", Toast.LENGTH_SHORT)
//                .show()
//            val intent = Intent(this@Register, Login::class.java)
//            startActivity(intent)
//
//        }
//        // Display a negative button on alert dialog
//        builder.setNegativeButton("Tidak") { dialog, which ->
//        }
//        // Finally, make the alert dialog using builder
//        val dialog: AlertDialog = builder.create()
//        // Display the alert dialog on app interface
//        dialog.show()
//    }

    /* override fun onSupportNavigateUp(): Boolean {

         finish()
         return true
     }*/

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    fun getValues(view: View) {
        Toast.makeText(this, "Title " + registerTitle.selectedItem.toString(), Toast.LENGTH_LONG).show()
    }


}

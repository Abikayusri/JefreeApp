package com.jefree.app.ui.home.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ReturnMode
import com.jefree.app.JefreeApp
import com.jefree.app.R
import com.jefree.app.common.Constant
import com.jefree.app.common.toast
import com.jefree.app.model.Profile.Data
import com.jefree.app.model.Profile.Profile
import com.jefree.app.ui.home.EditView
import com.jefree.app.ui.home.adapter.CustomeSpinnerAdapterEdit
import com.jefree.app.ui.home.adapter.SpinnerItemEditProfile
import com.jefree.app.ui.home.adapter.UtilityEditProfile
import com.jefree.app.ui.register.adapter.CustomeSpinnerAdapterRegister
import com.jefree.app.ui.register.adapter.SpinnerItemRegister
import com.jefree.app.ui.register.adapter.UtilityRegister
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.edit_profile.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class EditProfile : AppCompatActivity(), EditView {

    //    lateinit var registerTitle: Spinner
    lateinit var registerIdValue: String
    lateinit var registerCodeValue: String
    lateinit var registerNameValue: String
    lateinit var countryPickerDataRegister: List<SpinnerItemRegister>
    var indexRegister: Int = 0
    private var avatar: File? = null

    //    lateinit var editProfile: Spinner
    lateinit var editprofIdValue: String
    lateinit var editprofCodeValue: String
    lateinit var editprofNameValue: String
    lateinit var countryPickerDataEditProfile: List<SpinnerItemEditProfile>
    var indexEditProfile: Int = 0

    private val presenter = EditProfilePresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile)
        setSupportActionBar(toolbarEdit)
        supportActionBar?.title = "Edit Profile"
        setupView()
        SetupCountryPicker()
    }


    private fun setupView() {
        val data: Data? = intent.getParcelableExtra(Constant.USERS)
        btn_simpan_profile.setOnClickListener {
            editForm()
        }
        Log.d("errdat", "$data")
        countryPickerDataRegister = UtilityRegister.CountryDataSourceRegist
        countryPickerDataRegister.forEachIndexed { indexRegister, spinnerItem ->
            if (spinnerItem.name == data?.profile?.title) {
                Log.d("errspin", "$indexRegister")
                sp_edit_profile_title.setSelection(indexRegister)
                this.indexRegister = indexRegister
                return@forEachIndexed
            }
        }

        Glide.with(this)
            .load(data?.profile?.avatar?.url)
            .apply(RequestOptions().apply {
                placeholder(R.drawable.profil_default)
                error(R.drawable.profil_default)
            })
            .into(image_profile)

        countryPickerDataEditProfile = UtilityEditProfile.CountryDataSourceEdit
        countryPickerDataEditProfile.forEachIndexed { indexEdit, spinnerItem ->
            if (spinnerItem.name == data?.profile?.bussinessType) {
                Log.d("erredit", "$indexEdit")
                sp_edit_profile_jenis_usaha.setSelection(indexEdit)
                this.indexEditProfile = indexEdit
                return@forEachIndexed
            }
        }

        sp_edit_profile_title.setSelection(indexRegister)
        et_deskripsi.setText(data?.profile?.bio)
        sp_edit_profile_jenis_usaha.setSelection(indexEditProfile)
        et_edit_profile_nama.setText(data?.profile?.name)
        et_edit_profile_nomer.setText(data?.profile?.phone)
        et_alamat.setText(data?.profile?.address)
        et_email.setText(data?.profile?.email)
        add_image()
    }

    private fun add_image() {
        image_profile.setOnClickListener {
            ImagePicker.create(this)
                .returnMode(ReturnMode.ALL) // set whether pick and / or camera action should return immediate result or not.
                .folderMode(true) // folder mode (false by default)
                .toolbarImageTitle("Pilih Gambar") // image selection title
                .single() // single mode
                .showCamera(true) // show camera or not (true by default)
                .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                .theme(R.style.ImagePickerTheme) // must inherit ef_BaseTheme. please refer to sample
                .enableLog(false) // disabling log
                .start() // start image picker activity with request code
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            val image = ImagePicker.getFirstImageOrNull(data)
            avatar = File(image.path)
            Glide.with(this)
                .load(avatar)
                .into(image_profile)
        }
    }


    private fun editForm() {
        val nama = et_edit_profile_nama.text.toString()
        val email = et_email.text.toString()
        val address = et_alamat.text.toString()
        val phone = et_edit_profile_nomer.text.toString()
        val bio = et_deskripsi.text.toString()


        val map = mutableMapOf<String, RequestBody>()
        map["name"] = toRequestBody(nama)
        map["email"] = toRequestBody(email)
        map["phone"] = toRequestBody(phone)
        map["address"] = toRequestBody(address)
        map["bio"] = toRequestBody(bio)
        map["title"] = toRequestBody(registerNameValue)
        map["bussiness_type"] = toRequestBody(editprofNameValue)
        println("idUser" + JefreeApp.sp.id)
        Log.d("id user", JefreeApp.sp.id.toString())
        print("data edit" + map)

        val avatarImage = if (avatar != null) {
            val requestBody = RequestBody.create(MediaType.parse("image/*"), avatar!!)
            MultipartBody.Part.createFormData("avatar", avatar?.name, requestBody)
        } else null

        presenter.editUser(JefreeApp.sp.id, map, avatarImage)
    }

    private fun toRequestBody(str: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), str)
    }

    override fun showProgresEdit(show: Boolean) {
        if (show) {
            progressBar_profile?.visibility = View.VISIBLE

        } else
            progressBar_profile?.visibility = View.GONE
    }

    private fun SetupCountryPicker() {
        //the countryPickerData holds a list of objects of the class ExPickerItem.
//        countryPickerData = UtilityRegister.CountryDataSource
        Log.d("xxx", countryPickerDataRegister.toString())
        val pickerAdapterRegister =
            CustomeSpinnerAdapterRegister(
                this@EditProfile,
                R.layout.spinner_item_layout_register,
                countryPickerDataRegister
            )
        sp_edit_profile_title.setAdapter(pickerAdapterRegister)

        val pickerAdapterEditProfile =
            CustomeSpinnerAdapterEdit(this@EditProfile, R.layout.spinner_item_layout_edit, countryPickerDataEditProfile)
        sp_edit_profile_jenis_usaha.setAdapter(pickerAdapterEditProfile)

        //have the first item by-default selected
        sp_edit_profile_title.setSelection(indexRegister)
        registerIdValue = countryPickerDataRegister.get(0).id!!
        registerCodeValue = countryPickerDataRegister.get(0).code!!
        registerNameValue = countryPickerDataRegister.get(0).name!!

        //have the first item by-default selected
        sp_edit_profile_jenis_usaha.setSelection(indexEditProfile)
        editprofIdValue = countryPickerDataEditProfile.get(0).id!!
        editprofCodeValue = countryPickerDataEditProfile.get(0).code!!
        editprofNameValue = countryPickerDataEditProfile.get(0).name!!

        //Adding a listener to the custom spinner when an item is selected from the spinner
        sp_edit_profile_title.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                registerIdValue = countryPickerDataRegister.get(i).id!!
                registerCodeValue = countryPickerDataRegister.get(i).code!!
                registerNameValue = countryPickerDataRegister.get(i).name!!

//                Toast.makeText(this@Register, "You selected $registerNameValue with id $registerIdValue and code $registerCodeValue", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                //handle when no item selected
            }
        }

        //Adding a listener to the custom spinner when an item is selected from the spinner
        sp_edit_profile_jenis_usaha.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                editprofIdValue = countryPickerDataEditProfile.get(i).id!!
                editprofCodeValue = countryPickerDataEditProfile.get(i).code!!
                editprofNameValue = countryPickerDataEditProfile.get(i).name!!

//                Toast.makeText(this@Register, "You selected $registerNameValue with id $registerIdValue and code $registerCodeValue", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                //handle when no item selected
            }
        }
    }

    //umpan balik dari presenter
    override fun editProfile(success: Boolean, message: String) {
        toast(message)
        if (success) {
            finish()
        }
    }

    override fun message(message: String) {
        toast(message)
    }

}
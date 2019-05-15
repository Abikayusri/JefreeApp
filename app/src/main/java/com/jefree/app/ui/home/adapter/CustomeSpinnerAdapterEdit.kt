package com.jefree.app.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.jefree.app.R

class CustomeSpinnerAdapterEdit : ArrayAdapter<SpinnerItemEditProfile> {


    private var CustomSpinnerItems: List<SpinnerItemEditProfile>

    constructor(context: Context, resource: Int, pickerItems: List<SpinnerItemEditProfile>) : super(context, resource, pickerItems){this.CustomSpinnerItems = pickerItems}

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return CustomSpinnerView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return CustomSpinnerView(position, convertView, parent)
    }

    private fun CustomSpinnerView(position: Int, convertView: View?, parent: ViewGroup): View {
        //Getting the Layout Inflater Service from the system
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //Inflating out custom spinner view
        val customView = layoutInflater.inflate(R.layout.spinner_item_layout_edit, parent, false)
        //Declaring and initializing the widgets in custom layout
        val imageView = customView.findViewById(R.id.profileIcon) as ImageView
        val textView = customView.findViewById(R.id.pickerText) as TextView

        //displaying the data
        //drawable items are mapped with name prefixed with 'zx_' also image names are containing underscore instead of spaces.
        val imageRef = "ic_edit_" + CustomSpinnerItems[position].name!!.toLowerCase().replace(" ", "_")
        val resID = context.resources.getIdentifier(imageRef, "drawable", context.packageName)

        imageView.setImageResource(resID)
        //imageView.setImageDrawable();geResource(getApplicationContext(),getImageId(CustomSpinnerItems.get(position).getName()));
        textView.setText(CustomSpinnerItems[position].name)
        return customView
    }

}

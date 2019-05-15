package com.jefree.app.ui.sliderIntro


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jefree.app.R
import kotlinx.android.synthetic.main.fragment_slider.*

class SliderFragment : Fragment() {

    private var pageTitle : String = ""
    private var pageDesc : String = ""
    private var imageDrawable: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragment_title.text = pageTitle
        fragment_desc.text = pageDesc
        iv_onboard.setImageResource(imageDrawable)
    }

    fun setTitle(title : String){
        pageTitle = title
    }

    fun setDescription(desc : String){
        pageDesc = desc
    }

    fun setImage(image: Int) {
        imageDrawable = image
    }

}
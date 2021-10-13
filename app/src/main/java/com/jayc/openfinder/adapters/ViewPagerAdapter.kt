package com.jayc.openfinder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

import com.jayc.openfinder.R

class ViewPagerAdapter(context: Context): PagerAdapter() {

    private var context: Context = context
    private lateinit var layoutInflater: LayoutInflater
    private var images = arrayOf(R.drawable.card1, R.drawable.card2, R.drawable.card3, R.drawable.card4)


    override fun getCount() = images.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view: View = layoutInflater.inflate(R.layout.custom_layout, null)
        var imageView: ImageView = view.findViewById(R.id.imageView)
        imageView.setImageResource(images[position])

        var vp: ViewPager = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        var vp: ViewPager = container as ViewPager
        var view: View = `object` as View
        vp.removeView(view)
    }

}
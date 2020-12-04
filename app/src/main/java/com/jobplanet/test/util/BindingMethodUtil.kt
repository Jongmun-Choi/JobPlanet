package com.jobplanet.test.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.test.R
import com.jobplanet.test.adapter.DataListAdapter
import com.jobplanet.test.viewmodel.DataListViewModel
import java.text.DecimalFormat

class BindingMethodUtil {}

@BindingAdapter("setImageUrl")
fun ImageView.bindImageUrl(url : String) {
    if(url.isEmpty()==false) GlideApp.with(this.context).load(url).into(this)
}


@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run{
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}

@BindingAdapter(value = ["decimalText"])
fun TextView.bindDecimalText(nubmer:Int) {
    val decimalFormat = DecimalFormat("#,###")
    this.run{
        setText(decimalFormat.format(nubmer))
    }
}

@BindingAdapter(value = ["difficultyText"])
fun TextView.bindDifficultyText(point : Float) {
    this.run {
        when(point) {
            in 0f..1f -> {
                setText("매우쉬움")
                setTextColor(context.resources.getColor(R.color.color_blue))
            }
            in 1f..2f -> {
                setText("쉬움")
                setTextColor(context.resources.getColor(R.color.color_blue))
            }
            in 2f..3f -> {
                setText("보통")
                setTextColor(context.resources.getColor(R.color.color_green))
            }
            in 3f..4f -> {
                setText("어려움")
                setTextColor(context.resources.getColor(R.color.color_red))
            }
            else -> {
                setText("매우어려움")
                setTextColor(context.resources.getColor(R.color.color_red))
            }
        }
    }
}

@BindingAdapter(value = ["setDivider"])
fun RecyclerView.setItemDivider(resource: Drawable) {
    var dividerDecoration = DividerItemDecoration(this.context, LinearLayout.VERTICAL)
    dividerDecoration.setDrawable(resource)
    this.run {
        addItemDecoration(dividerDecoration)
    }
}

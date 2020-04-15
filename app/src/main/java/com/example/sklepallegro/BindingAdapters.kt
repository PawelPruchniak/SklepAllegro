package com.example.sklepallegro

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.overview.PhotoGridAdapter
import com.example.sklepallegro.network.Offer
import com.example.sklepallegro.network.Price
import com.example.sklepallegro.overview.AllegroApiStatus

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Offer>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("name")
fun bindName(txtView: TextView, name: String?) {
    txtView.text = name
}

@BindingAdapter("price")
fun bindPrice(txtView: TextView, price: Price?) {
    val priveText = "cena: " + "<b>" + price?.amount.toString() + " " + price?.currency + "</b>"
    txtView.text = Html.fromHtml((priveText))
}

@BindingAdapter("description")
fun bindDescription(txtView: TextView, description: String?){
    txtView.text = Html.fromHtml(description)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("allegroApiStatus")
fun bindStatus(statusImageView: ImageView, status: AllegroApiStatus?) {
    when (status) {
        AllegroApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        AllegroApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        AllegroApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
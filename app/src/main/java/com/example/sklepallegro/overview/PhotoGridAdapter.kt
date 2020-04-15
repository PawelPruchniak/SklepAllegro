package com.example.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sklepallegro.databinding.GridViewItemBinding
import com.example.sklepallegro.network.Offer

class PhotoGridAdapter( val onClickListener: OnClickListener ) :
    ListAdapter<Offer, PhotoGridAdapter.OfferViewHolder>(DiffCallback) {
    class OfferViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(offer: Offer) {
            binding.offer = offer
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): OfferViewHolder {
        return OfferViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(offer)
        }
        holder.bind(offer)
    }

    class OnClickListener(val clickListener: (offer:Offer) -> Unit) {
        fun onClick(offer:Offer) = clickListener(offer)
    }
}
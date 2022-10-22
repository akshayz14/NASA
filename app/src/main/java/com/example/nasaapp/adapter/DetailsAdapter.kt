package com.example.nasaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.nasaapp.NasaApplication
import com.example.nasaapp.R
import com.example.nasaapp.domain.entity.NasaDataEntityItem


class DetailsAdapter :
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {


    var items: MutableList<NasaDataEntityItem> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var detailsImageIV: ImageView
        var title: TextView
        var description: TextView

        init {
            detailsImageIV = itemView.findViewById(R.id.detailsImageIV)
            title = itemView.findViewById(R.id.titleTV)
            description = itemView.findViewById(R.id.descTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.details_image_layout, parent, false)
        return ViewHolder(view)

    }

    fun setData(list: List<NasaDataEntityItem>) {
        this.items.clear()
        notifyDataSetChanged()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var data = items[position]

        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.ic_error)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()

        Glide.with(NasaApplication.INSTANCE.applicationContext)
            .load(data.hdurl)
            .apply(options)
            .into(holder.detailsImageIV)

        holder.title.text = data.title
        holder.description.text = data.explanation

    }

    override fun getItemCount(): Int {
        return items.size
    }

}
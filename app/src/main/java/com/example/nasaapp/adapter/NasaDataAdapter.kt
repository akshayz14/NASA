package com.example.nasaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.nasaapp.NasaApplication
import com.example.nasaapp.R
import com.example.nasaapp.action.ActionPerformer
import com.example.nasaapp.action.NasaAction
import com.example.nasaapp.action.openImageInHD
import com.example.nasaapp.domain.entity.NasaDataEntityItem

class NasaDataAdapter(private val actionPerformer: ActionPerformer<NasaAction>) :
    RecyclerView.Adapter<NasaDataAdapter.ViewHolder>() {


    var items: MutableList<NasaDataEntityItem> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.grid_image_layout, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
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
            .load(data.url)
            .apply(options)
            .into(holder.gridImageIV)

        holder.gridImageIV.setOnClickListener {
            actionPerformer.performAction(openImageInHD(data.id))
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(list: List<NasaDataEntityItem>) {
        this.items.clear()
        notifyDataSetChanged()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var gridImageIV: ImageView

        init {
            gridImageIV = itemView.findViewById(R.id.gridImageIV)
        }
    }
}
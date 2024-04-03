package com.aivison.simpleapp.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aivison.simpleapp.R
import com.aivison.simpleapp.databinding.ItemListBinding
import com.aivison.simpleapp.model.SubCategoryModel
import com.bumptech.glide.Glide

class AudioPlayerAdapter(
    private val activity: Activity,
    private val audioItems: ArrayList<SubCategoryModel>
) : RecyclerView.Adapter<AudioPlayerAdapter.ItemsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ItemsViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        Glide
            .with(activity)
            .load(audioItems[position].icon)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.vHBinding.ivLogo);
        holder.vHBinding.tvName.text = audioItems[position].name
        holder.vHBinding.ratingBar.rating = audioItems[position].star.toFloat()
        holder.vHBinding.tvDownload.text = audioItems[position].installed_range

        holder.vHBinding.btnDownload.setOnClickListener {
            val appPackageName = audioItems[position].app_link
            try {
                activity.startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName"))
                )
            } catch (e: android.content.ActivityNotFoundException) {
                activity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return audioItems.size
    }

    inner class ItemsViewHolder(vHBinding: ItemListBinding) :
        RecyclerView.ViewHolder(vHBinding.root) {
        val vHBinding: ItemListBinding

        init {
            this.vHBinding = vHBinding
        }
    }
}
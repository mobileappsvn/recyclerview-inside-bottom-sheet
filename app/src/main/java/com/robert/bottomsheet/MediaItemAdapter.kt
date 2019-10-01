package com.robert.bottomsheet

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition


internal class MediaItemAdapter(private val context: Context, private val mItems: List<String>, private val mListener: ItemListener?) : RecyclerView.Adapter<MediaItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.bottom_sheet_item, parent, false))
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_bottom_sheet, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindData(mItems[position])
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var title: TextView
        var imageView: ImageView
        var item: String? = null

        init {
            itemView.setOnClickListener(this)
            title = itemView.findViewById(R.id.title)
            imageView = itemView.findViewById(R.id.image)
        }

        fun onBindData(url: String) {
            this.item = url
            title.text = url

            Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(object : CustomTarget<Bitmap>(){
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            imageView.setImageBitmap(resource)
                        }
                        override fun onLoadCleared(placeholder: Drawable?) {
                            // this is called when imageView is cleared on lifecycle call or for some other reason.
                            // if you are referencing the bitmap somewhere else too other than this imageView
                            // clear it here as you can no longer have the bitmap
                        }

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            super.onLoadFailed(errorDrawable)
                            Log.e("MediaItemAdapter", "Cannot load:$url")
                        }
                    })
        }

        override fun onClick(v: View) {
            mListener?.onItemClick(item)
        }
    }

    internal interface ItemListener {
        fun onItemClick(item: String?)
    }
}

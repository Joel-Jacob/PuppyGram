package com.example.puppygram.adapters

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.puppygram.R
import com.example.puppygram.models.Items
import java.text.SimpleDateFormat
import java.util.*

class PuppyGramAdapter : RecyclerView.Adapter<PuppyGramAdapter.PuppyGramViewHolder>() {
    private lateinit var context: Context
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.US)
    var puppyList: MutableList<Items> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuppyGramViewHolder {
        context = parent.context.applicationContext

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.puppy_profile_layout, parent, false)
        return PuppyGramViewHolder(view)
    }

    override fun onBindViewHolder(holder: PuppyGramViewHolder, position: Int) {
        holder.title.text = puppyList[position].title
        holder.author.text =
            context.resources.getString(R.string.author_format_text, puppyList[position].author)
        holder.description.text = context.resources.getString(
            R.string.description_format_text,
            puppyList[position].description
        )
        holder.dateTaken.text = context.resources.getString(
            R.string.date_taken_format_text, DateFormat.format(
                "MM-dd-yyyy hh:mm a",
                dateFormat.parse(puppyList[position].date_taken)
            )
        )
        holder.publishedDate.text = DateFormat.format(
            "MM-dd-yyyy hh:mm a",
            dateFormat.parse(puppyList[position].published)
        )

        Glide.with(context)
            .load(puppyList[position].media.m)
            .into(holder.image)

        flipVisibility(position, holder)

        holder.itemView.setOnClickListener {
            puppyList[position].showDetails = puppyList[position].showDetails == false
            flipVisibility(position, holder)
        }
    }

    /**
     * Method to change the visibility of the views in the holder based on user CTA
     * */
    private fun flipVisibility(position: Int, holder: PuppyGramViewHolder) {
        if (puppyList[position].showDetails) {
            holder.image.visibility = GONE
            holder.author.visibility = VISIBLE
            holder.description.visibility = VISIBLE
            holder.dateTaken.visibility = VISIBLE
        } else {
            holder.image.visibility = VISIBLE
            holder.author.visibility = GONE
            holder.description.visibility = GONE
            holder.dateTaken.visibility = GONE
        }
    }

    override fun getItemCount(): Int = puppyList.size

    class PuppyGramViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.puppy_title_text_view)
        var publishedDate: TextView = itemView.findViewById(R.id.puppy_published_text_view)
        var image: ImageView = itemView.findViewById(R.id.puppy_image_view)
        var description: TextView = itemView.findViewById(R.id.puppy_description_text_view)
        var dateTaken: TextView = itemView.findViewById(R.id.puppy_date_taken_text_view)
        var author: TextView = itemView.findViewById(R.id.puppy_author_text_view)
    }
}
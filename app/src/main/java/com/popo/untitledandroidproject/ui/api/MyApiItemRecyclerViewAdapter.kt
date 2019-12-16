package com.popo.untitledandroidproject.ui.api

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.popo.untitledandroidproject.R
import com.popo.untitledandroidproject.model.Movie


import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_apiitem.view.*

/**
 * [RecyclerView.Adapter] that can display a Movie and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MyApiItemRecyclerViewAdapter(
    private val mValues: List<Movie>,
    private val fragment: ApiItemFragment
) : RecyclerView.Adapter<MyApiItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Movie
            println("clicked ${item.title}")
            fragment.navigateToMovieInfos(item)

            val action = ApiItemFragmentDirections.actionApiItemFragmentToMovieInfos(item)
            v.findNavController().navigate(action)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_apiitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
//        holder.mIdView.text = item.title
        holder.mContentView.text = item.title
        Picasso.get().load(item.img).into(holder.mPosterView)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mPosterView: ImageView =mView.poster
//        val mIdView: TextView = mView.title
        val mContentView: TextView = mView.title


        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}

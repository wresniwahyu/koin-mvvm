package com.www.app.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.www.app.data.model.movies.ResultsItem
import com.www.app.databinding.ItemMovieBinding
import com.www.app.ui.base.BaseViewHolder
import com.www.app.ui.detail.DetailActivity

class MainAdapter(private val context: Context) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var list = ArrayList<ResultsItem>()

    fun addAll(items: ArrayList<ResultsItem>) {
        list = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val itemView = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.onBind(position)
    }

    inner class ViewHolder(var itemMovieBinding: ItemMovieBinding) : BaseViewHolder(itemMovieBinding.root) {

        lateinit var mainAdapterViewModel: MainAdapterViewModel
        val imgPoster = itemMovieBinding.imgPoster

        override fun onBind(position: Int) {
            mainAdapterViewModel = MainAdapterViewModel(list[position])
            itemMovieBinding.mainAdapterViewModel = mainAdapterViewModel
            itemMovieBinding.executePendingBindings()

            imgPoster.setOnClickListener {
                context.startActivity(DetailActivity.getActivityIntent(context, mainAdapterViewModel.item.id))
            }
        }
    }

}
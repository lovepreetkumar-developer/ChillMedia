package com.techwin.githubexamples.ui.main.home.view

import android.view.View
import com.techwin.githubexamples.R
import com.techwin.githubexamples.data.network.responses.GalleryModel
import com.techwin.githubexamples.databinding.ItemFeedBinding
import com.xwray.groupie.databinding.BindableItem

class ItemFeedPost(
    val model: GalleryModel,
    private val onItemClick: OnClickedListener
) : BindableItem<ItemFeedBinding>() {

    override fun getLayout(): Int = R.layout.item_feed

    override fun bind(viewBinding: ItemFeedBinding, position: Int) {
        viewBinding.model = model
        /*viewBinding.imgVolume.setOnClickListener{
            onItemClick.onItemClicked(this,it,position)
        }*/
    }

    interface OnClickedListener {
        fun onItemClicked(
            itemFeedPost: ItemFeedPost,
            view: View,
            position: Int
        )
    }
}
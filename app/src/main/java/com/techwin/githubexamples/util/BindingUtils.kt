package com.techwin.githubexamples.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.master.exoplayer.MasterExoPlayer
import com.techwin.githubexamples.data.network.responses.GalleryModel


class BindingUtils {

    companion object {

        /*@BindingAdapter(value = ["simpleResource"])
        @JvmStatic
        fun setStepGroupIcon(imageView: ImageView, simpleResource: Int) {
            if (simpleResource != -1) {
                imageView.setImageResource(simpleResource)
            }
        }

        @BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
        @JvmStatic
        fun setImageUrl(imageView: ImageView?, imageUrl: String?, placeholder: Drawable?) {

            imageUrl?.let {
                if (it.isNotEmpty()) {
                    if (placeholder == null) {
                        Picasso.get().load(imageUrl).into(imageView)
                    } else {
                        Picasso.get().load(imageUrl).placeholder(placeholder).into(imageView)
                    }
                }
            }
        }

        @BindingAdapter(value = ["image_path"], requireAll = false)
        @JvmStatic
        fun setBitmapOnImage(imageView: ImageView?, imagePath: String?) {
            Picasso.get().load(File(imagePath))
                .centerCrop()
                .resize(100, 100)
                .into(imageView)
        }

        @BindingAdapter("android:circleImage")
        @JvmStatic
        fun setCircleImageViewResource(imageView: ImageView?, resource: Int) {
            Picasso.get().load(resource).transform(CircleImageTransform()).into(imageView)
        }*/

        @BindingAdapter(value = ["feedImage"], requireAll = false)
        @JvmStatic
        fun feedImage(imageView: ImageView, galleryModel: GalleryModel) {

            if (galleryModel.images_count > 0) {

                val image = galleryModel.images[0]

                image.type?.let {

                    if (it.contains("jpg") ||
                        it.contains("jpeg")
                    ) {

                        if (image.link != "") {
                            imageView.visibility = View.VISIBLE
                            Glide.with(imageView.context)
                                .load(image.link)
                                .centerCrop()
                                .into(imageView)
                        } else {
                            imageView.visibility = View.GONE
                        }

                    } else {
                        imageView.visibility = View.GONE
                    }

                }
            }
        }

        @BindingAdapter(value = ["feedVideo"], requireAll = false)
        @JvmStatic
        fun feedVideo(exoPlayer: MasterExoPlayer, galleryModel: GalleryModel) {

            if (galleryModel.images_count > 0) {

                val image = galleryModel.images[0]

                image.type?.let {
                    if (it.contains("mp4")) {
                        if (image.link != "") {
                            exoPlayer.visibility = View.VISIBLE
                            exoPlayer.url = image.link
                        }
                    } else {
                        exoPlayer.visibility = View.GONE
                    }
                }
            }
        }

        @BindingAdapter(value = ["addSeeMoreText"], requireAll = false)
        @JvmStatic
        fun addSeeMoreText(textView: AppCompatTextView, galleryModel: GalleryModel) {

            textView.text =
                "Calling all Singers, Dancers, MusSingers, Dancers, Musicians. Get registered with yoCalling all Singers, Dancers, Musici Siicians. Get registered with yoCalling all Singers, Dancers, Musici Singers, Dancers, Musicians. Get registered with yoCalling all Singers, Dancers, Musicians. Get registered with yoCalling all Singers, Dancers, Musicians. Get registered with you valid id proof and data"
            makeTextViewResizable(textView, 2, "See More", true);

        }

        @BindingAdapter(value = ["circle_image", "placeholder"], requireAll = false)
        @JvmStatic
        fun setCircleImage(imageView: ImageView?, imagePath: String?, placeholder: Drawable?) {

            try {
                imagePath?.let {
                    if (!imagePath.contains(" ")) {
                        /*Picasso.get().load(imagePath)
                            .transform(CircleImageTransform())
                            .centerCrop()
                            .resize(100, 100)
                            .placeholder(placeholder!!)
                            .into(imageView)*/
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        /*@BindingAdapter(value = ["round_pic", "placeholder"], requireAll = false)
        @JvmStatic
        fun round(imageView: ImageView?, imageUrl: String?, placeholder: Drawable?) {
            Picasso.get()
                .load(imageUrl)
                .centerCrop()
                .resize(100, 100)
                .transform(CircleImageTransform())
                .placeholder(placeholder!!)
                .into(imageView)
        }

        @BindingAdapter(value = ["setProductPrice"], requireAll = false)
        @JvmStatic
        fun setProductPrice(textView: TextView?, list: List<ProductPriceModel>) {
            if (list.isNotEmpty()) textView?.text = "$" + list[0].product_price
        }

        @BindingAdapter(value = ["setPaintFlag"], requireAll = false)
        @JvmStatic
        fun setPaintFlag(textView: TextView?, string: String) {

            textView?.let {
                it.paintFlags = it.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
        }

        @BindingAdapter(value = ["likeImage"], requireAll = false)
        @JvmStatic
        fun likeImage(imageView: AppCompatImageView, model: ProductModel) {

            if (model.status==1){
                imageView.setImageResource(R.drawable.ic_filled_heart)
            }else{
                imageView.setImageResource(R.drawable.ic_empty_heart)
            }
        }

        @BindingAdapter(value = ["likeOrderImage"], requireAll = false)
        @JvmStatic
        fun likeOrderImage(imageView: AppCompatImageView, model: OrderProductModel) {

            if (model.status==1){
                imageView.setImageResource(R.drawable.ic_filled_heart)
            }else{
                imageView.setImageResource(R.drawable.ic_empty_heart)
            }
        }

        @BindingAdapter(value = ["setProductQuantity"], requireAll = false)
        @JvmStatic
        fun setProductQuantity(spinner: AppCompatSpinner, model: String) {

            //spinner.onItemSelectedListener
            *//*val adapter = ArrayAdapter.createFromResource(
                this,
                R.array.my_array,
                R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            mySpinner.setAdapter(adapter)

            spinner.en*//**//*
            if (list.isNotEmpty()) textView?.text = "$" + list[0].product_price*//*
        }

        @BindingAdapter(value = ["googleStyledImage"], requireAll = false)
        @JvmStatic
        fun googleStyledImage(imageView: ShapeableImageView, model: CategoryModel) {

            if (model.category_image.isNotEmpty()) {
                *//*if (placeholder == null) {
                    Picasso.get().load(model.category_image).into(imageView)
                } else {
                    Picasso.get().load(model.category_image).placeholder(placeholder).into(imageView)
                }*//*
                Picasso.get().load(model.category_image).placeholder(R.drawable.ic_user_placeholder).into(imageView)
            }

            imageView.shapeAppearanceModel = imageView.shapeAppearanceModel
                .toBuilder()
                .setBottomRightCorner(
                    CornerFamily.ROUNDED,
                    30.toFloat()
                )
                .setBottomLeftCorner(
                    CornerFamily.ROUNDED,
                    30.toFloat()
                )
                .build()
        }*/
    }
}
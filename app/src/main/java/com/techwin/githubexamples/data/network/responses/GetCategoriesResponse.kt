package com.techwin.githubexamples.data.network.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class GetCategoriesResponse(
    val `data`: List<CategoryModel>,
    val message: String,
    val method: String,
    val status: Int
)

@Parcelize
data class CategoryModel(
    val category_image: String,
    val category_name: String,
    val categoryid: String,
    val status: String
) : Parcelable
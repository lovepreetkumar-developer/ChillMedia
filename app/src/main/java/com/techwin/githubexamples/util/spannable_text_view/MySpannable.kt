package com.techwin.githubexamples.util.spannable_text_view

import android.graphics.Color
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

open class MySpannable(
    private val isUnderline: Boolean
) : ClickableSpan() {

    override fun updateDrawState(ds: TextPaint) {
        ds.isUnderlineText = isUnderline
        ds.color = Color.parseColor("#C3C3C3")
    }

    override fun onClick(view: View) {

    }
}
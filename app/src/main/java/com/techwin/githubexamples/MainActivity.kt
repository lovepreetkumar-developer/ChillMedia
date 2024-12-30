package com.techwin.githubexamples

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techwin.githubexamples.ui.main.home.menu.DrawerAdapter
import com.techwin.githubexamples.ui.main.home.menu.DrawerItem
import com.techwin.githubexamples.ui.main.home.menu.SimpleItem
import com.techwin.githubexamples.ui.main.home.menu.SpaceItem
import com.techwin.githubexamples.ui.main.home.view.FeedsFragment
import com.techwin.githubexamples.util.Constants.POS_ACCOUNT
import com.techwin.githubexamples.util.Constants.POS_CART
import com.techwin.githubexamples.util.Constants.POS_DASHBOARD
import com.techwin.githubexamples.util.Constants.POS_LOGOUT
import com.techwin.githubexamples.util.Constants.POS_MESSAGES
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu_left_drawer.*

class MainActivity : AppCompatActivity(), DrawerAdapter.OnItemSelectedListener {

    private lateinit var screenTitles: Array<String>
    private lateinit var screenIcons: MutableList<Drawable>
    private lateinit var slidingRootNav: SlidingRootNav

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        slidingRootNav = SlidingRootNavBuilder(this)
            .withToolbarMenuToggle(toolbar)
            .withMenuOpened(false)
            .withContentClickableWhenMenuOpened(false)
            .withSavedState(savedInstanceState)
            .withMenuLayout(R.layout.menu_left_drawer)
            .inject()

        screenIcons = loadScreenIcons()
        screenTitles = loadScreenTitles()

        @Suppress("INACCESSIBLE_TYPE")
        val adapter = DrawerAdapter(
            listOf(
                createItemFor(POS_DASHBOARD),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_MESSAGES),
                createItemFor(POS_CART),
                SpaceItem(48),
                createItemFor(POS_LOGOUT)
            )
        )
        adapter.setListener(this)

        val list = findViewById<RecyclerView>(R.id.list)
        list.isNestedScrollingEnabled = false
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        adapter.setSelected(POS_DASHBOARD)

        Glide.with(this)
            .load(R.raw.gif_side_menu)
            .placeholder(R.drawable.ic_feedback)
            .into(imgGif)
    }

    override fun onItemSelected(position: Int) {
        if (position == POS_LOGOUT) {
            finish()
        }
        slidingRootNav.closeMenu()/*
        val selectedScreen: Fragment = CenteredTextFragment.createFor(
            screenTitles[position]
        )*/
        val selectedScreen: Fragment = FeedsFragment()
        showFragment(selectedScreen)
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    @SuppressWarnings("rawtypes")
    private fun createItemFor(position: Int): DrawerItem<*> {
        return SimpleItem(screenIcons[position], screenTitles[position])
    }

    private fun loadScreenTitles(): Array<String> {
        return resources.getStringArray(R.array.ld_activityScreenTitles)
    }

    private fun loadScreenIcons(): MutableList<Drawable> {
        val ta = resources.obtainTypedArray(R.array.ld_activityScreenIcons)
        val icons = mutableListOf<Drawable>()
        for (i in 0 until ta.length()) {
            val id = ta.getResourceId(i, 0)
            if (id != 0) {
                ContextCompat.getDrawable(this, id)?.let {
                    icons.add(it)
                }
            }
        }
        ta.recycle()
        return icons
    }

    @ColorInt
    private fun color(@ColorRes res: Int): Int {
        return ContextCompat.getColor(this, res)
    }

    override fun onBackPressed() {

        if (slidingRootNav.isMenuOpened) {
            slidingRootNav.closeMenu()
            return
        }

        super.onBackPressed()
    }

    fun openSideMenu() {
        slidingRootNav.openMenu()
    }

    fun closeSideMenu() {
        slidingRootNav.closeMenu()
    }
}
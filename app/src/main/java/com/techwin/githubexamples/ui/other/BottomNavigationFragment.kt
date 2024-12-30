package com.techwin.githubexamples.ui.other

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.techwin.githubexamples.R
import com.techwin.githubexamples.ui.main.home.menu.DrawerAdapter
import com.techwin.githubexamples.ui.main.home.menu.DrawerItem
import com.techwin.githubexamples.ui.main.home.menu.SimpleItem
import com.techwin.githubexamples.ui.main.home.menu.SpaceItem
import com.techwin.githubexamples.ui.main.home.view.FeedsFragment
import com.techwin.githubexamples.util.Constants
import com.techwin.githubexamples.util.setupWithNavController
import com.techwin.githubexamples.util.toast
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*
import kotlinx.android.synthetic.main.menu_left_drawer.*

class BottomNavigationFragment : Fragment(), View.OnClickListener,
    DrawerAdapter.OnItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var screenTitles: Array<String>
    private lateinit var screenIcons: MutableList<Drawable>
    private lateinit var slidingRootNav: SlidingRootNav
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBottomNavigationBar()

        slidingRootNav = SlidingRootNavBuilder(requireActivity())
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
                createItemFor(Constants.POS_DASHBOARD),
                createItemFor(Constants.POS_ACCOUNT),
                createItemFor(Constants.POS_MESSAGES),
                createItemFor(Constants.POS_CART),
                SpaceItem(48),
                createItemFor(Constants.POS_LOGOUT)
            )
        )
        adapter.setListener(this)

        val list = requireActivity().findViewById<RecyclerView>(R.id.list)
        list.isNestedScrollingEnabled = false
        list.layoutManager = LinearLayoutManager(requireContext())
        list.adapter = adapter

        adapter.setSelected(Constants.POS_DASHBOARD)

        /*Glide.with(this)
            .load(R.raw.gif_side_menu)
            .placeholder(R.drawable.ic_feedback)
            .into(imgGif)*/

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onItemSelected(position: Int) {
        if (position == Constants.POS_LOGOUT) {
            requireActivity().finish()
        }
        slidingRootNav.closeMenu()/*
        val selectedScreen: Fragment = CenteredTextFragment.createFor(
            screenTitles[position]
        )*/
        val selectedScreen: Fragment = FeedsFragment()
        //showFragment(selectedScreen)
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
                ContextCompat.getDrawable(requireContext(), id)?.let {
                    icons.add(it)
                }
            }
        }
        ta.recycle()
        return icons
    }

    @ColorInt
    private fun color(@ColorRes res: Int): Int {
        return ContextCompat.getColor(requireContext(), res)
    }

    private fun setUpBottomNavigationBar() {

        val navGraphIds = listOf(
            R.navigation.nav_graph_one,
            R.navigation.nav_graph_two
        )

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.nav_host_container,
            intent = requireActivity().intent
        )

        currentNavController = controller
    }

    override fun onClick(view: View?) {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_graph_two -> requireContext().toast("yes profile click is working")
        }
        return false
    }
}
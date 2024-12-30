package com.techwin.githubexamples.ui.main.home.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.master.exoplayer.MasterExoPlayerHelper
import com.techwin.githubexamples.MainActivity
import com.techwin.githubexamples.R
import com.techwin.githubexamples.data.network.responses.GalleryModel
import com.techwin.githubexamples.ui.main.home.view_model.HomeFragmentViewModel
import com.techwin.githubexamples.ui.main.home.view_model.HomeFragmentViewModelFactory
import com.techwin.githubexamples.util.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_feeds.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class FeedsFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory: HomeFragmentViewModelFactory by instance()
    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var mFeedsAdapter: GroupAdapter<ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this, factory).get(HomeFragmentViewModel::class.java)

        return inflater.inflate(R.layout.fragment_feeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgMenu.setOnClickListener {
            (activity as MainActivity?)?.openSideMenu()
            //imageViewAnimatedChange(imgSettings, R.drawable.ic_share_app)
        }

        getGalleryData()

        /*swipeRefreshLayout.setOnRefreshListener {
            getGalleryData()
        }*/

        etSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(
                textView: TextView?,
                actionId: Int,
                p2: KeyEvent?
            ): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //requireContext().toast(textView?.text.toString())
                    hideSoftKeyboard(requireActivity())
                    searchGallery(textView?.text.toString())
                    return true
                }
                return false
            }
        })
    }

    /*private fun imageViewAnimatedChange(v: ImageView, new_image: Int) {
        val anim_out: Animation =
            AnimationUtils.loadAnimation(requireContext(), android.R.anim.fade_out)
        val anim_in: Animation =
            AnimationUtils.loadAnimation(requireContext(), android.R.anim.fade_in)
        anim_out.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                v.setImageResource(new_image)
                anim_in.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {}
                    override fun onAnimationRepeat(animation: Animation?) {}
                    override fun onAnimationEnd(animation: Animation?) {}
                })
                v.startAnimation(anim_in)
            }
        })
        v.startAnimation(anim_out)
    }*/

    /** Inventory Recycler View*/
    private fun initFeedPostRecyclerView(list: List<ItemFeedPost>) {

        mFeedsAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(list)
        }

        recyclerPosts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = mFeedsAdapter
        }

        val masterExoPlayerHelper =
            MasterExoPlayerHelper(
                mContext = requireContext(),
                id = R.id.masterExoPlayer,
                autoPlay = true
            )
        masterExoPlayerHelper.makeLifeCycleAware(this)
        masterExoPlayerHelper.attachToRecyclerView(recyclerPosts)

        //Used to customize attributes
        masterExoPlayerHelper.getPlayerView().apply {
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
        }
    }

    private fun List<GalleryModel>.toOurInventoryItems(): List<ItemFeedPost> {
        return this.map { model ->
            ItemFeedPost(model, object : ItemFeedPost.OnClickedListener {
                override fun onItemClicked(itemFeedPost: ItemFeedPost, view: View, position: Int) {
                    when (view.id) {
                        /*R.id.imgVolume -> {
                            requireContext().toast("Click working")
                            mFeedsAdapter.notifyItemChanged(position)
                        }*/
                    }
                }
            })
        }
    }

    private fun getGalleryData() {
        lifecycleScope.launch {
            try {
                val gallery = viewModel.getGallery()
                //progressDialogAvl.hide()
                //swipeRefreshLayout.isRefreshing = false
                if (gallery.status == Constants.SUCCESS_CODE) {
                    if (gallery.data.isNotEmpty()) {
                        /*getDatabaseReference()?.child(DB_POSTS)
                            ?.child(preferenceProvider.getDeviceId()!!)
                            ?.setValue(gallery.data)*/

                        //gallery.data[0].images[0].type = "gif"
                        //gallery.data[0].images[0].gifv = "https://i.imgur.com/STY9Ifs.gif"

                        val list = mutableListOf<GalleryModel>()

                        for (model in gallery.data) {
                            if (model.images_count > 0) {
                                model.images[0].type?.let {
                                    if (it.contains("jpg") ||
                                        it.contains("jpeg") ||
                                        it.contains("mp4")
                                    ) {
                                        list.add(model)
                                    }
                                }
                            }
                        }

                        initFeedPostRecyclerView(list.toOurInventoryItems())

                    }
                }
            } catch (e: MyApiException) {
                //progressDialogAvl.hide()
                e.printStackTrace()
            } catch (e: NoInternetException) {
                //progressDialogAvl.hide()
                e.printStackTrace()
            } catch (e: Exception) {
                //progressDialogAvl.hide()
                e.printStackTrace()
            }
        }
    }

    private fun searchGallery(query: String) {
        lifecycleScope.launch {
            try {
                val gallery = viewModel.searchGallery(query)
                //progressDialogAvl.hide()
                //swipeRefreshLayout.isRefreshing = false
                if (gallery.status == Constants.SUCCESS_CODE) {
                    if (gallery.data.isNotEmpty()) {
                        /*getDatabaseReference()?.child(DB_POSTS)
                            ?.child(preferenceProvider.getDeviceId()!!)
                            ?.setValue(gallery.data)*/

                        //gallery.data[0].images[0].type = "gif"
                        //gallery.data[0].images[0].gifv = "https://i.imgur.com/STY9Ifs.gif"

                        val list = mutableListOf<GalleryModel>()

                        for (model in gallery.data) {
                            if (model.images_count > 0) {
                                model.images[0].type?.let {
                                    if (it.contains("jpg") ||
                                        it.contains("jpeg") ||
                                        it.contains("mp4")
                                    ) {
                                        list.add(model)
                                    }
                                }
                            }
                        }

                        //mFeedsAdapter.add(list.toOurInventoryItems())
                        initFeedPostRecyclerView(list.toOurInventoryItems())
                    }
                }
            } catch (e: MyApiException) {
                //progressDialogAvl.hide()
                e.printStackTrace()
            } catch (e: NoInternetException) {
                //progressDialogAvl.hide()
                e.printStackTrace()
            } catch (e: Exception) {
                //progressDialogAvl.hide()
                e.printStackTrace()
            }
        }
    }
}
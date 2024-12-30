package com.techwin.githubexamples.ui.splash

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.techwin.githubexamples.MainActivity
import com.techwin.githubexamples.R
import kotlinx.android.synthetic.main.menu_left_drawer.*

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(R.raw.maddeit)
            .into(imgGif)

        //http://i.giphy.com/cgzW4StbNnuuc.gif
        //https://i.imgur.com/N8KkgJW.gif
        //https://i.imgur.com/STY9Ifs.gif

        /*Glide
            .with(this) // replace with 'this' if it's in activity
            .load("https://i.imgur.com/N8KkgJW.gifv")
            .error(R.drawable.image_circle) // show error drawable if the image is not a gif
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("dsfdsklj","dfsd")
                    return false
                }
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("dsfdsklj","dfsd")
                    return false
                }
            })
            .into(imgGif);*/

        /*val loading = Thread {
            override void run {
                try {
                    sleep(5000);
                    Intent main = new   Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(main);
                    finish();


                }

                catch (Exception e) {
                    e.printStackTrace();
                }

                finally {
                    finish();
                }
            }
        };

        loading.start();*/


        Handler().postDelayed({
            val mainIntent = Intent(requireContext(), MainActivity::class.java)
            startActivity(mainIntent)
        }, 2000)
    }
}
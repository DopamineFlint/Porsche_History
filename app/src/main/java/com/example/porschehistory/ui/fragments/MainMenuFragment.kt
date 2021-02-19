package com.example.porschehistory.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.porschehistory.R
import kotlinx.android.synthetic.main.fragment_main_menu.*

class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {

    companion object {
        const val FIVE_SECONDS_DELAY = 5000L
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        porsche_logo.animation = AnimationUtils.loadAnimation(context, R.anim.fade_in_anim)
        simple_text_view.animation = AnimationUtils.loadAnimation(context, R.anim.fade_in_anim)

        Handler().postDelayed(object : Runnable {
            override fun run() {
                //context let?
                context?.let {
                    Glide.with(it)
                        .asGif()
                        .load(
                            //context!!
                            resources.getIdentifier(
                                "anim_arrows_final",
                                "drawable",
                                context!!.packageName
                            )
                        )
                        .into(animated_left_arrow_gif)
                }

                context?.let {
                    Glide.with(it)
                        .asGif()
                        .load(
                            //context!!
                            resources.getIdentifier(
                                "anim_arrows_final",
                                "drawable",
                                context!!.packageName
                            )
                        )
                        .into(animated_right_arrow_gif)
                }
            }
        }, FIVE_SECONDS_DELAY)
    }
}
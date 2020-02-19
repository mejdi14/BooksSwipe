package asm.asmtunis.com.bookswipe.ui

import android.animation.ArgbEvaluator
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import asm.asmtunis.com.bookswipe.R
import asm.asmtunis.com.bookswipe.fragment.FirstFragment
import asm.asmtunis.com.bookswipe.fragment.SecondFragment
import asm.asmtunis.com.bookswipe.fragment.ThirdFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*


private const val NUM_PAGES = 3
private const val MEDIUM_DISTANCE = 400
private const val FAR_DISTANCE = 600

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    lateinit var evaluator: ArgbEvaluator
    lateinit var colors: IntArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        evaluator = ArgbEvaluator()
        context = this
        initColorsList()
        initBottomNavigationView()
        initPager()

    }

    private fun initColorsList() {
        colors = intArrayOf(
            context.getColor(R.color.yellow),
            context.getColor(R.color.orange),
            context.getColor(R.color.night)
        )
    }

    private fun initBottomNavigationView() {
        bottom_navigation.itemIconTintList = null
    }

    private fun initPager() {
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        pager.adapter = pagerAdapter
        manageViewPagerScrollActions(pagerAdapter)
    }

    private fun manageViewPagerScrollActions(pagerAdapter: ScreenSlidePagerAdapter) {
        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                changePageBackgroundColorOnSwipe(position, pagerAdapter, positionOffset)
                showDotsAndStars(position, positionOffset)
            }
        })
    }

    private fun showDotsAndStars(position: Int, positionOffset: Float) {
        when (position) {
            0 -> animateDots(positionOffset)
            1 -> animateStars(positionOffset)
        }
    }

    private fun animateStars(positionOffset: Float) {
        dots.animate().alpha(1 - positionOffset).setDuration(0).start()
        hideVegtablesObjectWithAnimation(positionOffset)
        showSpaceObjectsWithAnimation(positionOffset)
    }

    private fun showSpaceObjectsWithAnimation(positionOffset: Float) {
        mars.animate().translationX(0 - (MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
            .setDuration(0).start()
        neptune.animate().translationX((MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
            .setDuration(0).start()
        moon.animate().translationX((MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
            .setDuration(0).start()
        saturn.animate().translationX((MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
            .setDuration(0).start()
        venus.animate().translationY(0 - (FAR_DISTANCE - (FAR_DISTANCE * positionOffset)))
            .setDuration(0).start()
        sun.animate().translationY(0 - (FAR_DISTANCE - (FAR_DISTANCE * positionOffset)))
            .setDuration(0).start()
    }

    private fun hideVegtablesObjectWithAnimation(positionOffset: Float) {
        night_dots.animate().alpha(positionOffset).setDuration(0).start()
        tomato.animate().translationX(0 - ((MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
        potato.animate().translationX(0 - ((MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
        onion.animate().translationX(((MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
        pickel.animate().translationX(((MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
        carrots.animate().translationY(0 - ((FAR_DISTANCE * positionOffset))).setDuration(0).start()
        right_half_circle.animate().translationY(0 - ((FAR_DISTANCE * positionOffset))).setDuration(0).start()
    }

    private fun animateDots(positionOffset: Float) {
        hidePlantesObjectsWithAnimation(positionOffset)
        dots.animate().alpha(positionOffset).setDuration(0).start()
        showVegtablesObjectsWithAnimation(positionOffset)
    }

    private fun hidePlantesObjectsWithAnimation(positionOffset: Float) {
        animateFragmentObjects(positionOffset)
        leaf.animate().translationX((0 - (MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
        sanawbar.animate().translationX(((MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
        ears_tree.animate().translationX(((MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
        upper_purple_rose.animate().translationY(0 - ((FAR_DISTANCE * positionOffset))).setDuration(0).start()
        purple_rose.animate().translationY(0 - ((FAR_DISTANCE * positionOffset))).setDuration(0).start()
        points_leaf.animate().translationY(0 - ((FAR_DISTANCE * positionOffset))).setDuration(0).start()
    }

    private fun animateFragmentObjects(positionOffset: Float) {
        if (two_papers != null) {
            two_papers.animate().translationY(((MEDIUM_DISTANCE * positionOffset))).setDuration(0).start()
        }
    }

    private fun showVegtablesObjectsWithAnimation(positionOffset: Float) {
        tomato.animate().translationX(0 - (MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
            .setDuration(0).start()
        potato.animate().translationX(0 - (MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
            .setDuration(0).start()
        onion.animate().translationX((MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
            .setDuration(0).start()
        pickel.animate().translationX((MEDIUM_DISTANCE - (MEDIUM_DISTANCE * positionOffset)))
            .setDuration(0).start()
        carrots.animate().translationY(0 - (FAR_DISTANCE - (FAR_DISTANCE * positionOffset)))
            .setDuration(0).start()
        right_half_circle.animate()
            .translationY(0 - (FAR_DISTANCE - (FAR_DISTANCE * positionOffset))).setDuration(0)
            .start()
    }

    private fun changePageBackgroundColorOnSwipe(
        position: Int,
        pagerAdapter: ScreenSlidePagerAdapter,
        positionOffset: Float
    ) {
        if (position < pagerAdapter.itemCount - 1 && position < colors.size - 1) {
            pager.setBackgroundColor(
                (evaluator.evaluate(
                    positionOffset,
                    colors[position],
                    colors[position + 1]
                ) as Int)
            )
        } else
            pager.setBackgroundColor(colors[colors.size - 1])
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    FirstFragment()
                }
                1 -> {
                    SecondFragment()
                }
                else -> {
                    ThirdFragment()
                }
            }
        }
    }
}



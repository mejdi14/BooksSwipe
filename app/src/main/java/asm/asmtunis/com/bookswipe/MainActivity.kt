package asm.asmtunis.com.bookswipe

import android.animation.ArgbEvaluator
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import asm.asmtunis.com.bookswipe.fragment.FirstFragment
import asm.asmtunis.com.bookswipe.fragment.SecondFragment
import asm.asmtunis.com.bookswipe.fragment.ThirdFragment
import kotlinx.android.synthetic.main.activity_main.*


private const val NUM_PAGES = 3

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
                changePageColorOnSwipe(position, pagerAdapter, positionOffset)
                
            }
        })
    }

    private fun changePageColorOnSwipe(
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



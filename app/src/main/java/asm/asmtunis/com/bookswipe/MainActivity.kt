package asm.asmtunis.com.bookswipe

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View.OnScrollChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import asm.asmtunis.com.bookswipe.fragment.FirstFragment
import asm.asmtunis.com.bookswipe.fragment.SecondFragment
import asm.asmtunis.com.bookswipe.fragment.ThirdFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val NUM_PAGES = 3
class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    lateinit var context: Context
    private lateinit var bottom_navigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context=this
        initBottomNavigationView()
        initPager()

    }

    private fun initBottomNavigationView() {
        bottom_navigation= findViewById(R.id.bottom_navigation)
        bottom_navigation.itemIconTintList = null
    }

    private fun initPager() {
        viewPager = findViewById(R.id.pager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter
 /*       viewPager.setOnScrollChangeListener(object : OnScrollChangeListener {
            override fun onScrollChange(p0: View?, p1: Int, p2: Int, p3: Int, p4: Int) {
                Log.e("p1", "my Message"+p1)
                Log.e("p2", "my Message"+p2)
                Log.e("p3", "my Message"+p3)
                Log.e("p4", "my Message"+p4)
                Toast.makeText(applicationContext, "Test", Toast.LENGTH_LONG).show()
            }

        })
        viewPager.setOnDragListener(object : View.OnDragListener{
            override fun onDrag(p0: View?, p1: DragEvent?): Boolean {
              print("hello")
                return true
            }

        })*/

        viewPager.setOnClickListener {
            Toast.makeText(applicationContext, "Test", Toast.LENGTH_LONG).show()
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Toast.makeText(applicationContext, "Test", Toast.LENGTH_LONG).show()
                super.onPageSelected(position)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                Log.d("offset",positionOffset.toString())
                Log.d("pixel",positionOffsetPixels.toString())

            }
        })



        viewPager.setCurrentItem(2,true)


    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment{
            return when(position) {
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

private fun ViewPager2.setOnScrollChangeListener(
    onScrollChangeListener: OnScrollChangeListener,
    function: () -> Unit
) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

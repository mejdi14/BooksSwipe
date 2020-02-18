package asm.asmtunis.com.bookswipe.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asm.asmtunis.com.bookswipe.R
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_first, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun hideBottomCircle(positionOffset: Float){
        if (bottom_half_circle!=null){
           bottom_half_circle.animate().translationX( (0- (400 * positionOffset))).setDuration(0).start()
        }
        if (two_papers!=null){
            two_papers.animate().translationY( (0- (400 * positionOffset))).setDuration(0).start()
            Log.d("hello","nice")
        }
    }


}

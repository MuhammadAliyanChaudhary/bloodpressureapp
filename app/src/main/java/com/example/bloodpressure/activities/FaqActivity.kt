package com.example.bloodpressure.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import com.example.bloodpressure.R
import com.example.bloodpressure.databinding.ActivityFaqBinding
import com.example.bloodpressure.databinding.ActivityMainBinding

class FaqActivity : AppCompatActivity() {
    var binding : ActivityFaqBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaqBinding.inflate(layoutInflater)
        var view = binding!!.root
        setContentView(view)



        binding!!.backArrow.setOnClickListener{
            onBackPressed()
        }

        binding!!.cardView1.setOnClickListener{
            if(binding!!.textView1.visibility== View.VISIBLE){
                TransitionManager.beginDelayedTransition(binding!!.cardView1, AutoTransition())
                binding!!.textView1.visibility= View.GONE
                binding!!.arrowBtn1.setImageResource(R.drawable.ic_keyboard_arrow_up)
                binding!!.textHead1.setTextColor(getColor(R.color.black))
                binding!!.cardView1.setBackgroundColor(getColor(R.color.white))
            }else{
                TransitionManager.beginDelayedTransition(binding!!.cardView1, AutoTransition())
                binding!!.textView1.visibility= View.VISIBLE
                binding!!.arrowBtn1.setImageResource(R.drawable.ic_arrow_down)
                binding!!.textHead1.setTextColor(getColor(R.color.main_color))
                binding!!.cardView1.setBackgroundColor(getColor(R.color.main_color_light))
            }
        }
        binding!!.cardView2.setOnClickListener{
            if(binding!!.textView2.visibility== View.VISIBLE){
                TransitionManager.beginDelayedTransition(binding!!.cardView2, AutoTransition())
                binding!!.textView2.visibility= View.GONE
                binding!!.arrowBtn2.setImageResource(R.drawable.ic_keyboard_arrow_up)
                binding!!.textHead2.setTextColor(getColor(R.color.black))
                binding!!.cardView2.setBackgroundColor(getColor(R.color.white))
            }else{
                TransitionManager.beginDelayedTransition(binding!!.cardView2, AutoTransition())
                binding!!.textView2.visibility= View.VISIBLE
                binding!!.arrowBtn2.setImageResource(R.drawable.ic_arrow_down)
                binding!!.textHead2.setTextColor(getColor(R.color.main_color))
                binding!!.cardView2.setBackgroundColor(getColor(R.color.main_color_light))
            }
        }
        binding!!.cardView3.setOnClickListener{
            if(binding!!.textView3.visibility== View.VISIBLE){
                TransitionManager.beginDelayedTransition(binding!!.cardView3, AutoTransition())
                binding!!.textView3.visibility= View.GONE
                binding!!.arrowBtn3.setImageResource(R.drawable.ic_keyboard_arrow_up)
                binding!!.textHead3.setTextColor(getColor(R.color.black))
                binding!!.cardView3.setBackgroundColor(getColor(R.color.white))
            }else{
                TransitionManager.beginDelayedTransition(binding!!.cardView3, AutoTransition())
                binding!!.textView3.visibility= View.VISIBLE
                binding!!.arrowBtn3.setImageResource(R.drawable.ic_arrow_down)
                binding!!.textHead3.setTextColor(getColor(R.color.main_color))
                binding!!.cardView3.setBackgroundColor(getColor(R.color.main_color_light))
            }
        }
        binding!!.cardView4.setOnClickListener{
            if(binding!!.textView4.visibility== View.VISIBLE){
                TransitionManager.beginDelayedTransition(binding!!.cardView4, AutoTransition())
                binding!!.textView4.visibility= View.GONE
                binding!!.arrowBtn4.setImageResource(R.drawable.ic_keyboard_arrow_up)
                binding!!.textHead4.setTextColor(getColor(R.color.black))
                binding!!.cardView4.setBackgroundColor(getColor(R.color.white))
            }else{
                TransitionManager.beginDelayedTransition(binding!!.cardView4, AutoTransition())
                binding!!.textView4.visibility= View.VISIBLE
                binding!!.arrowBtn4.setImageResource(R.drawable.ic_arrow_down)
                binding!!.textHead4.setTextColor(getColor(R.color.main_color))
                binding!!.cardView4.setBackgroundColor(getColor(R.color.main_color_light))
            }
        }
        binding!!.cardView5.setOnClickListener{
            if(binding!!.textView5.visibility== View.VISIBLE){
                TransitionManager.beginDelayedTransition(binding!!.cardView5, AutoTransition())
                binding!!.textView5.visibility= View.GONE
                binding!!.arrowBtn5.setImageResource(R.drawable.ic_keyboard_arrow_up)
                binding!!.textHead5.setTextColor(getColor(R.color.black))
                binding!!.cardView5.setBackgroundColor(getColor(R.color.white))
            }else{
                TransitionManager.beginDelayedTransition(binding!!.cardView5, AutoTransition())
                binding!!.textView5.visibility= View.VISIBLE
                binding!!.arrowBtn5.setImageResource(R.drawable.ic_arrow_down)
                binding!!.textHead5.setTextColor(getColor(R.color.main_color))
                binding!!.cardView5.setBackgroundColor(getColor(R.color.main_color_light))
            }
        }
        binding!!.cardView6.setOnClickListener{
            if(binding!!.textView6.visibility== View.VISIBLE){
                TransitionManager.beginDelayedTransition(binding!!.cardView6, AutoTransition())
                binding!!.textView6.visibility= View.GONE
                binding!!.arrowBtn6.setImageResource(R.drawable.ic_keyboard_arrow_up)
                binding!!.textHead6.setTextColor(getColor(R.color.black))
                binding!!.cardView6.setBackgroundColor(getColor(R.color.white))
            }else{
                TransitionManager.beginDelayedTransition(binding!!.cardView6, AutoTransition())
                binding!!.textView6.visibility= View.VISIBLE
                binding!!.arrowBtn6.setImageResource(R.drawable.ic_arrow_down)
                binding!!.textHead6.setTextColor(getColor(R.color.main_color))
                binding!!.cardView6.setBackgroundColor(getColor(R.color.main_color_light))
            }
        }
        binding!!.cardView7.setOnClickListener{
            if(binding!!.textView7.visibility== View.VISIBLE){
                TransitionManager.beginDelayedTransition(binding!!.cardView7, AutoTransition())
                binding!!.textView7.visibility= View.GONE
                binding!!.arrowBtn7.setImageResource(R.drawable.ic_keyboard_arrow_up)
                binding!!.textHead7.setTextColor(getColor(R.color.black))
                binding!!.cardView7.setBackgroundColor(getColor(R.color.white))
            }else{
                TransitionManager.beginDelayedTransition(binding!!.cardView7, AutoTransition())
                binding!!.textView7.visibility= View.VISIBLE
                binding!!.arrowBtn7.setImageResource(R.drawable.ic_arrow_down)
                binding!!.textHead7.setTextColor(getColor(R.color.main_color))
                binding!!.cardView7.setBackgroundColor(getColor(R.color.main_color_light))
            }
        }
        binding!!.cardView8.setOnClickListener{
            if(binding!!.textView8.visibility== View.VISIBLE){
                TransitionManager.beginDelayedTransition(binding!!.cardView8, AutoTransition())
                binding!!.textView8.visibility= View.GONE
                binding!!.arrowBtn8.setImageResource(R.drawable.ic_keyboard_arrow_up)
                binding!!.textHead8.setTextColor(getColor(R.color.black))
                binding!!.cardView8.setBackgroundColor(getColor(R.color.white))
            }else{
                TransitionManager.beginDelayedTransition(binding!!.cardView8, AutoTransition())
                binding!!.textView8.visibility= View.VISIBLE
                binding!!.arrowBtn8.setImageResource(R.drawable.ic_arrow_down)
                binding!!.textHead8.setTextColor(getColor(R.color.main_color))
                binding!!.cardView8.setBackgroundColor(getColor(R.color.main_color_light))
            }
        }

    }
}
package com.game.gophers

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Toast
import com.game.gophers.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private var score = 0
    private lateinit var binding : ActivityMainBinding
    private var holeList = ArrayList<View>()
    private lateinit var targetHole : View
    private var life = 3
    private lateinit var handler : Handler
    val countDown = Runnable {
        kotlin.run {
            var i = (randomNum(10, 20) * 1000)
            Thread.sleep( 1000 )
            handler.sendEmptyMessage(1)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        holeList.add(binding.cell1)
        holeList.add(binding.cell2)
        holeList.add(binding.cell3)
        holeList.add(binding.cell4)
        holeList.add(binding.cell5)
        holeList.add(binding.cell6)
        holeList.add(binding.cell7)
        holeList.add(binding.cell8)
        holeList.add(binding.cell9)

        binding.cell1.setOnClickListener(this)
        binding.cell2.setOnClickListener(this)
        binding.cell3.setOnClickListener(this)
        binding.cell4.setOnClickListener(this)
        binding.cell5.setOnClickListener(this)
        binding.cell6.setOnClickListener(this)
        binding.cell7.setOnClickListener(this)
        binding.cell8.setOnClickListener(this)
        binding.cell9.setOnClickListener(this)

        handler = object:Handler(){
            @SuppressLint("ResourceAsColor")
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (msg.what == 1){
                    targetHole.setBackgroundColor(R.color.normal)
                    showGophers()
                }
            }
        }

        binding.start.setOnClickListener(this)

        binding.score.text = "CurrentScore: " + score

    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(p0: View?) {
        if (p0 != null) {
            if (p0 == binding.start){
                startGame()
            }else if (p0.equals(targetHole) ){
                p0.setBackgroundColor(R.color.normal)
                score += 1
                showGophers()
                binding.score.text = "CurrentScore: " + score
            }else{
                score -= 1
                binding.score.text = "CurrentScore: " + score
            }
        }

    }

    private fun startGame(){
        life = 3
        score = 0
        showGophers()
    }

    @SuppressLint("ResourceAsColor")
    fun showGophers(){
        targetHole = holeList[randomNum(1, 9)]
        targetHole.setBackgroundColor(R.color.clicked)
        Thread(countDown).start()
    }

//    fun showGophersTime(){
//
//    }

    private fun randomNum(i: Int, j: Int) : Int{
        val r = Random()
        return if ( i > j ){
            r.nextInt(i - j) + j
        }else{
            r.nextInt(j - i) + i
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (handler != null){
            handler.removeCallbacksAndMessages(null)
        }
    }

}

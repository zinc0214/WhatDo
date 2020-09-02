package zinc0214.whatdo.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import zinc0214.whatdo.R
import zinc0214.whatdo.data.CardInfo
import zinc0214.whatdo.util.setStatusBar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ViewDataBinding
    private var infoList = ArrayList<CardInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        val num = Random.nextInt(2)
        if (num == 1) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main_blue)
            setStatusBar(this@MainActivity, R.color.blueStart)
        } else {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main_pink)
            setStatusBar(this@MainActivity, R.color.pinkStart)
        }
    }

}
package zinc0214.whatdo.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import zinc0214.whatdo.R
import zinc0214.whatdo.data.ViewType
import zinc0214.whatdo.databinding.ActivityMainBinding
import zinc0214.whatdo.util.setStatusBar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewType : ViewType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {
        val num = Random.nextInt(2)
        with(binding) {
            viewType = if (num == 1) {
                this.rootLayout.setBackgroundResource(R.drawable.blue_background)
                this.startView.setBackgroundResource(R.drawable.blue_button_background)
                setStatusBar(this@MainActivity, R.color.blueStart)
                ViewType.BLUE
            } else {
                this.rootLayout.setBackgroundResource(R.drawable.pink_background)
                this.startView.setBackgroundResource(R.drawable.pink_button_background)
                setStatusBar(this@MainActivity, R.color.pinkStart)
                ViewType.PINK
            }
        }
    }
}
package zinc0214.whatdo.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import zinc0214.whatdo.R
import zinc0214.whatdo.data.CardInfo
import zinc0214.whatdo.data.ViewType
import zinc0214.whatdo.databinding.ActivityMainBinding
import zinc0214.whatdo.util.setStatusBar
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewType: ViewType
    private var infoList = ArrayList<CardInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        infoList = intent.getSerializableExtra("contents") as ArrayList<CardInfo>
        val isBlueType = intent.getBooleanExtra("isBlueType", true)
        viewType = if (isBlueType) ViewType.BLUE else ViewType.PINK

        setUpView()

        MobileAds.initialize(this) {
            val adRequest = AdRequest.Builder().build()
            binding.adView.loadAd(adRequest)
        }
    }

    private fun setUpView() {
        with(binding) {
            type = viewType
            activity = this@MainActivity
        }

        if (viewType == ViewType.BLUE) {
            setStatusBar(this@MainActivity, R.color.blueStart)
        } else {
            setStatusBar(this@MainActivity, R.color.pinkStart)
        }
    }

    fun startClickListener() {
        getCardInfo()
        binding.homeGroup.visibility = View.GONE
        binding.contentGroup.visibility = View.VISIBLE
    }

    fun getCardInfo() {
        val num = Random.nextInt(infoList.size - 1)
        binding.cardInfo = infoList[num]
        binding.notifyChange()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        binding.logoImage.visibility = View.GONE
        finish()
    }
}
package zinc0214.whatdo.presentation

import android.animation.ObjectAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import zinc0214.whatdo.R
import zinc0214.whatdo.data.CardInfo
import zinc0214.whatdo.data.ViewType
import zinc0214.whatdo.databinding.ActivitySplashBinding
import zinc0214.whatdo.util.setStatusBar
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private var isBlueType = true
    private var infoList = ArrayList<CardInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        val num = Random.nextInt(2)
        isBlueType = if (num == 1) {
            setStatusBar(this@SplashActivity, R.color.blueStart)
            binding.type = ViewType.BLUE
            true
        } else {
            setStatusBar(this@SplashActivity, R.color.pinkStart)
            binding.type = ViewType.PINK
            false
        }
        loadContent()
    }

    private fun loadContent() {

        val database = Firebase.database.reference
        val contentDB = database.child(getLocale())
        // Read from the database
        contentDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("ayhan", "error : $error")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<ArrayList<CardInfo?>>()
                value?.let {
                    for (info in value) {
                        info?.let { infoList.add(info) }
                    }
                }
                GlobalScope.launch {
                    delay(1000L)
                    withContext(Dispatchers.Main) {
                        startMain()
                    }
                }
                Log.e("ayhan", "infoListSize : ${infoList.size}")
            }
        })
    }

    private fun getLocale(): String {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (applicationContext.resources.configuration.locales.toLanguageTags() == "ko-KR") "ko" else "en"
        } else {
            if (Locale.getDefault().displayCountry == "대한민국") "ko" else "en"
        }
    }

    private fun startMain() {
        val intent = Intent(this, MainActivity::class.java)
        ObjectAnimator.ofFloat(binding.bookmark, "alpha", 1f, 0f).start()
        ObjectAnimator.ofFloat(binding.imageLogo, "alpha", 1f, 0f).start()

        intent.putExtra("contents", infoList)
        intent.putExtra("isBlueType", isBlueType)

        val transitionActivityOptions =
            ActivityOptions.makeSceneTransitionAnimation(this, binding.textLogo, "logo")
        startActivity(intent, transitionActivityOptions.toBundle())
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

}
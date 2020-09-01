package zinc0214.whatdo.presentation

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.coroutines.*
import zinc0214.whatdo.R
import zinc0214.whatdo.databinding.ActivitySplashBinding
import java.util.concurrent.Delayed


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        GlobalScope.launch {
            delay(1000L)
            withContext(Dispatchers.Main) {
                startMain()
            }
        }
    }


    private fun startMain() {
        val i = Intent(this, MainActivity::class.java)

        val transitionActivityOptions =
            ActivityOptions.makeSceneTransitionAnimation(this, binding.textLogo, "logo")
        startActivity(i, transitionActivityOptions.toBundle())

    }
}
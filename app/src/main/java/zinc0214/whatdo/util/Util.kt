package zinc0214.whatdo.util

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import zinc0214.whatdo.R

fun setStatusBar(activity: Activity, colors: Int = R.color.colorPrimary) {

    val window = activity.window

    if (colors != android.R.color.transparent) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    } else {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (colors == android.R.color.white)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = activity.resources.getColor(colors, null)
    } else
        window.statusBarColor = ContextCompat.getColor(activity, colors)
}
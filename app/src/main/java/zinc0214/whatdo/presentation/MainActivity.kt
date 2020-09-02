package zinc0214.whatdo.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
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
        setUpView()
    }

    private fun setUpView() {
        val num = Random.nextInt(2)
        viewType = if (num == 1) {
            setStatusBar(this@MainActivity, R.color.blueStart)
            ViewType.BLUE
        } else {
            setStatusBar(this@MainActivity, R.color.pinkStart)
            ViewType.PINK
        }

        with(binding) {
            type = viewType
            activity = this@MainActivity
        }

        loadContent()
    }

    private fun loadContent() {
        val database = Firebase.database.reference
        val contentDB = database.child("ko")
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
                Log.e("ayhan", "infoListSize : ${infoList.size}")
            }
        })
    }

    public fun startClickListener() {
        getCardInfo()
        binding.homeGroup.visibility = View.GONE
        binding.contentGroup.visibility = View.VISIBLE
    }

    public fun getCardInfo() {
        val num = Random.nextInt(infoList.size - 1)
        binding.cardInfo = infoList[num]
        binding.notifyChange()
    }
}
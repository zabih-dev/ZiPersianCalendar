package zabih.lib.zipersiancalendar.sample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import zabih.lib.zipersiancalendar.ZiPersianCalendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView1: TextView = findViewById(R.id.textView1)

        val pCal = ZiPersianCalendar()
        textView1.text = pCal.longDate

    }
}
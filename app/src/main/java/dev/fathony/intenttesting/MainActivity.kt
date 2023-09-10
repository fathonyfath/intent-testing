package dev.fathony.intenttesting

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(createAppNotificationIntent())
        }
    }

    private fun createAppNotificationIntent(): Intent {
        val intent = Intent()
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O) {
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
        } else {
            intent.action = ACTION_NOTIFICATION_SETTINGS
            intent.putExtra(KEY_APP_PACKAGE, packageName)
            intent.putExtra(KEY_APP_UID, applicationInfo.uid)
        }
        return intent
    }

    companion object {
        const val ACTION_NOTIFICATION_SETTINGS = "android.settings.APP_NOTIFICATION_SETTINGS"
        const val KEY_APP_PACKAGE = "app_package"
        const val KEY_APP_UID = "app_uid"
    }
}
package com.wisnu.language

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var myLocale: Locale? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_language1.text = getString(R.string.bahasa)
        tv_language2.text = getString(R.string.bahasa)
//        tv_language2.text = applicationContext.getString(R.string.bahasa)

        btn_en.setOnClickListener(this@MainActivity)
        btn_ml.setOnClickListener(this@MainActivity)
        btn_th.setOnClickListener(this@MainActivity)
        loadLocale()
    }


//    override fun attachBaseContext(newBase: Context) {
////        super.attachBaseContext(LocalizationUtil.applyLanguage(newBase, "ml"))
////        super.attachBaseContext(LocalizationUtil.applyLanguage(newBase, "id"))
//    }

    private fun loadLocale() {
        val langPref = "Language"
        val prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE)
        val language = prefs.getString(langPref, "")
        changeLang(language)
    }

    private fun saveLocale(lang: String?) {
        val langPref = "Language"
        val prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(langPref, lang)
        editor.apply()
    }

    private fun changeLang(lang: String) {
        if (lang.equals("", ignoreCase = true)) return
        myLocale = Locale(lang)
        saveLocale(lang)
        Locale.setDefault(myLocale)
        val config = Configuration()
        config.locale = myLocale
        baseContext.resources
            .updateConfiguration(config, baseContext.resources.displayMetrics)

    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (myLocale != null) {
            newConfig.locale = myLocale
            Locale.setDefault(myLocale)
            baseContext.resources
                .updateConfiguration(newConfig, baseContext.resources.displayMetrics)
        }
    }

    override fun onClick(v: View?) {
        var lang = "en"
        when (v?.id) {
            R.id.btn_en -> lang = "en"
            R.id.btn_th -> lang = "th"
            R.id.btn_ml -> lang = "hi"
            else -> {
            }
        }
        changeLang(lang)

        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
package io.bonat.customer_lib.utils

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import java.util.*
import javax.inject.Inject

class LocaleHelper @Inject constructor() {
    fun onAttach(context: Context, defaultLanguage: String): Context {
        val lang = getPersistedData(context, defaultLanguage)
        return setLocale(context, lang)
    }

    fun getLanguage(context: Context): String? {
        return getPersistedData(context, Locale.getDefault().language)
    }

/*
    fun isArabic(context: Context): Boolean {
        return getLanguage(context)!!.trim { it <= ' ' }.matches("ar")
    }
*/

    companion object {
        private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
        fun onAttach(context: Context): Context {
            val lang = getPersistedData(context, Locale.getDefault().language)
            return setLocale(context, lang)
        }

        fun setLocale(context: Context, language: String?): Context {
            persist(context, language)
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                updateResources(context, language)
            } else updateResourcesLegacy(context, language)
        }

        private fun getPersistedData(context: Context, defaultLanguage: String): String? {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(SELECTED_LANGUAGE, defaultLanguage)
        }

        fun persist(context: Context?, language: String?) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = preferences.edit()
            editor.putString(SELECTED_LANGUAGE, language)
            editor.apply()
        }

        @TargetApi(Build.VERSION_CODES.N)
        private fun updateResources(context: Context, language: String?): Context {
            val locale = Locale(language)
            Locale.setDefault(locale)
            val configuration = context.resources.configuration
            configuration.setLocale(locale)
            configuration.setLayoutDirection(locale)
            return context.createConfigurationContext(configuration)
        }

        private fun updateResourcesLegacy(context: Context, language: String?): Context {
            val locale = Locale(language)
            Locale.setDefault(locale)
            val resources = context.resources
            val configuration = resources.configuration
            configuration.locale = locale
            configuration.setLayoutDirection(locale)
            resources.updateConfiguration(configuration, resources.displayMetrics)
            return context
        }

        fun updateResources2(context: Context, language: String) {
            val res = context.resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.setLocale(Locale(language.toLowerCase())) // API 17+ only.
            val locale = Locale(language.toLowerCase())
            Locale.setDefault(locale)
            res.updateConfiguration(conf, dm)
        }
    }
}
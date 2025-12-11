package com.example.lab_week_11_a // Sesuaikan dengan package kamu

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PreferenceWrapper(private val sharedPreferences: SharedPreferences) {

    // The text Live data is used to notify the view model when the text changes
    private val textLiveData = MutableLiveData<String>()

    init {
        // Register a Listener to the shared preferences
        // The Listener is called when the shared preferences change
        sharedPreferences.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            when (key) {
                KEY_TEXT -> {
                    // Notify the view model that the text has changed
                    // The view model will then notify the activity
                    textLiveData.postValue(sharedPreferences.getString(KEY_TEXT, ""))
                }
            }
        }
    }

    // Save the text to the shared preferences
    fun saveText(text: String) {
        sharedPreferences.edit()
            .putString(KEY_TEXT, text)
            .apply()
    }

    // Get the text from the shared preferences
    fun getText(): LiveData<String> {
        textLiveData.postValue(sharedPreferences.getString(KEY_TEXT, ""))
        return textLiveData
    }

    // The key used to store the text in the shared preferences
    companion object {
        const val KEY_TEXT = "keyText"
    }
}
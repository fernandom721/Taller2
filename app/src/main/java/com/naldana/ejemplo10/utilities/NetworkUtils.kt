package com.naldana.ejemplo10.utilities

import android.net.Uri
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class NetworkUtils {

    val BASE_URL = "http://www.omdbapi.com/?i=tt3896198&apikey=5a15fd67"
    val COIN_NAME = "name"
    val COIN_IMAGE = "image"
    val COIN_COUNTRY = "country"
    val COIN_VALUE = "value"

    private val TAG = NetworkUtils::class.java.simpleName

    fun buildUrl (root: String, coinID: String): URL {
        val builtUri = Uri.parse(BASE_URL)
            .buildUpon()
            .appendPath(root)
            .appendPath(coinID)
            .build()

        val url = try {
            URL(builtUri.toString())
        }catch (e: MalformedURLException){
            URL("")
        }

        Log.d(TAG, "Built URI $url")

        return url
    }

    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL): String {
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val `in` = urlConnection.inputStream

            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            return if (hasInput) {
                scanner.next()
            } else {
                ""
            }
        } finally {
            urlConnection.disconnect()
        }
    }

}
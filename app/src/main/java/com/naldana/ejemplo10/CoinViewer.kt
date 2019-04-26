package com.naldana.ejemplo10

import android.annotation.SuppressLint
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.naldana.ejemplo10.utilities.NetworkUtils
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.grid_coins_layout.*
import org.json.JSONObject
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class CoinViewer : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coin_layout)

        val uri:String = this.intent.extras.getString("COIN")
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        FetchCoincs().execute(uri)
    }

    fun init(coin: Coin){
        name.text = coin.name
        //TODO VALORES QUE SE REFERENCIA EN EL LAYOUT DE COIN
        country.text = coin.text
        value.text = coin.text
    }


    private inner class FetchCoincs : AsyncTask<String,String,String>(){
        override fun doInBackground(vararg params: String?): String {

            if(params.isNullOrEmpty()) return ""

            val url = params[0]
            val CoinAPI = Uri.parse(url).buildUpon().build()
            val finalurl = try {
                URL(CoinAPI.toString())
            }catch (e: MalformedURLException){
                URL("")
            }

            return try {
                NetworkUtils().getResponseFromHttpUrl(finalurl)
            }catch (e: IOException){
                e.printStackTrace()
                ""
            }

        }

        override fun onPostExecute(coininfo: String) {

            val coin = if(!coininfo.isEmpty()){
                val root = JSONObject(coininfo)
                val name = root.getString("name")
                val country = root.getString("Country")
                val value = root.getString("value")

                Coin(root.getInt("id"),
                    root.getString("name").capitalize(),
                    root.getString("country"),
                    root.getString("value"))
            }else{
                Coin(0,"N/A","N/A","N/A")
            }
            init(coin)
        }


    }

}
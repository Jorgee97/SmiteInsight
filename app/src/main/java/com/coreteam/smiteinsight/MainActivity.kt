package com.coreteam.smiteinsight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.coreteam.smiteinsight.api.SmiteApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var smiteApi: SmiteApi

    val smiteSessionHandler = SmiteSessionHandler()
    var session = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).mainComponent.inject(this)

        val observer = smiteApi.getSession(smiteSessionHandler.getDevKey(), smiteSessionHandler.signature(),
            smiteSessionHandler.getTimeStamp())

        observer
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                session = it.session_id
                Log.d("SESSION", session)
                Log.d("SIGNATURE", smiteSessionHandler.signature())
                val esports = smiteApi.getEsportsDetails(smiteSessionHandler.getDevKey(), smiteSessionHandler.signature(),
                    session, smiteSessionHandler.getTimeStamp())

                esports.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("ESPORTS", it.toString());
                    },  {
                        Log.e("Error", it.message)
                    })
            }, {
                Log.wtf("Error", it.message)
            })



    }
}

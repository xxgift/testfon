package com.estimote.proximity

import android.app.Application
import com.estimote.proximity_sdk.api.EstimoteCloudCredentials

//
// Running into any issues? Drop us an email to: contact@estimote.com
//

class MyApplication : Application() {

    val cloudCredentials =  EstimoteCloudCredentials("testfon-09y", "07041deab4846fdda06c89dd77f8e01f")
}

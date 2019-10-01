package com.estimote.proximity.estimote

import android.content.Context
import android.util.Log
import com.estimote.proximity.MainActivity
import com.estimote.proximity.MyApplication
import com.estimote.proximity_sdk.api.ProximityObserver
import com.estimote.proximity_sdk.api.ProximityObserverBuilder
import com.estimote.proximity_sdk.api.ProximityZoneBuilder
import java.util.*

//
// Running into any issues? Drop us an email to: contact@estimote.com
//

class ProximityContentManager(private val context: Context) {

    private var proximityObserverHandler: ProximityObserver.Handler? = null

    fun start() {

        val proximityObserver = ProximityObserverBuilder(context, (context.applicationContext as MyApplication).cloudCredentials)
                .withTelemetryReportingDisabled()
                .withEstimoteSecureMonitoringDisabled()
                .onError { throwable ->
                    Log.e("app", "proximity observer error: $throwable")
                }
                .withBalancedPowerMode()
                .build()

        val zone = ProximityZoneBuilder()
                .forTag("testfon-09y")
                .inNearRange()
                .onContextChange { contexts ->
                    val nearbyContent = ArrayList<ProximityContent>(contexts.size)
                    for (context in contexts) {
                        val title: String = context.attachments["testfon-09y/title"] ?: "unknown"
                        val subtitle = Utils.getShortIdentifier(context.deviceId)
                        nearbyContent.add(ProximityContent(title, subtitle))
                    }
                    (context as MainActivity).setNearbyContent(nearbyContent)
                }
                .build()

        proximityObserverHandler = proximityObserver.startObserving(zone)
    }

    fun stop() {
        proximityObserverHandler?.stop()
    }
}

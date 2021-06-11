package com.england.android

import android.app.Application
import com.england.android.ui.feed.feedModule
import com.england.startKoin

class EnglandApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(feedModule)
        }
    }
}

package com.techwin.githubexamples

import android.app.Application
import com.techwin.githubexamples.data.db.AppDatabase
import com.techwin.githubexamples.data.network.Apis
import com.techwin.githubexamples.data.network.NetworkConnectionInterceptor
import com.techwin.githubexamples.data.preferences.PreferenceProvider
import com.techwin.githubexamples.data.respositories.UserRepository
import com.techwin.githubexamples.ui.main.home.view_model.HomeFragmentViewModelFactory
import com.techwin.githubexamples.util.FieldsValidator
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import com.yarolegovich.slidingrootnav.SlidingRootNavLayout
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MainApplication : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
    }

    override val kodein = Kodein.lazy {

        import(androidXModule(this@MainApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { FieldsValidator() }
        bind() from singleton { Apis(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance()) }
        bind() from singleton { HomeFragmentViewModelFactory(instance()) }
    }
}
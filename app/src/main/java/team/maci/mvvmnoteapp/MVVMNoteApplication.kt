package team.maci.mvvmnoteapp

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import team.maci.mvvmnoteapp.di.AppBinderModule
import team.maci.mvvmnoteapp.di.AppComponent
import team.maci.mvvmnoteapp.di.ComponentContainer
import team.maci.mvvmnoteapp.di.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

class MVVMNoteApplication : Application(), HasActivityInjector{
    override fun onCreate() {
        super.onCreate()

        createTimberTree()

        initComponentContainer()
    }

    private fun createTimberTree(){
        if(!BuildConfig.DEBUG){
            return
        }

        Timber.plant(Timber.DebugTree())
    }

    private fun initComponentContainer(){
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        ComponentContainer.init(appComponent)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return ComponentContainer.get<AppComponent>().provideDispatchingActivityInjector()
    }
}
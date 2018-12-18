package team.maci.mvvmnoteapp.di

import android.app.Activity
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.DispatchingAndroidInjector
import team.maci.mvvmnoteapp.MainActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class,
    AppBinderModule::class,
    ActivityModule::class,
    AndroidInjectionModule::class
])
interface AppComponent{

    fun provideDispatchingActivityInjector() : DispatchingAndroidInjector<Activity>


    @Component.Builder
    interface Builder{
        @BindsInstance fun application(application: Application) : Builder
        fun build() : AppComponent
    }

}
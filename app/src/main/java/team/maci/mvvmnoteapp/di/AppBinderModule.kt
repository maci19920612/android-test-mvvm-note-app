package team.maci.mvvmnoteapp.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class AppBinderModule{
    @Binds
    abstract fun provideAppContext(application: Application) : Context
}
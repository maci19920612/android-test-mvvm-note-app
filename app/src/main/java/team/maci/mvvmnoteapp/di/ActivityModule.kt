package team.maci.mvvmnoteapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import team.maci.mvvmnoteapp.ui.details.DetailsActivity
import team.maci.mvvmnoteapp.ui.details.DetailsBinderModule
import team.maci.mvvmnoteapp.ui.details.DetailsModule
import team.maci.mvvmnoteapp.ui.edit.EditActivity
import team.maci.mvvmnoteapp.ui.edit.EditBinderModule
import team.maci.mvvmnoteapp.ui.edit.EditModule
import team.maci.mvvmnoteapp.ui.list.ListActivity
import team.maci.mvvmnoteapp.ui.list.ListBinderModule
import team.maci.mvvmnoteapp.ui.list.ListModule
import team.maci.mvvmnoteapp.ui.list.ListModule_ProvideViewModelProviderFactory
import team.maci.mvvmnoteapp.ui.login.LoginActivity
import team.maci.mvvmnoteapp.ui.login.LoginBinderModule
import team.maci.mvvmnoteapp.ui.login.LoginModule
import team.maci.mvvmnoteapp.ui.register.RegisterActivity
import team.maci.mvvmnoteapp.ui.register.RegisterBinderModule
import team.maci.mvvmnoteapp.ui.register.RegisterModule
import team.maci.mvvmnoteapp.ui.splash.SplashActivity
import team.maci.mvvmnoteapp.ui.splash.SplashBinderModule
import team.maci.mvvmnoteapp.ui.splash.SplashModule


@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [
        SplashBinderModule::class,
        SplashModule::class
    ])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [
        LoginBinderModule::class,
        LoginModule::class
    ])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [
        RegisterModule::class,
        RegisterBinderModule::class
    ])
    abstract fun bindRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector(modules = [
        ListModule::class,
        ListBinderModule::class
    ])
    abstract fun bindListActivity(): ListActivity

    @ContributesAndroidInjector(modules = [
        DetailsModule::class,
        DetailsBinderModule::class
    ])
    abstract fun bindDetailsActivity(): DetailsActivity

    @ContributesAndroidInjector(modules = [
        EditModule::class,
        EditBinderModule::class
    ])
    abstract fun bindEditActivity(): EditActivity

}
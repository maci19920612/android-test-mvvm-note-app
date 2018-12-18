package team.maci.mvvmnoteapp.di

import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.multibindings.IntoMap
import android.app.Activity
import dagger.android.AndroidInjector
import team.maci.mvvmnoteapp.MainActivity
import team.maci.mvvmnoteapp.ui.details.DetailsActivity
import team.maci.mvvmnoteapp.ui.details.DetailsComponent
import team.maci.mvvmnoteapp.ui.edit.EditActivity
import team.maci.mvvmnoteapp.ui.edit.EditComponent
import team.maci.mvvmnoteapp.ui.list.ListActivity
import team.maci.mvvmnoteapp.ui.list.ListComponent
import team.maci.mvvmnoteapp.ui.login.LoginActivity
import team.maci.mvvmnoteapp.ui.login.LoginActivity_MembersInjector
import team.maci.mvvmnoteapp.ui.login.LoginComponent
import team.maci.mvvmnoteapp.ui.register.RegisterActivity
import team.maci.mvvmnoteapp.ui.register.RegisterComponent
import team.maci.mvvmnoteapp.ui.splash.SplashActivity
import team.maci.mvvmnoteapp.ui.splash.SplashComponent


@Module
abstract class ActivityModule{
    @Binds
    @IntoMap
    @ActivityKey(SplashActivity::class)
    abstract fun bindSplashActivity(builder: SplashComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity::class)
    abstract fun bindLoginActivity(builder: LoginComponent.Builder) : AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(RegisterActivity::class)
    abstract fun bindRegisterActivity(builder: RegisterComponent.Builder) : AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(ListActivity::class)
    abstract fun bindListActivity(builder: ListComponent.Builder) : AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(DetailsActivity::class)
    abstract fun bindDetailsActivity(builder: DetailsComponent.Builder) : AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(EditActivity::class)
    abstract fun bindEditActivity(builder: EditComponent.Builder) : AndroidInjector.Factory<out Activity>

}
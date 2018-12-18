package team.maci.mvvmnoteapp.di

import android.content.Context
import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import team.maci.mvvmnoteapp.Environment
import team.maci.mvvmnoteapp.api.ApiClient
import team.maci.mvvmnoteapp.manager.AuthService
import team.maci.mvvmnoteapp.manager.PreferenceManager
import team.maci.mvvmnoteapp.ui.login.LoginComponent
import team.maci.mvvmnoteapp.ui.splash.SplashComponent
import team.maci.mvvmnoteapp.util.UIDispatcher
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor
import team.maci.mvvmnoteapp.ui.details.DetailsActivity
import team.maci.mvvmnoteapp.ui.details.DetailsComponent
import team.maci.mvvmnoteapp.ui.edit.EditComponent
import team.maci.mvvmnoteapp.ui.list.ListComponent
import team.maci.mvvmnoteapp.ui.register.RegisterComponent
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*


@Module(
    subcomponents = [
        SplashComponent::class,
        LoginComponent::class,
        RegisterComponent::class,
        ListComponent::class,
        DetailsComponent::class,
        EditComponent::class
    ]
)
class AppModule {
    @Provides
    @Singleton
    fun provideUIDispatcher() = UIDispatcher()

    @Provides
    @Singleton
    fun provideSharedPreference(context: Context) =
        context.getSharedPreferences(context.packageName + ".preference", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideRetrofit(environment: Environment, okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        val builder = Retrofit.Builder()

        builder
            .baseUrl(environment.baseApiUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient)


        return builder.build()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideAuthService(preferenceManager: PreferenceManager, apiClient: ApiClient): AuthService {
        return AuthService(preferenceManager, apiClient)
    }


    @Provides
    @Singleton
    fun provideGson() : Gson{
        val gsonBuilder = GsonBuilder()

        gsonBuilder.registerTypeAdapter(Date::class.java, object: JsonSerializer<Date?>, JsonDeserializer<Date?> {
            private val dateFormat = SimpleDateFormat("yyyy/MM/dd/HH/mm/ss", Locale.getDefault())


            override fun serialize(src: Date?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
                return if(src == null){
                    null
                }else{
                    JsonPrimitive(dateFormat.format(src))
                }
            }

            override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Date? {
                return if(json.isJsonNull){
                    return null
                }else{
                    dateFormat.parse(json.asString)
                }
            }

        })
        return gsonBuilder.create()
    }
}
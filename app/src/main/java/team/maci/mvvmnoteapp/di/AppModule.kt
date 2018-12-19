package team.maci.mvvmnoteapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import team.maci.mvvmnoteapp.Environment
import team.maci.mvvmnoteapp.api.ApiClient
import team.maci.mvvmnoteapp.database.NoteDatabase
import team.maci.mvvmnoteapp.manager.AuthService
import team.maci.mvvmnoteapp.manager.PreferenceManager
import team.maci.mvvmnoteapp.util.UIDispatcher
import java.lang.reflect.Type
import java.util.*
import javax.inject.Singleton


@Module()
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

        gsonBuilder.registerTypeAdapter(DateTime::class.java, object: JsonSerializer<DateTime?>, JsonDeserializer<DateTime?> {

            override fun serialize(src: DateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
                return if(src == null){
                    null
                }else{
                    JsonPrimitive(src.toString(ISODateTimeFormat.dateTime()))
                }
            }

            override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): DateTime? {
                return if(json.isJsonNull){
                    return null
                }else{
                    DateTime(json.asString)
                }
            }

        })
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(application: Application) : NoteDatabase{
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            "note-database"
        ).build()
    }

    @Provides
    fun provideUserDao(noteDatabase: NoteDatabase) = noteDatabase.userDao()

    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()
}
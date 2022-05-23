package com.sanyao.machin.di
import com.mapgis.baselib.moshiFactories.MoshiUtils
import com.sanyao.machin.api.ApiUrl
import com.sanyao.machin.constant.GlobalValues
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val timeoutRead = 30   //In seconds
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val timeoutConnect = 30   //In seconds

@Module
@InstallIn(SingletonComponent::class)
object HttpManageModule {

    @Provides
    fun provideBaseUrl() = ApiUrl.BASE_URL

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (GlobalValues.debugMode) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    private fun okhttpClient(): OkHttpClient? {

        return null
    }


    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(logger)
//        .addInterceptor(HeaderInterceptor())
//        .addInterceptor(ResponseInterceptor())
        .connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        .readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(MoshiUtils.moshiBuild))
        .build()



}
package org.wikiedufoundation.wikiedudashboard.data.network

import com.google.gson.GsonBuilder

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.wikiedufoundation.wikiedudashboard.util.Urls
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProviderUtils {
    companion object {

        val apiObject: WikiEduDashboardApi
            get() {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
                        .readTimeout(5, TimeUnit.MINUTES).build()
                val gson = GsonBuilder()
                        .setLenient()
                        .create()
                val retrofit = Retrofit.Builder()
                        .baseUrl(Urls.BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                return retrofit.create(WikiEduDashboardApi::class.java)
            }
    }
}
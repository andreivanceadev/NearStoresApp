package com.andreivanceadev.data.network

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


abstract class NearStoresRetrofit {

    companion object {
        fun getRetrofit(): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rest.paysafecard.com/rest/")
            .client(createOkHttpClient())
            .build()

        private fun createOkHttpClient(): OkHttpClient {
            val certificatePinner = CertificatePinner.Builder()
                .add(
                    "rest.paysafecard.com",
                    "sha256/OAt/zfrM/X/ZDXhpHPU6i/ZZd0fZAQKVqpZSmRdg7V4=",
                    "sha256/yMKGNy9ETFo1MzQT5X95eJfzTcthHykS+MbEdkLLZyg=",
                    "sha256/4RqkrIrErrVo/xOhdRj/PH+oayfIHW0q+GDLMu1KtEg=",
                    "sha256/XbvMTGE+o0q2THhvRYZcx4ewplxh/VuVsYBcljBiiqE=",
                    "sha256/kGf17cbOkRz2/gJG7neCH/EmAsq740gtqzx5Rmgn9wI=",
                    "sha256/l48xflikR+ED5yb5fNCQP1o+p4c15U7YDTuj35pj39I=",
                    "sha256/0enbD8qdYHHaavFafUYuyzoEzyAWODJyUDXpXpKYVBk="
                )
                .build()
            return OkHttpClient.Builder()
                .certificatePinner(certificatePinner)
                .addInterceptor(HttpLoggingInterceptor())
                .build()
        }
    }
}
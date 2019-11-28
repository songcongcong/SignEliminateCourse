package com.example.retrofitmvplibrary.retrofit;

import android.content.Context;
import android.os.Build;


import androidx.annotation.NonNull;

import com.example.retrofitmvplibrary.utils.NetWorkConstants;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 使用Retrofit实现的网络请求。
 * Created by huanzhang on 2016/4/11.
 */
public class RetrofitSource {
    private static final int CONNECT_TIME_OUT = 60;
    private static final int WRITE_TIME_OUT = 120;
    private static final int READ_TIME_OUT = 60;

    private static OkHttpClient okHttpClient;

    public static OkHttpClient getInstance(Context context) {
        if (okHttpClient == null) {
            synchronized (RetrofitSource.class) {
                if (okHttpClient == null) {
                    ClearableCookieJar cookieJar =
                            new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
                    okHttpClient = new OkHttpClient().newBuilder()
                            .addNetworkInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(@NonNull Chain chain) throws IOException {
                                    Request request = chain.request();
                                    HttpUrl.Builder builder = request.url().newBuilder()
                                            .addQueryParameter("device", Build.MODEL);
                                    request = request.newBuilder().url(builder.build()).build();
                                    return chain.proceed(request);
                                }
                            })
                            .cookieJar(cookieJar)
                            .sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())
                            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true).build();
//                    if (BuildConfig.DEBUG) {
//                        okHttpClient.addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                            @Override
//                            public void log(@NonNull String message) {
//                                Log.e("Retrofit", message);
//                            }
//                        }).setLevel(HttpLoggingInterceptor.Level.BODY));
//                    }
                }
            }
        }

        return okHttpClient;
    }

    public static <T> T createApi(Class<T> clazz, Context context) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetWorkConstants.MURL)
                .client(getInstance(context))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(clazz);
    }

    public static <T> T createApi(Class<T> clazz, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    /**
     * 忽略证书
     * @return SSLSocketFactory
     */
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    /**
     * 忽略ssl证书
     */
    static class TrustAllCerts implements X509TrustManager {
        public TrustAllCerts() {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {}

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[] {};
        }
    }
}

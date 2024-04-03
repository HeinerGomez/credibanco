package com.avility.credibanco.di

import android.content.Context
import androidx.room.Room
import com.avility.data.database.AppDataBase
import com.avility.data.database.datasource.TransactionLocalDataSource
import com.avility.data.remote.datasource.CrediBancoAPIDataSource
import com.avility.data.repository.TransactionRepositoryImpl
import com.avility.domain.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }

    @Provides
    @Singleton
    fun provideCrediBancoAPI(client: OkHttpClient): CrediBancoAPIDataSource {
        return Retrofit
            .Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            "credibanco"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTransactionLocalDataSource(dataBase: AppDataBase): TransactionLocalDataSource {
        return dataBase.transactionDao()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(remoteSource: CrediBancoAPIDataSource, localSource: TransactionLocalDataSource): TransactionRepository {
        return TransactionRepositoryImpl(remoteSource, localSource)
    }
}
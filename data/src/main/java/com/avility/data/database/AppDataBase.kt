package com.avility.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.avility.data.database.converter.DateTimeConverter
import com.avility.data.database.datasource.TransactionLocalDataSource
import com.avility.data.database.entities.TransactionEntity

@Database(
    entities = [
        TransactionEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    DateTimeConverter::class
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun transactionDao() : TransactionLocalDataSource
}
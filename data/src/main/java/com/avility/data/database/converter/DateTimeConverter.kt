package com.avility.data.database.converter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class DateTimeConverter {
    @TypeConverter
    fun fromDateTime(dateTime: LocalDateTime?): Long? = dateTime?.toInstant(ZoneOffset.UTC)?.toEpochMilli()


    @TypeConverter
    fun toDateTime(dateLong: Long?): LocalDateTime? = if (dateLong != null) LocalDateTime.ofInstant(
        Instant.ofEpochMilli(dateLong),
        ZoneOffset.UTC
    )
    else
        null

}
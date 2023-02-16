package com.example.carauosellnews.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

object Utility {
    fun getTextCreatedAt(timeCreated: Long): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Period.between(
                Instant.ofEpochSecond(timeCreated).atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now()
            ).let { period -> TimeFormatter.format(period) }
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }
}
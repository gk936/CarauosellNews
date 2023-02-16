package com.example.carauosellnews.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Period

object TimeFormatter {

    @RequiresApi(Build.VERSION_CODES.O)
    fun format(period: Period): String {
        return when {
            period.years > 0 -> {
                "${period.years} ${if (period.years > 1) "years" else "year"} ago"
            }
            period.months > 0 -> {
                "${period.months} ${if (period.months > 1) "months" else "month"} ago"
            }
            period.days / 7 < 1 -> {
                "${period.days} ${if (period.days > 1) "days" else "day"} ago"
            }
            else -> {
                "${period.days / 7} ${if ((period.days / 7) > 1) "weeks" else "week"} ago"
            }
        }
    }

}
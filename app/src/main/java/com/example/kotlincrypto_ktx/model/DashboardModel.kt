package com.example.kotlincrypto_ktx.model

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.core.content.ContextCompat
import com.example.data.model.Dashboard
import com.example.kotlincrypto_ktx.R
import java.text.NumberFormat
import java.util.*

data class DashboardModel(
    val name: String,
    val dayOpen: String,
    val dayVolume: String,
    val weekOpen: String,
    val weekVolume: String,
    val monthOpen: String,
    val monthVolume: String,
    val yearOpen: String,
    val yearVolume: String,
    val close: String,
    val high: String
) {

    companion object {

        private const val null_string = "N/A"

        fun create(dashboard: Dashboard) : DashboardModel {
            var format = NumberFormat.getCurrencyInstance(Locale.US)

            return DashboardModel(
                name = dashboard.currency,
                dayOpen = format.format(dashboard.dayOpen?.toFloat() ?: 0),
                dayVolume = dashboard.dayVolume ?: null_string,
                weekOpen = format.format(dashboard.weekOpen?.toFloat() ?: 0),
                weekVolume = dashboard.weekVolume ?: null_string,
                monthOpen = format.format(dashboard.monthOpen?.toFloat() ?: 0),
                monthVolume = dashboard.monthVolume ?: null_string,
                yearOpen = format.format(dashboard.yearOpen?.toFloat() ?: 0),
                yearVolume = dashboard.yearVolume ?: null_string,
                close = format.format(dashboard.close?.toFloat() ?: 0),
                high = format.format(dashboard.high?.toFloat() ?: 0)
            )
        }
    }

    //example here to show that we can do special formatting of the string in code and reapply it with databinding still
    fun formatDayOpen(context: Context) : SpannableStringBuilder {
        val builder = SpannableStringBuilder("Day Open: $dayOpen")
        val indexOfColon = builder.indexOf(":") + 1
        builder.setSpan(StyleSpan(Typeface.BOLD), 0, indexOfColon, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        builder.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.dashboardTitle)), 0, indexOfColon, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        builder.setSpan(StyleSpan(Typeface.BOLD_ITALIC), indexOfColon, builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return builder
    }
}
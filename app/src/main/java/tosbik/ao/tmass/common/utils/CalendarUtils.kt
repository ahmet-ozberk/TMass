package tosbik.ao.tmass.common.utils

import android.app.LocaleManager
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import tosbik.ao.tmass.MainApplication
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

object CalendarUtils{
    private fun getAppLocale(): String {
        val context = MainApplication.applicationContext()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (context.getSystemService(LocaleManager::class.java).applicationLocales.isEmpty.not()) {
                context.getSystemService(LocaleManager::class.java)
                    .applicationLocales[0].toLanguageTag()
            } else {
                "en"
            }
        } else {
            if (AppCompatDelegate.getApplicationLocales().isEmpty.not() && AppCompatDelegate.getApplicationLocales()[0] != null) {
                AppCompatDelegate.getApplicationLocales()[0]!!.toLanguageTag()
            } else {
                "en"
            }
        }
    }

    private fun getLocale(): Locale {
        return Locale(getAppLocale())
    }

    fun getCurrentDate(): LocalDate = LocalDate.now()

    fun getCurrentMonthName(style: TextStyle = TextStyle.SHORT): String {
        val current = LocalDate.now()
        return current.month.getDisplayName(style, getLocale())
    }

    fun getCurrentYear(): Int = LocalDate.now().year

    fun getDayName(date: LocalDate, style: TextStyle = TextStyle.SHORT): String {
        return date.dayOfWeek.getDisplayName(style, getLocale())
    }

    fun getDaysInMonthAsLocalDate(selectedDate: LocalDate): List<LocalDate> {
        val daysInMonth = mutableListOf<LocalDate>()
        var currentDate = selectedDate.withDayOfMonth(1)
        while (currentDate.month == selectedDate.month) {
            daysInMonth.add(currentDate)
            currentDate = currentDate.plusDays(1)
        }
        return daysInMonth
    }
}
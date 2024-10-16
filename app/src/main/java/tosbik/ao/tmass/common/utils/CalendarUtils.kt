package tosbik.ao.tmass.common.utils

import android.app.LocaleManager
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import tosbik.ao.tmass.MainApplication
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
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

    fun formatCurrentDate(): String {
        /// "Mon, 21 Sept 2024"
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("E, d MMM yyyy", getLocale())
        return today.format(formatter)
    }

    fun greetingBasedOnTime(): String {
        // Şu anki zamanı al
        val currentTime = LocalTime.now()

        // Selamlamayı belirle
        return when {
            currentTime.isBefore(LocalTime.NOON) -> "Good morning" // 12:00'dan önce
            currentTime.isBefore(LocalTime.of(17, 0)) -> "Good afternoon" // 12:00 - 17:00 arası
            currentTime.isBefore(LocalTime.of(21, 0)) -> "Good evening" // 17:00 - 21:00 arası
            else -> "Good night" // 21:00'dan sonra
        }
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
package oncall.service

import oncall.constants.DayOfWeek
import oncall.constants.LegalHoliday
import oncall.constants.Month

object DayClassifier {

    private val legalHolidayStore =
        LegalHoliday.entries.associate { it.month to it.day }

    private val weekdayStore = setOf(
        DayOfWeek.MON, DayOfWeek.TUE, DayOfWeek.WED, DayOfWeek.THU, DayOfWeek.FRI
    )

    fun isWeekday(dayOfWeek: DayOfWeek) =
        weekdayStore.contains(dayOfWeek)

    fun isLegalHoliday(month: Month, day: Int): Boolean {
        val legalDay = legalHolidayStore[month] ?: return false
        return legalDay == day
    }
git
    fun isHoliday(month: Month, day: Int, dayOfWeek: DayOfWeek) =
        isLegalHoliday(month, day) || !isWeekday(dayOfWeek)
}
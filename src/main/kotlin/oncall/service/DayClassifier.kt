package oncall.service

import oncall.constants.DayOfWeek
import oncall.constants.Month

object DayClassifier {

    private val legalHoliDayStore = mutableMapOf<Month, Int>()

    init {
        legalHoliDayStore[Month.JAN] = 1
        legalHoliDayStore[Month.MAR] = 1
        legalHoliDayStore[Month.MAY] = 5
        legalHoliDayStore[Month.JUN] = 6
        legalHoliDayStore[Month.AUG] = 15
        legalHoliDayStore[Month.OCT] = 3
        legalHoliDayStore[Month.OCT] = 9
        legalHoliDayStore[Month.DEC] = 25
    }

    fun isWeekDay(dayOfWeek: DayOfWeek): Boolean {
        if (dayOfWeek == DayOfWeek.MON) return true
        if (dayOfWeek == DayOfWeek.TUE) return true
        if (dayOfWeek == DayOfWeek.WED) return true
        if (dayOfWeek == DayOfWeek.THU) return true
        if (dayOfWeek == DayOfWeek.FRI) return true
        return false
    }

    fun isLegalHoliDay(month: Month, day: Int): Boolean {
        val legalDay = legalHoliDayStore[month] ?: return false
        if (legalDay == day) return true
        return false
    }

    fun isHoliday(month: Month, day: Int, dayOfWeek: DayOfWeek) =
        isLegalHoliDay(month, day) || !isWeekDay(dayOfWeek)
}
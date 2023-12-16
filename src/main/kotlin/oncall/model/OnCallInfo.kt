package oncall.model

import oncall.constants.DayOfWeek
import oncall.constants.Month


class OnCallInfo(private val month: Month, private val startDay: DayOfWeek) {

    private val dayOfWeekStore = mutableMapOf<Int, DayOfWeek>()

    init {
        initDayOfWeekStore()
    }

    private fun initDayOfWeekStore() {
        enumValues<DayOfWeek>().forEach { day ->
            if (day.ordinal >= startDay.ordinal) {
                dayOfWeekStore[day.ordinal- startDay.ordinal] = day
            }
        }

        if (dayOfWeekStore.size == WEEK) return

        enumValues<DayOfWeek>().forEach { day ->
            if (day.ordinal < startDay.ordinal) {
                dayOfWeekStore[WEEK - startDay.ordinal + day.ordinal] = day
            }
        }
    }

    fun forEach(action: (Month, Int, DayOfWeek) -> Unit) {
        for (day in 1..month.day) {
            action(month, day, getDayOfWeek(day))
        }
    }

    private fun getDayOfWeek(day: Int) =
        dayOfWeekStore[(day -  1) % WEEK]!!

    companion object {
        private const val WEEK = 7
    }
}
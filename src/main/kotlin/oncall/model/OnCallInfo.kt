package oncall.model

import oncall.constants.Day
import oncall.constants.Month


class OnCallInfo(private val month: Month, private val startDay: Day) {

    private val dayStore = mutableMapOf<Int, Day>()

    init {
        initDayStore()
    }

    private fun initDayStore() {
        enumValues<Day>().forEach { day ->
            if (day.ordinal >= startDay.ordinal) {
                dayStore[day.ordinal- startDay.ordinal] = day
            }
        }

        if (dayStore.size == WEEK) return

        enumValues<Day>().forEach { day ->
            if (day.ordinal < startDay.ordinal) {
                dayStore[WEEK - startDay.ordinal + day.ordinal] = day
            }
        }
    }

    fun forEach(action: (Int, Day) -> Unit) {
        for (day in 1..month.day) {
            action(day, getDay(day))
        }
    }

    private fun getDay(day: Int) = dayStore[(day -  1) % WEEK]!!

    companion object {
        private const val WEEK = 7
    }
}
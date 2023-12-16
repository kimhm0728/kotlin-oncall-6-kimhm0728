package oncall.model

import oncall.constants.DayOfWeek
import oncall.constants.Month
import oncall.service.DayClassifier

class WorkShiftInfo(
    private val month: Month,
    private val day: Int,
    private val dayOfWeek: DayOfWeek,
    private val worker: Worker
) {

    override fun toString(): String {
        if (DayClassifier.isWeekDay(dayOfWeek) && DayClassifier.isLegalHoliDay(month, day)) {
            return "${month.title}월 ${day}일 ${dayOfWeek.title}(휴일) $worker"
        }
        return "${month.title}월 ${day}일 ${dayOfWeek.title} $worker"
    }
}
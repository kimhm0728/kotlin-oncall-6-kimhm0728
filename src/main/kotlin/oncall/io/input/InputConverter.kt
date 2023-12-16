package oncall.io.input

import oncall.constants.DayOfWeek
import oncall.constants.Month
import oncall.model.OnCallInfo
import oncall.model.WorkSort
import oncall.model.Worker
import oncall.util.convertListWithComma

class InputConverter {

    fun convertMonthAndStartDay(input: String): OnCallInfo {
        val monthAndStartDay = input.convertListWithComma()
        val month = Month.entries.first { it.title == monthAndStartDay[0] }
        val dayOfWeek = DayOfWeek.entries.first { it.title == monthAndStartDay[1] }

        return OnCallInfo(month, dayOfWeek)
    }

    fun convertWorkSort(input: String) =
        input.convertListWithComma().map { Worker(it) }.run {
            WorkSort(this)
        }
}
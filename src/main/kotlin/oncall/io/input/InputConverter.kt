package oncall.io.input

import oncall.constants.Day
import oncall.constants.Month
import oncall.model.OnCallInfo
import oncall.model.WorkSort
import oncall.model.Worker
import oncall.util.convertListWithComma

class InputConverter {

    fun convertMonthAndStartDay(input: String): OnCallInfo {
        val monthAndStartDay = input.convertListWithComma()
        val month = Month.entries.first { it.title == monthAndStartDay[0] }
        val day = Day.entries.first { it.title == monthAndStartDay[1] }

        return OnCallInfo(month, day)
    }

    fun convertWorkSort(input: String) =
        input.convertListWithComma().map { Worker(it) }.run {
            WorkSort(this)
        }
}
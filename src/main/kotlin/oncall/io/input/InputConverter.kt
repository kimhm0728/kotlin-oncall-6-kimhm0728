package oncall.io.input

import oncall.constants.Day
import oncall.constants.Month
import oncall.model.OnCallInfo
import oncall.util.convertListWithComma

class InputConverter {

    fun convertMonthAndStartDay(input: String): OnCallInfo {
        val monthAndStartDay = input.convertListWithComma()
        val month = Month.entries.first { it.title == monthAndStartDay[0] }
        val day = Day.entries.first { it.title == monthAndStartDay[1] }

        return OnCallInfo(month, day)
    }
}
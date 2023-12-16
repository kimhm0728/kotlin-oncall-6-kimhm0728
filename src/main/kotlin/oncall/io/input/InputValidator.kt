package oncall.io.input

import oncall.constants.Day
import oncall.constants.Month
import oncall.util.convertListWithComma

class InputValidator {

    fun validateMonthAndStartDay(input: String) {
        input.validateEmpty()
        with(input.convertListWithComma()) {
            validateMonthAndStartDaySize()
            this[0].validateMonth()
            this[1].validateStartDay()
        }
    }

    private fun String.validateEmpty() = require(this.isNotEmpty())

    private fun List<String>.validateMonthAndStartDaySize() = require(size == 2)

    private fun String.validateMonth() = require(Month.entries.any { it.title == this })

    private fun String.validateStartDay() = require(Day.entries.any { it.title == this })
}
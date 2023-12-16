package oncall.io.input

import oncall.constants.DayOfWeek
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

    private fun String.validateStartDay() = require(DayOfWeek.entries.any { it.title == this })

    fun validateWorkSort(input: String) {
        input.validateEmpty()
        with(input.convertListWithComma()) {
            validateWorkerSize()
            validateWorkerDuplication()
            validateWorkerNickName()
        }
    }

    private fun List<String>.validateWorkerSize() =
        require(size in WORKER_RANGE)

    private fun List<String>.validateWorkerDuplication() =
        require(distinct().size == size)

    private fun List<String>.validateWorkerNickName() =
        require(all { NICKNAME_REGEX.matches(it) })

    companion object {
        private val WORKER_RANGE = 5..35
        private val NICKNAME_REGEX = "^[ㄱ-ㅎ가-힣]{1,5}$".toRegex()
    }
}
package oncall

import oncall.io.input.InputView
import oncall.io.output.OutputView
import oncall.model.OnCallInfo
import oncall.model.WorkSort
import oncall.service.WorkSortAssignee
import oncall.util.retryWhileNoException

class OnCallController(
    private val inputView: InputView,
    private val outputView: OutputView
) {

    fun run() {
        val onCallInfo = getOnCallInfo()
        val (weekDayWorkSort, holiDayWorkSort) = getWorkSortByWeekDayAndHoliDay()

        val workShiftStore =
            WorkSortAssignee.assign(onCallInfo, weekDayWorkSort, holiDayWorkSort)

        outputView.printWorkShiftList(workShiftStore)
    }

    private fun getOnCallInfo() = retryWhileNoException {
        inputView.readMonthAndStartDay()
    }

    private fun getWorkSortByWeekDayAndHoliDay() = retryWhileNoException {
        inputView.readWorkSortByWeekDayAndHoliDay()
    }
}
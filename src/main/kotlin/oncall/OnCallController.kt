package oncall

import oncall.io.input.InputView
import oncall.io.output.OutputView
import oncall.service.WorkSortAssignee
import oncall.util.retryWhileNoException

class OnCallController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val workSortAssignee: WorkSortAssignee
) {

    fun run() {
        val onCallInfo = getOnCallInfo()
        val (weekDayWorkSort, holidayWorkSort) = getWorkSortByWeekDayAndHoliday()

        val workShiftStore =
            workSortAssignee.assign(onCallInfo, weekDayWorkSort, holidayWorkSort)

        outputView.printWorkShiftList(workShiftStore)
    }

    private fun getOnCallInfo() = retryWhileNoException {
        inputView.readMonthAndStartDay()
    }

    private fun getWorkSortByWeekDayAndHoliday() = retryWhileNoException {
        inputView.readWorkSortByWeekdayAndHoliday().apply {
            first.validateWorkOnce(second)
        }
    }
}
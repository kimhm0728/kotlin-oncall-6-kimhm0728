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

    private val onCallInfo: OnCallInfo
    private val weekDayWorkSort: WorkSort
    private val holiDayWorkSort: WorkSort

    init {
        onCallInfo = getOnCallInfo()
        weekDayWorkSort = getWeekDayWorkSort()
        holiDayWorkSort = getHoliDayWorkSort()
    }

    private fun getOnCallInfo() = retryWhileNoException {
        inputView.readMonthAndStartDay()
    }

    private fun getWeekDayWorkSort() = retryWhileNoException {
        inputView.readWorkSortByWeekDay()
    }

    private fun getHoliDayWorkSort() = retryWhileNoException {
        inputView.readWorkSortByHoliDay()
    }

    fun run() {
        val workShiftStore =
            WorkSortAssignee.assign(onCallInfo, weekDayWorkSort, holiDayWorkSort)

        outputView.printWorkShiftList(workShiftStore)
    }
}
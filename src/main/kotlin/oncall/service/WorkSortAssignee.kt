package oncall.service

import oncall.constants.Month
import oncall.model.OnCallInfo
import oncall.model.WorkShiftInfo
import oncall.model.WorkShiftStore
import oncall.model.WorkSort

class WorkSortAssignee {

    fun assign(
        onCallInfo: OnCallInfo,
        weekDayWorkSort: WorkSort,
        holiDayWorkSort: WorkSort
    ): WorkShiftStore {

        var weekDayWorkSortIdx = 0
        var holiDayWorkSortIdx = 0
        val workShiftStore = mutableListOf<WorkShiftInfo>()

        onCallInfo.forEach { month, day, dayOfWeek ->
            if (DayClassifier.isHoliDay(dayOfWeek) ||
                DayClassifier.isLegalHoliDay(month, day)
            ) {

                val worker = holiDayWorkSort[holiDayWorkSortIdx++]
                workShiftStore.add(WorkShiftInfo(month, day, dayOfWeek, worker))
                return@forEach
            }

            val worker = weekDayWorkSort[weekDayWorkSortIdx++]
            workShiftStore.add(WorkShiftInfo(month, day, dayOfWeek, worker))
        }

        return WorkShiftStore(workShiftStore)
    }
}
package oncall.service

import oncall.model.*

object WorkSortAssignee {

    fun assign(
        onCallInfo: OnCallInfo,
        weekDayWorkSort: WorkSort,
        holiDayWorkSort: WorkSort
    ): WorkShiftStore {

        var weekDayWorkSortIdx = 0
        var holiDayWorkSortIdx = 0
        val workShiftStore = mutableListOf<WorkShiftInfo>()

        onCallInfo.forEach { month, day, dayOfWeek ->
            if (DayClassifier.isHoliday(month, day, dayOfWeek)) {
                val worker = holiDayWorkSort[holiDayWorkSortIdx]
                if (WorkHistory.isWorkYesterday(worker, day)) {
                    holiDayWorkSort.sortChange(holiDayWorkSortIdx)
                    val changeWorker = holiDayWorkSort[holiDayWorkSortIdx]
                    workShiftStore.add(WorkShiftInfo(month, day, dayOfWeek, changeWorker))
                    WorkHistory.work(changeWorker, day)
                    holiDayWorkSortIdx++
                    return@forEach
                }

                workShiftStore.add(WorkShiftInfo(month, day, dayOfWeek, worker))
                WorkHistory.work(worker, day)
                holiDayWorkSortIdx++
                return@forEach
            }

            val worker = weekDayWorkSort[weekDayWorkSortIdx]
            if (WorkHistory.isWorkYesterday(worker, day)) {
                weekDayWorkSort.sortChange(weekDayWorkSortIdx)
                val changeWorker = weekDayWorkSort[weekDayWorkSortIdx]
                workShiftStore.add(WorkShiftInfo(month, day, dayOfWeek, changeWorker))
                WorkHistory.work(changeWorker, day)
                weekDayWorkSortIdx++
                return@forEach
            }

            workShiftStore.add(WorkShiftInfo(month, day, dayOfWeek, worker))
            WorkHistory.work(worker, day)
            weekDayWorkSortIdx++
        }

        return WorkShiftStore(workShiftStore)
    }
}
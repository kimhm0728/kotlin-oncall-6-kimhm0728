package oncall.service

import oncall.model.*

class WorkSortAssignee {

    private var weekDayWorkSortIdx = 0
    private var holiDayWorkSortIdx = 0
    private val workShiftStore = mutableListOf<WorkShiftInfo>()

    fun assign(
        onCallInfo: OnCallInfo,
        weekDayWorkSort: WorkSort,
        holiDayWorkSort: WorkSort
    ): WorkShiftStore {

        onCallInfo.forEach { month, day, dayOfWeek ->
            if (DayClassifier.isHoliday(month, day, dayOfWeek)) {
                val worker = getWorker(holiDayWorkSort, holiDayWorkSortIdx, day)
                val workShiftInfo = WorkShiftInfo(month, day, dayOfWeek, worker)
                applyHolidayWork(worker, day, workShiftInfo)
                return@forEach
            }

            val worker = getWorker(weekDayWorkSort, weekDayWorkSortIdx, day)
            val workShiftInfo = WorkShiftInfo(month, day, dayOfWeek, worker)
            applyWeekdayWork(worker, day, workShiftInfo)
        }

        return WorkShiftStore(workShiftStore)
    }

    private fun getWorker(workSort: WorkSort, idx: Int, day: Int): Worker {
        val worker = workSort[idx]
        if (!WorkHistory.isWorkYesterday(worker, day)) {
            return worker
        }
        workSort.sortChange(day)
            return workSort[idx]
    }

    private fun applyHolidayWork(worker: Worker, day: Int, workShiftInfo: WorkShiftInfo) {
        applyWork(worker, day, workShiftInfo)
        holiDayWorkSortIdx++
    }

    private fun applyWeekdayWork(worker: Worker, day: Int, workShiftInfo: WorkShiftInfo) {
        applyWork(worker, day, workShiftInfo)
        weekDayWorkSortIdx++
    }

    private fun applyWork(worker: Worker, day: Int, workShiftInfo: WorkShiftInfo) {
        workShiftStore.add(workShiftInfo)
        WorkHistory.work(worker, day)
    }
}
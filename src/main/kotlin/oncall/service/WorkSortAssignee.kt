package oncall.service

import oncall.model.*

class WorkSortAssignee {

    private var weekdayWorkSortIdx = 0
    private var holidayWorkSortIdx = 0
    private val workShiftStore = mutableListOf<WorkShiftInfo>()
    private val workHistory = WorkHistory()

    fun assign(
        onCallInfo: OnCallInfo,
        weekDayWorkSort: WorkSort,
        holidayWorkSort: WorkSort
    ): WorkShiftStore {

        onCallInfo.forEach { month, day, dayOfWeek ->
            if (DayClassifier.isHoliday(month, day, dayOfWeek)) {
                val worker = getWorker(holidayWorkSort, holidayWorkSortIdx, day)
                val workShiftInfo = WorkShiftInfo(month, day, dayOfWeek, worker)
                applyHolidayWork(worker, day, workShiftInfo)
                return@forEach
            }

            val worker = getWorker(weekDayWorkSort, weekdayWorkSortIdx, day)
            val workShiftInfo = WorkShiftInfo(month, day, dayOfWeek, worker)
            applyWeekdayWork(worker, day, workShiftInfo)
        }

        return WorkShiftStore(workShiftStore)
    }

    private fun getWorker(workSort: WorkSort, idx: Int, day: Int): Worker {
        val worker = workSort[idx]
        if (!workHistory.isWorkYesterday(worker, day)) {
            return worker
        }
        workSort.changeSort(idx)
        return workSort[idx]
    }

    private fun applyHolidayWork(worker: Worker, day: Int, workShiftInfo: WorkShiftInfo) {
        applyWork(worker, day, workShiftInfo)
        holidayWorkSortIdx++
    }

    private fun applyWeekdayWork(worker: Worker, day: Int, workShiftInfo: WorkShiftInfo) {
        applyWork(worker, day, workShiftInfo)
        weekdayWorkSortIdx++
    }

    private fun applyWork(worker: Worker, day: Int, workShiftInfo: WorkShiftInfo) {
        workShiftStore.add(workShiftInfo)
        workHistory.work(worker, day)
    }
}
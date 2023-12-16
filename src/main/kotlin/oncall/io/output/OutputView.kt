package oncall.io.output

import oncall.model.WorkShiftStore

class OutputView {

    fun printWorkShiftList(workShiftStore: WorkShiftStore) {
        print(workShiftStore)
    }
}
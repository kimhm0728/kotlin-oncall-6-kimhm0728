package oncall.io.output

import oncall.model.WorkShiftStore

class OutputView {

    fun printWorkShiftList(workShiftStore: WorkShiftStore) {
        val stringBuilder = StringBuilder()
        workShiftStore.store.forEach {
            stringBuilder.append(it).append("\n")
        }

        print(stringBuilder)
    }
}
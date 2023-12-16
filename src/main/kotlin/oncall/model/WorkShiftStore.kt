package oncall.model

class WorkShiftStore(private val store: List<WorkShiftInfo>) {

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        store.forEach {
            stringBuilder.append(it).append("\n")
        }
        return stringBuilder.toString()
    }
}
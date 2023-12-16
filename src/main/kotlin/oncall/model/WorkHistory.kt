package oncall.model

class WorkHistory {

    private val history = mutableMapOf<String, MutableList<Int>>()

    fun work(worker: Worker, day: Int) {
        if (history.contains(worker.toString())) {
            history[worker.toString()]?.add(day)
            return
        }

        history[worker.toString()] = mutableListOf(day)
    }

    fun isWorkYesterday(worker: Worker, day: Int) =
        history[worker.toString()]?.contains(day - 1) == true
}
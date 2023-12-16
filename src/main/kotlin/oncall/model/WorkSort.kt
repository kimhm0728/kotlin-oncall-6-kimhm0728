package oncall.model

class WorkSort(private val workers: List<Worker>) {

    operator fun get(position: Int) = workers[position % workers.size]

    fun validateWorkOnce(otherWorkSort: WorkSort) {
        validateSize(otherWorkSort)
        validateContainsAllWorker(otherWorkSort)
    }

    private fun validateSize(otherWorkSort: WorkSort) =
        require(workers.size == otherWorkSort.workers.size)

    private fun validateContainsAllWorker(otherWorkSort: WorkSort) =
        require(workers.all { otherWorkSort.workers.contains(it) })
}
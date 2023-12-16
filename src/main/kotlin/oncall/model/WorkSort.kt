package oncall.model

class WorkSort(private val workers: MutableList<Worker>) {

    operator fun get(position: Int) = workers[position % workers.size]

    fun changeSort(position: Int) {
        val tempWorker = workers[position % workers.size]
        workers[position] = workers[position + 1]
        workers[position + 1] = tempWorker
    }

    fun validateWorkOnce(otherWorkSort: WorkSort) {
        validateSize(otherWorkSort)
        validateContainsAllWorker(otherWorkSort)
    }

    private fun validateSize(otherWorkSort: WorkSort) =
        require(workers.size == otherWorkSort.workers.size)

    private fun validateContainsAllWorker(otherWorkSort: WorkSort) =
        require(workers.all { otherWorkSort.workers.contains(it) })
}
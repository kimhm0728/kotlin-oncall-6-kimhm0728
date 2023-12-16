package oncall.model

class WorkSort(private val workers: MutableList<Worker>) {

    operator fun get(position: Int) = workers[getPosition(position)]

    fun changeSort(position: Int) {
        val tempWorker = workers[getPosition(position)]
        workers[getPosition(position)] = workers[getPosition(position + 1)]
        workers[getPosition(position + 1) % workers.size] = tempWorker
    }

    private fun getPosition(position: Int) = position % workers.size

    fun validateWorkOnce(otherWorkSort: WorkSort) {
        validateSize(otherWorkSort)
        validateContainsAllWorker(otherWorkSort)
    }

    private fun validateSize(otherWorkSort: WorkSort) =
        require(workers.size == otherWorkSort.workers.size)

    private fun validateContainsAllWorker(otherWorkSort: WorkSort) =
        require(workers.all { otherWorkSort.workers.contains(it) })
}
package oncall

import oncall.io.input.InputView
import oncall.io.output.OutputView
import oncall.service.WorkSortAssignee

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val workSortAssignee = WorkSortAssignee()
    val onCallController = OnCallController(inputView, outputView, workSortAssignee)

    onCallController.run()
}

package oncall

import oncall.io.input.InputView
import oncall.io.output.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val onCallController = OnCallController(inputView, outputView)

    onCallController.run()
}

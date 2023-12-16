package oncall.io.input

import camp.nextstep.edu.missionutils.Console
import oncall.model.OnCallInfo
import oncall.model.WorkSort

class InputView(
    private val validator: InputValidator = InputValidator(),
    private val converter: InputConverter = InputConverter(),
) {

    fun readMonthAndStartDay(): OnCallInfo {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        val input = Console.readLine()

        validator.validateMonthAndStartDay(input)
        return converter.convertMonthAndStartDay(input)
    }

    fun readWorkSortByWeekDay(): WorkSort {
        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        return readWorkSort()
    }

    fun readWorkSortByHoliDay(): WorkSort {
        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        return readWorkSort()
    }

    private fun readWorkSort(): WorkSort {
        val input = Console.readLine()

        validator.validateWorkSort(input)
        return converter.convertWorkSort(input)
    }
}
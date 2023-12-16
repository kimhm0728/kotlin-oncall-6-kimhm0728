package oncall.model

class Worker(private val nickname: String) {

    override fun hashCode() = nickname.hashCode()

    override fun equals(other: Any?) =
        this.nickname == (other as Worker).nickname

    override fun toString() = nickname
}
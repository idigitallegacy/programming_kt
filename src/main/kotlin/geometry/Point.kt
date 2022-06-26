package geometry

data class Point(var x: Double, var y: Double) {
    constructor(copied: Point) : this(copied.x, copied.y)

    fun setXY(newX: Double, newY: Double) {
        x = newX
        y = newY
    }

    constructor(_x: Long, _y: Long) : this(_x.toDouble(), _y.toDouble())

    override operator fun equals(other: Any?): Boolean {
        if (other is Point) return other.x == this.x && other.y == this.y
        return false
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }
}
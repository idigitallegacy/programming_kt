package geometry

import kotlin.math.sqrt

class CorrectPolygon(fromPointsArray: ArrayList<Point>) : Polygon(fromPointsArray) {
    private fun checkCorrectness(): Boolean {
        var length = 0.0
        for (i in (0 until pointsArray.size - 1)) {
            val tmp = sqrt((pointsArray[i + 1].x - pointsArray[i].x)
                    * (pointsArray[i + 1].x - pointsArray[i].x)
                    + (pointsArray[i + 1].y - pointsArray[i].y)
                    * (pointsArray[i + 1].y - pointsArray[i].y))
            if (i == 0) {
                length = tmp
                continue
            }
            if (length != tmp) return false
        }
        return true
    }

    init {
        if (!checkCorrectness())
            throw IllegalArgumentException("Polygon is not correct.")
    }

    constructor(fromClosedPolyline: ClosedPolyline) : this(fromClosedPolyline.getLineArray())
    constructor(fromPolygon: Polygon) : this(fromPolygon.getLineArray())
    constructor(fromCorrectPolygon: CorrectPolygon) : this(fromCorrectPolygon.getLineArray())
    constructor(fromTriangle: Triangle) : this(fromTriangle.getLineArray())
    constructor(fromTrapezium: Trapezium) : this(fromTrapezium.getLineArray())

    fun reshape(multiplier: Double) {
        if (multiplier == 0.0)
            throw IllegalArgumentException("Invalid multiplier.")
        pointsArray.forEach {
            it.x *= multiplier
            it.y *= multiplier
        }
    }

    override fun changePoint(oldPoint: Point, newPoint: Point) {
        throw IllegalArgumentException("Unable to change single point at correct polygon. Use reshape() instead.")
    }

    override fun erasePoint(point: Point) {
        throw NoSuchMethodException("Unable to erase points from correct polygon.")
    }

    override fun pushPoint(point: Point) {
        throw NoSuchMethodException("Unable to push points into correct polygon.")
    }
}
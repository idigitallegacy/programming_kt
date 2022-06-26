package geometry

import kotlin.math.sqrt

open class Polyline(fromPointsArray: ArrayList<Point>) {
    protected var pointsArray = arrayListOf<Point>()

    init {
        if (fromPointsArray.size < 2)
            throw IllegalArgumentException("Invalid pointsArray size")

        fromPointsArray.forEach {
            if (!pointsArray.contains(it))
                pointsArray.add(it)
        }

        if (pointsArray.size < 2)
            throw IllegalArgumentException("pointsArray has duplicates, so scaled down to 1 point.")
    }

    open fun changePoint(oldPoint: Point, newPoint: Point) {
        val oldIndex = pointsArray.indexOf(oldPoint)
        if (!pointsArray.contains(newPoint))
            pointsArray[oldIndex] = newPoint
        else
            throw IllegalArgumentException("newPoint is already exists. Use erase() method instead.")
    }

    open fun erasePoint(point: Point) {
        if (pointsArray.size > 3 && pointsArray.contains(point))
            pointsArray.remove(point)
        else if (pointsArray.size > 3)
            throw IllegalArgumentException("Point doesn't exists.")
    }

    open fun pushPoint(point: Point) {
        if (!pointsArray.contains(point))
            pointsArray.add(point)
    }

    open fun getLength(): Double {
        var length = 0.0
        for (i in (0 until pointsArray.size - 1)) {
            length += sqrt(
                (pointsArray[i + 1].x - pointsArray[i].x) * (pointsArray[i + 1].x - pointsArray[i].x) +
                        (pointsArray[i + 1].y - pointsArray[i].y) * (pointsArray[i + 1].y - pointsArray[i].y)
            )
        }
        return length
    }

    fun getLineArray(): ArrayList<Point> = pointsArray

    fun getSize(): Int = pointsArray.size
}
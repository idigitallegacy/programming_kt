package geometry

import kotlin.math.abs
import kotlin.math.sqrt

open class ClosedPolyline(fromPointsArray: ArrayList<Point>) : Polyline(fromPointsArray) {
    final override fun getLength(): Double {
        val lastIndex = pointsArray.size - 1
        val lastLine = sqrt(
            (pointsArray[0].x - pointsArray[lastIndex].x) * (pointsArray[0].x - pointsArray[lastIndex].x) +
                    (pointsArray[0].y - pointsArray[lastIndex].y) * (pointsArray[0].y - pointsArray[lastIndex].y)
        )
        return super.getLength() + lastLine
    }

    constructor(fromPolyline: Polyline) : this(fromPolyline.getLineArray()) {}

    open fun getSquare(): Double { // using Gaussian square formula
        var sigma1: Double = pointsArray[0].x * (pointsArray[1].y - pointsArray[pointsArray.size - 1].y)
        for (i in (1 until pointsArray.size - 1)) {
            sigma1 += pointsArray[i].x * (pointsArray[i + 1].y - pointsArray[i - 1].y)
        }
        sigma1 += pointsArray[pointsArray.size - 1].x * (pointsArray[0].y - pointsArray[pointsArray.size - 2].y)

        return abs(sigma1) / 2
    }
}
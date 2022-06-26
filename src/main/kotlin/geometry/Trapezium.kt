package geometry

class Trapezium(fromPointsArray: ArrayList<Point>) : Polygon(fromPointsArray) {
    private fun checkTrapezium(): Boolean {
        if (pointsArray.size != 4)
            return false

        val AB = Point(pointsArray[1].x - pointsArray[0].x, pointsArray[1].y - pointsArray[0].y)
        val BC = Point(pointsArray[2].x - pointsArray[1].x, pointsArray[2].y - pointsArray[1].y)
        val CD = Point(pointsArray[3].x - pointsArray[2].x, pointsArray[3].y - pointsArray[2].y)
        val DA = Point(pointsArray[0].x - pointsArray[3].x, pointsArray[0].y - pointsArray[3].y)

        if ((AB.x * CD.y - AB.y * CD.x) != 0.0 &&
            (BC.x * DA.y - BC.y * DA.x) != 0.0)
            return false

        if ((AB.x * CD.y - AB.y * CD.x) == 0.0 &&
            (AB.x * BC.y - AB.y * BC.x) == 0.0)
            return false
        return true
    }

    init {
        if (!checkTrapezium())
            throw IllegalArgumentException("Shape is not a trapezium.")
    }

    constructor(fromClosedPolyline: ClosedPolyline) : this(fromClosedPolyline.getLineArray()) {}
    constructor(fromPolygon: Polygon) : this(fromPolygon.getLineArray()) {}
    constructor(fromCorrectPolygon: CorrectPolygon) : this(fromCorrectPolygon.getLineArray()) {}

    override fun changePoint(oldPoint: Point, newPoint: Point) {
        super.changePoint(oldPoint, newPoint)
        if (!checkTrapezium()) {
            super.changePoint(newPoint, oldPoint)
            throw IllegalArgumentException("New shape is not a trapezium.")
        }
    }

    override fun erasePoint(point: Point) {
        throw NoSuchMethodException("Unable to erase points from trapezium.")
    }

    override fun pushPoint(point: Point) {
        throw NoSuchMethodException("Unable to push points into trapezium.")
    }
}
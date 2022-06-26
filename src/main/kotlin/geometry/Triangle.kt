package geometry

class Triangle(fromPointsArray: ArrayList<Point>) : Polygon(fromPointsArray) {
    private fun checkTriangularity() : Boolean {
        if (pointsArray.size != 3)
            return false
        return true
    }

    init {
        if (!checkTriangularity())
            throw IllegalArgumentException("Shape is not a triangle.")
    }

    constructor(fromClosedPolyline: ClosedPolyline) : this(fromClosedPolyline.getLineArray()) {}
    constructor(fromPolygon: Polygon) : this(fromPolygon.getLineArray()) {}
    constructor(fromCorrectPolygon: CorrectPolygon) : this(fromCorrectPolygon.getLineArray()) {}

    override fun changePoint(oldPoint: Point, newPoint: Point) {
        super.changePoint(oldPoint, newPoint)
        if (!checkTriangularity()) {
            super.changePoint(newPoint, oldPoint)
            throw IllegalArgumentException("New point leads shape to line or polygon.")
        }
    }

    override fun erasePoint(point: Point) {
        throw NoSuchMethodException("Unable to erase points from triangle.")
    }

    override fun pushPoint(point: Point) {
        throw NoSuchMethodException("Unable to push points into triangle.")
    }
}
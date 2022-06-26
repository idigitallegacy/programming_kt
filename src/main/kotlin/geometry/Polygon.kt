package geometry

open class Polygon(fromPointsArray: ArrayList<Point>) : ClosedPolyline(fromPointsArray) {
    protected fun checkConvexity(): Boolean {
        var direction = 0.0
        for (i in (1 until pointsArray.size)) {
            val AB = Point(pointsArray[i].x - pointsArray[i - 1].x,
            pointsArray[i].y - pointsArray[i - 1].y)
            val BC = Point(pointsArray[(i + 1) % pointsArray.size].x - pointsArray[i].x,
            pointsArray[(i + 1) % pointsArray.size].y - pointsArray[i].y)


            if (i == 1) {
                direction = AB.x * BC.y - AB.y * BC.x
                continue;
            }
            val tmp: Double = AB.x * BC.y - AB.y * BC.x
            if (direction < 0 && tmp > 0 || direction > 0 && tmp < 0)
                return false;
            direction = tmp;
        }

        val square = this.getSquare()
        if (square == 0.0) return false;
        return true;
    }

    init {
        if (pointsArray.size < 3)
            throw IllegalArgumentException("Unable to create polygon: it's not a polygon.")
        if (!checkConvexity())
            throw IllegalArgumentException("Unable to create polygon: it's not convex.")
    }

    constructor(fromPolyline: Polyline) : this(fromPolyline.getLineArray())
    constructor(fromClosedPolyline: ClosedPolyline) : this(fromClosedPolyline.getLineArray())
    constructor(fromPolygon: Polygon) : this(fromPolygon.getLineArray())
    constructor(fromTriangle: Triangle) : this(fromTriangle.getLineArray())
    constructor(fromTrapezium: Trapezium) : this(fromTrapezium.getLineArray())
    constructor(fromCorrectPolygon: CorrectPolygon) : this(fromCorrectPolygon.getLineArray())

    fun getPerimeter(): Double {
        return super.getLength()
    }

    override fun changePoint(oldPoint: Point, newPoint: Point) {
        super.changePoint(oldPoint, newPoint)

        if (!checkConvexity()) {
            super.changePoint(newPoint, oldPoint)
            throw IllegalArgumentException("Unable to update polygon point: it's not convex anymore.")
        }
    }

    override fun erasePoint(point: Point) {
        if (pointsArray.size < 4)
            return
        super.erasePoint(point)

        if (!checkConvexity()) {
            super.pushPoint(point)
            throw IllegalArgumentException("Unable to erase polygon point: it's not convex anymore.")
        }
    }
}
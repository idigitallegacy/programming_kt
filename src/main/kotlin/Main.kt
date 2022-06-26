import geometry.*


fun main() {
    val point1 = Point(0, 0)
    val point2 = Point(0, 1)
    val point3 = Point(1, 1)
    val point4 = Point(1, 0)

    val array = arrayListOf(point1, point2, point3, point4)

    val line = Polyline(array)
    val closedLine = ClosedPolyline(array)
    val polygon = Polygon(array)
    val correctPolygon = CorrectPolygon(array)
}
/**
 * ADVENT OF CODE 2022 (https://adventofcode.com/2022/)
 *
 * Solution to day 9 (https://adventofcode.com/2022/day/9)
 *
 */

import kotlin.math.abs

private data class Point(val x: Int, val y: Int) {

    operator fun minus(otherPoint: Point): Point = Point(x - otherPoint.x, y - otherPoint.y)
}

private class Knot(
    var position: Point = Point(0, 0),
    val registerVisitedPositions: Boolean = false,
) {
    var visitedPositions: MutableSet<Point> = mutableSetOf(position)

    fun move(x: Int = position.x, y: Int = position.y) {
        position = Point(x, y)
        if (registerVisitedPositions) {
            visitedPositions += position
        }
    }
}

fun main() {

    fun solve(motions: List<String>, knotCount: Int): Int {
        val knots = List(knotCount) { index -> Knot(registerVisitedPositions = index == knotCount - 1) }
        val head = knots.first()
        val tail = knots.last()

        motions.forEach { direction ->
            when (direction) {
                "L" -> head.move(x = head.position.x - 1)
                "R" -> head.move(x = head.position.x + 1)
                "U" -> head.move(y = head.position.y + 1)
                "D" -> head.move(y = head.position.y - 1)
            }
            knots.windowed(2).forEach { (knot, nextKnot) ->
                (knot.position - nextKnot.position).takeIf { diff -> abs(diff.x) > 1 || abs(diff.y) > 1 }?.let { diff ->
                    nextKnot.move(
                        x = knot.position.x + when {
                            diff.x > 1 -> -1
                            diff.x < -1 -> 1
                            else -> 0
                        },
                        y = knot.position.y + when {
                            diff.y > 1 -> -1
                            diff.y < -1 -> 1
                            else -> 0
                        }
                    )
                }
            }
        }

        return tail.visitedPositions.count()
    }

    val motions = buildList {
        readInputLines("day09_dataset").forEach { line ->
            "([RLUD]) (\\d+)".toRegex().find(line)!!.destructured.let { (direction, steps) ->
                repeat((1..steps.toInt()).count()) { add(direction) }
            }
        }
    }

    solve(motions, 2).println()
    solve(motions, 10).println()
}

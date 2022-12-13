/**
 * ADVENT OF CODE 2022 (https://adventofcode.com/2022/)
 * 
 * Solution to day 6 (https://adventofcode.com/2022/day/6)
 *
 */

fun main() {

    fun calculateResult(dataStream: String, markerSize: Int): Int {
        return dataStream.asSequence().windowed(markerSize).indexOfFirst { sequence ->
            sequence.toList().distinct().count() == markerSize
        } + markerSize
    }

    val dataStream: String = readInput("day06_dataset")

    calculateResult(dataStream, 4).println()
    calculateResult(dataStream, 14).println()
}

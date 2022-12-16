/**
 * ADVENT OF CODE 2022 (https://adventofcode.com/2022/)
 *
 * Solution to day 10 (https://adventofcode.com/2022/day/10)
 *
 */

fun main() {

    fun part1(instructions: Sequence<Int>): Int {
        return instructions
            .runningFold(1) { acc, x -> acc + x }
            .withIndex()
            .filter { (index, _) -> index in listOf(19, 59, 99, 139, 179, 219) }
            .sumOf { (index, x) -> (index + 1) * x }
    }

    fun part2(instructions: Sequence<Int>): String {
        return instructions
            .runningFold(1) { acc, x -> acc + x }
            .withIndex()
            .map { (index, x) -> if (index.mod(40) in ((x - 1)..(x + 1))) "#" else "." }
            .chunked(40)
            .map { row -> row.joinToString("") }.joinToString("\n")
    }

    val instructions = buildList {
        readInputLines("day10_dataset").map { line ->
            add(0)
            "addx (-?\\d+)".toRegex().find(line)?.destructured?.let { (x) -> add(x.toInt()) }
        }
    }.dropLast(1).asSequence()

    part1(instructions).println()
    part2(instructions).println()
}


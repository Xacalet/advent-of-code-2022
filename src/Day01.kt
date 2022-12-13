/**
 * ADVENT OF CODE 2022 (https://adventofcode.com/2022/)
 *
 * Solution to day 1 (https://adventofcode.com/2022/day/1)
 *
 */

fun main() {

    data class Elf(val calories: List<Int>)

    fun part1(elves: List<Elf>): Int {
        return elves.maxOfOrNull { it.calories.sum() } ?: 0
    }

    fun part2(elves: List<Elf>): Int {
        return elves.map { it.calories.sum() }.sortedDescending().take(3).sum()
    }

    val elves = readInput("day01_dataset").split("\n\n").map { line ->
        Elf(line.split("\n").map(Integer::parseInt))
    }

    part1(elves).println()
    part2(elves).println()
}

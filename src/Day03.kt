/**
 * ADVENT OF CODE 2022 (https://adventofcode.com/2022/)
 * 
 * Solution to day 3 (https://adventofcode.com/2022/day/3)
 *
 */

private typealias Rucksack = List<Char>

private val charToPriorityMap: Map<Char, Int> =
    (('a'..'z') + ('A'..'Z')).withIndex().associate { (index, value) ->
        value to index + 1
    }
private val Char.priority: Int
    get() = charToPriorityMap[this] ?: 0

fun main() {

    fun part1(rucksacks: List<Rucksack>): Int {
        return rucksacks
            .map { rucksack -> rucksack.chunked(rucksack.size / 2) }
            .sumOf { (compartment1, compartment2) ->
                compartment1.intersect(compartment2.toSet()).first().priority
            }
    }

    fun part2(rucksacks: List<Rucksack>): Int {
        return rucksacks.chunked(3).sumOf { (r1, r2, r3) ->
            r1.intersect(r2).intersect(r3).first().priority
        }
    }

    val rucksacks = readInputLines("day03_dataset").map { line -> line.toList()}

    part1(rucksacks).println()
    part2(rucksacks).println()
}

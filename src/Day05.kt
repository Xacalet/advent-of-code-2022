/**
 * ADVENT OF CODE 2022 (https://adventofcode.com/2022/)
 * 
 * Solution to day 5 (https://adventofcode.com/2022/day/5)
 *
 */

fun main() {

    data class StackRearrangement(
        val count: Int,
        val fromIndex: Int,
        val toIndex: Int,
    )

    fun part1(stacks: List<MutableList<String>>, rearrangements: List<StackRearrangement>): String {
        rearrangements.forEach { arrangement ->
            (1..arrangement.count).forEach {
                val movingCrate = stacks[arrangement.fromIndex - 1].last()
                stacks[arrangement.toIndex - 1].add(movingCrate)
                stacks[arrangement.fromIndex - 1].removeLast()
            }
        }

        return stacks.joinToString("") { it.lastOrNull() ?: " " }
    }

    fun part2(stacks: List<MutableList<String>>, rearrangements: List<StackRearrangement>): String {
        rearrangements.forEach { arrangement ->
            val movingCranes = (1..arrangement.count).map {
                stacks[arrangement.fromIndex - 1].removeLast()
            }
            stacks[arrangement.toIndex - 1].addAll(movingCranes.reversed())
        }

        return stacks.joinToString("") { it.lastOrNull() ?: " " }
    }

    fun parseStackInput(input: List<String>): List<MutableList<String>> {
        return input.reversed().drop(1).map { it.drop(1).windowed(1,4) }.let { slots ->
            slots.flatten().withIndex().groupBy { it.index % slots.first().count() }.map { it.value.map { it.value }.filter { it.isNotBlank() }.toMutableList() }
        }
    }

    fun parseRearrangementsInput(input: List<String>): List<StackRearrangement> {
        return input.map {
            """move (\d+) from (\d+) to (\d+)""".toRegex().find(it)!!.destructured.let { (count, origin, destination) ->
                StackRearrangement(count.toInt(), origin.toInt(), destination.toInt())
            }
        }
    }

    val (stackInput, rearrangementsInput) = readInput("day05_dataset").split("\n\n").map { it.split("\n") }
    val stacks = parseStackInput(stackInput)
    val rearrangements = parseRearrangementsInput(rearrangementsInput)
    val (stacks1, stacks2) = (0..1).map { stacks.map { it.toMutableList() }.toList() }

    part1(stacks1, rearrangements).println()
    part2(stacks2, rearrangements).println()
}

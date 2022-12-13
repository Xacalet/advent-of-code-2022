/**
 * ADVENT OF CODE 2022 (https://adventofcode.com/2022/)
 * 
 * Solution to day 4 (https://adventofcode.com/2022/day/4)
 *
 */

fun main() {

    fun part1(assignments: List<Pair<IntRange, IntRange>>): Int {
        return assignments.count { (elf1, elf2) ->
            (elf1.first >= elf2.first && elf1.last <= elf2.last) ||
                    (elf2.first >= elf1.first && elf2.last <= elf1.last)
        }
    }

    fun part2(assignments: List<Pair<IntRange, IntRange>>): Int {
        return assignments.count { (elf1, elf2) ->
            (elf1.first >= elf2.first && elf1.first <= elf2.last) ||
                    (elf2.first >= elf1.first && elf2.first <= elf1.last)
        }
    }

    val assignments = readInputLines("day04_dataset").map { line ->
        line.split(",").map { assignment ->
            assignment.split("-").let { 
                (start, end) -> start.toInt()..end.toInt() 
            }   
        }.let { (a1, a2) -> Pair(a1, a2) }
    }

    part1(assignments).println()
    part2(assignments).println()
}

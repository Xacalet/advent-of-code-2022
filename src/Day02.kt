/**
 * ADVENT OF CODE 2022 (https://adventofcode.com/2022/)
 * 
 * Solution to day 2 (https://adventofcode.com/2022/day/2)
 *
 */

private enum class Hand(val points: Int) {
    ROCK(1), PAPER(2), SCISSORS(3)
}

fun main() {

    fun resolveRound(myHand: Hand, opponentsHand: Hand): Int {
        val pointsFromChosenHand = myHand.points
        val pointsFromRoundOutcome = if (myHand == opponentsHand) {
            3
        } else if (myHand == Hand.PAPER && opponentsHand == Hand.ROCK ||
            myHand == Hand.SCISSORS && opponentsHand == Hand.PAPER ||
            myHand == Hand.ROCK && opponentsHand == Hand.SCISSORS) {
            6
        } else {
            0
        }
        return pointsFromChosenHand + pointsFromRoundOutcome
    }

    fun part1(strategy: List<Pair<String, String>>): Int {
        return strategy.map { (h1, h2) ->
            val opponentsHand = when (h1) {
                "A" -> Hand.ROCK
                "B" -> Hand.PAPER
                "C" -> Hand.SCISSORS
                else -> throw IllegalArgumentException("Unknown input: $h1")
            }
            val myHand = when (h2) {
                "X" -> Hand.ROCK
                "Y" -> Hand.PAPER
                "Z" -> Hand.SCISSORS
                else -> throw IllegalArgumentException("Unknown input: $h2")
            }
            Pair(opponentsHand, myHand)
        }.sumOf { (opponentsHand, myHand) ->
            resolveRound(myHand, opponentsHand)
        }
    }

    fun part2(strategy: List<Pair<String, String>>): Int {
        return strategy.map { (h1, h2) ->
            val opponentsHand = when (h1) {
                "A" -> Hand.ROCK
                "B" -> Hand.PAPER
                "C" -> Hand.SCISSORS
                else -> throw IllegalArgumentException("Unknown input: $h1")
            }
            val myHand = when (h2) {
                "X" -> when(opponentsHand) {
                    Hand.ROCK -> Hand.SCISSORS
                    Hand.PAPER -> Hand.ROCK
                    Hand.SCISSORS -> Hand.PAPER
                }
                "Y" -> opponentsHand
                "Z" -> when(opponentsHand) {
                    Hand.ROCK -> Hand.PAPER
                    Hand.PAPER -> Hand.SCISSORS
                    Hand.SCISSORS -> Hand.ROCK
                }
                else -> throw IllegalArgumentException("Unknown input: $h2")
            }
            Pair(opponentsHand, myHand)
        }.sumOf { (opponentsHand, myHand) ->
            resolveRound(myHand, opponentsHand)
        }
    }

    val strategy = readInputLines("day02_dataset").map { line ->
        line.split(" ").let { (h1, h2) -> Pair(h1, h2) }
    }

    part1(strategy).println()
    part2(strategy).println()
}

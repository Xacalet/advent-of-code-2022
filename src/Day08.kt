/**
 * ADVENT OF CODE 2022 (https://adventofcode.com/2022/)
 * 
 * Solution to day 8 (https://adventofcode.com/2022/day/8)
 *
 */

import java.lang.Integer.max

fun main() {

    fun part1(trees: List<List<Int>>): Int {

        fun isVisibleFromTop(i: Int, j: Int): Boolean =
            !(i - 1).downTo(0).any { trees[i][j] <= trees[it][j] }
        fun isVisibleFromBottom(i: Int, j: Int): Boolean =
            !((i + 1)..trees.lastIndex).any { trees[i][j] <= trees[it][j] }
        fun isVisibleFromLeft(i: Int, j: Int): Boolean =
            !(j - 1).downTo(0).any { trees[i][j] <= trees[i][it] }
        fun isVisibleFromRight(i: Int, j: Int): Boolean =
            !((j + 1)..trees[0].lastIndex).any { trees[i][j] <= trees[i][it] }

        var visibleTrees = 0
        trees.forEachIndexed { i, row ->
            row.indices.forEach { j ->
                if (isVisibleFromTop(i, j) ||
                    isVisibleFromBottom(i, j) ||
                    isVisibleFromLeft(i, j) ||
                    isVisibleFromRight(i, j)) {
                    visibleTrees++
                }
            }
        }

        return visibleTrees
    }

    fun part2(trees: List<List<Int>>): Int {

        fun visibleTreesFromTop(i: Int, j: Int): Int =
            (i - 1).downTo(0).countUntil { trees[i][j] <= trees[it][j] }
        fun visibleTreesFromBottom(i: Int, j: Int): Int =
            ((i + 1)..trees.lastIndex).countUntil { trees[i][j] <= trees[it][j] }
        fun visibleTreesFromLeft(i: Int, j: Int): Int =
            (j - 1).downTo(0).countUntil { trees[i][j] <= trees[i][it] }
        fun visibleTreesFromRight(i: Int, j: Int): Int =
            ((j + 1)..trees[0].lastIndex).countUntil { trees[i][j] <= trees[i][it] }

        var widestView = -1
        trees.forEachIndexed { i, row ->
            row.indices.forEach { j ->
                widestView = max(visibleTreesFromTop(i, j) *
                    visibleTreesFromBottom(i, j) *
                    visibleTreesFromLeft(i, j) *
                    visibleTreesFromRight(i, j), widestView)
            }
        }

        return widestView
    }

    val trees = readInputLines("day08_dataset").map { line -> line.map { it.toString().toInt() }  }

    part1(trees).println()
    part2(trees).println()
}

/**
 * ADVENT OF CODE 2022 (https://adventofcode.com/2022/)
 * 
 * Solution to day 7 (https://adventofcode.com/2022/day/7)
 *
 */

private sealed interface Node

private data class FileNode(
    val size: Long,
    val name: String,
): Node

private data class DirectoryNode(
    val name: String,
    val parentNode: DirectoryNode? = null,
    val contents: MutableMap<String, Node> = mutableMapOf(),
    var size: Long = 0L,
): Node

fun main() {

    fun part1(root: DirectoryNode): Long {

        fun DirectoryNode.calcSize(sizes: MutableList<Long>): Long {
            return contents.values.sumOf { content ->
                when(content) {
                    is FileNode -> content.size
                    is DirectoryNode -> content.calcSize(sizes)
                }
            }.also { size ->
                this.size = size
                if (size <= 100_000) {
                    sizes.add(size)
                }
            }
        }

        return mutableListOf<Long>().let { sizes ->
            root.calcSize(sizes)
            sizes.sum()
        }
    }

    fun part2(root: DirectoryNode): Long {

        fun DirectoryNode.getSmallestDirToFreeSpace(spaceToFree: Long): DirectoryNode =
            contents.values
                .filterIsInstance(DirectoryNode::class.java)
                .filter { dir -> dir.size >= spaceToFree }
                .map { dir -> dir.getSmallestDirToFreeSpace(spaceToFree) }
                .minByOrNull { dir -> dir.size } ?: this

        val unusedSpace = 70_000_000L - root.size
        val spaceToFree = 30_000_000L - unusedSpace
        val directoryToDelete = root.getSmallestDirToFreeSpace(spaceToFree)
        return directoryToDelete.size
    }

    fun buildFileSystem(outputLines: List<String>): DirectoryNode {
        val root = DirectoryNode("/")
        var currentDirectory: DirectoryNode = root
        outputLines.forEach { line ->
            when {
                "\\$ cd (\\.\\.|/|(\\w+))".toRegex().matches(line) -> {
                    val (param) = "\\$ cd (.+)".toRegex().find(line)!!.destructured
                    currentDirectory =  when(param) {
                        ".." -> currentDirectory.parentNode!!
                        "/" -> root
                        else -> currentDirectory.contents[param] as DirectoryNode
                    }
                }
                "dir (\\w+)".toRegex().matches(line) -> {
                    val (name) = "dir (\\w+)".toRegex().find(line)!!.destructured
                    currentDirectory.contents[name] = DirectoryNode(name, currentDirectory)
                }
                "(\\d+) ([\\w.]+)".toRegex().matches(line) -> {
                    val (size, name) = "(\\d+) ([\\w.]+)".toRegex().find(line)!!.destructured
                    currentDirectory.contents[name] = FileNode(size.toLong(), name)
                }
                line == "$ ls" -> Unit
                else -> throw IllegalArgumentException("Unknown command: $line")
            }
        }
        return root
    }

    val fileSystem = buildFileSystem(readInputLines("day07_dataset"))

    part1(fileSystem).println()
    part2(fileSystem).println()
}

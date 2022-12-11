import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInputLines(name: String): List<String> {
    return File(ClassLoader.getSystemClassLoader().getResource("$name.txt")!!.toURI()).readLines()
}

/**
 * Reads string from the given input txt file.
 */
fun readInput(name: String): String {
    return File(ClassLoader.getSystemClassLoader().getResource("$name.txt")!!.toURI()).readText()
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

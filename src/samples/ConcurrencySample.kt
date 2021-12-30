package samples

import kotlinx.coroutines.*

fun main() = runBlocking {
    val deferreds = (1..3).map {
        async {
            delay(1000L * it)
            log("Loading $it")
            it
        }
    }
    val sum = deferreds.awaitAll().sum()
    println("$sum")
}
package samples

import kotlin.concurrent.thread

fun main() {
    thread(isDaemon = true) {
        while (true) {
            println("Running")
            Thread.sleep(100)
        }
    }
    println("Done")
    Thread.sleep(1000)
}
package tasks

import contributors.MockGithubService
import contributors.expectedConcurrentResults
import contributors.testRequestData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

class Request5ConcurrentKtTest {
    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun testConcurrent() = runBlockingTest {
        @OptIn(ExperimentalCoroutinesApi::class)
        val startTime = currentTime
        val result = loadContributorsConcurrent(MockGithubService, testRequestData)
        Assert.assertEquals("Wrong result for 'loadContributorsConcurrent'", expectedConcurrentResults.users, result)
        @OptIn(ExperimentalCoroutinesApi::class)
        val totalTime = currentTime - startTime
        Assert.assertEquals(
            "The calls run concurrently, so the total virtual time should be 2200 ms: " +
                    "1000 ms for repos request plus max(1000, 1200, 800) = 1200 ms for concurrent contributors requests)",
            expectedConcurrentResults.timeFromStart, totalTime
        )
        Assert.assertTrue(
            "The calls run concurrently, so the total virtual time should be 2200 ms: " +
                    "1000 ms for repos request plus max(1000, 1200, 800) = 1200 ms for concurrent contributors requests)",
            totalTime in expectedConcurrentResults.timeFromStart..(expectedConcurrentResults.timeFromStart + 500)
        )
    }
}
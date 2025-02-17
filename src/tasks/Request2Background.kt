package tasks

import contributors.GitHubService
import contributors.RequestData
import contributors.User
import kotlin.concurrent.thread

fun loadContributorsBackground(service: GitHubService, req: RequestData, updateResults: (List<User>) -> Unit) {
    thread(name = "Background Thread") {
        val users = loadContributorsBlocking(service, req)
        updateResults(users)
    }
}
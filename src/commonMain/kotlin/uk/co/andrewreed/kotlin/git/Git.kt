package uk.co.andrewreed.kotlin.git

interface Git {
    suspend fun show(ref: String): String
}

expect class GitImpl: Git

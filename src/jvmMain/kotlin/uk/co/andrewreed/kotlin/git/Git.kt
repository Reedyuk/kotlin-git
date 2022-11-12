package uk.co.andrewreed.kotlin.git

actual class GitImpl: Git {
    override suspend fun show(ref: String): String = BashCommand.execute("git show $ref")
}

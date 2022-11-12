package uk.co.andrewreed.kotlin.git

import uk.co.andrewreed.runTest
import kotlin.test.Test

class GitTests {
    @Test
    fun showTest() = runTest {
        val git = GitImpl()
        val show = git.show("1")
        println(show)
    }
}
package uk.co.andrewreed

import kotlinx.coroutines.runBlocking

actual fun runTest(test: suspend () -> Unit) = runBlocking { test() }

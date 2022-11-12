package uk.co.andrewreed.kotlin.git

import java.io.BufferedReader
import java.io.InputStreamReader

class BashCommand {
    companion object {
        fun execute(command: String): String {
            println("Executing BASH command:\n   $command")
            val r = Runtime.getRuntime()
            // Use bash -c so we can handle things like multi commands separated by ; and
            // things like quotes, $, |, and \. My tests show that command comes as
            // one argument to bash, so we do not need to quote it to make it one thing.
            // Also, exec may object if it does not have an executable file as the first thing,
            // so having bash here makes it happy provided bash is installed and in path.
            val commands = arrayOf("bash", "-c", command)
            val p = r.exec(commands)
            p.waitFor()
            val b = BufferedReader(InputStreamReader(p.inputStream))
            var line: String?
            var output = ""
            while (b.readLine().also { line = it } != null) {
                println(line)
                output += line
            }
            b.close()
            return output
        }
    }
}

package com.isograd.exercise

import kotlin.Throws
import java.lang.ClassNotFoundException
import java.lang.reflect.InvocationTargetException
import java.lang.NoSuchMethodException
import java.lang.IllegalAccessException
import java.io.IOException
import com.isograd.exercise.TestUtils
import org.junit.Assert
import java.nio.file.Path
import java.nio.file.Files
import java.io.FileOutputStream
import java.io.OutputStream
import java.io.PrintStream
import java.util.*

object TestUtils {
    @Throws(ClassNotFoundException::class, InvocationTargetException::class, NoSuchMethodException::class, IllegalAccessException::class, IOException::class)
    fun runTests(classTest: Class<*>) {
        val klass = classTest.classLoader.loadClass(classTest.name.replace("Test", ""))
        runTest(classTest, klass, 0)
        var i = 1
        while (runTest(classTest, klass, i)) i++
    }

    @Throws(NoSuchMethodException::class, IOException::class, InvocationTargetException::class, IllegalAccessException::class)
    fun runTest(classTest: Class<*>, klass: Class<*>, index: Int): Boolean {
        val instance=klass.getConstructor().newInstance()

        val input = classTest.getResourceAsStream("input$index.txt") ?: return false
        println("test file$index")
        val tempFile = Files.createTempFile("temp", ".txt")
        val output: OutputStream = FileOutputStream(tempFile.toFile())
        val initMethod = klass.getDeclaredMethod("initDebug", PrintStream::class.java)
        initMethod.invoke(instance, PrintStream(output))
        val method = klass.getDeclaredMethod("mainContent", Scanner::class.java)
        method.invoke(instance, Scanner(input))
        val expectedOutput = Scanner(classTest.getResourceAsStream("output$index.txt"))
        val actualOutput = Scanner(tempFile)
        Assert.assertEquals(actualOutput.hasNextLine(), expectedOutput.hasNextLine())
        while (actualOutput.hasNextLine()) {
            val expected = expectedOutput.nextLine()
            val actual = actualOutput.nextLine()
            Assert.assertEquals(expected, actual)
            println(actual)
            Assert.assertEquals(actualOutput.hasNextLine(), expectedOutput.hasNextLine())
        }
        return true
    }
}
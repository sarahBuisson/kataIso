package com.isograd.exercise

import kotlin.Throws
import java.lang.ClassNotFoundException
import java.lang.reflect.InvocationTargetException
import java.lang.NoSuchMethodException
import java.lang.IllegalAccessException
import java.io.IOException
import com.isograd.exercise.TestUtils
import org.junit.Test
import java.nio.file.Path
import java.nio.file.Files
import java.io.FileOutputStream
import java.io.PrintStream

class IsoContestTest {
    @Test
    @Throws(ClassNotFoundException::class, NoSuchMethodException::class, IOException::class, IllegalAccessException::class, InvocationTargetException::class)
    fun testMain() {
        TestUtils.runTests(this.javaClass)
    }
}
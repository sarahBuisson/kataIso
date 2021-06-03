package com.isograd.exercise;

import org.junit.Assert;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class TestUtils {


    public static void runTests(Class classTest) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {

        Class klass = classTest.getClassLoader().loadClass(classTest.getName().replace("Test", ""));

        runTest(classTest, klass, 0);
        int i = 1;
        while (runTest(classTest, klass, i))
            i++;

    }

    public static boolean runTest(Class classTest, Class klass, int index) throws NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException {
        InputStream input = classTest.getResourceAsStream("input" + index + ".txt");
        if (input == null)
            return false;

        System.out.println("test file"+index);
        Path tempFile = Files.createTempFile("temp", ".txt");
        OutputStream output=new FileOutputStream(tempFile.toFile());

        Method initMethod = klass.getDeclaredMethod("initDebug", PrintStream.class);
        initMethod.invoke(null, new PrintStream(output));



        Method method = klass.getDeclaredMethod("mainContent", Scanner.class);


        method.invoke(null, new Scanner(input));


        Scanner expectedOutput = new Scanner(classTest.getResourceAsStream("output" + index + ".txt"));
        Scanner actualOutput =  new Scanner(tempFile);

        Assert.assertEquals(actualOutput.hasNextLine(), expectedOutput.hasNextLine());

        while(actualOutput.hasNextLine()) {
    String expected=expectedOutput.nextLine();
    String actual=actualOutput.nextLine();

    Assert.assertEquals(expected, actual);
            System.out.println(actual);
    Assert.assertEquals(actualOutput.hasNextLine(), expectedOutput.hasNextLine());


};


        return true;

    }
}

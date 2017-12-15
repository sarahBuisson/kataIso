package com.isograd.exercise;


import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class IsoContestTest {


    @Test
    public void testMain() throws ClassNotFoundException, NoSuchMethodException, IOException, IllegalAccessException, InvocationTargetException {

        TestUtils.runTests(this.getClass());
    }
}
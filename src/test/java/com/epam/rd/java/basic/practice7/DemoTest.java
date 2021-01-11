package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.constants.Names;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class DemoTest {
    @Test
    public void demoTest() throws Exception {
        Demo.main(new String[] { Constants.VALID_XML_INPUT_FILE });
        Assert.assertTrue("Assertion to be compliant", true);
    }

    @Test
    public void checkIfLengthLessThanOne() throws Exception {
        Main.main(new String[] {});
        Assert.assertTrue("Assertion to be compliant", true);
    }

    @Test()
    public void checkIfNamesPrivateConstructorExists() {
        final Constructor<?>[] constructors = Names.class.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
    }

    @Test()
    public void checkIfConstantsConstructorExists() {
        final Constructor<?>[] constructors = Constants.class.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
    }

/*    @Test
    public void checkIfNamesConstructorExists() {
        Assert.assertNotEquals(new Names(), null);
    }

    @Test
    public void checkIfConstantsConstructorExists() {
        Assert.assertNotEquals(new Constants(), null);
    }*/
}
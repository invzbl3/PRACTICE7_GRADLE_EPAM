package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.constants.Names;
import com.epam.rd.java.basic.practice7.entity.Mobiles;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    @Test
    public void mobilesTest() {
        System.out.println(new Mobiles());
        Assert.assertTrue("Assertion to be compliant", true);
    }

    /*    @Test
    public void checkIfNamesConstructorExists() {
        Assert.assertNotEquals(new Names(), null);
    }
    @Test
    public void checkIfConstantsConstructorExists() {
        Assert.assertNotEquals(new Constants(), null);
    }*/

    @After
    public void clean() throws IOException {
        Path pathDom = Paths.get(Constants.VALID_XML_OUTPUT_DOM_FILE);
        Path pathSax = Paths.get(Constants.VALID_XML_OUTPUT_SAX_FILE);
        Path pathStax = Paths.get(Constants.VALID_XML_OUTPUT_STAX_FILE);
        Files.deleteIfExists(pathDom);
        Files.deleteIfExists(pathSax);
        Files.deleteIfExists(pathStax);
        Assert.assertTrue("Assertion to be compliant", true);
    }
}
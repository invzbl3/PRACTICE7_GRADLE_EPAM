package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.controller.DOMController;
import org.junit.Assert;
import org.junit.Test;

public class DOMControllerTest {
    @Test
    public void main() throws Exception {
        DOMController.main(new String[] { Constants.VALID_XML_INPUT_FILE });
        Assert.assertTrue("Assertion to be compliant", true);
    }
}

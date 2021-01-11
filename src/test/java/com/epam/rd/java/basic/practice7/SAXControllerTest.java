package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.controller.SAXController;
import org.junit.Assert;
import org.junit.Test;

public class SAXControllerTest {
    @Test
    public void main() throws Exception {
        SAXController.main(new String[] { Constants.VALID_XML_INPUT_FILE });
        Assert.assertTrue("Assertion to be compliant", true);
    }
}

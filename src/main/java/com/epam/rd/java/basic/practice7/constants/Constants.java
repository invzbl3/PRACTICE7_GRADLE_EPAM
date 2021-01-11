package com.epam.rd.java.basic.practice7.constants;

public final class Constants {

    private Constants() {}

    // files
    public static final String VALID_XML_INPUT_FILE = "input.xml";
    public static final String VALID_XML_OUTPUT_DOM_FILE = "output.dom.xml";
    public static final String VALID_XML_OUTPUT_SAX_FILE = "output.sax.xml";
    public static final String VALID_XML_OUTPUT_STAX_FILE = "output.stax.xml";
    public static final String INVALID_XML_FILE = "input-invalid.xml";
    public static final String XSD_FILE = "input.xsd";

    // validation features
    public static final String FEATURE_TURN_VALIDATION_ON = "http://xml.org/sax/features/validation";
    public static final String FEATURE_TURN_SCHEMA_VALIDATION_ON = "http://apache.org/xml/features/validation/schema";

}
package com.epam.rd.java.basic.practice7.controller;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.constants.Names;
import com.epam.rd.java.basic.practice7.entity.Mobile;
import com.epam.rd.java.basic.practice7.entity.Mobiles;
import com.epam.rd.java.basic.practice7.entity.Samsung;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;


public class SAXController extends DefaultHandler {

    private static final Logger logger = Logger.getLogger("SAXController.class");
    private static final String MESSAGE = "Something went wrong:";
    public static final String DELIMITER = "====================================";
    
    private final String xmlFileName;

    // current element name holder
    private String currentElement;

    // main container
    private Mobiles mobiles;

    private Mobile mobile;

    private Samsung samsung;

    public SAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     * Parses XML document.
     *
     * @param validate
     *            If true validate XML document against its XML schema. With
     *            this parameter it is possible make parser validating.
     */
    public void parse(boolean validate)
            throws ParserConfigurationException, SAXException, IOException {
        System.out.println("Invoking parse()");
        // obtain sax parser factory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        
        try{
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);

        } catch(ParserConfigurationException | SAXNotRecognizedException | SAXNotSupportedException exp){
            logger.severe(MESSAGE);
            logger.severe(Arrays.toString(exp.getStackTrace()));
        }
        
        // XML document contains namespaces
        factory.setNamespaceAware(true);

        // set validation
        if (validate) {
            factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        SAXParser parser = factory.newSAXParser();
        parser.parse(xmlFileName, this);
    }
    
    // ///////////////////////////////////////////////////////////
    // ERROR HANDLER IMPLEMENTATION
    // ///////////////////////////////////////////////////////////

    @Override
    public void error(org.xml.sax.SAXParseException e) throws SAXException {
        // if XML document not valid just throw exception
        throw e;
    }

    public Mobiles getMobiles() {
        return mobiles;
    }

    // ///////////////////////////////////////////////////////////
    // CONTENT HANDLER IMPLEMENTATION
    // ///////////////////////////////////////////////////////////


    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {

        currentElement = localName;

        if (Names.MOBILES.equals(currentElement)) {
            mobiles = new Mobiles();
            return;
        }

        if (Names.MOBILE.equals(currentElement)) {
            mobile = new Mobile();
            return;
        }

        if (Names.SAMSUNG.equals(currentElement)) {
            samsung = new Samsung();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        String elementText = new String(ch, start, length).trim();

        // return if content is empty
        if (elementText.isEmpty()) {
            return;
        }

        if (Names.MODEL.equals(currentElement)) {
            mobile.setModel(elementText);
            return;
        }

        if (Names.OS.equals(currentElement)) {
            mobile.setOS(elementText);
            return;
        }

        if (Names.ORIGIN.equals(currentElement)) {
            mobile.setOrigin(elementText);
            return;
        }

        if (Names.MATERIAL.equals(currentElement)) {
            mobile.setMaterial(elementText);
            return;
        }

        if (Names.WLAN.equals(currentElement)) {
            samsung.setWlan(elementText);
            return;
        }

        if (Names.CARD_SLOT.equals(currentElement)) {
            samsung.setCardSlot(elementText);
            return;
        }

        if (Names.RADIO_AVAILABILITY.equals(currentElement)) {
            samsung.setRadioAvailability(elementText);
            return;
        }

        if (Names.BLUETOOTH_AVAILABILITY.equals(currentElement)) {
            samsung.setBluetoothAvailability(elementText);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (samsung != null) {
            mobile.setSamsung(samsung);
        }

        if (Names.MOBILE.equals(localName)) {
            // just add mobile to container
            mobiles.getMobileList().add(mobile);
        }
    }

    public static void main(String[] args) throws Exception {
        
        // to validate xsd schema file
        SAXController saxCon = new SAXController(Constants.XSD_FILE);
        saxCon.parse(false);


        // try to parse valid XML file (success)
        SAXController saxContr = new SAXController(Constants.VALID_XML_INPUT_FILE);

        // do parse with validation on (success)
        saxContr.parse(false);

        // obtain container
        Mobiles mobiles = saxContr.getMobiles();

        // we have Mobiles object at this point:
        System.out.println(DELIMITER);
        System.out.print("Here is the mobiles: \n" + mobiles);
        System.out.println(DELIMITER);
        
        // now try to parse NOT valid XML (failed)
        saxContr = new SAXController(Constants.INVALID_XML_FILE);
        try {
            // do parse with validation on (failed)
            saxContr.parse(true);
        } catch (Exception ex) {
            System.err.println(DELIMITER);
            System.err.println("Validation is failed:\n" + ex.getMessage());
            System.err
                    .println("Try to print mobiles object:" + saxContr.getMobiles());
            System.err.println(DELIMITER);
        }

        // and now try to parse NOT valid XML with validation off (success)
        saxContr.parse(false);

        // we have Mobiles object at this point:
        System.out.println(DELIMITER);
        System.out.print("Here is the mobiles: \n" + saxContr.getMobiles());
        System.out.println(DELIMITER);
    }
}
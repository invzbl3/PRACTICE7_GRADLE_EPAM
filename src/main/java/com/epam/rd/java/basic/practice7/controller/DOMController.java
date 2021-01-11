package com.epam.rd.java.basic.practice7.controller;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.constants.Names;
import com.epam.rd.java.basic.practice7.entity.Mobile;
import com.epam.rd.java.basic.practice7.entity.Mobiles;
import com.epam.rd.java.basic.practice7.entity.Samsung;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.xml.XMLConstants;

public class DOMController {
    
    private static final Logger logger = Logger.getLogger("DOMController.class");
    private static final String MESSAGE = "Something went wrong:";
    public static final String DELIMITER = "====================================";

    private final String xmlFileName;

    // main container
    private Mobiles mobiles;

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Mobiles getMobiles() {
        return mobiles;
    }

    /**
     * Parses XML document.
     *
     * @param validate If true validate XML document against its XML schema.
     */
    public void parse(boolean validate)
            throws ParserConfigurationException, SAXException, IOException {
                
        System.out.println("Invoking parse()");
        // obtain DOM parser
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try{
            dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);

        } catch(ParserConfigurationException exp){
            logger.severe(MESSAGE);
            logger.severe(Arrays.toString(exp.getStackTrace()));
        }
        
        // set properties for Factory

        // XML document contains namespaces
        dbf.setNamespaceAware(true);

        // make parser validating
        if (validate) {
            // turn validation on
            dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);

            // turn on xsd validation
            dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        DocumentBuilder db = dbf.newDocumentBuilder();

        // set error handler
        db.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                // throw exception if XML document is NOT valid
                throw e;
            }
        });

        // parse XML document
        Document document = db.parse(xmlFileName);

        // get root element
        Element root = document.getDocumentElement();

        // create container
        mobiles = new Mobiles();

        // obtain mobiles nodes
        NodeList mobileNodes = root
                .getElementsByTagName(Names.MOBILE);

        // process mobiles nodes
        for (int j = 0; j < mobileNodes.getLength(); j++) {
            Mobile question = getMobile(mobileNodes.item(j));
            // add mobile to container
            mobiles.getMobileList().add(question);
        }
    }
    
    /**
     * Extracts question object from the question XML node.
     *
     * @param qNode Question node.
     * @return Question object.
     */
    private static Mobile getMobile(Node qNode) {
        
        System.out.println("Invoking getMobile()");
        
        Mobile mobile = new Mobile();
        Element qElement = (Element) qNode;

        // process model
        Node modNode = qElement.getElementsByTagName(Names.MODEL)
                .item(0);
        // set model
        mobile.setModel(modNode.getTextContent());

        // process os
        Node hanNode = qElement.getElementsByTagName(Names.OS)
                .item(0);
        // set os
        mobile.setOS(hanNode.getTextContent());

        // process origin
        Node orNode = qElement.getElementsByTagName(Names.ORIGIN)
                .item(0);
        // set origin
        mobile.setOrigin(orNode.getTextContent());

        // process material
        Node matNode = qElement.getElementsByTagName(Names.MATERIAL)
                .item(0);
        // set material
        mobile.setMaterial(matNode.getTextContent());

        // process samsung
        Samsung ttc = new Samsung(getSamsung(qElement.getElementsByTagName(Names.SAMSUNG)));
        // set samsung
        mobile.setSamsung(ttc);

        return mobile;
    }

    /**
     * Extracts Samsung object from the Samsung XML node.
     *
     * @param nodeList Answer node.
     * @return Samsung object.
     */
    private static Samsung getSamsung(NodeList nodeList) {
        
        System.out.println("Invoking getSamsung()");
        
        Samsung samsung = new Samsung();
        Element samsungElement = (Element) nodeList.item(0);

        // process wlan
        Node lrNode = samsungElement.getElementsByTagName(Names.WLAN).item(0);
        // set wlan
        samsung.setWlan(lrNode.getTextContent());

        // process card slot
        Node srNode = samsungElement.getElementsByTagName(Names.CARD_SLOT).item(0);
        // set card slot
        samsung.setCardSlot(srNode.getTextContent());

        // process radio availability
        Node clipNode = samsungElement.getElementsByTagName(Names.RADIO_AVAILABILITY).item(0);
        // set radio availability
        samsung.setRadioAvailability(clipNode.getTextContent());

        // process bluetooth availability
        Node opticsNode = samsungElement.getElementsByTagName(Names.BLUETOOTH_AVAILABILITY).item(0);
        // set bluetooth availability
        samsung.setBluetoothAvailability(opticsNode.getTextContent());

        return samsung;
    }

    // //////////////////////////////////////////////////////
    // Static util methods
    // //////////////////////////////////////////////////////

    /**
     * Creates and returns DOM of the Mobiles container.
     *
     * @param mobiles Mobiles object.
     * @throws ParserConfigurationException
     */
    public static Document getDocument(Mobiles mobiles) throws ParserConfigurationException {

        System.out.println("Invoking getDocument()");

        // obtain DOM parser
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

        // set properties for Factory

        // XML document contains namespaces
        dbf.setNamespaceAware(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        // create root element
        Element root = document.createElement(Names.MOBILES);

        // add root element
        document.appendChild(root);

        // add questions elements
        for (Mobile mobile : mobiles.getMobileList()) {

            // add mobile
            Element gElement = document.createElement(Names.MOBILE);
            root.appendChild(gElement);

            // add model
            Element modElement =
                    document.createElement(Names.MODEL);
            modElement.setTextContent(mobile.getModel());
            gElement.appendChild(modElement);

            // add os
            Element hElement =
                    document.createElement(Names.OS);
            hElement.setTextContent(mobile.getOS());
            gElement.appendChild(hElement);

            // add origin
            Element orElement =
                    document.createElement(Names.ORIGIN);
            orElement.setTextContent(mobile.getOrigin());
            gElement.appendChild(orElement);

            // add material
            Element matElement =
                    document.createElement(Names.MATERIAL);
            matElement.setTextContent(mobile.getMaterial());
            gElement.appendChild(matElement);

            // add samsung
            Element ttcElement = document.createElement(Names.SAMSUNG);

            // add wlan
            Element lrElement = document.createElement(Names.WLAN);
            lrElement.setTextContent(mobile.getSamsung().getWlan());
            ttcElement.appendChild(lrElement);

            // add card slot
            Element srElement = document.createElement(Names.CARD_SLOT);
            srElement.setTextContent(mobile.getSamsung().getCardSlot());
            ttcElement.appendChild(srElement);

            // add radio availability
            Element clipElement = document.createElement(Names.RADIO_AVAILABILITY);
            clipElement.setTextContent(mobile.getSamsung().getRadioAvailability());
            ttcElement.appendChild(clipElement);

            // add bluetooth availability
            Element optElement = document.createElement(Names.BLUETOOTH_AVAILABILITY);
            optElement.setTextContent(mobile.getSamsung().getBluetoothAvailability());
            ttcElement.appendChild(optElement);

            gElement.appendChild(ttcElement);
        }

        return document;
    }

    /**
     * Saves Mobiles object to XML file.
     *
     * @param mobiles        Mobiles object to be saved.
     * @param xmlFileName Output XML file name.
     */
    public static void saveToXML(Mobiles mobiles, String xmlFileName)
            throws ParserConfigurationException, TransformerException {
        System.out.println("Invoking saveToXML()");
        // Test -> DOM -> XML
        saveToXML(getDocument(mobiles), xmlFileName);
    }

    /**
     * Save DOM to XML.
     *
     * @param document    DOM to be saved.
     * @param xmlFileName Output XML file name.
     */
    public static void saveToXML(Document document, String xmlFileName)
            throws TransformerException {
        System.out.println("Invoking saveToXML()");
        StreamResult result = new StreamResult(new File(xmlFileName));

        // set up transformation
        TransformerFactory tf = TransformerFactory.newInstance();
        // Secure this "Transformer" by either disabling external DTDs or enabling secure processing.
        tf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        
        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");

        // run transformation
        t.transform(new DOMSource(document), result);
    }

    public static void main(String[] args) throws Exception {
        
        // to validate xsd schema file
        DOMController domCon = new DOMController(Constants.XSD_FILE);
        domCon.parse(true);

        // try to parse NOT valid XML document with validation on (failed)
        DOMController domContr = new DOMController(Constants.VALID_XML_INPUT_FILE);
        try {
            // parse with validation (failed)
            domContr.parse(true);
        } catch (SAXException ex) {
            System.err.println(DELIMITER);
            System.err.println("XML not valid");
            System.err.println("Mobiles object --> " + domContr.getMobiles());
            System.err.println(DELIMITER);
        }

        // try to parse NOT valid XML document with validation off (success)
        domContr.parse(false);

        // we have Mobiles object at this point:
        System.out.println(DELIMITER);
        System.out.print("Here is the mobiles: \n" + domContr.getMobiles());
        System.out.println(DELIMITER);

        // save mobiles in XML file
        Mobiles mobiles = domContr.getMobiles();
        DOMController.saveToXML(mobiles, Constants.VALID_XML_INPUT_FILE.substring(0, 5) + ".dom-result.xml");
    }
}
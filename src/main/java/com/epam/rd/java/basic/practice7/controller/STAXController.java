package com.epam.rd.java.basic.practice7.controller;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.constants.Names;
import com.epam.rd.java.basic.practice7.entity.Mobile;
import com.epam.rd.java.basic.practice7.entity.Mobiles;
import com.epam.rd.java.basic.practice7.entity.Samsung;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.transform.stream.StreamSource;
import java.util.Objects;

public class STAXController extends DefaultHandler {
    private final String xmlFileName;

    // main container
    private Mobiles mobiles;

    public Mobiles getMobiles() {
        return mobiles;
    }

    public STAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     * Parses XML document with StAX (based on event reader). There is no validation during the
     * parsing.
     */
    public void parse() throws XMLStreamException {
        System.out.println("Invoking parse()");
        Mobile mobile = null;
        Samsung samsung = null;

        // current element name holder
        String currentElement = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

        XMLEventReader reader = factory.createXMLEventReader(
                new StreamSource(xmlFileName));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            // skip any empty content
            if (skipEmptyContent(event)) continue;

            // handler for start tags
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();
                if (Names.MOBILES.equals(currentElement)) {
                    mobiles = new Mobiles();
                } else if (Names.MOBILE.equals(currentElement)) {
                    mobile = new Mobile();
                } else if (Names.SAMSUNG.equals(currentElement)) {
                    samsung = new Samsung();
                }
            }

            // handler for contents
            if (event.isCharacters()) {
                handlerForContents(mobile, samsung, currentElement, event);
            }

            // handler for end tags
            if (event.isEndElement()) {
                handlerForEndTags(mobile, samsung, event);
            }
        }
        reader.close();
    }

    private boolean skipEmptyContent(XMLEvent event) {
        return event.isCharacters() && event.asCharacters().isWhiteSpace();
    }

    private void handlerForContents(Mobile mobile, Samsung samsung, String currentElement, XMLEvent event) {
        Characters characters = event.asCharacters();
        if (Names.MODEL.equals(currentElement)) {
            Objects.requireNonNull(mobile);
            mobile.setModel(characters.getData());
        }
        if (Names.OS.equals(currentElement)) {
            Objects.requireNonNull(mobile);
            mobile.setOS(characters.getData());
        }
        if (Names.ORIGIN.equals(currentElement)) {
            Objects.requireNonNull(mobile);
            mobile.setOrigin(characters.getData());
        }

        handlerForMaterialWlanCardSlot(mobile, samsung, currentElement, characters);
        handlerForRadioBluetooth(samsung, currentElement, characters);
    }

    private void handlerForRadioBluetooth(Samsung samsung, String currentElement, Characters characters) {
        if (Names.RADIO_AVAILABILITY.equals(currentElement)) {
            Objects.requireNonNull(samsung);
            samsung.setRadioAvailability(characters.getData());
        }
        if (Names.BLUETOOTH_AVAILABILITY.equals(currentElement)) {
            Objects.requireNonNull(samsung);
            samsung.setBluetoothAvailability(characters.getData());
        }
    }

    private void handlerForMaterialWlanCardSlot(Mobile mobile, Samsung samsung, String currentElement, Characters characters) {
        if (Names.MATERIAL.equals(currentElement)) {
            Objects.requireNonNull(mobile);
            mobile.setMaterial(characters.getData());
        }// else опционально, currentElement не может одновременно быть равным всем этим значениям
        if (Names.WLAN.equals(currentElement)) {
            Objects.requireNonNull(samsung);
            samsung.setWlan(characters.getData());
        }// else
        if (Names.CARD_SLOT.equals(currentElement)) {
            Objects.requireNonNull(samsung);
            samsung.setCardSlot(characters.getData());
        }
    }

    private void handlerForEndTags(Mobile mobile, Samsung samsung, XMLEvent event) {
        EndElement endElement = event.asEndElement();
        String localName = endElement.getName().getLocalPart();

        if (samsung != null) {
            Objects.requireNonNull(mobile);
            mobile.setSamsung(samsung);
        }

        if (Names.MOBILE.equals(localName)) {
            mobiles.getMobileList().add(mobile);
        }
    }

    public static void main(String[] args) throws Exception {

        // to validate xsd schema file
        STAXController staxCon = new STAXController(Constants.XSD_FILE);
        staxCon.parse();
        
        // try to parse (valid) XML file (success)
        STAXController staxContr = new STAXController(Constants.VALID_XML_INPUT_FILE);
        staxContr.parse(); // <-- do parse (success)

        // obtain container
        Mobiles mobiles = staxContr.getMobiles();

        // we have Mobiles object at this point:
        System.out.println("====================================");
        System.out.print("Here is the mobiles: \n" + mobiles);
        System.out.println("====================================");
    }
}

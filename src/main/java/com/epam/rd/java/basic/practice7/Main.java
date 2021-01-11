package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.controller.DOMController;
import com.epam.rd.java.basic.practice7.controller.SAXController;
import com.epam.rd.java.basic.practice7.controller.STAXController;
import com.epam.rd.java.basic.practice7.entity.Mobiles;
import com.epam.rd.java.basic.practice7.util.Sorter;

public final class Main {

    public static final String ARROW = " ==> ";
    public static final String OUTPUT = "Output";
    public static final String INPUT = "Input";

    public static void usage() {
        System.out.println("java com.epam.rd.java.basic.practice7.Main xmlFileName");
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            usage();
            return;
        }

        String xmlFileName = args[0];
        System.out.println(INPUT + ARROW + xmlFileName);

        ////////////////////////////////////////////////////////
        // DOM
        ////////////////////////////////////////////////////////

        // get
        DOMController domController = new DOMController(xmlFileName);
        domController.parse(true);
        Mobiles mobiles = domController.getMobiles();

        // sort (case 1)
        Sorter.sortMobilesByModel(mobiles);

        // save
        String outputXmlFile = Constants.VALID_XML_OUTPUT_DOM_FILE;
        DOMController.saveToXML(mobiles, outputXmlFile);
        System.out.println(OUTPUT + ARROW + outputXmlFile);

        ////////////////////////////////////////////////////////
        // SAX
        ////////////////////////////////////////////////////////

        // get
        SAXController saxController = new SAXController(xmlFileName);
        saxController.parse(true);
        mobiles = saxController.getMobiles();

        // sort  (case 2)
        Sorter.sortMobilesByOrigin(mobiles);

        // save
        outputXmlFile = Constants.VALID_XML_OUTPUT_SAX_FILE;

        // other way:
        DOMController.saveToXML(mobiles, outputXmlFile);
        System.out.println(OUTPUT + ARROW + outputXmlFile);

        ////////////////////////////////////////////////////////
        // StAX
        ////////////////////////////////////////////////////////

        // get
        STAXController staxController = new STAXController(xmlFileName);
        staxController.parse();
        mobiles = staxController.getMobiles();

        // sort  (case 3)
        Sorter.sortMobilesByMaterial(mobiles);

        // save
        outputXmlFile = Constants.VALID_XML_OUTPUT_STAX_FILE;
        DOMController.saveToXML(mobiles, outputXmlFile);
        System.out.println(OUTPUT + ARROW + outputXmlFile);
    }
}
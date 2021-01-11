package com.epam.rd.java.basic.practice7.util;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.controller.DOMController;
import com.epam.rd.java.basic.practice7.entity.Mobile;
import com.epam.rd.java.basic.practice7.entity.Mobiles;
import java.util.Collections;
import java.util.Comparator;

public class Sorter {

    public static final String DELIMITER = "====================================";
    // //////////////////////////////////////////////////////////
    // these are comparators
    // //////////////////////////////////////////////////////////

    /**
     * Sorts questions by model
     */
    public static final Comparator<Mobile> SORT_MOBILES_BY_MODEL = Comparator.comparing(Mobile::getModel);

    /**
     * Sorts questions by origin
     */
    public static final Comparator<Mobile> SORT_MOBILES_BY_ORIGIN = Comparator.comparing(Mobile::getOrigin);

    /**
     * Sorts questions by card slot.
     */
    public static final Comparator<Mobile> SORT_MOBILES_BY_MATERIAL = Comparator.comparing(Mobile::getMaterial);

    // //////////////////////////////////////////////////////////
    // these methods take Test object and sort it
    // with according comparator
    // //////////////////////////////////////////////////////////

    public static final void sortMobilesByModel(Mobiles mobiles) {
        System.out.println("Invoking sortMobilesByModel()");
        Collections.sort(mobiles.getMobileList(), SORT_MOBILES_BY_MODEL);
    }

    public static final void sortMobilesByOrigin(Mobiles mobiles) {
        System.out.println("Invoking sortMobilesByOrigin()");
        Collections.sort(mobiles.getMobileList(), SORT_MOBILES_BY_ORIGIN);
    }

    public static final void sortMobilesByMaterial(Mobiles mobiles) {
        System.out.println("Invoking sortMobilesByMaterial()");
        Collections.sort(mobiles.getMobileList(), SORT_MOBILES_BY_MATERIAL);
    }

    public static void main(String[] args) throws Exception {
        // Test.xml --> Test object
        DOMController domController = new DOMController(
                Constants.VALID_XML_INPUT_FILE);
        domController.parse(false);
        Mobiles mobiles = domController.getMobiles();

        System.out.println(DELIMITER);
        System.out.println(mobiles);
        System.out.println(DELIMITER);

        System.out.println(DELIMITER);
        Sorter.sortMobilesByModel(mobiles);
        System.out.println(mobiles);
        System.out.println(DELIMITER);

        System.out.println(DELIMITER);
        Sorter.sortMobilesByModel(mobiles);
        System.out.println(mobiles);
    }
}
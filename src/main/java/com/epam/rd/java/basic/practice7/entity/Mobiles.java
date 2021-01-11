package com.epam.rd.java.basic.practice7.entity;

import java.util.ArrayList;
import java.util.List;

public class Mobiles {
    private List<Mobile> mobileList;

    public List<Mobile> getMobileList() {
        System.out.println("Invoking getMobileList()");
        if (mobileList == null) {
            mobileList = new ArrayList<>();
        }
        return mobileList;
    }

    @Override
    public String toString() {
        if (mobileList == null || mobileList.isEmpty()) {
            return "Mobiles contains no instances\n";
        }
        StringBuilder result = new StringBuilder();
        for (Mobile mobile : mobileList) {
            result.append(mobile).append('\n');
        }
        return result.toString();
    }
}
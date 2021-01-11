package com.epam.rd.java.basic.practice7.entity;

public class Samsung {
    private String wlan;
    private String cardSlot;
    private String radioAvailability;
    private String bluetoothAvailability;

    public Samsung(){

    }

    public Samsung(Samsung samsung) {
        this.wlan = samsung.wlan;
        this.cardSlot = samsung.cardSlot;
        this.radioAvailability = samsung.radioAvailability;
        this.bluetoothAvailability = samsung.bluetoothAvailability;
    }

    public String getWlan() {
        return wlan;
    }

    public void setWlan(String wlan) {
        this.wlan = wlan;
    }

    public String getCardSlot() {
        return cardSlot;
    }

    public void setCardSlot(String cardSlot) {
        this.cardSlot = cardSlot;
    }

    public String getRadioAvailability() {
        return radioAvailability;
    }

    public void setRadioAvailability(String radioAvailability) {
        this.radioAvailability = radioAvailability;
    }

    public String getBluetoothAvailability() {
        return bluetoothAvailability;
    }

    public void setBluetoothAvailability(String bluetoothAvailability) {
        this.bluetoothAvailability = bluetoothAvailability;
    }

    @Override
    public String toString() {
        return  "   WLAN: " + wlan + "\n" +
                "   CardSlot: " + cardSlot + "\n" +
                "   RadioAvailability: " + radioAvailability + "\n" +
                "   BluetoothAvailability: " + bluetoothAvailability + "\n";
    }
}
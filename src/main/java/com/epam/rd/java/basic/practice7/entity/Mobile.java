package com.epam.rd.java.basic.practice7.entity;

public class Mobile {
    private String model;
    private String os;
    private String origin;
    private String material;
    private Samsung samsung;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOS() {
        return os;
    }

    public void setOS(String os) {
        this.os = os;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Samsung getSamsung() {
        return samsung;
    }

    public void setSamsung(Samsung samsung) {
        this.samsung = new Samsung(samsung);
    }

    @Override
    public String toString() {
        return  "Model: " + model + "\n" +
                "OS: " + os + "\n" +
                "Origin: " + origin + "\n" +
                "Material: " + material + "\n" +
                "Samsung: \n" + samsung + "\n";
    }
}
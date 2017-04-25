package com.guma.desarrollo.gmv;

import java.util.ArrayList;

public class ChildInfo {

    private String sequence = "";
    private String name = "";
    private String Codigo="";
    private String Cumple="";
    private boolean Cake;

    public boolean isCake() {
        return Cake;
    }

    public void setCake(boolean cake) {
        Cake = cake;
    }

    public String getCodigo() {
        return Codigo;
    }
    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getCumple() {
        return Cumple;
    }

    public void setCumple(String cumple) {
        Cumple = cumple;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
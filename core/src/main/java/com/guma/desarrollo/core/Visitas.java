package com.guma.desarrollo.core;

/**
 * Created by maryan.espinoza on 23/03/2017.
 */

public class Visitas {
    String mIdPlan,mIdCliente,mFecha,mLati,mLogi,mLocal,mObservacion,mAccion;

    public Visitas(String mIdPlan, String mIdCliente, String mFecha, String mLati, String mLogi, String mLocal, String mObservacion, String mAccion) {
        this.mIdPlan = mIdPlan;
        this.mIdCliente = mIdCliente;
        this.mFecha = mFecha;
        this.mLati = mLati;
        this.mLogi = mLogi;
        this.mLocal = mLocal;
        this.mObservacion = mObservacion;
        this.mAccion = mAccion;
    }
    public Visitas(){}

    public String getmIdPlan() {
        return mIdPlan;
    }

    public void setmIdPlan(String mIdPlan) {
        this.mIdPlan = mIdPlan;
    }

    public String getmIdCliente() {
        return mIdCliente;
    }

    public void setmIdCliente(String mIdCliente) {
        this.mIdCliente = mIdCliente;
    }

    public String getmFecha() {
        return mFecha;
    }

    public void setmFecha(String mFecha) {
        this.mFecha = mFecha;
    }

    public String getmLati() {
        return mLati;
    }

    public void setmLati(String mLati) {
        this.mLati = mLati;
    }

    public String getmLogi() {
        return mLogi;
    }

    public void setmLogi(String mLogi) {
        this.mLogi = mLogi;
    }

    public String getmLocal() {
        return mLocal;
    }

    public void setmLocal(String mLocal) {
        this.mLocal = mLocal;
    }

    public String getmObservacion() {
        return mObservacion;
    }

    public void setmObservacion(String mObservacion) {
        this.mObservacion = mObservacion;
    }

    public String getmAccion() {
        return mAccion;
    }

    public void setmAccion(String mAccion) {
        this.mAccion = mAccion;
    }
}

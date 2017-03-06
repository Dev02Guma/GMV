package com.guma.desarrollo.core;

/**
 * Created by maryan.espinoza on 06/03/2017.
 */

public class Indicadores {
    String mCliente;
    String mVendedor;
    String mMetas;
    String mVentasActual;
    String mPromedioVenta3M;
    String mCantidadItems3M;
    String mCredito;
    String mLimite;
    String mNombre;

    public Indicadores(String mCliente, String mVendedor, String mMetas, String mVentasActual, String mPromedioVenta3M, String mCantidadItems3M, String mCredito, String mLimite, String mNombre) {
        this.mCliente = mCliente;
        this.mVendedor = mVendedor;
        this.mMetas = mMetas;
        this.mVentasActual = mVentasActual;
        this.mPromedioVenta3M = mPromedioVenta3M;
        this.mCantidadItems3M = mCantidadItems3M;
        this.mCredito = mCredito;
        this.mLimite = mLimite;
        this.mNombre = mNombre;
    }
    public Indicadores(){

    }

    public String getmCliente() {
        return mCliente;
    }

    public void setmCliente(String mCliente) {
        this.mCliente = mCliente;
    }

    public String getmVendedor() {
        return mVendedor;
    }

    public void setmVendedor(String mVendedor) {
        this.mVendedor = mVendedor;
    }

    public String getmMetas() {
        return mMetas;
    }

    public void setmMetas(String mMetas) {
        this.mMetas = mMetas;
    }

    public String getmVentasActual() {
        return mVentasActual;
    }

    public void setmVentasActual(String mVentasActual) {
        this.mVentasActual = mVentasActual;
    }

    public String getmPromedioVenta3M() {
        return mPromedioVenta3M;
    }

    public void setmPromedioVenta3M(String mPromedioVenta3M) {
        this.mPromedioVenta3M = mPromedioVenta3M;
    }

    public String getmCantidadItems3M() {
        return mCantidadItems3M;
    }

    public void setmCantidadItems3M(String mCantidadItems3M) {
        this.mCantidadItems3M = mCantidadItems3M;
    }

    public String getmCredito() {
        return mCredito;
    }

    public void setmCredito(String mCredito) {
        this.mCredito = mCredito;
    }

    public String getmLimite() {
        return mLimite;
    }

    public void setmLimite(String mLimite) {
        this.mLimite = mLimite;
    }

    public String getmNombre() {
        return mNombre;
    }

    public void setmNombre(String mNombre) {
        this.mNombre = mNombre;
    }
}

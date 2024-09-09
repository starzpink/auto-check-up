package com.example.autocheckup;

import java.io.Serializable;

public class Veiculo implements Serializable {
    private long id;
    private String apelido, modelo_carro, ano_carro, placa_carro, mediakm, kmatual, oleodata, flutdata, flufdata,fludhdata, tpneudata, freiodata, eletdata, refrdata,cormangdata, revgeraldata, ult_abs, tipoCombustivel, qtlitros;

    public Veiculo(long id, String apelido,String modelo_carro,String ano_carro,String placa_carro,String mediakm,String kmatual,String oleodata,String flutdata,String flufdata,String fludhdata,String tpneudata,String freiodata,String eletdata,String refrdata,String cormangdata,String revgeraldata,String ult_abs,String tipoCombustivel,String qtlitros) {
        this.id = id;
        this.apelido = apelido;
        this.modelo_carro = modelo_carro;
        this.ano_carro = ano_carro;
        this.placa_carro = placa_carro;
        this.mediakm = mediakm;
        this.kmatual = kmatual;
        this.oleodata = oleodata;
        this.flutdata = flutdata;
        this.flufdata = flufdata;
        this.fludhdata = fludhdata;
        this.tpneudata = tpneudata;
        this.freiodata = freiodata;
        this.eletdata = eletdata;
        this.refrdata = refrdata;
        this.cormangdata = cormangdata;
        this.revgeraldata = revgeraldata;
        this.ult_abs = ult_abs;
        this.tipoCombustivel = tipoCombustivel;
        this.qtlitros = qtlitros;
    }

    public Veiculo(String apelido,String modelo_carro,String ano_carro,String placa_carro,String mediakm,String kmatual,String oleodata,String flutdata,String flufdata,String fludhdata,String tpneudata,String freiodata,String eletdata,String refrdata,String cormangdata,String revgeraldata,String ult_abs,String tipoCombustivel,String qtlitros) {
        this.apelido = apelido;
        this.modelo_carro = modelo_carro;
        this.ano_carro = ano_carro;
        this.placa_carro = placa_carro;
        this.mediakm = mediakm;
        this.kmatual = kmatual;
        this.oleodata = oleodata;
        this.flutdata = flutdata;
        this.flufdata = flufdata;
        this.fludhdata = fludhdata;
        this.tpneudata = tpneudata;
        this.freiodata = freiodata;
        this.eletdata = eletdata;
        this.refrdata = refrdata;
        this.cormangdata = cormangdata;
        this.revgeraldata = revgeraldata;
        this.ult_abs = ult_abs;
        this.tipoCombustivel = tipoCombustivel;
        this.qtlitros = qtlitros;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        apelido = apelido;
    }

    public String getModelo_carro() {
        return modelo_carro;
    }

    public void setModelo_carro(String modelo_carro) {
        this.modelo_carro = modelo_carro;
    }

    public String getAno_carro() {
        return ano_carro;
    }

    public void setAno_carro(String ano_carro) {
        this.ano_carro = ano_carro;
    }

    public String getPlaca_carro() {
        return placa_carro;
    }

    public void setPlaca_carro(String placa_carro) {
        this.placa_carro = placa_carro;
    }

    public String getMediakm() {
        return mediakm;
    }

    public void setMediakm(String mediakm) {
        this.mediakm = mediakm;
    }

    public String getKmatual() {
        return kmatual;
    }

    public void setKmatual(String kmatual) {
        this.kmatual = kmatual;
    }

    public String getOleodata() {
        return oleodata;
    }

    public void setOleodata(String oleodata) {
        this.oleodata = oleodata;
    }

    public String getFlutdata() {
        return flutdata;
    }

    public void setFlutdata(String flutdata) {
        this.flutdata = flutdata;
    }

    public String getFlufdata() {
        return flufdata;
    }

    public void setFlufdata(String flufdata) {
        this.flufdata = flufdata;
    }

    public String getFludhdata() {
        return fludhdata;
    }

    public void setFludhdata(String fludhdata) {
        this.fludhdata = fludhdata;
    }

    public String getTpneudata() {
        return tpneudata;
    }

    public void setTpneudata(String tpneudata) {
        this.tpneudata = tpneudata;
    }

    public String getFreiodata() {
        return freiodata;
    }

    public void setFreiodata(String freiodata) {
        this.freiodata = freiodata;
    }

    public String getEletdata() {
        return eletdata;
    }

    public void setEletdata(String eletdata) {
        this.eletdata = eletdata;
    }

    public String getRefrdata() {
        return refrdata;
    }

    public void setRefrdata(String refrdata) {
        this.refrdata = refrdata;
    }

    public String getCormangdata() {
        return cormangdata;
    }

    public void setCormangdata(String cormangdata) {
        this.cormangdata = cormangdata;
    }

    public String getRevgeraldata() {
        return revgeraldata;
    }

    public void setRevgeraldata(String revgeraldata) {
        this.revgeraldata = revgeraldata;
    }

    public String getUlt_abs() {
        return ult_abs;
    }

    public void setUlt_abs(String ult_abs) {
        this.ult_abs = ult_abs;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public String getQtlitros() {
        return qtlitros;
    }

    public void setQtlitros(String qtlitros) {
        this.qtlitros = qtlitros;
    }
}
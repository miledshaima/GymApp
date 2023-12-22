package com.example.gymapplication;

public class reserver {

    private String nomComplet;
    private String numeroTelephone;
    private String adresse;
    private String typeCours;
    private String dateheure;

    private String type;

    public reserver(String nomComplet, String numeroTelephone, String adresse, String typeCours, String dateheure, String type) {
        this.nomComplet = nomComplet;
        this.numeroTelephone = numeroTelephone;
        this.adresse = adresse;
        this.typeCours = typeCours;
        this.dateheure = dateheure;
        this.type = type;
    }

    public reserver(String nomComplet, String numeroTelephone, String adresse, String typeCours, String dateheure) {
        this.nomComplet = nomComplet;
        this.numeroTelephone = numeroTelephone;
        this.adresse = adresse;
        this.typeCours = typeCours;
        this.dateheure = dateheure;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTypeCours() {
        return typeCours;
    }

    public void setTypeCours(String typeCours) {
        this.typeCours = typeCours;
    }

    public String getDate() {
        return dateheure;
    }

    public void setDate(String date) {
        this.dateheure = date;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "reserver{" +
                "nomComplet='" + nomComplet + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", adresse='" + adresse + '\'' +
                ", typeCours='" + typeCours + '\'' +
                ", date='" + dateheure + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public reserver() {
    }

}

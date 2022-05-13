/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.entities;

/**
 *
 * @author LENOVO
 */
    public class Equipe {
    
     private int idequipe;
    private String nom,logo;
    private int score;
    private String region;

    public Equipe(int idequipe, String nom, String logo, int score, String region) {
        this.idequipe = idequipe;
        this.nom = nom;
        this.logo = logo;
        this.score = score;
        this.region = region;
    }

    public Equipe(String nom, String logo, int score, String region) {
        this.nom = nom;
        this.logo = logo;
        this.score = score;
        this.region = region;
    }

    public Equipe() {
    }

    public int getIdequipe() {
        return idequipe;
    }

    public String getNom() {
        return nom;
    }

    public String getLogo() {
        return logo;
    }

    public int getScore() {
        return score;
    }

    public String getRegion() {
        return region;
    }

    public void setIdequipe(int idequipe) {
        this.idequipe = idequipe;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    
    
}

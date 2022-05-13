/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.entities;

import java.util.LinkedHashMap;

/**
 *
 * @author DeathKnight
 */
public class CategoryReclamation {  
    private int id;
    private String nom;

    public LinkedHashMap<String, Object> getHm() {
        return hm;
    }

    public void setHm(LinkedHashMap<String, Object> hm) {
        this.hm = hm;
    }
    LinkedHashMap<String,Object> hm;

    public CategoryReclamation(){
        
    }
    public CategoryReclamation(String nom){
        this.nom = nom;
    }
    public CategoryReclamation(int id ,String nom) {
        this.id = id;
        this.nom = nom;

    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
    
}

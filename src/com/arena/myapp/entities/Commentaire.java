/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.entities;

import java.util.LinkedHashMap;

/**
 *
 * @author tarek
 */
public class Commentaire {
        int id_com;
    int id_user;
    String desc_com;
    String date_com;
    int id_post;
    
        LinkedHashMap<String,Object> hm;
    private String titre;
    
    
    

    public Commentaire(int id_com, int id_user, String desc_com, String date_com, int id_post) {
        this.id_com = id_com;
        this.id_user = id_user;
        this.desc_com = desc_com;
        this.date_com = date_com;
        this.id_post = id_post;
    }

    public Commentaire(int id_user, String desc_com, String date_com, int id_post) {
        this.id_user = id_user;
        this.desc_com = desc_com;
        this.date_com = date_com;
        this.id_post = id_post;
    }

    public Commentaire() {
    }
    public LinkedHashMap<String, Object> getHm() {
        return hm;
    }

    public void setHm(LinkedHashMap<String, Object> hm) {
        this.hm = hm;
    }



    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    
    
    
    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public String getDate_com() {
        return date_com;
    }

    public void setDate_com(String date_com) {
        this.date_com = date_com;
    }

    public String getDesc_com() {
        return desc_com;
    }

    public void setDesc_com(String desc_com) {
        this.desc_com = desc_com;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_com() {
        return id_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_post=" + id_post + ", date_com=" + date_com + ", desc_com=" + desc_com + ", id_user=" + id_user + ", id_com=" + id_com + '}';
    }
    
    
    
    
    
}

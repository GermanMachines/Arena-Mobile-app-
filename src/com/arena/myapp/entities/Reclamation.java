/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.entities;
//import java.sql.Date
import java.util.Date;
import java.util.LinkedHashMap;

/**
 *
 * @author DeathKnight
 */
public class Reclamation {
    private int id;
    private String titre;
    private String message;
    private int userId;

    private boolean etat;
    private Date date;
    private int categoryReclamationId;
    LinkedHashMap<String,Object> hm;
    private String nomCategory;

    public String getNomCategory() {
        return nomCategory;
    }

    public void setNomCategory(String nomCategory) {
        this.nomCategory = nomCategory;
    }
    
    
    
       //use this when constructing reclamation
        public Reclamation(String titre,String message, int userId, int categoryReclamationId) {
        this.titre = titre;
        this.message = message;
        this.userId = 47; // id mtaa nour
        this.categoryReclamationId = categoryReclamationId;
        this.etat = false;
        this.date = new Date();

        }

    public LinkedHashMap<String, Object> getHm() {
        return hm;
    }

    public void setHm(LinkedHashMap<String, Object> hm) {
        this.hm = hm;
    }
        
        
    //use this when constructing reclamation
        public Reclamation(int id,String titre,String message, int userId, int categoryReclamationId) {
        this.id = id;
        this.titre = titre;
        this.message = message;
        this.userId = userId;
        this.categoryReclamationId = categoryReclamationId;
        this.etat = false;
        this.date = new Date();

    }
        
    //use this when getting data from db
    public Reclamation(int id,String titre,String message, int userId, int categoryReclamationId, boolean etat, Date date) {
        this.id = id;
        this.titre = titre;
        this.message = message;
        this.userId = userId;
        this.categoryReclamationId = categoryReclamationId;
        this.etat = etat;
        this.date = date;

    }

    public Reclamation() {
               this.etat = false;
              this.date = new Date();
         }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryReclamationId() {
        return categoryReclamationId;
    }

    public void setCategoryReclamationId(int categoryReclamationId) {
        this.categoryReclamationId = categoryReclamationId;
    }

    public boolean getEtat() {
        return etat;
    }
    
    public void setEtat2(int etat){
        if(etat==1){
            this.etat= true;
            return;
        }
        this.etat = false;
    }
    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", titre=" + titre + ", message=" + message + ", userId=" + userId + ", categoryReclamationId=" + categoryReclamationId + ", etat=" + etat + ", date=" + date + '}';
    }

   



 
    
}

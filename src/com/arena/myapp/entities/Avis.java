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
public class Avis {
    public int id;
    private int score;
    private String commentaire;
    private int idUtulisateur;
    private int idJeux;
    LinkedHashMap<String,Object> hm;
    private String nomJeux;

    public Avis() {
   }

    public LinkedHashMap<String, Object> getHm() {
        return hm;
    }

    public void setHm(LinkedHashMap<String, Object> hm) {
        this.hm = hm;
    }

    public String getNomJeux() {
        return nomJeux;
    }

    public void setNomJeux(String nomJeux) {
        this.nomJeux = nomJeux;
    }
    
    public Avis( int id,int score, String commentaire, int idUtulisateur, int idJeux) {
        this.id = id;
        this.score = score;
        this.commentaire = commentaire;
        this.idUtulisateur = idUtulisateur;
        this.idJeux = idJeux;
    }

    public Avis(int score,String commentaire,int idUtulisateur , int idJeux) {
        this.score = score;
        this.commentaire = commentaire;
        this.idUtulisateur = idUtulisateur;
        this.idJeux = idJeux;
       
    }
       
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getIdUtulisateur() {
        return idUtulisateur;
    }

    public void setIdUser(int idUtulisateur) {
        this.idUtulisateur = idUtulisateur;
    }

    public int getIdJeux() {
        return idJeux;
    }

    public void setIdJeux(int idJeux) {
        this.idJeux = idJeux;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", score=" + score + ", commentaire=" + commentaire + ", idUtulisateur=" + idUtulisateur + ", idJeux=" + idJeux + '}';
    }


    

   

  


}

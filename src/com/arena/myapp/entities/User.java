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
public class User {
    
     private int id;
    private String nom;
    private String surnom, image, email, mdp, telephone;
    private int idEquipe;
    private String role;
    private String block;
    private String roles, username;

    public User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getSurnom() {
        return surnom;
    }

    public String getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public User(String nom, String surnom, String image, String email, String mdp, String telephone, int idEquipe, String role, String block, String roles, String username) {
        this.nom = nom;
        this.surnom = surnom;
        this.image = image;
        this.email = email;
        this.mdp = mdp;
        this.telephone = telephone;
        this.idEquipe = idEquipe;
        this.role = role;
        this.block = block;
        this.roles = roles;
        this.username = username;
    }

    public User(String nom, String surnom, String image, String email, String mdp, String telephone, String role, String block, String roles, String username) {
        this.nom = nom;
        this.surnom = surnom;
        this.image = image;
        this.email = email;
        this.mdp = mdp;
        this.telephone = telephone;
        this.role = role;
        this.block = block;
        this.roles = roles;
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public String getRole() {
        return role;
    }

    public String getBlock() {
        return block;
    }

    public String getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", surnom=" + surnom + ", image=" + image + ", email=" + email + ", mdp=" + mdp + ", telephone=" + telephone + ", id_equipe=" + idEquipe + ", role=" + role + ", block=" + block + ", roles=" + roles + ", username=" + username + '}';
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.entities;

/**
 *
 * @author tarek
 */
public class Participation {
  
      private int IdEquipe;
      private int IdTournois;

    public int getIdEquipe() {
        return IdEquipe;
    }

    public void setIdEquipe(int IdEquipe) {
        this.IdEquipe = IdEquipe;
    }

    public int getIdTournois() {
        return IdTournois;
    }

    public void setIdTournois(int IdTournois) {
        this.IdTournois = IdTournois;
    }

    public Participation(int IdEquipe, int IdTournois) {
        this.IdEquipe = IdEquipe;
        this.IdTournois = IdTournois;
    }

    public Participation() {
    }

    @Override
    public String toString() {
        return "Participation{" + "IdEquipe=" + IdEquipe + ", IdTournois=" + IdTournois + '}';
    }
      
      
      
}

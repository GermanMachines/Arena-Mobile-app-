/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.entities;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 *
 * @author tarek
 */
public class Tournois {
   private int idtournois;
   private String titre;
   private String  dateDebut;
    private String  dateFin;
      //private Date  dateDebut;
    //private Date  dateFin;
   
    private String descriptiontournois;
    private String type;
    
     private int nbrparticipants;
     private String winner;
     private String status;
        private int idjeux;
                private Jeux jeux;


    public Tournois(int idtournois, String titre, String dateDebut, String dateFin, String descriptiontournois, String type, int nbrparticipants, String winner, String status) {
        this.idtournois = idtournois;
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.descriptiontournois = descriptiontournois;
        this.type = type;
        this.nbrparticipants = nbrparticipants;
        this.winner = winner;
        this.status = status;
    }
                
                
//         public Tournois(int idtournois, String titre, Date dateDebut, Date dateFin, String descriptiontournois, String type, int nbrparticipants, String winner, String status) {
//        this.idtournois = idtournois;
//        this.titre = titre;
//        this.dateDebut = dateDebut;
//        this.dateFin = dateFin;
//        this.descriptiontournois = descriptiontournois;
//        this.type = type;
//        this.nbrparticipants = nbrparticipants;
//        this.winner = winner;
//        this.status = status;
//    }          
                
                
                
                
                
                
                
                
                
                
                

    public Tournois(String titre, String dateDebut, String dateFin, String descriptiontournois, String type, int nbrparticipants, String winner, String status, int idjeux) {
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.descriptiontournois = descriptiontournois;
        this.type = type;
        this.nbrparticipants = nbrparticipants;
        this.winner = winner;
        this.status = status;
        this.idjeux = idjeux;
    }

//             public Tournois(String titre, Date dateDebut, Date dateFin, String descriptiontournois, String type, int nbrparticipants, String winner, String status, int idjeux) {
//        this.titre = titre;
//        this.dateDebut = dateDebut;
//        this.dateFin = dateFin;
//        this.descriptiontournois = descriptiontournois;
//        this.type = type;
//        this.nbrparticipants = nbrparticipants;
//        this.winner = winner;
//        this.status = status;
//        this.idjeux = idjeux;
//    }       
                
                
                
                
                
                
                
                
                
                
                
    public Tournois(int idtournois, String titre, String dateDebut, String dateFin, String descriptiontournois, String type, int nbrparticipants, String winner, String status, int idjeux) {
        this.idtournois = idtournois;
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.descriptiontournois = descriptiontournois;
        this.type = type;
        this.nbrparticipants = nbrparticipants;
        this.winner = winner;
        this.status = status;
        this.idjeux = idjeux;
    }

                
                
//         public Tournois(int idtournois, String titre, Date dateDebut, Date dateFin, String descriptiontournois, String type, int nbrparticipants, String winner, String status, int idjeux) {
//        this.idtournois = idtournois;
//        this.titre = titre;
//        this.dateDebut = dateDebut;
//        this.dateFin = dateFin;
//        this.descriptiontournois = descriptiontournois;
//        this.type = type;
//        this.nbrparticipants = nbrparticipants;
//        this.winner = winner;
//        this.status = status;
//        this.idjeux = idjeux;
//    }           
                
                
                
                
                
                
                
                
                
                
                
    public Tournois(int idtournois, String titre, String dateDebut, String dateFin, String descriptiontournois, String type, int nbrparticipants, String winner, String status, Jeux jeux) {
        this.idtournois = idtournois;
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.descriptiontournois = descriptiontournois;
        this.type = type;
        this.nbrparticipants = nbrparticipants;
        this.winner = winner;
        this.status = status;
        this.jeux = jeux;
    }

 

 
    
//        public Tournois(int idtournois, String titre, Date dateDebut, Date dateFin, String descriptiontournois, String type, int nbrparticipants, String winner, String status, Jeux jeux) {
//        this.idtournois = idtournois;
//        this.titre = titre;
//        this.dateDebut = dateDebut;
//        this.dateFin = dateFin;
//        this.descriptiontournois = descriptiontournois;
//        this.type = type;
//        this.nbrparticipants = nbrparticipants;
//        this.winner = winner;
//        this.status = status;
//        this.jeux = jeux;
//    }
    
    
    
    
    
    public Jeux getJeux() {
        return jeux;
    }

    public void setJeux(Jeux jeux) {
        this.jeux = jeux;
    }


    
    public Tournois() {
    }

    public int getIdjeux() {
        return idjeux;
    }

    public void setIdjeux(int idjeux) {
        this.idjeux = idjeux;
    }

    
    
    


 
     
     
    public int getIdtournois() {
        return idtournois;
    }

    public void setIdtournois(int idtournois) {
        this.idtournois = idtournois;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

//    public Date getDateDebut() {
//        return dateDebut;
//    }
//
//    public void setDateDebut(Date dateDebut) {
//        this.dateDebut = dateDebut;
//    }
//
//    public Date getDateFin() {
//        return dateFin;
//    }
//
//    public void setDateFin(Date dateFin) {
//        this.dateFin = dateFin;
//    }
//    
    
    
    
    

    public String getDescriptiontournois() {
        return descriptiontournois;
    }

    public void setDescriptiontournois(String descriptiontournois) {
        this.descriptiontournois = descriptiontournois;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbrparticipants() {
        return nbrparticipants;
    }

    public void setNbrparticipants(int nbrparticipants) {
        this.nbrparticipants = nbrparticipants;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tournois{" + "idtournois=" + idtournois + ", titre=" + titre + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", descriptiontournois=" + descriptiontournois + ", type=" + type + ", nbrparticipants=" + nbrparticipants + ", winner=" + winner + ", status=" + status + ", Jeux=" + idjeux + '}';
    }


    
    
}

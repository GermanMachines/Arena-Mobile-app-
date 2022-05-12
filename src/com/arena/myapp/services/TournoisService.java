/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.services;

import com.arena.myapp.entities.Tournois;
import com.arena.myapp.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tarek
 */
public class TournoisService {
        
    
     public static TournoisService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Tournois> Tournois;

    public TournoisService() {
         req = new ConnectionRequest();
    }

    public static TournoisService getInstance() {
        if (instance == null) {
            instance = new TournoisService();
        }
        return instance;
    }
    
     public boolean addTournois(Tournois a) {
       // String url = Statics.BASE_URL + "/tournois/s/AjouterTournoisMobile?titre=" + a.getTitre() + "&dateDebut=" + a.getDateDebut()+ "&dateFin=" + a.getDateFin() + "&descriptiontournois=" + a.getDescriptiontournois()+ "&type=" + a.getType()+ "&nbrparticipants=" + a.getNbrparticipants()+ "&winner=" + a.getWinner() + "&status=" + a.getStatus() +"&idjeux="+ a.getIdjeux() ; //création de l'URL
       String url = Statics.BASE_URL + "/tournois/s/AjouterTournoisMobile";
         System.out.println(url);
       req.setPost(false);
        req.addArgument("titre",a.getTitre());
        req.addArgument("dateDebut", a.getDateDebut());
         req.addArgument("dateFin", a.getDateFin());
          req.addArgument("descriptiontournois", a.getDescriptiontournois());
           req.addArgument("type", a.getType());
           req.addArgument("nbrparticipants", Integer.toString(a.getNbrparticipants()));
           req.addArgument("Winner", a.getWinner());
           req.addArgument("status",a.getStatus());
           req.addArgument("idjeux", Integer.toString(0));
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      return resultOK;
    } 
     
     public ArrayList<Tournois> parseTournois(String jsonText){
        try {
            ArrayList<Object> Tournois = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ClientListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)ClientListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Tournois a = new Tournois();
                float id = Float.parseFloat(obj.get("idTournois").toString());
             //    float score = Float.parseFloat(obj.get("score").toString());
                a.setIdtournois((int)id);
                a.setTitre(obj.get("titre").toString());
                a.setDateDebut(obj.get("dateDebut").toString());
                a.setDateFin(obj.get("dateFin").toString());
                 
                a.setDescriptiontournois(obj.get("descriptiontournois").toString());
                a.setType(obj.get("type").toString());

                 
                  a.setNbrparticipants(16);
                  a.setWinner(obj.get("winner").toString());
                   a.setStatus(obj.get("status").toString());
                  float idjeux = Float.parseFloat(obj.get("idjeux").toString());

               
                //Ajouter la tâche extraite de la réponse Json à la liste
                Tournois.add(a);
            }
            
            
        } catch (Exception ex) {
             ex.printStackTrace();
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return Tournois;
    }
     
     public ArrayList<Tournois> getAllTournois(){
         String url = Statics.BASE_URL + "Tournois/s/AllTournois";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Tournois = parseTournois(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
         System.out.println("ggg");
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Tournois;
    }
     

     
     
          public ArrayList<Tournois> AfficherTournois()
    {

        ArrayList<Tournois> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/tournois/s/AllTournois";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapCategorie = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapCategorie.get("root");
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        Tournois c = new Tournois();
                        float id = Float.parseFloat(obj.get("idtournois").toString());
                        String titre = obj.get("titre").toString();
                        String dateDebut = obj.get("dateDebut").toString();
                         String dateFin = obj.get("dateFin").toString(); 
                         
                        String descriptiontournois = obj.get("descriptiontournois").toString();
                        String type = obj.get("type").toString();
                        float nbrparticipants = Float.parseFloat(obj.get("nbrparticipants").toString());
                        String winner = obj.get("winner").toString();
                        String status = obj.get("status").toString();
                     //   float idjeux = Float.parseFloat(obj.get("idjeux").toString());


                        c.setIdtournois((int)id);
                        //c.setProduit(produit.getId());
                        //c.setQuantite((int)quantite);
                        c.setTitre(titre);
                        c.setDateDebut(dateDebut);
                        c.setDateFin(dateFin);
                        c.setDescriptiontournois(descriptiontournois);
                        c.setType(type);

                        c.setNbrparticipants((int)nbrparticipants);
                        c.setWinner(winner);
                        c.setStatus(status);
                    //    c.setIdjeux((int) idjeux);

                        result.add(c);
                        System.out.println(c.getTitre()+" "+c.getDateDebut()+ " " + c.getDateFin() + " " + c.getDescriptiontournois() +  " " + c.getType() +" " + c.getNbrparticipants() + " " + c.getWinner()+ " " + c.getStatus());
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    
    } 
          
     public boolean  Delete(int id){
       String url = Statics.BASE_URL + "/tournois/s/deleteTournois/" +id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
      
      
      }
     
     public boolean modifier(Tournois a) {
        String url = Statics.BASE_URL + "/tournois/s/ModifierTournoisMobile/"+ a.getIdtournois()+ "?" +"titre=" + a.getTitre() + "&dateDebut=" + a.getDateDebut()+ "&dateFin=" + a.getDateFin() + "&descriptiontournois=" + a.getDescriptiontournois()+ "&type=" + a.getType()+ "&nbrparticipants=" + a.getNbrparticipants()+ "&winner=" + a.getWinner() + "&status=" + a.getStatus() +"&idjeux="+ a.getIdjeux() ; //création de l'URL
       

        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               resultOK = req.getResponseCode() == 200; // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        boolean resultOK = false;
    return resultOK;
        
    }
}

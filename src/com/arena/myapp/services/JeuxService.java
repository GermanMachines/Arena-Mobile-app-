/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.services;

import com.arena.myapp.entities.Jeux;
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
public class JeuxService {
      
     public static JeuxService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Jeux> Jeux;

    public JeuxService() {
         req = new ConnectionRequest();
    }

    public static JeuxService getInstance() {
        if (instance == null) {
            instance = new JeuxService();
        }
        return instance;
    }
    
     public boolean addJeux(Jeux a) {
        String url = Statics.BASE_URL + "/jeux/s/AjouterJeuxMobile?nomjeux=" + a.getNomjeux() + "&imagejeux=" + a.getImagejeux() ; //création de l'URL
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
     
     public ArrayList<Jeux> parseJeux(String jsonText){
        try {
            ArrayList<Object> Jeux = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ClientListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)ClientListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Jeux a = new Jeux();
                float id = Float.parseFloat(obj.get("idjeux").toString());
             //    float score = Float.parseFloat(obj.get("score").toString());
                a.setIdjeux((int)id);
                a.setNomjeux(obj.get("nomjeux").toString());
                a.setImagejeux(obj.get("imagejeux").toString());

               
                //Ajouter la tâche extraite de la réponse Json à la liste
                Jeux.add(a);
            }
            
            
        } catch (Exception ex) {
             ex.printStackTrace();
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return Jeux;
    }
     
     public ArrayList<Jeux> getAllJeux(){
         String url = Statics.BASE_URL + "/jeux/s/AfficherJeuxMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Jeux = parseJeux(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
         System.out.println("ggg");
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Jeux;
    }
     
     public Jeux DetailJeux( int id , Jeux Jeux) {
         
 String url = Statics.BASE_URL + "/jeux/s/AfficherJeuxMobile" + id ;
req.setUrl (url);
String str = new String(req.getResponseData()) ;
req.addResponseListener (((evt) -> {
JSONParser jsonp = new JSONParser() ;
try { 
    Map<String,Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
    Jeux.setNomjeux(obj.get("nomjeux").toString()) ;
     Jeux.setImagejeux(obj.get("imagejeux").toString()) ;
     

} catch(Exception ex) {
             ex.printStackTrace();}
        System.out.println("error in" + str);
} ))  ;
 NetworkManager.getInstance().addToQueueAndWait(req);
 return Jeux;
     }
     
     
          public ArrayList<Jeux> AfficherJeux()
    {

        ArrayList<Jeux> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/jeux/s/AfficherJeuxMobile";
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
                        Jeux c = new Jeux();
                        float id = Float.parseFloat(obj.get("idjeux").toString());
                        String nomJeux = obj.get("nomjeux").toString();
                        String imagejeux = obj.get("imagejeux").toString();

                        c.setIdjeux((int)id);
                        //c.setProduit(produit.getId());
                        //c.setQuantite((int)quantite);
                        c.setNomjeux(nomJeux);
                        c.setImagejeux(imagejeux);

                        result.add(c);
                        System.out.println(c.getIdjeux()+" "+c.getNomjeux()+ " " + c.getImagejeux() );
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
         
       String url = Statics.BASE_URL+"/jeux/s/deleteJeux/"+id;

        req.setUrl(url);
         System.out.println(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
      
      
      }
     
     public boolean modifier(Jeux a) {
        String url = Statics.BASE_URL + "/jeux/s/ModifierJeuxMobile/" + a.getIdjeux()+ "?"+"nomjeux=" + a.getNomjeux() + "&imagejeux=" + a.getImagejeux(); //création de l'URL
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

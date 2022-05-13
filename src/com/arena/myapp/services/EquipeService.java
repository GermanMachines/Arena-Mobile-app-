/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.services;

import com.arena.myapp.entities.Equipe;
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
 * @author LENOVO
 */
public class EquipeService {
    
    
     public static EquipeService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Equipe> Equipe;

    public EquipeService() {
         req = new ConnectionRequest();
    }

    public static EquipeService getInstance() {
        if (instance == null) {
            instance = new EquipeService();
        }
        return instance;
    }
    
     public boolean addEquipe(Equipe a) {
        String url = Statics.BASE_URL + "/AddTeammm?nom=" + a.getNom() + "&logo=" + a.getLogo()+ "&score=" + a.getScore() + "&region=" + a.getRegion() ; //création de l'URL
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
     
     public ArrayList<Equipe> parseEquipe(String jsonText){
        try {
            ArrayList<Object> Equipe = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ClientListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)ClientListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Equipe a = new Equipe();
                float id = Float.parseFloat(obj.get("idequipe").toString());
             //    float score = Float.parseFloat(obj.get("score").toString());
                a.setIdequipe((int)id);
                a.setNom(obj.get("nom").toString());
                a.setLogo(obj.get("logo").toString());
                  a.setScore(85);
                  a.setRegion( obj.get("region").toString());
               
                //Ajouter la tâche extraite de la réponse Json à la liste
                Equipe.add(a);
            }
            
            
        } catch (Exception ex) {
             ex.printStackTrace();
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return Equipe;
    }
     
     public ArrayList<Equipe> getAllEquipe(){
         String url = Statics.BASE_URL + "/allequipes";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Equipe = parseEquipe(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
         System.out.println("ggg");
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Equipe;
    }
     
     public Equipe DetailEquipe( int id , Equipe Equipe) {
         
 String url = Statics.BASE_URL + "/Equipe" + id ;
req.setUrl (url);
String str = new String(req.getResponseData()) ;
req.addResponseListener (((evt) -> {
JSONParser jsonp = new JSONParser() ;
try { 
    Map<String,Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
    Equipe.setNom(obj.get("nom").toString()) ;
     Equipe.setLogo(obj.get("logo").toString()) ;
     Equipe.setScore(Integer.parseInt( obj.get("score").toString())) ;
     

} catch(Exception ex) {
             ex.printStackTrace();}
        System.out.println("error in" + str);
} ))  ;
 NetworkManager.getInstance().addToQueueAndWait(req);
 return Equipe;
     }
     
     
          public ArrayList<Equipe> AfficherJeux()
    {

        ArrayList<Equipe> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/allequipes";
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
                        Equipe c = new Equipe();
                        float id = Float.parseFloat(obj.get("idequipe").toString());
                        String Namecateg = obj.get("nom").toString();
                        String Descriptioncateg = obj.get("logo").toString();
                        float score = Float.parseFloat(obj.get("score").toString());
                        String region = obj.get("region").toString();
                        c.setIdequipe((int)id);
                        //c.setProduit(produit.getId());
                        //c.setQuantite((int)quantite);
                        c.setNom(Namecateg);
                        c.setLogo(Descriptioncateg);
                         c.setScore((int)score);
                         c.setRegion(region);
                        result.add(c);
                        System.out.println(c.getIdequipe()+" "+c.getNom()+ " " + c.getLogo() + " " + c.getScore() + " " + c.getRegion() );
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
       String url = Statics.BASE_URL + "/DeleteTeam/" +id;

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
     
     public boolean modifier(Equipe a) {
        String url = Statics.BASE_URL + "/UpdateEquipe/" + a.getIdequipe()+ "?"+"nom=" + a.getNom() + "&logo=" + a.getLogo()+ "&score=" + a.getScore()+ "&region="+ a.getRegion(); //création de l'URL
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




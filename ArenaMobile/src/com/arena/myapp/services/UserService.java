/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.services;

import com.arena.myapp.entities.User;
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
public class UserService {
     public static UserService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<User> user;

    public UserService() {
         req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    public ArrayList<User> parseUser(String jsonText){
        try {
            ArrayList<Object> user = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ClientListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)ClientListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                User a = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int)id);
                a.setNom(obj.get("nom").toString());
                a.setSurnom(obj.get("surnom").toString());
                  a.setUsername( obj.get("username").toString());
                  a.setEmail( obj.get("email").toString());
                a.setRole(obj.get("role").toString());
               
                a.setRoles(obj.get("roles").toString());
                a.setMdp(obj.get("mdp").toString());
                a.setImage(obj.get("image").toString());
                a.setTelephone(obj.get("telephone").toString());
                a.setIdEquipe((int)obj.get("idEquipe"));
               
               
                //Ajouter la tâche extraite de la réponse Json à la liste
                user.add(a);
            }
            
            
        } catch (Exception ex) {
             ex.printStackTrace();
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return user;
    }
       public ArrayList<User> getAllUser(){
         String url = Statics.BASE_URL + "/AllUsers";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                user = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }
       
        public boolean  Delete(int id){
       String url = Statics.BASE_URL + "/codename/DeleteUser" +id;

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
        
        public boolean addUser(User a) {
        String url = Statics.BASE_URL + "/AddUserr?nom=" + a.getNom() + "&surnom=" + a.getSurnom()+ "&image=" + a.getImage() + "&email=" + a.getEmail() +"&mdp=" + a.getMdp() +"&telephone=" + a.getTelephone() +  "&role=" + a.getRole()+ "&block=" + a.getBlock()+ "&roles=" + a.getRoles() + "&username=" + a.getUsername() ; //création de l'URL
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
      
      /*  public User detailsUser (int id , User user)
        {  String url = Statics.BASE_URL + "/User" + id ;
        
            return null;
}*/
        
        
}

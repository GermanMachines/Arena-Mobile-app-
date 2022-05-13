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
     private User loggedUser = new User();
     boolean verf ; 
    public ArrayList<User> users;

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
                    ArrayList<User> userr = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String,Object> ClientListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)ClientListJson.get("root");

            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                User a = new User();
                         System.out.println("-------------");
                              System.out.println(obj.get("nom"));
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
//                a.setIdEquipe((int)obj.get("idEquipe"));
               
               
                //Ajouter la tâche extraite de la réponse Json à la liste
                userr.add(a);
            }
            
            
        } catch (Exception ex) {
             ex.printStackTrace();
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */


        return userr;
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
       String url = Statics.BASE_URL + "/codename/DeleteUser/" +id;

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
            System.out.println(url);
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
        
            public User authentification(String username, String password) {
      //  verf = true;
       // String a = "\" \"";

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/auth?password=" + password + "&username=" + username);
        con.addResponseListener((e) -> {
           // System.out.print("a= "); System.out.print(a); System.out.println("hhhfiejf");
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);
            //Affichage de la réponse serveur sur la console
           // int b = str.compareTo(a);
           // if (b == 0) {
                
             //   verf = false;
           // }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
                User usr = new User() ;

        return usr;
    }
            
            public ArrayList<User> getuser(String password , String username){
                      //  con.setUrl("http://127.0.0.1:8000/auth?password=" + password + "&username=" + username);

        String url = "http://127.0.0.1:8000/auth?password=" + password + "&username=" + username ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                

                users = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
                System.out.println(users);
        return users;
    }
            public User fetch(String username) {
        UserService us = new UserService();
        User usr = new User();
        ArrayList<User> listUsers;
         listUsers = new ArrayList();
         
        listUsers = us.getAllUser();
                        System.out.println("****************");

                System.out.println(us.getAllUser());
       // for (int i = 0 ; i<= listUsers.size() ; i++){
       // for (User u : listUsers) {
//           if (listUsers.get(i).getUsername().equals(username)) {
//                usr.setId(listUsers.get(i).getId());
//              usr.setEmail(listUsers.get(i).getEmail());
//               usr.setNom(listUsers.get(i).getNom());
//                usr.setSurnom(listUsers.get(i).getSurnom());
//                usr.setMdp(listUsers.get(i).getMdp());
//                usr.setUsername(listUsers.get(i).getUsername());
//              //  usr.setEquipe(u.getEquipe());
//                // usr.setPic(u.getPic());
//                 usr.setRole(listUsers.get(i).getRole());
//              usr.setRoles(listUsers.get(i).getRoles());
//              usr.setBlock(listUsers.get(i).getBlock());
//              usr.setImage(listUsers.get(i).getImage());
//               
//
//break;
//
           
       
        
       return usr;
    }
            
            public ArrayList<User> AfficherJeux()
    {

        ArrayList<User> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AllUsers";
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
                        User a = new User();
                        float id = Float.parseFloat(obj.get("id").toString());
                        //String Namecateg = obj.get("nom").toString();
                        // String Descriptioncateg = obj.get("logo").toString();
                    //    float idequipe = Float.parseFloat(obj.get("idequipe").toString());
                     //   System.out.println(idequipe);
                        // String region = obj.get("region").toString();
                        
                        
                         a.setId((int)id);
                a.setNom(obj.get("nom").toString());
                a.setSurnom(obj.get("surnom").toString());
                     a.setImage(obj.get("image").toString());
             
                  a.setEmail( obj.get("email").toString());
                   a.setMdp(obj.get("mdp").toString());
           
                a.setRole(obj.get("role").toString());
               
               
               
                a.setTelephone(obj.get("telephone").toString());
             //        a.setIdEquipe( (int)idequipe );
                                a.setBlock(obj.get("block").toString());
 a.setRoles(obj.get("roles").toString());
      a.setUsername( obj.get("username").toString());
                        //c.setIdequipe((int)id);
                        //c.setProduit(produit.getId());
                        //c.setQuantite((int)quantite);
                       // c.setNom(Namecateg);
                       // c.setLogo(Descriptioncateg);
                       //  c.setScore((int)score);
                        // c.setRegion(region);
                        result.add(a);
                        System.out.println(a.getId()+" "+a.getNom()+ " " + a.getSurnom() + " " + a.getImage() + " " + a.getEmail()+ " " + a.getMdp() + " " + a.getTelephone()  + " " + a.getRole() + " " + a.getBlock() + " " +a.getRoles() + " " +a.getUsername() );
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
          
     public boolean  Delete1 (int id){
       String url = Statics.BASE_URL + "/codename/DeleteUser/" +id ;

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
     
     public boolean modifier(User a) {
        String url = Statics.BASE_URL + "/UpdateUser/" + a.getId()+ "?"+"nom=" + a.getNom() + "&surnom=" + a.getSurnom()+ "&image=" + a.getImage()+ "&email="+ a.getEmail()+"&mdp="+ a.getMdp()+ "&telephone="+ a.getTelephone()+ "&role="+ a.getRole()+ "&block="+ a.getBlock() + "&roles="+ a.getRoles() + "&username="+ a.getUsername(); //création de l'URL
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

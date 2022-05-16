/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.services;

import com.arena.myapp.entities.Avis;
import com.arena.myapp.entities.Commentaire;
import com.arena.myapp.utils.Session;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tarek
 */
public class CommentaireService {
    
    //singleton 
    public static CommentaireService instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static CommentaireService getInstance() {
        if(instance == null )
            instance = new CommentaireService();
        return instance ;
    }
    
    
    
    public CommentaireService() {
        req = new ConnectionRequest();
        
    }
    
     public void ajoutCommentaire(Commentaire a) {
        System.out.println(a);
        String url = "http://127.0.0.1:8000/commentaire/s/addCommentJSON";
        req.setPost(false);
        String iduser = Integer.toString(Session.getInstance().getLoggedInUser().getId());
        req.setUrl(url);
        req.addArgument("idUser", iduser);
        req.addArgument("idPost", Integer.toString(a.getId_post()));
        req.addArgument("descCom", a.getDesc_com());
        req.addArgument("dateCom", a.getDate_com());
        System.out.println("req data :" + req.getRequestBody());
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
     
         //affiche tous les Commentaire
    
    public ArrayList<Commentaire>affichageCommentaire() {
        ArrayList<Commentaire> result = new ArrayList<>();
        
        String url = "http://127.0.0.1:8000/s/AfficherComMobile";
        System.out.println(url);
        req.setPost(false);
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    System.out.println(req.getResponseData());
                    Map<String,Object>mapCommentaire = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>)  mapCommentaire.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Commentaire re = new Commentaire();
                        

                      
                           // re.setHm((LinkedHashMap<String, Object>) obj.get("Id_post"));
                                            
                        String commentaire = obj.get("Desc_com").toString();
                        
                   
                       
                        
                        String Date_com = obj.get("Date_com").toString();
                        //float idUser = Float.parseFloat(obj.get("iduser").toString());
                        float idUser = Session.getInstance().getLoggedInUser().getId();
                        
                       // re.setCategoryReclamationId(idCategoryReclaamtion);
                        re.setDesc_com(commentaire);
                        re.setDate_com(Date_com);
                        int idCommentaire =(int) Float.parseFloat(obj.get("id").toString());
                  
                        System.out.println(idCommentaire);
                      //  re.setId(idCommentaire);
                     //   re.setId_post((int) Float.parseFloat(re.getHm().get("Id_post").toString()));
                      //  re.setNomJeux(re.getHm().get("nomjeux").toString());
                      
                      
                
                        
                        //insert data into ArrayList result
                        System.out.println(re);
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
    
     public ArrayList<Commentaire>affichageCommentaireFront() {
        ArrayList<Commentaire> result = new ArrayList<>();
        int iduser = Session.getInstance().getLoggedInUser().getId();
        String url = "http://127.0.0.1:8000/commentaire/s/getAllComJSONFront?iduser="+iduser;
        System.out.println(url);
        req.setPost(false);
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    System.out.println(req.getResponseData());
                    Map<String,Object>mapAvis = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>)  mapAvis.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Commentaire re = new Commentaire();
                        
                       
                            re.setHm((LinkedHashMap<String, Object>) obj.get("idPost"));

                         
                        String descCom = obj.get("descCom").toString();
                        String dateCom = obj.get("dateCom").toString();
                   
                       
                        
                   
                        re.setDesc_com(descCom);
                        re.setDate_com(dateCom);
                        int idCom =(int) Float.parseFloat(obj.get("idCom").toString());
                  
                        re.setId_com(idCom);
                        
                        re.setId_post((int) Float.parseFloat(re.getHm().get("idPost").toString()));
                        
                        re.setTitre(re.getHm().get("titre").toString());
                      
                      
                
                        System.out.println(re);
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
     
     
     
     
     
     public boolean deleteCommentaire(int id ) {
        System.out.println(id);
        String url = "http://127.0.0.1:8000/commentaire/s/deleteCommentaireJSON?idCom="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
     
     
     
 public boolean modifierCommentaire(Commentaire a) {
        //System.out.println(a.getId());
        
     //   String url = Statics.BASE_URL +"/reclamation/updateReclamationJSON";
   //  String url = "http://127.0.0.1:8000/Commentaire/updateCommentaireJSON?id="+a.getId()+"&score="+a.getScore()+"&commentaire="+a.getCommentaire()  ;
   //  System.out.println(url);
         req.setPost(false);
       //  req.setUrl(url);
         
    /*     req.addArgument("id", Integer.toString(a.getId()));
         req.addArgument("idcategoryreclamation", Integer.toString(reclamation.getCategoryReclamationId()));
         req.addArgument("titre", reclamation.getTitre());
         req.addArgument("message", reclamation.getMessage()); */
        System.out.println("req data :" + req.getRequestBody());
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                  String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
               System.out.println("data == "+str);
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }

}

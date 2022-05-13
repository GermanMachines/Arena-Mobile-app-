/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.services;

import com.arena.myapp.entities.Avis;
import com.arena.myapp.entities.Reclamation;
import static com.arena.myapp.services.ServiceReclamation.resultOk;
import com.arena.myapp.utils.Statics;
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
 * @author DeathKnight
 */
public class AvisService {
    
    //singleton 
    public static AvisService instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static AvisService getInstance() {
        if(instance == null )
            instance = new AvisService();
        return instance ;
    }
    
    
    
    public AvisService() {
        req = new ConnectionRequest();
        
    }
    
     public void ajoutAvis(Avis a) {
        System.out.println(a);
        String url =Statics.BASE_URL+"/avis/addAvisJSON";
        req.setPost(false);
        req.setUrl(url);
        req.addArgument("iduser", "48");
        req.addArgument("idjeux", Integer.toString(a.getIdJeux()));
        req.addArgument("score", Integer.toString(a.getScore()));
        req.addArgument("commentaire", a.getCommentaire());
        System.out.println("req data :" + req.getRequestBody());
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
     
         //affiche tous les avis
    
    public ArrayList<Avis>affichageAvis() {
        ArrayList<Avis> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/avis/getAllAvisJSON";
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
                        Avis re = new Avis();
                        
                        //dima id fi codename one float 5outhouha
                        //iduser?
                        //JSONParser parser = new JSONParser(Integer.toString(obj.get("idcategoryreclamation")));
                      
                            re.setHm((LinkedHashMap<String, Object>) obj.get("idjeux"));
                   //     System.out.println(obj.getOrDefault("idcategoryreclamation", new CategoryReclamation()));
                   
                      //  int idCategoryReclaamtion = Integer.parseInt(obj.get("idcategoryreclamation").toString());
                         
                        String commentaire = obj.get("commentaire").toString();
                        
                   
                       
                        
                        int score = (int) Float.parseFloat(obj.get("score").toString());
                        //float idUser = Float.parseFloat(obj.get("iduser").toString());
                        float idUser = 48;
                        
                       // re.setCategoryReclamationId(idCategoryReclaamtion);
                        re.setCommentaire(commentaire);
                        re.setScore(score);
                        int idAvis =(int) Float.parseFloat(obj.get("id").toString());
                  
                        System.out.println(idAvis);
                        re.setId(idAvis);
                        re.setIdJeux((int) Float.parseFloat(re.getHm().get("idjeux").toString()));
                        re.setNomJeux(re.getHm().get("nomjeux").toString());
                      
                      
                  //     int idcat = Integer.parseInt();
                       
                      //  re.setCategoryReclamationId(idcat);
             
                 //       re.setObjet(objet);
                 //       re.setDescription(description);
                //       re.setEtat((int)etat);
                        
                  
                  //      re.setDate(dateString);
                        
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
     public ArrayList<Avis>affichageAvisFront() {
        ArrayList<Avis> result = new ArrayList<>();
        int iduser = 48;
        String url = Statics.BASE_URL+"/avis/getAllAvisJSONFront?iduser="+iduser;
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
                        Avis re = new Avis();
                        
                        //dima id fi codename one float 5outhouha
                        //iduser?
                        //JSONParser parser = new JSONParser(Integer.toString(obj.get("idcategoryreclamation")));
                      
                            re.setHm((LinkedHashMap<String, Object>) obj.get("idjeux"));
                   //     System.out.println(obj.getOrDefault("idcategoryreclamation", new CategoryReclamation()));
                   
                      //  int idCategoryReclaamtion = Integer.parseInt(obj.get("idcategoryreclamation").toString());
                         
                        String commentaire = obj.get("commentaire").toString();
                        
                   
                       
                        
                        int score = (int) Float.parseFloat(obj.get("score").toString());
                        //float idUser = Float.parseFloat(obj.get("iduser").toString());
               
                        
                       // re.setCategoryReclamationId(idCategoryReclaamtion);
                        re.setCommentaire(commentaire);
                        re.setScore(score);
                        int idAvis =(int) Float.parseFloat(obj.get("id").toString());
                  
                        System.out.println(idAvis);
                        re.setId(idAvis);
                        re.setIdJeux((int) Float.parseFloat(re.getHm().get("idjeux").toString()));
                        re.setNomJeux(re.getHm().get("nomjeux").toString());
                      
                      
                  //     int idcat = Integer.parseInt();
                       
                      //  re.setCategoryReclamationId(idcat);
             
                 //       re.setObjet(objet);
                 //       re.setDescription(description);
                //       re.setEtat((int)etat);
                        
                  
                  //      re.setDate(dateString);
                        
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
     public boolean deleteAvis(int id ) {
        System.out.println(id);
        String url = Statics.BASE_URL +"/avis/deleteAvisJSON?id="+id;
        
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
 public boolean modifierAvis(Avis a) {
        System.out.println(a.getId());
        
     //   String url = Statics.BASE_URL +"/reclamation/updateReclamationJSON";
     String url = "http://127.0.0.1:8000/avis/updateAvisJSON?id="+a.getId()+"&score="+a.getScore()+"&commentaire="+a.getCommentaire()  ;
     System.out.println(url);
         req.setPost(false);
         req.setUrl(url);
         
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

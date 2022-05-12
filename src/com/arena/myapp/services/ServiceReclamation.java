/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.services;

import com.arena.myapp.entities.CategoryReclamation;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.arena.myapp.entities.Reclamation;
import java.util.ArrayList;
import com.arena.myapp.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.l10n.SimpleDateFormat;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


/**
 *
 * @author DeathKnight
 */
public class ServiceReclamation {
    
    //singleton 
    public static ServiceReclamation instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceReclamation getInstance() {
        if(instance == null )
            instance = new ServiceReclamation();
        return instance ;
    }
    
    
    
    public ServiceReclamation() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutReclamation(Reclamation reclamation) {
        System.out.println(reclamation);
        String url =Statics.BASE_URL+"/reclamation/json/addReclamationJSON";
        req.setPost(false);
        req.setUrl(url);
        req.addArgument("iduser", "47");
        req.addArgument("idcategoryreclamation", Integer.toString(reclamation.getCategoryReclamationId()));
        req.addArgument("titre", reclamation.getTitre());
        req.addArgument("message", reclamation.getMessage());
        System.out.println("req data :" + req.getRequestBody());
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Reclamation>affichageReclamations() {
        ArrayList<Reclamation> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/reclamation/getReclamationsJSON";
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
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>)  mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Reclamation re = new Reclamation();
                        
                        //dima id fi codename one float 5outhouha
                        //iduser?
                        //JSONParser parser = new JSONParser(Integer.toString(obj.get("idcategoryreclamation")));
                      
                        re.setHm((LinkedHashMap<String, Object>) obj.get("idcategoryreclamation"));
                   //     System.out.println(obj.getOrDefault("idcategoryreclamation", new CategoryReclamation()));
                   
                      //  int idCategoryReclaamtion = Integer.parseInt(obj.get("idcategoryreclamation").toString());
                         
                        String titre = obj.get("titre").toString();
                        
                   
                       
                        
                        String message = obj.get("message").toString();
                        //float idUser = Float.parseFloat(obj.get("iduser").toString());
                        float idUser = 47;
                        
                       // re.setCategoryReclamationId(idCategoryReclaamtion);
                        re.setTitre(titre);
                        re.setMessage(message);
                        int idrec =(int) Float.parseFloat(obj.get("id").toString());
                  
                        System.out.println(idrec);
                        re.setId(idrec);
                        re.setCategoryReclamationId((int) Float.parseFloat(re.getHm().get("id").toString()));
                        re.setNomCategory(re.getHm().get("nom").toString());
                      
                  //     int idcat = Integer.parseInt();
                       
                      //  re.setCategoryReclamationId(idcat);
             
                 //       re.setObjet(objet);
                 //       re.setDescription(description);
                //       re.setEtat((int)etat);
                        
                  
                  //      re.setDate(dateString);
                        
                        //insert data into ArrayList result
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
     public ArrayList<Reclamation>affichageReclamationsFront() {
        ArrayList<Reclamation> result = new ArrayList<>();
        int iduser = 47;
        String url = Statics.BASE_URL+"/reclamation/getReclamationsJSONFront?iduser="+iduser;
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
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>)  mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Reclamation re = new Reclamation();
                        
                        //dima id fi codename one float 5outhouha
                        //iduser?
                        //JSONParser parser = new JSONParser(Integer.toString(obj.get("idcategoryreclamation")));
                      
                        re.setHm((LinkedHashMap<String, Object>) obj.get("idcategoryreclamation"));
                   //     System.out.println(obj.getOrDefault("idcategoryreclamation", new CategoryReclamation()));
                   
                      //  int idCategoryReclaamtion = Integer.parseInt(obj.get("idcategoryreclamation").toString());
                         
                        String titre = obj.get("titre").toString();
                        
                   
                       
                        
                        String message = obj.get("message").toString();
                        //float idUser = Float.parseFloat(obj.get("iduser").toString());
                        float idUser = 47;
                        
                       // re.setCategoryReclamationId(idCategoryReclaamtion);
                        re.setTitre(titre);
                        re.setMessage(message);
                        int idrec =(int) Float.parseFloat(obj.get("id").toString());
                  
                        System.out.println(idrec);
                        re.setId(idrec);
                        re.setCategoryReclamationId((int) Float.parseFloat(re.getHm().get("id").toString()));
                        re.setNomCategory(re.getHm().get("nom").toString());
                      
                  //     int idcat = Integer.parseInt();
                       
                      //  re.setCategoryReclamationId(idcat);
             
                 //       re.setObjet(objet);
                 //       re.setDescription(description);
                //       re.setEtat((int)etat);
                        
                  
                  //      re.setDate(dateString);
                        
                        //insert data into ArrayList result
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

    
    
    
    //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Reclamation DetailRecalamation( int id , Reclamation reclamation) {
        
        String url = Statics.BASE_URL+"/getReclamationJSON/"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                reclamation.setTitre(obj.get("titre").toString());
                reclamation.setMessage(obj.get("message").toString());
                if(obj.get("etat").toString().toLowerCase() == "true"){
                      reclamation.setEtat(true);
                }else{
                    reclamation.setEtat(false);
                }
                //reclamation.setCategoryReclamationId(Integer.parseInt(obj.get("idcategoryreclamation").toString()));
                reclamation.setCategoryReclamationId(13);
                reclamation.setId(Integer.parseInt(obj.get("id").toString()));
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return reclamation;
        
        
    }
    
    
    //Delete 
    public boolean deleteReclamation(int id ) {
        System.out.println(id);
        String url = Statics.BASE_URL +"/reclamation/deleteReclamationJSON/"+id;
        
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
    
    
    
    //Update 
    public boolean modifierReclamation(Reclamation reclamation) {
        System.out.println(reclamation.getId());
        
     //   String url = Statics.BASE_URL +"/reclamation/updateReclamationJSON";
     String url = "http://127.0.0.1:8000/reclamation/updateReclamationJSON?id="+reclamation.getId()+"&titre="+reclamation.getTitre()+"&message="+reclamation.getMessage()+"&catid="+reclamation.getCategoryReclamationId()  ;
     System.out.println(url);
         req.setPost(false);
         req.setUrl(url);
         
         req.addArgument("id", Integer.toString(reclamation.getId()));
         req.addArgument("idcategoryreclamation", Integer.toString(reclamation.getCategoryReclamationId()));
         req.addArgument("titre", reclamation.getTitre());
         req.addArgument("message", reclamation.getMessage());
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
    
    
  
   //search 
    public Vector<Reclamation> search(String search) {
       Vector<Reclamation> result = new Vector<>();
     //   String url = Statics.BASE_URL +"/reclamation/updateReclamationJSON";
         String url = "http://localhost:8000/reclamation/json/search?search="+search;
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
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>)  mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Reclamation re = new Reclamation();
                        
                        //dima id fi codename one float 5outhouha
                        //iduser?
                        //JSONParser parser = new JSONParser(Integer.toString(obj.get("idcategoryreclamation")));
                      
                        re.setHm((LinkedHashMap<String, Object>) obj.get("idcategoryreclamation"));
                   //     System.out.println(obj.getOrDefault("idcategoryreclamation", new CategoryReclamation()));
                   
                      //  int idCategoryReclaamtion = Integer.parseInt(obj.get("idcategoryreclamation").toString());
                         
                        String titre = obj.get("titre").toString();
                        
                   
                       
                        
                        String message = obj.get("message").toString();
                        //float idUser = Float.parseFloat(obj.get("iduser").toString());
                        float idUser = 47;
                        
                       // re.setCategoryReclamationId(idCategoryReclaamtion);
                        re.setTitre(titre);
                        re.setMessage(message);
                        int idrec =(int) Float.parseFloat(obj.get("id").toString());
                  
                        System.out.println(idrec);
                        re.setId(idrec);
                        re.setCategoryReclamationId((int) Float.parseFloat(re.getHm().get("id").toString()));
                        re.setNomCategory(re.getHm().get("nom").toString());
                      
                  //     int idcat = Integer.parseInt();
                       
                      //  re.setCategoryReclamationId(idcat);
             
                 //       re.setObjet(objet);
                 //       re.setDescription(description);
                //       re.setEtat((int)etat);
                        
                  
                  //      re.setDate(dateString);
                        
                        //insert data into ArrayList result
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
}
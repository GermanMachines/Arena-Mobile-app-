/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.events.ActionListener;
import com.arena.myapp.entities.CategoryReclamation;
import com.arena.myapp.entities.Reclamation;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.arena.myapp.utils.Statics;
import java.util.Vector;

/**
 *
 * @author DeathKnight
 */
public class ServiceCategoryReclamation {
    public ArrayList<CategoryReclamation> CategoryReclamation;
    
    public static ServiceCategoryReclamation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCategoryReclamation() {
         req = new ConnectionRequest();
    }

    public static ServiceCategoryReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceCategoryReclamation();
        }
        return instance;
    }
    
       public void ajoutCategorieReclamation(CategoryReclamation cat) {
        System.out.println(cat);
        String url ="http://127.0.0.1:8000/categoryreclamation/addCategorieReclamationJSON?nom="+cat.getNom();
        req.setPost(false);
        req.setUrl(url);
  
        System.out.println("req data :" + req.getRequestBody());
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }

       
public  ArrayList<CategoryReclamation> parseCategorieReclamation(String jsonText){
        try {
            CategoryReclamation =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> catsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)catsListJson.get("root");
            for(Map<String,Object> obj : list){
                CategoryReclamation cr = new CategoryReclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                cr.setId((int)id);
                cr.setNom((obj.get("nom").toString()));
                if (obj.get("nom")==null)
                   cr.setNom("null");
                else
                    cr.setNom(obj.get("nom").toString());
                CategoryReclamation.add(cr);
            }
            
            
        } catch (IOException ex) {
            
        }
        return CategoryReclamation;
    }

public ArrayList<CategoryReclamation> getCategoriesReclaamtion()
    {

        ArrayList<CategoryReclamation> result = new ArrayList<>();
       // String url = Statics.BASE_URL+"jeux/s/AfficherJeuxMobile";
         String url = "http://127.0.0.1:8000/categoryreclamation/categoriesReclamation/json"; 
         System.out.println(url);
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
                        CategoryReclamation c = new CategoryReclamation();
                        float id = Float.parseFloat(obj.get("id").toString());
                        System.out.println((int)id);
                        String Namecateg = obj.get("nom").toString();
                      
                        c.setId((int)id);
                        c.setNom(Namecateg);
                        System.out.println(c);
      
                        
                        result.add(c);
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

public Vector<CategoryReclamation> getCategoriesReclamationVector()
    {

        List<CategoryReclamation> result = new Vector<>();
         String url = "http://127.0.0.1:8000/categoryreclamation/categoriesReclamation/json"; 
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
                        CategoryReclamation c = new CategoryReclamation();
                        float id = Float.parseFloat(obj.get("id").toString());
                        System.out.println((int)id);
                        String Namecateg = obj.get("nom").toString();
                      
                        c.setId((int)id);

                        c.setNom(Namecateg);
                        System.out.println(c);
      
                        
                        result.add(c);

                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return (Vector<CategoryReclamation>) result;
    
    }
   public boolean deleteCategorieReclamation(int id ) {
        System.out.println(id);
      //   req.setPost(false);
          String url ="http://127.0.0.1:8000/categoryreclamation/deleteCategorieReclamationJSON?id="+id;
          System.out.println(url);
         req.setUrl(url);
        System.out.println(req);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("inside response listener");
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  true;
    }
    public boolean modifierCategorieReclamation(CategoryReclamation cat) {
      
        
     //   String url = Statics.BASE_URL +"/reclamation/updateReclamationJSON";
   //  String url = "http://localhost:8000/categoryreclamation/updateCategorieReclamationJSON?id="+cat.getId()+"&nom="+cat.getNom() ;
   String url= "http://127.0.0.1:8000/categoryreclamation/updateCategorieReclamationJSON?id="+cat.getId()+"&nom="+cat.getNom();
    // String url = "http://localhost:8000/categoryreclamation/updateCategorieReclamationJSON?id=42&nom=cateogriiezz";
     System.out.println("update url : " +url);
         req.setPost(false);
         req.setUrl(url);
         

        System.out.println("req data :" + req.getRequestBody());
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                  String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
               System.out.println("data == "+str);
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }
    
    

/*
     public ComboBox<Map<Integer, String>> getCatoriesReclamation(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"get/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Statics.categoriesReclamation = parseCategorieReclamation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return CategoryReclamation;
    } */
}

    

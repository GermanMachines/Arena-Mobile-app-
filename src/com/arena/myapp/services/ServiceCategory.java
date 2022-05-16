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
import com.codename1.ui.events.ActionListener;
import com.arena.myapp.entities.Category;
import com.arena.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceCategory {

    Boolean resultOK;
    public static ServiceCategory instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceCategory getInstance() {
        if (instance == null) {
            instance = new ServiceCategory();
        }
        return instance;

    }

    public ServiceCategory() {
        req = new ConnectionRequest();
    }

    public boolean addCategory(Category c) {

        String url = Statics.BASE_URL + "/categories/category/addcategorymobile/new/" + c.getName() 
                + "&description=" + c.getDesc();
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

    public ArrayList<Category> getCategory() {

        ArrayList<Category> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/categories/category/getcategorymobile";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapCategorie = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> ListOfMaps = (List<Map<String, Object>>) mapCategorie.get("root");
                    for (Map<String, Object> obj : ListOfMaps) {

                        Category c = new Category();

                        float id = Float.parseFloat(obj.get("id").toString());
                        String name = obj.get("name").toString();
                        String desc = obj.get("description").toString();
                        c.setId((int) id);
                        c.setName(name);
                        c.setDesc(desc);

                        result.add(c);
                        System.out.println(c.getId() + " " + c.getName() + " " + c.getDesc());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;

    }

    public boolean updateCategory(Category c) {
        String url = Statics.BASE_URL + "/categories/category/updatecategorymobile/" + c.getId()
                + "&name=" + c.getName()
                + "&description=" + c.getDesc();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;

    }

    public boolean deleteCategory(int id) {
        String url = Statics.BASE_URL + "/categories/category/deletecategorymobile/" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

}

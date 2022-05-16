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
import com.arena.myapp.entities.Product;
import com.arena.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceProduct {

    Boolean resultOK;
    public static ServiceProduct instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceProduct getInstance() {
        if (instance == null) {
            instance = new ServiceProduct();
        }
        return instance;

    }

    public ServiceProduct() {
        req = new ConnectionRequest();
    }

    public boolean addProduct(Product p) {

        String url = Statics.BASE_URL + "/products/addproductmobile/new?name=" + p.getName()
                + "&price=" + p.getPrice()
                + "&qty=" + p.getQty()
                + "&description=" + p.getDesc();
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

    public ArrayList<Product> getProduct() {

        ArrayList<Product> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/products/product/getproductmobile";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapProducts = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> ListOfMaps = (List<Map<String, Object>>) mapProducts.get("root");
                    for (Map<String, Object> obj : ListOfMaps) {

                        Product p = new Product();

                        float id = Float.parseFloat(obj.get("id").toString());
                        String name = obj.get("name").toString();
                        float price = Float.parseFloat(obj.get("price").toString());
                        float qty = Float.parseFloat(obj.get("qty").toString());
                        String desc = obj.get("description").toString();
                        String image = obj.get("image").toString();
                        Category idCat = (Category) obj.get("idCat");

                        p.setId((int) id);
                        p.setName(name);
                        p.setPrice((int) price);
                        p.setQty((int) qty);
                        p.setDesc(desc);
                        p.setImage(image);
                        p.setCat(idCat);

                        result.add(p);
                        System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice() + " " + p.getQty() + " " + p.getDesc() + " " + p.getImage());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;

    }

    public boolean updateProduct(Product p) {
        String url = Statics.BASE_URL + "/products/product/updateproductmobile/" + p.getId()
                + "&name=" + p.getName()
                + "&price=" + p.getPrice()
                + "&qty=" + p.getQty()
                + "&description=" + p.getDesc();
        System.out.println(url);
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

    public boolean deleteProduct(int id) {
        String url = Statics.BASE_URL + "/products/product/deleteproductmobile/" + id;

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

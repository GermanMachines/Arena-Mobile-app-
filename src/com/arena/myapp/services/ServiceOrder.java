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
import com.arena.myapp.entities.Order;
import com.arena.myapp.utils.Statics;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceOrder {

    Boolean resultOK;
    public static ServiceOrder instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceOrder getInstance() {
        if (instance == null) {
            instance = new ServiceOrder();
        }
        return instance;

    }

    public ServiceOrder() {
        req = new ConnectionRequest();
    }

    public void addOrder(Order o) {

        String url = Statics.BASE_URL + "orders/order/addordermobile/new?=num" + o.getNum() + "&idProduct=" + o.getIdProduct() + "&idUser=" + o.getIdUser() + "&productQty=" + o.getProductQty()
                + "&createdAt=" + o.getCreatedAt();

        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public ArrayList<Order> getOrder() {

        ArrayList<Order> result = new ArrayList<>();
        String url = Statics.BASE_URL + "orders/order/getordermobile";
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

                        Order o = new Order();

                        float id = Float.parseFloat(obj.get("id").toString());
                        float num = Float.parseFloat(obj.get("num").toString());
//                        float idProduct = Float.parseFloat(obj.get("idProduct").toString());
//                        float idUser = Float.parseFloat(obj.get("idUser").toString());
                        float productQty = Float.parseFloat(obj.get("productQty").toString());
//                        Date createdAt = (Date) obj.get("createdAt");

                        o.setId((int) id);
                        o.setNum((int) num);
//                        o.setIdProduct((int) idProduct);
//                        o.setIdUser((int) idUser);
                        o.setProductQty((int) productQty);
//                        o.setCreatedAt(createdAt);

                        result.add(o);
//                        System.out.println(o.getId() + " " + o.getNum() + " " + o.getIdProduct() + " " + o.getIdUser() + " " + o.getProductQty() + " " + o.getCreatedAt());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;

    }

    public boolean updateOrder(Order o) {
        String url = Statics.BASE_URL + "orders/order/updateordermobile/" + o.getId() + "&num" + o.getNum() + "&idProduct=" + o.getIdProduct() + "&idUser=" + o.getIdUser() + "&productQty=" + o.getProductQty()
                + "&createdAt=" + o.getCreatedAt();
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

    public boolean deleteOrder(int id) {
        String url = Statics.BASE_URL + "orders/order/deleteordermobile/?id=" + id;

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

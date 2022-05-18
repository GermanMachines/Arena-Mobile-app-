/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.CategoryReclamation;
import com.arena.myapp.entities.Reclamation;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.arena.myapp.services.ServiceCategoryReclamation;
import com.arena.myapp.utils.Session;
import com.arena.myapp.utils.Statics;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DeathKnight
 */
public class ListCategoriesReclamation extends BaseForm{
     public ArrayList<CategoryReclamation> CategoryReclamation;
     
        public ListCategoriesReclamation(Resources res, CategoryReclamation c) {
        super(BoxLayout.y());
        System.out.println("List Categories Reclamation !!!");
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("List Categories Reclamation");
        getContentPane().setScrollVisible(true);
        if (Session.getInstance().getLoggedInUser().getRole().equals("admin")){
        
        super.addSideMenu(res);
        
         }else{
              super.addSideMenuUser(res);
         }
        
        
        ServiceCategoryReclamation as = new ServiceCategoryReclamation();
        ArrayList<CategoryReclamation> list = as.getCategoriesReclaamtion();
            System.out.println(list);
  {

            for (CategoryReclamation a : list) {

                Container c3 = new Container(BoxLayout.y());

                SpanLabel cat = new SpanLabel("Nom :" + a.getNom());
              //  SpanLabel cat1 = new SpanLabel("Id :" + a.getId());

            
                c3.add(cat);
                //c3.add(cat1);
                  cat.setTextUIID("0xFFFFFF");

                
                Button Delete = new Button("Delete");
                c3.add(Delete);
                 
                Delete.getAllStyles().setBgColor(0xF36B08);
                Delete.addActionListener(e -> {
                    Dialog alert = new Dialog("Attention");
                    SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette reclamation?");
                    alert.add(message);
                    Button ok = new Button("oui");
                    Button cancel = new Button(new Command("non"));
                    //User clicks on ok to delete account
                    ok.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                            as.deleteCategorieReclamation(a.getId());

                            alert.dispose();
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setShowProgressIndicator(true);
                            //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                            status.setMessage("Categorie SUPPRIMEE AVEC SUCCES");
                            status.setExpires(10000);
                            status.show();

                            refreshTheme();
                            new ListCategoriesReclamation(res,a).show();
                        }

                    }
                    );

        
                    alert.add(cancel);
                    alert.add(ok);
                    alert.showDialog();

                });
               Button Modifier = new Button("Modifier ");
                    c3.add(Modifier);
                     Modifier.getAllStyles().setBgColor(0xF36B08);
              Modifier.addActionListener(e -> {
                    new UpdateCategorieReclamationForm(res,a).show();
                        } 

                   
                    );

        


                System.out.println("");

                add(c3);

            }

        }

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
 

   
    


}



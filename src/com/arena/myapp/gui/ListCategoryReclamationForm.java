/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.CategoryReclamation;
import com.arena.myapp.entities.Reclamation;
import com.arena.myapp.services.ServiceCategoryReclamation;
import com.arena.myapp.utils.Session;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author DeathKnight
 */
public class ListCategoryReclamationForm extends BaseForm {
    public ListCategoryReclamationForm(Resources res){
      super(BoxLayout.y());
         if (Session.getInstance().getLoggedInUser().getRole().equals("admin")){
        
        super.addSideMenu(res);
        
         }else{
              super.addSideMenuUser(res);
         }
        setTitle("Ajouter une Reclamation");
        setLayout(BoxLayout.y());
        
        ServiceCategoryReclamation scr = new ServiceCategoryReclamation();
         ArrayList<CategoryReclamation> list = scr.getCategoriesReclaamtion();
             
            for (CategoryReclamation a : list) {
             Container c3 = new Container(BoxLayout.y());
               

                SpanLabel cat = new SpanLabel("Nom Categorie :" + a.getNom());
              
 
                c3.add(cat);
                  cat.setTextUIID("0xFFFFFF");


                Button Delete = new Button("Delete");
                c3.add(Delete);
                 
                Delete.getAllStyles().setBgColor(0xF36B08);
                Delete.addActionListener(e -> {
                    Dialog alert = new Dialog("Attention");
                    SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette categorie?");
                    alert.add(message);
                    Button ok = new Button("oui");
                    Button cancel = new Button(new Command("non"));
                    System.out.println("pre ok !!");
                    //User clicks on ok to delete account
                    ok.addActionListener(new ActionListener() {
                        
                        public void actionPerformed(ActionEvent evt) {
                            System.out.println("ok !!");
                            scr.deleteCategorieReclamation(a.getId());

                            alert.dispose();
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setShowProgressIndicator(true);
                            //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                            status.setMessage("Categorie SUPPRIMEE AVEC SUCCES");
                            status.setExpires(10000);
                            status.show();

                            refreshTheme();
                            new ListCategoryReclamationForm(res).show();
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

        



                add(c3);
               
            }
    }
}

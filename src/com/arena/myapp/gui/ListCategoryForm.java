/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Category;
import com.arena.myapp.entities.CategoryReclamation;
import com.arena.myapp.entities.Reclamation;
import com.arena.myapp.services.ServiceCategory;
import com.arena.myapp.services.ServiceCategoryReclamation;
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
public class ListCategoryForm extends BaseForm {
        public ListCategoryForm(Resources res, Category c) {
      super(BoxLayout.y());
        super.addSideMenu(res);
        setTitle("Ajouter une Category");
        setLayout(BoxLayout.y());
        
        ServiceCategory scr = new ServiceCategory();
         ArrayList<Category> list = scr.getCategory();
             
            for (Category a : list) {
             Container c3 = new Container(BoxLayout.y());
               

                SpanLabel cat = new SpanLabel("Nom Categorie :" + a.getName());
              
 
                c3.add(cat);
  

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
                            scr.deleteCategory(a.getId());

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
                    new UpdateCategoryForm(res,a).show();
                        } 

                   
                    );

        



                add(c3);
               
            }
    }
}

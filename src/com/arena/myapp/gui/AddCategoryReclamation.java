/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.CategoryReclamation;
import com.arena.myapp.entities.Jeux;
import com.arena.myapp.services.JeuxService;
import com.arena.myapp.services.ServiceCategoryReclamation;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author DeathKnight
 */
public class AddCategoryReclamation extends BaseForm{
    
    public AddCategoryReclamation(Resources res){
          super(BoxLayout.y());
        super.addSideMenu(res);
        setTitle("Ajouter une Reclamation");
        setLayout(BoxLayout.y());
        
        TextField tfCategorie = new TextField("","Nom Categorie ");
         tfCategorie.setUIID("TextFieldBlack"); 
         Button addBtn = new Button("Add");
         
         addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfCategorie.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                
             else
                {
             
                      try{
                        CategoryReclamation a;
                        a = new CategoryReclamation(tfCategorie.getText());
                        
                     ServiceCategoryReclamation.getInstance().ajoutCategorieReclamation(a);
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
                              status.setMessage("Categorie ajouté avec succes");
                                                  status.setExpires(10000);   
                      status.show();
                        }catch(Exception e){
                                 Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                       
                        
                    }
                    
                }
                
        });    
        
        
        addAll(tfCategorie , addBtn);
   
     }
}

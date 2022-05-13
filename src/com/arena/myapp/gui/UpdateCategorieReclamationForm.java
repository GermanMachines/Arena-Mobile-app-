/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.CategoryReclamation;
import com.arena.myapp.services.ServiceCategoryReclamation;
import com.arena.myapp.services.ServiceReclamation;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author DeathKnight
 */
public class UpdateCategorieReclamationForm extends BaseForm {
    Form current;
    public UpdateCategorieReclamationForm(Resources res,CategoryReclamation a){
       
        super(BoxLayout.y());
         System.out.println(a.getId());
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
           TextField tfNomCategorie= new TextField(a.getNom(),"Titre",20,TextField.ANY);
            tfNomCategorie.setUIID("TextFieldBlack"); 
            
        Button btnValider = new Button("Modifier Categorie");
        btnValider.setUIID("Button");
        
             btnValider.addPointerPressedListener(e -> {

            a.setNom(tfNomCategorie.getText());
  
             if( ServiceCategoryReclamation.getInstance().modifierCategorieReclamation(a)){
                               
                      
                      new ListCategoryReclamationForm(res).show();
                        }
             ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("Categorie modifié avec succes");
                                                  status.setExpires(10000);   
                      status.show();
               new ListCategoriesReclamation(res,a).show();
        });
         addAll(tfNomCategorie, btnValider);
       show();
    }
}

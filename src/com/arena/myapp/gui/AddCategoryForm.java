/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Category;
import com.arena.myapp.services.ServiceCategory;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;


/**
 *
 * @author LENOVO
 */
public class AddCategoryForm extends BaseForm {
    
    
    
Resources theme = UIManager.initFirstTheme("/theme");

    
       public AddCategoryForm (Resources res) {
        
          super(BoxLayout.y());

        super.addSideMenu(res);
        

        
        
      
        setTitle("Ajouter un Categorie ");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Category ");
         tfNom.setUIID("TextFieldBlack"); 
         
        TextField tfDescription = new TextField("","Description");
         tfDescription.setUIID("TextFieldBlack");


        Button btnValider = new Button("Ajouter Category");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfDescription.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                
             else
                {
             
                      
                        Category a;
                        a = new Category(tfNom.getText() , tfDescription.getText());
                        
                        if( ServiceCategory.getInstance().addCategory(a)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
                              status.setMessage("category ajouté avec succes");
                                                  status.setExpires(10000);   
                      status.show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        
                    }
                    
                }
                
        });    
        
   

        addAll(tfNom , tfDescription, btnValider);
        
        
            
   
       
       
        
     }
       
       

       
       
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Jeux;

import com.arena.myapp.services.JeuxService;

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
public class AddJeuxForm extends BaseForm {
    
    
    
Resources theme = UIManager.initFirstTheme("/theme");
//    String selectedImage;
//    
//    
//       Label imageLabel;
//     ImageViewer imageIV;
//    Button selectImageButton;
    
    
       public AddJeuxForm (Resources res) {
        
          super(BoxLayout.y());
//        Toolbar tb = new Toolbar(true);
//        setToolbar(tb);
//      tb.setTitle("Liste des users");
//     getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
        
        
   //JeuxService.getInstance().sendMail(email, resourceObjectInstance);
        
        
      
        setTitle("Ajouter un Jeux ");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Nom ");
         tfNom.setUIID("TextFieldBlack"); 
        TextField tflogo = new TextField("","Image");
         tflogo.setUIID("TextFieldBlack");

//       imageLabel = new Label("Image : ");
//        imageLabel.setUIID("labelDefault");
//        selectImageButton = new Button("Ajouter une image");
//         imageIV = new ImageViewer(theme.getImage("Logo.png").fill(500, 500));
//       
      

        Button btnValider = new Button("Ajouter Jeux");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tflogo.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                
             else
                {
             
                      
                        Jeux a;
                        a = new Jeux(tfNom.getText() , tflogo.getText());
                        
                        if( JeuxService.getInstance().addJeux(a)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
                              status.setMessage("Jeux ajouté avec succes");
                                                  status.setExpires(10000);   
                      status.show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        
                    }
                    
                }
                
        });    
        
   

        addAll(tfNom , tflogo, btnValider);
        
        
            
   
       
       
        
     }
       
       

       
       
}
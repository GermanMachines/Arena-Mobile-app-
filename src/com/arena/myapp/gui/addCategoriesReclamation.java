/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Reclamation;
import com.arena.myapp.services.ServiceReclamation;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.Map;

/**
 *
 * @author DeathKnight
 */
public class addCategoriesReclamation extends BaseForm{
        public addCategoriesReclamation(Resources res) {
                super(BoxLayout.y());
//        Toolbar tb = new Toolbar(true);
//        setToolbar(tb);
//      tb.setTitle("Liste des users");
//     getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

      
        setTitle("Ajouter une Reclamation");
        setLayout(BoxLayout.y());
        
        TextField tfTitre = new TextField("","Titre ");
         tfTitre.setUIID("TextFieldBlack"); 
        TextField tfMessage = new TextField("","Message");
         tfMessage.setUIID("TextFieldBlack");
         ComboBox<Map<String, Object>> categoriesReclamation = new ComboBox<>();
        // categoriesReclamation.setUIID("TextFieldBlack"); 
     
      
        
       
      

        Button btnValider = new Button("Ajouter Recalamtion");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length()==0)||(tfMessage.getText().length()==0)||(categoriesReclamation.getSelectedItem().isEmpty()))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                
             else
                {
                    try {
                      
                        Reclamation a;
                        //categoriesReclamation.getSelectedItem().values(
                        a = new Reclamation( tfTitre.getText() , tfMessage.getText() ,47 ,13 );
                        
                      /*  if( ServiceReclamation.getInstance().addReclamation(a)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("Equipe ajouté avec succes");
                                                  status.setExpires(10000);   
                      status.show();
                   //   new listEquipeForm(res,a).show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                      */  
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });    
        
        
        addAll(tfTitre , tfMessage,categoriesReclamation, btnValider);
    }
}

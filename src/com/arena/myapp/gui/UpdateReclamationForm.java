/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.CategoryReclamation;
import com.arena.myapp.entities.Equipe;
import com.arena.myapp.entities.Reclamation;
import com.arena.myapp.services.EquipeService;
import com.arena.myapp.services.ServiceCategoryReclamation;
import com.arena.myapp.services.ServiceReclamation;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.Vector;

/**
 *
 * @author DeathKnight
 */
public class UpdateReclamationForm extends BaseForm {
     Form current;
     UpdateReclamationForm(Resources res,Reclamation a) {
            super(BoxLayout.y());
//        Toolbar tb = new Toolbar(true);
//        current = this;
//        setToolbar(tb);
//        tb.setTitle("Liste des equipe");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
        
         
        TextField tfTitre= new TextField(a.getTitre(),"Titre",20,TextField.ANY);
         tfTitre.setUIID("TextFieldBlack"); 
        
        TextField tfMessage = new TextField(a.getMessage(),"logo",20,TextField.ANY);
         tfMessage.setUIID("TextFieldBlack");
        
      
  
         
          ServiceCategoryReclamation scr = new ServiceCategoryReclamation();
         Vector<CategoryReclamation> vcr = scr.getCategoriesReclamationVector();
        ComboBox<CategoryReclamation> cbCategoriesReclamation;
 
      
        //cbCategoriesReclamation.setUIID("TextFieldBlack"); 
        cbCategoriesReclamation = new ComboBox<>(vcr);
          
        
        
      
        
         tfTitre.setSingleLineTextArea(true);
        tfMessage.setSingleLineTextArea(true);

   
  
    
    
        Button btnValider = new Button("Modifier Reclammation");
        btnValider.setUIID("Button");
         System.out.println("before click event");
        btnValider.addPointerPressedListener(e -> {
            System.out.println("after click event");
            a.setTitre(tfTitre.getText());
            a.setMessage(tfMessage.getText());
           System.out.println("before catrec");
            int catrec = cbCategoriesReclamation.getSelectedItem().getId();
            System.out.println("cat rec = "+catrec);
             a.setCategoryReclamationId(catrec);
             
             if( ServiceReclamation.getInstance().modifierReclamation(a)){
                               
                      
                      new ListReclamationForm(res,a).show();
                        }
             ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("Reclamation modifié avec succes");
                                                  status.setExpires(10000);   
                      status.show();
               new ListReclamationForm(res,a).show();
        });
        
         
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListReclamationForm(res,a).show();
       });
       
      Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
        Label l6 = new Label("");
         Label l7 = new Label("");
          Label l8 = new Label("");
     
       
        Label l1 = new Label();
      
     /*   Container content = BoxLayout.encloseY(
        11,12,
                new FloatingHint(tfNom),
             //   createLineSeparator(),
                 new FloatingHint(tfPrenom),
             //   createLineSeparator(),
                 new FloatingHint(tfdate),
            //    createLineSeparator(),
                 new FloatingHint(tfnum),
            //    createLineSeparator(),
                 new FloatingHint(tfsexe),
            //    createLineSeparator(),
                 new FloatingHint(tfemail),
            //    createLineSeparator(),
                 new FloatingHint(tfpassword),
             //   createLineSeparator(),
                
        btnValider,
        btnAnnuler
                
                
        );
            */  
       addAll(tfTitre,tfMessage,cbCategoriesReclamation, btnValider,btnAnnuler);
       show();
     }
}

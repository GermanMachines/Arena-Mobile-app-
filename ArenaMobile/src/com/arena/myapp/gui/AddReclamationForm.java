/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.CategoryReclamation;
import com.arena.myapp.entities.Equipe;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.arena.myapp.entities.Reclamation;
import com.arena.myapp.services.EquipeService;
import com.arena.myapp.services.ServiceReclamation;
import com.codename1.components.ToastBar;
import com.codename1.ui.util.Resources;
import static java.lang.Integer.parseInt;
import java.util.Map;
import com.arena.myapp.entities.CategoryReclamation;
import com.arena.myapp.services.ServiceCategoryReclamation;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author DeathKnight
 */
public class AddReclamationForm extends BaseForm {
    private Map<String, Object> createListEntry(String name, int id) {
    Map<String, Object> entry = new HashMap<>();
    entry.put(name, id);
  //  entry.put(name, id);
    return entry;
}
    public AddReclamationForm(Resources res) {
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
         CategoryReclamation cr = new CategoryReclamation(35,"category");
       /*  ComboBox<Map<String, Object>> cobmo = new ComboBox<>(
                    createListEntry("A Game of Thrones", "1996"),
                 
         
         );*/
       ServiceCategoryReclamation scr = new ServiceCategoryReclamation();
       Vector<CategoryReclamation> vcr = scr.getCategoriesReclamationVector();
        ComboBox<CategoryReclamation> cbCategoriesReclamation;
 
      
        //cbCategoriesReclamation.setUIID("TextFieldBlack"); 
        cbCategoriesReclamation = new ComboBox<>(vcr);
       
     
      
        
       
      

        Button btnValider = new Button("Ajouter Recalamtion");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length()==0)||(tfMessage.getText().length()==0) || (cbCategoriesReclamation.getModel().getSelectedIndex()<0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                
             else
                {
                    try {
                      
                        Reclamation a;
                        //categoriesReclamation.getSelectedItem().values(
                        System.out.println("selected "+cbCategoriesReclamation.getModel().getSelectedIndex());
                       // System.out.println(cbCategoriesReclamation.getSelectedItem().getId());
                        //int catrec = Integer.parseInt(cbCategoriesReclamation.getSelectedItem().getId());
                        int catrec = cbCategoriesReclamation.getSelectedItem().getId();
                        a = new Reclamation( tfTitre.getText() , tfMessage.getText() ,47 ,catrec );
                        
                        ServiceReclamation.getInstance().ajoutReclamation(a);
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("Reclamation ajouté avec succes");
                                                  status.setExpires(10000);   
                      status.show();
                   //   new listEquipeForm(res,a).show();
        
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });    
        
        
        addAll(tfTitre , tfMessage,cbCategoriesReclamation, btnValider);
    }
    
}

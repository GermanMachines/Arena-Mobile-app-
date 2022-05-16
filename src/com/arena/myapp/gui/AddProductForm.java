/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Post;
import com.arena.myapp.services.ServicePost;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.arena.myapp.entities.Product;
import com.arena.myapp.services.ServiceProduct;


/**
 *
 * @author LENOVO
 */
public class AddProductForm extends BaseForm {
    
    
    
Resources theme = UIManager.initFirstTheme("/theme");

    
    
       public AddProductForm (Resources res) {
        
          super(BoxLayout.y());

        super.addSideMenu(res);
        
       
        
      
        setTitle("Ajouter un Produit ");
        setLayout(BoxLayout.y());
        
        
         TextField name = new TextField("", "enter name!!");
        name.setUIID("TextFieldBlack");

        TextField price = new TextField("", "enter price!!");
        price.setUIID("TextFieldBlack");

        TextField qty = new TextField("", "entrer qty!!");
        qty.setUIID("TextFieldBlack");

        TextField description = new TextField("", "entrer description!!");
        description.setUIID("TextFieldBlack");
        
        
      
        Button btnValider = new Button("Ajouter Produit");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 if(name.getText().equals("") || description.getText().equals("")) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                 }
             else
                {
             
                      

                    Product p = new Product(name.getText(), Integer.valueOf(price.getText()), Integer.valueOf(qty.getText()), description.getText());

                        if( ServiceProduct.getInstance().addProduct(p)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
                              status.setMessage("produit ajouté avec succes");
                                                  status.setExpires(10000);   
                      status.show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        
                    }
                    
                }
                
        });    
        
   

        addAll(name , price, qty,description,btnValider);
        
        
            
   
       
       
        
     }
       
       

       
       
}

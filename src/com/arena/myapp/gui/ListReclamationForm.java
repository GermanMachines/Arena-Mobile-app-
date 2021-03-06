/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Reclamation;
import com.arena.myapp.entities.User;
import com.arena.myapp.services.ServiceReclamation;
import com.arena.myapp.services.UserService;
import com.arena.myapp.utils.Session;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.plaf.Style.MARGIN_UNIT;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author DeathKnight
 */
public class ListReclamationForm extends BaseForm {
    public ListReclamationForm(Resources res, Reclamation c) {
       
        super(BoxLayout.y());
         final int ADMIN = 0;
        System.out.println("here");
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Liste des reclamations");
        getContentPane().setScrollVisible(true);
        if (Session.getInstance().getLoggedInUser().getRole().equals("admin")){
        
        super.addSideMenu(res);
        
         }else{
              super.addSideMenuUser(res);
         }
       

        ServiceReclamation as = new ServiceReclamation();
          ArrayList<Reclamation> list ;

        if (Session.getInstance().getLoggedInUser().getRole().equals("admin")) {
            
                         list = as.affichageReclamations();

        }else{
             list = as.affichageReclamationsFront();
        }
     
    

        {
          Container c1 = new Container(BoxLayout.y());
          
            TextField searchTf = new TextField("","Search...");
            Button searchBtn = new Button("serach");
              Button resetBtn = new Button("reset");
            Style searchTfStyle = searchTf.getAllStyles();
            searchTfStyle.setMarginTop(4);
            searchTfStyle.setMarginLeft(4);
            c1.add(searchTf);
            c1.add(searchBtn);
            c1.add(resetBtn);
             add(c1);
             searchBtn.addActionListener(e -> {
               
                    Vector<Reclamation> recSearch;
                    String sr = searchTf.getText();
                           recSearch = as.search(searchTf.getText());
                            
                        
                           

                            refreshTheme();
                            new SearchForm(res,sr,recSearch).show();
                        }

                    
                    );
             resetBtn.addActionListener(e -> {
                  refreshTheme();
                  new ListReclamationForm(res,c);
             });

      

         
             
           
            for (Reclamation a : list) {
             Container c3 = new Container(BoxLayout.y());
               

                SpanLabel cat = new SpanLabel("Titre :" + a.getTitre());
                SpanLabel cat1 = new SpanLabel("Message :" + a.getMessage());
            //    SpanLabel cat2 = new SpanLabel("id category :" + a.getCategoryReclamationId());
                SpanLabel cat6 = new SpanLabel("Nom category :" + a.getNomCategory());
                SpanLabel cat3 = new SpanLabel("Date :" + a.getDate());
                SpanLabel cat4 = new SpanLabel("Etat :" + a.getEtat());
              //  SpanLabel cat5 = new SpanLabel("id rec :" + a.getId());
             
 
                c3.add(cat);
                c3.add(cat1);
              //  c3.add(cat2);
                c3.add(cat3);
                c3.add(cat4);
              //  c3.add(cat5);
                c3.add(cat6);
                        cat.setTextUIID("0xFFFFFF");                cat1.setTextUIID("0xFFFFFF");
                cat3.setTextUIID("0xFFFFFF");                cat4.setTextUIID("0xFFFFFF");
                cat6.setTextUIID("0xFFFFFF");




                Button Delete = new Button("Delete");
                c3.add(Delete);
                 
                Delete.getAllStyles().setBgColor(0xF36B08);
                Delete.addActionListener(e -> {
                    Dialog alert = new Dialog("Attention");
                    SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette reclamation?");
                    alert.add(message);
                    Button ok = new Button("oui");
                    Button cancel = new Button(new Command("non"));
                    //User clicks on ok to delete account
                    ok.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                            as.deleteReclamation(a.getId());

                            alert.dispose();
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setShowProgressIndicator(true);
                            //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                            status.setMessage("Reclamation SUPPRIMEE AVEC SUCCES");
                            status.setExpires(10000);
                            status.show();

                            refreshTheme();
                            new ListReclamationForm(res,a).show();
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
                    new UpdateReclamationForm(res,a).show();
                        } 

                   
                    );

        


                System.out.println("");
                add(c3);
               
            }
            

        }

    }

    
}

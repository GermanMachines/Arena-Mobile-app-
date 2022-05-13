/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Avis;
import com.arena.myapp.entities.Jeux;
import com.arena.myapp.entities.Reclamation;
import com.arena.myapp.services.ServiceReclamation;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author DeathKnight
 */
public class SearchForm extends BaseForm {
    SearchForm(Resources res,String search,Vector<Reclamation> recSearch){
        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Search Reclamation");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
         ServiceReclamation as = new ServiceReclamation();
            Vector<Reclamation> list = as.search(search);
            Button resetBtn = new Button("Reset");
               add(resetBtn);
            if(recSearch.isEmpty()){
                 SpanLabel message = new SpanLabel("Coudlnt find : "+search);
                 add(message);
                 return;
            }
    for (Reclamation a : list) {
             Container c3 = new Container(BoxLayout.y());
               

                SpanLabel cat = new SpanLabel("Titre :" + a.getTitre());
                SpanLabel cat1 = new SpanLabel("Message :" + a.getMessage());
         //       SpanLabel cat2 = new SpanLabel("id category :" + a.getCategoryReclamationId());
                SpanLabel cat6 = new SpanLabel("nom category :" + a.getNomCategory());
                SpanLabel cat3 = new SpanLabel("Date :" + a.getDate());
                SpanLabel cat4 = new SpanLabel("Etat :" + a.getEtat());
            //    SpanLabel cat5 = new SpanLabel("id rec :" + a.getId());
             
 
                c3.add(cat);
                c3.add(cat1);
              //  c3.add(cat2);
                c3.add(cat3);
                c3.add(cat4);
               // c3.add(cat5);
                c3.add(cat6);
        

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

      
         
            resetBtn.addActionListener(e -> {
                   refreshTheme();
                   Reclamation c = new Reclamation();
                  new ListReclamationForm(res,c).show();
            });
                 add(c3);
               
            }
            

        }

    }

    


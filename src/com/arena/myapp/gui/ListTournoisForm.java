/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Jeux;
import com.arena.myapp.entities.Participation;
import com.arena.myapp.entities.Tournois;
import com.arena.myapp.services.JeuxService;
import com.arena.myapp.services.TournoisService;
import com.arena.myapp.utils.Session;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author tarek
 */
public class ListTournoisForm extends BaseForm{
      public ListTournoisForm(Resources res,Tournois c ) {

        super(BoxLayout.y());

        getContentPane().setScrollVisible(true);
        
            if (Session.getInstance().getLoggedInUser().getRole().equals("admin")){
        
        super.addSideMenu(res);
        
         }else{
              super.addSideMenuUser(res);
         }
       // super.addSideMenu(res);

        
 

        TournoisService as = new TournoisService();
        ArrayList<Tournois> list = as.AfficherTournois();
       

        {

            for (Tournois a : list) {

                Container c3 = new Container(BoxLayout.y());

                SpanLabel cat = new SpanLabel("Titre :" + a.getTitre());
                SpanLabel cat1 = new SpanLabel("DateDebut :" + a.getDateDebut());
                SpanLabel cat2 = new SpanLabel("DateFin :" + a.getDateFin());
                SpanLabel cat3 = new SpanLabel("Desc :" + a.getDescriptiontournois());
                SpanLabel cat4 = new SpanLabel("type :" + a.getType());
                SpanLabel cat5 = new SpanLabel("nbrparticipants :" + a.getNbrparticipants());
                SpanLabel cat6 = new SpanLabel("idjeux :" + a.getIdjeux());
                SpanLabel cat7 = new SpanLabel("winner :" + a.getWinner()); 
                SpanLabel cat8 = new SpanLabel("status :" + a.getStatus());
                SpanLabel cat9 = new SpanLabel("NomJeux :" + a.getNomjeux());

                
                
                c3.add(cat);
                c3.add(cat1);
                c3.add(cat2);
                c3.add(cat3);
                c3.add(cat4);
                c3.add(cat5);
                c3.add(cat6);
                c3.add(cat7);
                c3.add(cat8);
                c3.add(cat9);
                cat.setTextUIID("0xFFFFFF"); cat1.setTextUIID("0xFFFFFF"); cat2.setTextUIID("0xFFFFFF");
                 cat3.setTextUIID("0xFFFFFF"); cat4.setTextUIID("0xFFFFFF"); cat5.setTextUIID("0xFFFFFF");
                 cat6.setTextUIID("0xFFFFFF"); cat7.setTextUIID("0xFFFFFF"); cat8.setTextUIID("0xFFFFFF");
                 cat9.setTextUIID("0xFFFFFF");
                           
       

                Button Delete = new Button("Delete");
                if (Session.getInstance().getLoggedInUser().getRole().equals("admin")){
                c3.add(Delete);
                }
                Button Participer = new Button("Participer");
                    c3.add(Participer);
                    
                   Participer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Participation p = new Participation();
                            p.setIdTournois(a.getIdtournois());
                            p.setIdEquipe(94);
                    TournoisService su = TournoisService.getInstance();
                    su.addReservation(p);
                    
                    if (su.addReservation(p)) {
                        Dialog.show("Reservation", "participation ajoute", new Command("OK"));

                    } else {
                        Dialog.show("Reservation", "Erreur de participation", new Command("OK"));
                    }
                }
            });
        
                        
                    
                    
                    
                    
                
                
                Delete.getAllStyles().setBgColor(0xF36B08);
                Delete.addActionListener(e -> {
                    Dialog alert = new Dialog("Attention");
                    SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette Tournois??");
                    alert.add(message);
                    Button ok = new Button("oui");
                    Button cancel = new Button(new Command("non"));
                    //User clicks on ok to delete account
                    ok.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                           as.Delete(a.getIdtournois());

                            alert.dispose();
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setShowProgressIndicator(true);
                            //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                            status.setMessage("Tournois SUPPRIMEE AVEC SUCCES");
                            status.setExpires(10000);
                            status.show();

                            refreshTheme();
                            new ListTournoisForm(res,a).show();
                        }

                    }
                    );

        
                    alert.add(cancel);
                    alert.add(ok);
                    alert.showDialog();

                });
                Button Modifier = new Button("Modifier ");
                if (Session.getInstance().getLoggedInUser().getRole().equals("admin")){
                    c3.add(Modifier);
                }
                     Modifier.getAllStyles().setBgColor(0xF36B08);
              Modifier.addActionListener(e -> {
                    new UpdateTournoisForm(res,a).show();
                        }

                   
                    );

        


                System.out.println("");

                add(c3);

            }
 
        }

    }

}

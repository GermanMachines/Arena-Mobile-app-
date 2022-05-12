/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;

import com.arena.myapp.entities.Equipe;
import com.arena.myapp.entities.Jeux;
import com.arena.myapp.services.EquipeService;
import com.arena.myapp.services.JeuxService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author tarek
 */
public class ListJeuxForm extends BaseForm{
     
      public ListJeuxForm(Resources res,Jeux c ) {

        super(BoxLayout.y());

        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

        
 

        JeuxService as = new JeuxService();
        ArrayList<Jeux> list = as.AfficherJeux();
       

        
    int i = 0;
            for (Jeux a : list) {

              /*  Container c3 = new Container(BoxLayout.y());

                SpanLabel cat = new SpanLabel("Nom :" + a.getNomjeux());
                SpanLabel cat1 = new SpanLabel("logo :" + a.getImagejeux()); */
                Button avisBtn = new Button("Rate");
                Button Delete = new Button("Delete");
                Button Modifier = new Button("Modifer");
                 ArrayList<Jeux>liste = JeuxService.getInstance().AfficherJeux(); 
         
      
        
            i++;
            String urlImage = "http://localhost:8000/uploads/"+a.getImagejeux();
          System.out.println(urlImage);
            Image placeHolder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            addJeux(urlim,a,res,i,avisBtn,Delete,Modifier);
            ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
                

               avisBtn.addActionListener(e -> {
                     new AvisForm(res,a).show();
               });

                
      
                 
               


        

          }
      }
        private void addJeux(Image img,Jeux c, Resources res, int i,Button avisBtn,Button Delete,Button Modifier) {
        
        
        int height = Display.getInstance().convertToPixels(16f);
        int width = Display.getInstance().convertToPixels(12f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        

          
        Label CategorieTxt = new Label("Jeux "+i,"NewsTopLine2");
        Label NameCategTxt = new Label("Nom de jeux: "+c.getNomjeux(),"NewsTopLine2");
        Label DescriptionCategTxt = new Label("Image: "+c.getImagejeux(),"NewsTopLine2");
        Label margin = new Label("","NewsTopLine2");
        JeuxService as = new JeuxService();
        
        
                cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(

            BoxLayout.encloseX(CategorieTxt),
            BoxLayout.encloseX(NameCategTxt),
            BoxLayout.encloseX(DescriptionCategTxt),
            BoxLayout.encloseX(margin),
            BoxLayout.encloseX(avisBtn),
            BoxLayout.encloseX(Modifier),
            BoxLayout.encloseX(Delete)
                  

        ));
                 Delete.getAllStyles().setBgColor(0xF36B08);
                Delete.addActionListener(e -> {
                    Dialog alert = new Dialog("Attention");
                    SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette Jeux??");
                    alert.add(message);
                    Button ok = new Button("oui");
                    Button cancel = new Button(new Command("non"));
                    //User clicks on ok to delete account
                    ok.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                           as.Delete(c.getIdjeux());

                            alert.dispose();
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setShowProgressIndicator(true);
                            //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                            status.setMessage("Jeux SUPPRIMEE AVEC SUCCES");
                            status.setExpires(10000);
                            status.show();

                            refreshTheme();
                            new ListJeuxForm(res,c).show();
                        }

                    }
                    );

        
                    alert.add(cancel);
                    alert.add(ok);
                    alert.showDialog();

                });
                               
              Modifier.getAllStyles().setBgColor(0xF36B08);
              Modifier.addActionListener(e -> {
                    new UpdateJeuxForm(res,c).show();
                        }

                   
                    );


        add(cnt);
        System.out.println(cnt);
    }

 
}

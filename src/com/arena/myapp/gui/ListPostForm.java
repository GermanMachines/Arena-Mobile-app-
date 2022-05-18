/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.gui;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import com.codename1.ui.FontImage;

import com.arena.myapp.entities.Post;
import com.arena.myapp.services.ServicePost;
import com.arena.myapp.utils.Session;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
/**
 *
 * @author tarek
 */
public class ListPostForm extends BaseForm{
     
      public ListPostForm(Resources res,Post p){

        super(BoxLayout.y());

        getContentPane().setScrollVisible(true);
      if (Session.getInstance().getLoggedInUser().getRole().equals("admin")){
        
        super.addSideMenu(res);
        
         }else{
              super.addSideMenuUser(res);
         }

        
 

        ServicePost as = new ServicePost();

      
      
              //Appel affichage methode
        ArrayList<Post>list = ServicePost.getInstance().AfficherPost(); 
             int i = 0;
        for(Post c : list)
        {
                            Button CommentBtn = new Button("Comment");

            
            
            
            
            i++;
             String urlImage = "http://127.0.0.1:8000/uploads/"+c.getImg_post();
            System.out.println(c.getImg_post());
            Image placeHolder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            addJeux(urlim,c,res,i,CommentBtn);
            ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            
            
            
               CommentBtn.addActionListener(e -> {
                     new AddCommentaireForm(res,c).show();
               });

            
            
        }
        
        
        
      }
      
      
      
 private void addJeux(Image img,Post c, Resources res, int i,Button CommentBtn) {
        
        
        int height = Display.getInstance().convertToPixels(50f);
        int width = Display.getInstance().convertToPixels(60f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.north(image);
        

          
        Label CategorieTxt = new Label("Post "+i,"NewsTopLine2");
        Label titre = new Label("Titre: "+c.getTitre(),"NewsTopLine2");
        Label auteur = new Label("auteur: "+c.getAuteur(),"NewsTopLine2");
         Label date = new Label("date: "+c.getDate_post(),"NewsTopLine2");
       
          Label rate = new Label("rate: "+c.getRate(),"NewsTopLine2");
        Label margin = new Label("","NewsTopLine2");

        createLineSeparator();
        
        
         Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer cette Post ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Reclamation
                if(ServicePost.getInstance().deletePost(c.getId_post())) {
                    new ListPostForm(res,c).show();
                }
           
        });
        
        
        
        
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l -> {
            //System.out.println("hello update");
            new ModifierPostForm(res ,c).show();
        });
        
         if (Session.getInstance().getLoggedInUser().getRole().equals("admin")){
                cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(

           
            BoxLayout.encloseX(titre),
            BoxLayout.encloseX(auteur),
             BoxLayout.encloseX(date),
                     
            BoxLayout.encloseX(rate),
              BoxLayout.encloseX(CommentBtn),
              
            BoxLayout.encloseX(lSupprimer,lModifier)
                
                
                ));
         }else{
              cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(

           
            BoxLayout.encloseX(titre),
            BoxLayout.encloseX(auteur),
             BoxLayout.encloseX(date),
                     
            BoxLayout.encloseX(rate),
              BoxLayout.encloseX(CommentBtn)
                
                
                ));
         }
     

        add(cnt);
        System.out.println(cnt);
    }
 
}

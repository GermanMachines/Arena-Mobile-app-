


/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.arena.myapp.gui;

import com.arena.myapp.entities.Avis;
import com.arena.myapp.entities.Category;
import com.arena.myapp.entities.CategoryReclamation;
import com.arena.myapp.entities.Commentaire;
import com.arena.myapp.entities.Equipe;
import com.arena.myapp.entities.Jeux;
import com.arena.myapp.entities.Post;
import com.arena.myapp.entities.Product;
import com.arena.myapp.entities.Reclamation;
import com.arena.myapp.entities.Tournois;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.arena.myapp.entities.User;
import com.arena.myapp.utils.Session;



public class BaseForm extends Form {

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
   
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
       sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
//        User a = new User();
     Equipe b = new Equipe();
     Reclamation r = new Reclamation();
     CategoryReclamation c = new CategoryReclamation();
     Jeux j = new Jeux();
     Tournois t = new Tournois();
     User u = new User();
      Avis a = new Avis();
      Post p = new Post();
       Commentaire com = new Commentaire();
       
       Category cat = new Category();
         Product prod = new Product();
       
       
       
      
//        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
//                sl,
//                FlowLayout.encloseCenterBottom(
//                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
//        ));
         tb.addMaterialCommandToSideMenu("Ajout user", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> new AjoutUser(res).show());
         tb.addMaterialCommandToSideMenu("Ajout equipe", FontImage.MATERIAL_UPDATE, e -> new AddTeamForm(res).show());
             //   tb.addMaterialCommandToSideMenu("Voir Profile", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> new listUserForm(res,u).show());

                    tb.addMaterialCommandToSideMenu("Liste users", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> new ListUsers(res,u).show());

       //  tb.addMaterialCommandToSideMenu("Ajouter reclammation", FontImage.MATERIAL_ERROR, e -> new addReclaForm(res).show());
           tb.addMaterialCommandToSideMenu("Liste Equipe", FontImage.MATERIAL_LIST, e -> new ListTeamForm(res,b).show());

       
           // tb.addMaterialCommandToSideMenu("Newsfeed", FontImage.MATERIAL_UPDATE, e -> new NewsfeedForm(res).show());
       // tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
       // tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());
           tb.addMaterialCommandToSideMenu("Ajout Jeux", FontImage.MATERIAL_UPDATE, e -> new AddJeuxForm(res).show());
           tb.addMaterialCommandToSideMenu("Liste Jeux", FontImage.MATERIAL_LIST, e -> new ListJeuxForm(res,j).show());
           tb.addMaterialCommandToSideMenu("Ajout Tournois", FontImage.MATERIAL_UPDATE, e -> new AddTournoisForm(res).show());
           tb.addMaterialCommandToSideMenu("Liste Tournois", FontImage.MATERIAL_LIST, e -> new ListTournoisForm(res,t).show());
           
                      
           tb.addMaterialCommandToSideMenu("Liste Avis", FontImage.MATERIAL_LIST, e -> new ListAvisForm(res,a).show());
             tb.addMaterialCommandToSideMenu("Liste Categories", FontImage.MATERIAL_LIST, e -> new ListCategoriesReclamation(res,c).show());
          tb.addMaterialCommandToSideMenu("Add Category reclamation", FontImage.MATERIAL_LIST, e -> new AddCategoryReclamation(res).show());
           tb.addMaterialCommandToSideMenu("Ajouter reclammation", FontImage.MATERIAL_LIST, e -> new AddReclamationForm(res).show());
           tb.addMaterialCommandToSideMenu("Liste reclammation", FontImage.MATERIAL_LIST, e -> new ListReclamationForm(res,r).show());
           
       //       tb.addMaterialCommandToSideMenu("Post", FontImage.MATERIAL_EXIT_TO_APP, e -> new AjoutPostForm(res).show());
       
       
         tb.addMaterialCommandToSideMenu("Ajouter Post", FontImage.MATERIAL_LIST, e -> new AjoutPostForm(res).show());
           tb.addMaterialCommandToSideMenu("Liste Post", FontImage.MATERIAL_LIST, e -> new ListPostForm(res,p).show());    
       
              
           tb.addMaterialCommandToSideMenu("Liste Commentaire", FontImage.MATERIAL_LIST, e -> new ListCommentaireForm(res,com).show());    
           
           
           
           
                    tb.addMaterialCommandToSideMenu("Ajouter Category produit", FontImage.MATERIAL_LIST, e -> new AddCategoryForm(res).show());
                      tb.addMaterialCommandToSideMenu("Liste Category produits", FontImage.MATERIAL_LIST, e -> new ListCategoryForm(res,cat).show());    

           
                    
                    tb.addMaterialCommandToSideMenu("Ajouter produit", FontImage.MATERIAL_LIST, e -> new AddProductForm(res).show());
                      tb.addMaterialCommandToSideMenu("Liste produits", FontImage.MATERIAL_LIST, e -> new ListProductForm(res,prod).show());    

           
            
           
           

      
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());
           
        
      
           
   
    }
}

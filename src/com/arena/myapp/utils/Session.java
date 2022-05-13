/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arena.myapp.utils;

import com.arena.myapp.entities.User;

/**
 *
 * @author LENOVO
 */
public class Session {
    static Session instance;
    User loggedInUser;
    
    public Session()
    {
        
    }
    
    public static Session getInstance()
    {
        if(instance == null)
        {
            instance = new Session();
            return instance;
        }
        else
        {
            return instance;
        }
    }
    
    public User getLoggedInUser()
    {
        return loggedInUser;
    }
    
    public void setLoggedInUser( User LoggedInUser)
    {
        this.loggedInUser = LoggedInUser;
    }
    
}

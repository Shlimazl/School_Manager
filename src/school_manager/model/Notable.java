/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

/**
 *
 * @author abrasha
 */
public interface Notable {
    
    String specialNotes = "";
    
    public void addNote(String note);
    public String getNote();
    public void removeNotes();
    
}

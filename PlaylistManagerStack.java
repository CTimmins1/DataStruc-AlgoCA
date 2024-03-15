/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author CamaraAdmin
 */
public interface PlaylistManagerStack {
    //this will push() a song onto the top of the stack
   void pushSong(String song, String genre);
   //this will pop() the top song from stack, removing most recent song
   String popSong(String song);
   //this will display the top song on the top of stack
   String peekSong(String song);
   //this will check if the stack isEmpty().
   boolean isEmpty(String song);
   //will return the number of songs in the stack
   int size(String song);
   //searching the stack
   boolean searchSong(String song);
   //to displayPlaylist()
   void displayPlaylist(String genre);
   //to clear all of the stack
   void clearPlaylist(String genre);
   //to remove specific song
   void removeSong(String song, String genre);
   //this will see if a chosen song is in the stack through boolean of true or false
   boolean containsSong(String song, String genre);
}

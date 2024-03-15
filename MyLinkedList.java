
import java.util.Collections;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author CamaraAdmin
 */
//import java.util.MyLinkedList
//here i will define playlistmanager interface
interface PlaylistManager {
    //these are defining operations for my stack to work with the linked list for managing the playlists
    void addSongToPlaylist(String song, String genre);
    void displayPlaylist(String genre);
    void clearPlaylist(String genre);
    boolean isEmpty(String genre);
    int size(String genre);
    void removeSong(String song, String genre);
    boolean containsSong(String song, String genre);
    //Stack methods below
    void pushSong(String song);
    String popSong(String song);
    String peekSong(String song);
    boolean searchSong(String song);
    
}

public class MyLinkedList implements PlaylistManagerStack {

    private java.util.LinkedList<String> songList;
    private java.util.LinkedList<String> genreList;
    private java.util.LinkedList<String> rapList;
    private java.util.LinkedList<String> popList;

    public MyLinkedList() {
        //for some reason i had to physcially import inline the list util. I tried to fix imports but it would not import it no matter what I tried edit; I mentioned this in my report, it was because I was using a DoublyLinkedList, just a java encapsulation and not creating my own linked list so I had to call on it manually rather then just importing package. This is good to know for future.
        songList = new java.util.LinkedList<>();
        genreList = new java.util.LinkedList<>();
        rapList = new java.util.LinkedList<>();
        popList = new java.util.LinkedList<>();
    }
//note; ask Hamilton is it ok to stick with a DoublyLinkedList rather than LinkedList

    public void addSong(String song, String genre) {
        if (songList.size() < 7 && genreList.size() < 7) {
            songList.add(song);
            genreList.add(genre);
        } else {
            //opted for JOptionPane rather then S.Out. Cleaner UI.
            JOptionPane.showMessageDialog(null, "Maximum Songs reached pal.");
        }
    }

    public void addPlaylist(String song, String genre) {
        if (genre.equals("pop")) {
            //popList.clear();//this will clear the poplist to remove previous entries
            //looking for "pop" in popList and will add it to the playlist
            popList.add(song);
            JOptionPane.showMessageDialog(null, "Pop song has been added");
        } else if (genre.equals("rap")) {
            //rapList.clear();
            rapList.add(song);
            JOptionPane.showMessageDialog(null, "Rap song has been added");

        } else {
            //error message if user enters a genre not rap or pop
            JOptionPane.showMessageDialog(null, "This is a rap and pop app only fam.");
        }
    }

    public void removeFirstSong() {
        //if the song and genre list are not empty..
        if (!songList.isEmpty() && !genreList.isEmpty()) {
            songList.removeFirst();
            genreList.removeFirst();
        }
    }

    public void shuffleSongs() {
        //https://www.geeksforgeeks.org/collections-shuffle-method-in-java-with-examples/ got this great example of how to shuffle things using the shuffle() method. very easy to implement and I am trying to imitate the smart shuffle feature on spotify.
        Collections.shuffle(songList);
        //needs to have multipple entries in the list of different songs

    }

    public void eraseAllSongs() {
        //clearing both my linked lists to ensure no remants that would cause an error.
        songList.clear();
        genreList.clear();

    }

    
    public void addSongToPlaylist(String song, String genre) {
        if (songList.size() < 7 && genreList.size() < 7) {
            songList.add(song);
            genreList.add(genre);
            //this was 
            if(genre.equals("pop")){
                popList.add(song);
            }else if(genre.equals("rap")){
                rapList.add(song);
            }else{
                JOptionPane.showMessageDialog(null,"Not a valid genre.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maximum Songs reached pal.");
        }
    }

    @Override
    public void clearPlaylist(String genre) {
        //the implementation of myplaylistmanager interface
        this.songList.clear();
        this.genreList.clear();
        this.rapList.clear();
        this.popList.clear();

    }

    @Override
    public void displayPlaylist(String genre) {
        StringBuilder playlist = new StringBuilder();
        if (genre.equals("pop")) {
            for (String song : popList) {
                playlist.append("Song: ").append(song).append(", Genre: Pop\n");
            }
        } else if (genre.equals("rap")) {
            for (String song : rapList) {
                playlist.append("Song: ").append(song).append(", Genre: Rap\n");
            }
        } else {
            // Handle the case where the genre is neither "pop" nor "rap"
            playlist.append("Invalid genre.");
        }

    }
@Override
    public boolean isEmpty(String genre) {
        if (genre.equals("pop")) {
            return popList.isEmpty();
        } else if (genre.equals("rap)")) {
            return rapList.isEmpty();
        } else {
           //with java if you have a non void method it needs a return type, can be true/false or some integer.
            return false;
        }
    }
@Override
        public int size(String genre) {
        if (genre.equals("pop")) {
            return popList.size();
        } else if (genre.equals("rap")) {
            return rapList.size();
        } else {
                     return 0;
        }

    }
        @Override
        public void removeSong(String song, String genre){
            if(genre.equals("pop")){
                popList.remove(song);
            }else if(genre.equals("rap")){
                rapList.remove(song);
            }else{
                JOptionPane.showMessageDialog(null, "Genre not accepted");
            }
        }
        @Override
        public boolean containsSong(String song, String genre){
            if(genre.equals("pop")){
                return popList.contains(song);
            }else if(genre.equals("rap")){
                return rapList.contains(song);
            }else{
                JOptionPane.showMessageDialog(null,"Genre not accepted");
                return false;
            }
        }

    public String getListAsString() {
        //strinf builder builds strings(duh) but is a convenient way of building strings together. as seen  by both the string of " song" and "genre"
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < songList.size(); i++) {
            builder.append("Song: ").append(songList.get(i)).append(", Genre: ").append(genreList.get(i)).append("\n");
        }
        return builder.toString();
    }
    //implementing the stack methods I have instantiated above
   @Override
    public void pushSong(String song, String genre){
        songList.push(song);
    }
    @Override
    public String popSong(String song){
        return songList.pop();
    }
    @Override
    public String peekSong(String song){
        return songList.peek();
    }
    @Override
    public boolean searchSong(String song){
        return songList.contains(song);
    }
    //Again I  have to specify the java.util.LinkedList to avoid any confusion in my program
    public java.util.LinkedList<String> getSongList() {
        return songList;
    }

    public java.util.LinkedList<String> getGenreList() {
        return genreList;
    }

    public java.util.LinkedList<String> getPopList() {
        return popList;
    }

    public java.util.LinkedList<String> getRapList() {
        return rapList;
    }
}

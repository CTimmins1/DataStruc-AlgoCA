
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
interface PlaylistManagerStack {

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
    //creating an array to store the saved playlist to repeat itself
    private String[][] playlists;

    public MyLinkedList() {
        //for some reason i had to physcially import inline the list util. I tried to fix imports but it would not import it no matter what I tried edit; I mentioned this in my report, it was because I was using a DoublyLinkedList, just a java encapsulation and not creating my own linked list so I had to call on it manually rather then just importing package. This is good to know for future.
        songList = new java.util.LinkedList<>();
        genreList = new java.util.LinkedList<>();
        rapList = new java.util.LinkedList<>();
        popList = new java.util.LinkedList<>();
        //initialize array for playlists
        playlists = new String[2][];
        //start pop playlist with an empty array
        playlists[0] = new String[0];
        //start rap playlist with an empty array
        playlists[1] = new String[0];
    }
//note; ask Hamilton is it ok to stick with a DoublyLinkedList rather than LinkedList

    public List<String> getAllSongs() {
        List<String> allSongs = new ArrayList<>();
        // Concatenate all songs from all playlists
        allSongs.addAll(songList);
        allSongs.addAll(rapList);
        allSongs.addAll(popList);
        return allSongs;

    }

    public void addSong(String song, String genre) {
        if (genre.equalsIgnoreCase("pop") || genre.equals("rap")) {
            if (songList.size() < 7 && genreList.size() < 7) {
                songList.add(song);
                genreList.add(genre);
                if (genre.equalsIgnoreCase("pop")) {
                    popList.add(song);
                } else {
                    rapList.add(song);
                }
                JOptionPane.showMessageDialog(null, genre + " song has been added");
            } else {
                JOptionPane.showMessageDialog(null, "Maximum Songs reached pal.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "This is a rap and pop app only fam.");
        }
    }

    //public void addPlaylist(String song, String genre) {
    //  if (genre.equals("pop")) {
    //popList.clear();//this will clear the poplist to remove previous entries
    //looking for "pop" in popList and will add it to the playlist
    //    popList.add(song);
    //  JOptionPane.showMessageDialog(null, "Pop song has been added");
    //} else if (genre.equals("rap")) {
    //rapList.clear();
    //  rapList.add(song);
    //JOptionPane.showMessageDialog(null, "Rap song has been added");
    //} else {
    //error message if user enters a genre not rap or pop
    // JOptionPane.showMessageDialog(null, "This is a rap and pop app only fam.");
    //}
    // }
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

    public void clearPlaylistStack() {
        //clears the playlist stack by removing all elements
        songList.clear();
    }

    @Override
    public void addSongToPlaylist(String song, String genre) {
        if (songList.size() < 7 && genreList.size() < 7) {
            songList.add(song);
            genreList.add(genre);
            if (genre.equals("pop")) {
                popList.add(song);
            } else if (genre.equals("rap")) {
                rapList.add(song);
            } else {
                JOptionPane.showMessageDialog(null, "Not a valid genre.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maximum Songs reached.");
        }
    }

    @Override
    public void clearPlaylist(String genre) {
        //this was the cause of an issue of the whole song list clearing entirely when i wanted to clear specific genre only
        //not very experienced with iterators so i opted for a simple for loop to check
        // this.songList.clear();
        //this.genreList.clear();
        //this.rapList.clear();
        //this.popList.clear();
        for (int i = 0; i < songList.size(); i++) {
            String song = songList.get(i);
            String songGenre = genreList.get(i);
            if (songGenre.equals(genre)) {
                //remove song from songList on liked songs
                songList.remove(i);
                //will remove the associated genre too
                genreList.remove(i);
                // Adjust index to account for the removal
                //elements will shift left so this adjustment is nessecary
                i--;//minus by 1 so the index is the same for next iteration
            }
        }
    }
//beginning incorporating the arrays

    private void updatePlaylistArray(List<String> playlist) {
        if (playlist.equals(popList)) {
            playlists[0] = playlist.toArray(new String[0]);
        } else if (playlist.equals(rapList)) {
            playlists[1] = playlist.toArray(new String[0]);
        }
    }

    @Override
    public void displayPlaylist(String genre) {
        StringBuilder playlist = new StringBuilder();
        if (genre.equalsIgnoreCase("pop")) {
            updatePlaylistArray(popList);
            for (String song : popList) {
                playlist.append("Song: ").append(song).append(", Genre: Pop\n");
            }
        } else if (genre.equalsIgnoreCase("rap")) {
            updatePlaylistArray(rapList);
            for (String song : rapList) {
                playlist.append("Song: ").append(song).append(", Genre: Rap\n");
            }
        } else {
            //haandles the case where the genre is neither "pop" or "rap"
            playlist.append("Invalid genre.");
        }
    }
    public void setPlaylists(String[][] playlists){
        this.playlists = playlists;
    }
    public String[][] getPlaylists(){
        return playlists;
    }
    

    //used to show a mthod in a subclass or implementation is overriding a method in parent class.
    @Override
    public boolean isEmpty(String genre) {
        if (genre.equals("pop")) {
            return popList.isEmpty();
        } else if (genre.equals("rap")) {
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
    public void removeSong(String song, String genre) {
        if (genre.equals("pop")) {
            popList.remove(song);
        } else if (genre.equals("rap")) {
            rapList.remove(song);
        } else {
            JOptionPane.showMessageDialog(null, "Genre not accepted");
        }
    }

    @Override
    public boolean containsSong(String song, String genre) {
        if (genre.equals("pop")) {
            return popList.contains(song);
        } else if (genre.equals("rap")) {
            return rapList.contains(song);
        } else {
            JOptionPane.showMessageDialog(null, "Genre not accepted");
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
    public void pushSong(String song, String genre) {
        songList.push(song);
    }

    //here im aetting the move up and down btn funcs.
    public void moveUp(String genre) {
        //ensures there are at least two songs in the playlist
        if (size(genre) < 2) {
            return; // Cannot move up with less than two songs
        }
        //get the index of the last song in the playlist
        int lastIndex;
        if (genre.equals("pop")) {
            lastIndex = popList.size() - 1;
        } else {
            lastIndex = popList.size() + rapList.size() - 1;
        }
        //reemoves the last song from the playlist
        String lastSong = songList.removeLast();
        //add the last song before the current last song
        songList.add(lastIndex - 1, lastSong);
    }

//process similar here for down button
    public void moveDown(String genre) {
        if (size(genre) < 2) {
            return;
        }
        int lastIndex;
        if (genre.equals("pop")) {
            lastIndex = popList.size() - 1;
        } else {
            lastIndex = popList.size() + rapList.size() - 1;
        }
        String lastSong = songList.removeLast();
        // was crashing app when i pressed down, had accident used + instead of -, conflicting with logic and crashing app. fixed.
        songList.add(lastIndex - 1, lastSong);
    }

    //pops top song from the stack
    @Override
    public String popSong(String song) {
        return songList.pop();
    }

    @Override
    public String peekSong(String song) {
        return songList.peek();
    }

    @Override
    public boolean searchSong(String song) {
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

    @Override
    public void pushSong(String song) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

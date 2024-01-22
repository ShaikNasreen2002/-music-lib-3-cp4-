// Write your code here
package com.example.song.repository;

import java.util.*;
import com.example.song.model.*;

public interface SongRepository {
    ArrayList<Song> getSongs();

    Song getSongById(int id);

    Song addSong(Song song);

    void deleteSong(int id);

    Song updateSong(int id, Song song);
}
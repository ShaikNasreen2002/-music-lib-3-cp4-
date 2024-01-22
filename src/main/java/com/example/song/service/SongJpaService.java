/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 */
package com.example.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.song.repository.*;
import com.example.song.model.Song;
// Write your code here

import com.example.song.repository.SongRepository;

@Service
public class SongJpaService implements SongRepository {
    @Autowired
    private SongJpaRepository songJpaRepository;

    @Override
    public ArrayList<Song> getSongs() {
        List<Song> songList = songJpaRepository.findAll();
        ArrayList<Song> songs = new ArrayList<>(songList);
        return songs;
    }

    @Override
    public Song getSongById(int id) {
        try {
            Song song = songJpaRepository.findById(id).get();
            return song;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Song addSong(Song song) {

        songJpaRepository.save(song);
        return song;
    }

    @Override
    public Song updateSong(int songId, Song song) {
        try {
            Song newSong = songJpaRepository.findById(songId).get();
            if (song.getSongName() != null) {
                newSong.setSongName(song.getSongName());
            }
            if (song.getLyricist() != null) {
                newSong.setLyricist(song.getLyricist());
            }
            songJpaRepository.save(newSong);
            return newSong;

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void deleteSong(int songId) {
        try {
            songJpaRepository.deleteById(songId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}
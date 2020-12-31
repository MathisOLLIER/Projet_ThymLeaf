package com.myaudiolibrary.web.Controller;


import com.myaudiolibrary.web.Model.Albums;
import com.myaudiolibrary.web.Model.Artist;
import com.myaudiolibrary.web.Repository.AlbumsRepository;
import com.myaudiolibrary.web.Repository.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping(value = "/albums")
public class AlbumController {
    @Autowired
    private AlbumsRepository albumsRepository;

    @PostMapping(value = "")
    public RedirectView newAlbum(Albums albums) {
        albumsRepository.save(albums);
        return new RedirectView("/artists/" + albums.getArtist().getId());
    }

    @DeleteMapping(value = "{id}")
    public RedirectView deleteAlbumById(@PathVariable Integer id) {
        Optional<Albums> album = albumsRepository.findById(id);
        System.out.println(album);
        if (album.isPresent()){
            albumsRepository.deleteById(id);
        }
        return new RedirectView("/artists/" + album.get().getArtist().getId());
    }

}

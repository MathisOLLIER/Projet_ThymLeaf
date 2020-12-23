package com.myaudiolibrary.web.Controller;


import com.myaudiolibrary.web.Model.Albums;
import com.myaudiolibrary.web.Model.Artist;
import com.myaudiolibrary.web.Repository.AlbumsRepository;
import com.myaudiolibrary.web.Repository.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "albums")
public class AlbumController {
    @Autowired
    private AlbumsRepository albumsRepository;

    @RequestMapping(method = RequestMethod.POST, value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Albums createAlbum(@RequestBody Albums id){
        return albumsRepository.save(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAlbums(@PathVariable("id")Integer id){
        this.albumsRepository.deleteById(id);
    }

}

package com.myaudiolibrary.web.Controller;

import com.myaudiolibrary.web.Repository.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.myaudiolibrary.web.Model.Artist;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/artists")
public class ArtistController {

    @Autowired
    private ArtistsRepository artistsRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist getArtistById(
            @PathVariable(value = "id") Integer id){
        Optional<Artist> artist = artistsRepository.findById(id);
        return artist.get();

    }

    @RequestMapping(method = RequestMethod.GET, value= "", params = {"name"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> searchArtistByName(
            @RequestParam String name,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sortProperty,
            @RequestParam String sortDirection){

        Page<Artist> searchedArtist = artistsRepository.findByName(name,PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortProperty));

        return searchedArtist;
    }

    @RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> listArtist(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sortProperty,
            @RequestParam String sortDirection
    ){
        return artistsRepository.findAll(PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortProperty));
    }

    @RequestMapping(method = RequestMethod.POST, value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Artist createArtist(@RequestBody Artist id){
        return artistsRepository.save(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Artist updateArtist(@RequestBody Artist id){
        return artistsRepository.save(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable("id")Integer id){
        this.artistsRepository.delete(getArtistById(id));
    }

}

package com.myaudiolibrary.web.Controller;

import com.myaudiolibrary.web.Repository.AlbumsRepository;
import com.myaudiolibrary.web.Repository.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.myaudiolibrary.web.Model.Artist;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value="/artists")
public class ArtistController {

    @Autowired
    ArtistsRepository artistsRepository;
    AlbumsRepository albumsRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getArtistById(
            @PathVariable(value = "id") Integer id, final ModelMap model){
        Optional<Artist> artist = artistsRepository.findById(id);
        model.put("artist", artist.get());
        return "detailArtist";

    }

    @RequestMapping(method = RequestMethod.GET, value= "", params = {"name", "page", "size", "sortDirection", "sortProperty"})
    public String searchArtistByName(final  ModelMap model,
                                     @RequestParam String nameArtist,
                                     @RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer size,
                                     @RequestParam(defaultValue = "ASC") String sortDirection,
                                     @RequestParam(defaultValue = "name") String sortProperty){

        PageRequest pageList = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        Page <Artist> searchedArtist = artistsRepository.findByName(nameArtist, pageList);
        model.put("artist", searchedArtist);
        model.put("pageNumber", page + 1);
        model.put("previousPage", page - 1);
        model.put("nextPage", page + 1);
        model.put("start", page * size + 1);
        model.put("end", (page) * size + searchedArtist.getNumberOfElements());
        return "listeArtists";
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public String listArtist(final ModelMap model,
                                    @RequestParam Integer page,
                                    @RequestParam Integer size,
                                    @RequestParam String sortProperty,
                                    @RequestParam String sortDirection){
        Page<Artist> find = artistsRepository.findAll(PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortProperty));
        model.put("artist", find);
        model.put("pageNumber", page + 1);
        model.put("previousPage", page - 1);
        model.put("nextPage", page + 1);
        model.put("start", page * size + 1);
        model.put("end", (page) * size + find.getNumberOfElements());
        return "listeArtists";

    }

    @PostMapping(value = "/newArtist")
    public RedirectView newArtist(Artist artist) {
        artistsRepository.save(artist);
        return new RedirectView("/artists/" + artist.getId());
    }

    @PutMapping(value = "/{id}")
    public RedirectView updateArtist(Artist artist) {
        artistsRepository.save(artist);
        return new RedirectView("/artists/" + artist.getId());
    }

    @DeleteMapping(value = "/{id}")
    public String deleteByArtistId(@PathVariable Integer id) {
        albumsRepository.deleteByArtistId(id);
        artistsRepository.deleteById(id);
        return "listeArtists";
    }

}

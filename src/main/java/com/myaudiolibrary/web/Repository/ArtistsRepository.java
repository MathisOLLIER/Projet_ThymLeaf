package com.myaudiolibrary.web.Repository;

import com.myaudiolibrary.web.Model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistsRepository extends JpaRepository<Artist, Integer> {
    Page<Artist> findByName(String name, Pageable page);

    List<Artist> findByNameAndId(String name, Integer id);
}

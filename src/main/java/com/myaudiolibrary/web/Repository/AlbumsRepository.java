package com.myaudiolibrary.web.Repository;

import com.myaudiolibrary.web.Model.Albums;
import com.myaudiolibrary.web.Model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AlbumsRepository extends JpaRepository<Albums,Integer> {
    Albums getAlbumsById(Integer id);
}

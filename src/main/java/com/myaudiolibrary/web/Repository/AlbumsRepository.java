package com.myaudiolibrary.web.Repository;

import com.myaudiolibrary.web.Model.Albums;
import com.myaudiolibrary.web.Model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AlbumsRepository extends JpaRepository<Albums,Integer> {
    Albums getAlbumsById(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Albums a WHERE a.artist.id = :id")

    void deleteByArtistId(@Param("id")Integer id);
}

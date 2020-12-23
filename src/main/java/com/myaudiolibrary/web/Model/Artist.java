package com.myaudiolibrary.web.Model;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Set;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ArtistId")
    private Integer id;
    @Column(name = "Name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Albums> albums;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Albums> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Albums> albums) {
        this.albums = albums;
    }
}
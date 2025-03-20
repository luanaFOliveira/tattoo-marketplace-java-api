package com.tattoo_marketplace.domain.entities.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "tattoo_artist_images")
public class TattooArtistImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "tattoo_artist_id", nullable = false)
    private TattooArtist tattooArtist;
}

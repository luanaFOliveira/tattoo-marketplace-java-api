package com.tattoo_marketplace.domain.entities.models;

import java.math.BigDecimal;

import com.tattoo_marketplace.domain.entities.models.User;
import com.tattoo_marketplace.domain.entities.models.TattooArtist;
import com.tattoo_marketplace.domain.entities.models.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

import org.hibernate.annotations.*;

import jakarta.persistence.FetchType;

@Data
@NoArgsConstructor
@Entity(name = "quotes")
public class Quote{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String placement;

    @Column(nullable = false)
    private String color;

    @Column(precision = 10, scale = 2, nullable=false)
    private BigDecimal size;

    @Column(precision = 10, scale = 2, nullable=false)
    private BigDecimal price;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tattoo_artist_id", nullable = false)
    private TattooArtist tattooArtist;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

}

package com.tattoo_marketplace.domain.entities.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String key;

    @Column
    private String url;

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Column(name = "entity_type")
    private String entityType;
}
// @Any(metaColumn = @Column(name = "entity_type"))
    // @AnyMetaDef(
    //     name = "ImageableMetaDef",
    //     idType = "long",
    //     metaType = "string",
    //     metaValues = {
    //         @MetaValue(value = "QUOTE", targetEntity = Quote.class),
    //         @MetaValue(value = "TATTOO_ARTIST", targetEntity = TattooArtist.class)
    //     }
    // )
    // @JoinColumn(name = "entity_id")
    // private Imageable entity;


    // public void setEntity(Imageable entity, String entityType) {
    //     this.entity = entity;
    // }

    // public Imageable getEntity() {
    //     return entity;
    // }

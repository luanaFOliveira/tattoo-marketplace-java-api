package com.tattoo_marketplace.infra.specification;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Join;

import java.util.ArrayList;
import java.util.List;

import com.tattoo_marketplace.domain.entities.models.TattooArtist;
import com.tattoo_marketplace.application.dto.tattoo_artist.TattooArtistFilter; 

public class TattooArtistSpecification implements Specification<TattooArtist> {

    private final TattooArtistFilter filter;

    public TattooArtistSpecification(TattooArtistFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<TattooArtist> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getName() != null) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
        }

        if (filter.getCategory() != null) {
            Join<Object, Object> categoriesJoin = root.join("categories");
            predicates.add(cb.equal(categoriesJoin.get("name"), filter.getCategory())); 
        }

        if (filter.getLocation() != null) {
            predicates.add(cb.equal(root.get("location"), filter.getLocation()));
        }

        if (filter.getSortBy() != null) {
            if ("desc".equalsIgnoreCase(filter.getSortOrder())) {
                query.orderBy(cb.desc(root.get(filter.getSortBy())));
            } else {
                query.orderBy(cb.asc(root.get(filter.getSortBy())));
            }
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}

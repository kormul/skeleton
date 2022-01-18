package com.kormul.skeleton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kormul.skeleton.domain.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}

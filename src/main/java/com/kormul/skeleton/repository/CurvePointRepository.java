package com.kormul.skeleton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kormul.skeleton.domain.CurvePoint;

public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}

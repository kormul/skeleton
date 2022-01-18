package com.kormul.skeleton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kormul.skeleton.domain.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer> {

}

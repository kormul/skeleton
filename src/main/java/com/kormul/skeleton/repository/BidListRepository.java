package com.kormul.skeleton.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kormul.skeleton.domain.BidList;

public interface BidListRepository extends JpaRepository<BidList, Integer> {

	public List<BidList> findByAccountAndType(String account, String type);

}

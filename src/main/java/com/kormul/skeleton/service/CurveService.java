package com.kormul.skeleton.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kormul.skeleton.domain.CurvePoint;
import com.kormul.skeleton.repository.CurvePointRepository;

import lombok.Data;

@Data
@Service
public class CurveService {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private CurvePointRepository curvepointRepository;

	public CurvePoint createCurvePoint(@Valid CurvePoint curvePoint) {
		
		return curvepointRepository.save(curvePoint);
	}

	public List<CurvePoint> findAllCurvePoint() {
        List<CurvePoint> allCurvePoint = curvepointRepository.findAll();
        return allCurvePoint;
	}

	public CurvePoint updateCurvePoint(Integer id, CurvePoint curvePoint) {
		Optional<CurvePoint> curvePointModify = curvepointRepository.findById(id);
		if(!curvePointModify.isPresent()) {
			logger.error("Failed to update. CurvePoint not Found");
			throw new NoSuchElementException("Failed to update CurvePoint");
		}
		else {
			curvePoint.setId(curvePointModify.get().getId());
			//curvePoint.setCurveId(curvePointModify.get().getCurveId());
			curvePoint.setCreationDate(curvePointModify.get().getCreationDate());
			logger.info("CurvePoint has updated");
			return curvepointRepository.save(curvePoint);
		}
		
		
	}

	public void deleteCurvePointById(Integer id) {
		Optional<CurvePoint> curvePointDelete = curvepointRepository.findById(id);
		if (!curvePointDelete.isPresent()) {
			logger.error("Failed to delete. CurvePoint not Found");
			throw new NoSuchElementException("Failed to delete CurvePoint");
        } else {
        	logger.info("CurvePoint has deleted");
        	curvepointRepository.deleteById(id);
        }
		
	}

	public CurvePoint getbyId(Integer id) {
		Optional<CurvePoint> curvePoint = curvepointRepository.findById(id);
		if (!curvePoint.isPresent()) {
			logger.error("Failed to find. curvePoint not Found");
			throw new NoSuchElementException("Failed to find curvePoint");
        } else {
        	logger.debug("curvePoint has find");
        	return curvePoint.get();
        }
	}

	
}

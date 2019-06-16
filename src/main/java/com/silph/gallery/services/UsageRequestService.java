package com.silph.gallery.services;

import javax.transaction.Transactional;

import com.silph.gallery.model.UsageRequest;
import com.silph.gallery.repositories.UsageRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UsageRequestService
 */
@Service
public class UsageRequestService {

    @Autowired
	private UsageRequestRepository usageRequestRepository;
	
	@Transactional
	public UsageRequest putUsageRequest(UsageRequest usageRequest) {
		return usageRequestRepository.save(usageRequest);
	}
    
}
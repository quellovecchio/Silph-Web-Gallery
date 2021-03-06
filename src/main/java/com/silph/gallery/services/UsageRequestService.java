package com.silph.gallery.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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

	@Transactional
	public List<UsageRequest> getAllUsageRequests() {
		List<UsageRequest> list = new ArrayList<>();
		Iterator<UsageRequest> i = usageRequestRepository.findAll().iterator();
		i.forEachRemaining(list::add);
		return list;
	}

	@Transactional
	public UsageRequest getUsageRequestById(Long id) {
		try {
			return usageRequestRepository.findById(id).get();
		}
		catch (NoSuchElementException ex) {
			return null;
		}
	}

	@Transactional
	public void deleteUsageRequestById(Long id) {
		usageRequestRepository.deleteById(id);
	}
    
}
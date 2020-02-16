package com.gayan.allocationservice.service;

import com.gayan.allocationservice.model.Allocation;

import java.util.List;

public interface AllocationService {

    Allocation save(Allocation allocation);

    List<Allocation> fetchAllAllocations();

    List<Allocation> getAllocationByEmployeeId(Integer id);

}

package com.lead.dashboard.serviceImpl;


import com.lead.dashboard.domain.BitrixBad;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.repository.BitrixRepo;
import com.lead.dashboard.repository.StatusRepository;
import com.lead.dashboard.service.BitrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BitrixImpl implements BitrixService {


    @Autowired
    private BitrixRepo bitrixRepo;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<BitrixBad> getAllBitrixBad() {

        List<BitrixBad> bitrixBadList = bitrixRepo.findAll();


        for (BitrixBad bitrixBad : bitrixBadList) {

            Status status = statusRepository.findByName(bitrixBad.getStage());


            if ("Bad Fit".equals(bitrixBad.getStage())) {

                System.out.println("Found Bad Fit record: " + bitrixBad);
            }
        }

        return bitrixBadList;
    }
}

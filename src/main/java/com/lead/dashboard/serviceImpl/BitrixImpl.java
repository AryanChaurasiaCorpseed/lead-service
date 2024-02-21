package com.lead.dashboard.serviceImpl;


import com.lead.dashboard.domain.*;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.*;
import com.lead.dashboard.service.BitrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BitrixImpl implements BitrixService {


    @Autowired
    private BitrixRepo bitrixRepo;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private LeadHistoryRepository leadHistoryRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<BitrixBad> getAllBitrixBad() {
        int pageSize = 100;
        int pageNumber = 0;

        List<BitrixBad> convertedBitrixBadList = new ArrayList<>();

        while (true) {
            PageRequest pageable = PageRequest.of(pageNumber, pageSize);
            Page<BitrixBad> bitrixBadPage = bitrixRepo.findAll(pageable);

            if (bitrixBadPage.isEmpty()) {
                break;
            }

            List<BitrixBad> bitrixBadList = bitrixBadPage.getContent();

            for (BitrixBad bitrixBad : bitrixBadList) {
                if ("Bad Fit".equals(bitrixBad.getStage())) {
                    Lead lead = createLeadFromBitrixBad(bitrixBad);
                    leadRepository.save(lead);
//                    System.out.println("Found Bad Fit record: " + bitrixBad);
                }
                convertedBitrixBadList.add(bitrixBad);
            }

            pageNumber++;
        }

        return convertedBitrixBadList;
    }




    private Lead createLeadFromBitrixBad(BitrixBad bitrixBad) {
        Lead lead = new Lead();
        Status status = statusRepository.findByName(bitrixBad.getStage());

        lead.setLeadName(bitrixBad.getDealName());
        lead.setStatus(status);
        lead.setName(bitrixBad.getContact());
        lead.setLeadDescription(bitrixBad.getComment());
//
        Client client = new Client();
        client.setName(bitrixBad.getContact());
        client.setEmails(bitrixBad.getClientEmail());
        clientRepository.save(client);

        createLeadHistory(lead);

        return lead;
    }

    public LeadHistory createLeadHistory(Lead lead) {
        LeadHistory leadHistory= new LeadHistory();
        leadHistory.setCreateDate(new Date());
        leadHistory.setEventType("Lead has been created");
        leadHistory.setLeadId(lead.getId());
        leadHistoryRepository.save(leadHistory);

        return leadHistory;
    }


}

package com.lead.dashboard.service;

import org.springframework.stereotype.Service;

import java.util.*;
import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.lead.Remark;

@Service
public interface ChatService {

	Client createChat(Long clientId, Long userId, String message);

	Boolean deleteChat(Long chatId);

	Remark createRemarks(Long leadId,Long userId, String message);

	List<Remark> getAllRemarks(Long leadId);
                     
}

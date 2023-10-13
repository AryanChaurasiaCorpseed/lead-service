package com.lead.dashboard.service;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Client;

@Service
public interface ChatService {

	Client createChat(Long clientId, Long userId, String message);

	Boolean deleteChat(Long chatId);

	Client createRemarks(Long clientId,Long userId, String message);
                     
}

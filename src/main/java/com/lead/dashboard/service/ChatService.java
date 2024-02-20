package com.lead.dashboard.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.lead.Remark;

@Service
public interface ChatService {

	Client createChat(Long clientId, Long userId, String message);

	Boolean deleteChat(Long chatId);

	Remark createRemarks(Long leadId,Long userId, String message, MultipartFile multipartFile) throws IOException;

	List<Remark> getAllRemarks(Long leadId);
                     
}

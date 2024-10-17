package com.lead.dashboard.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.lead.Remark;
import com.lead.dashboard.dto.UpdateRemarkDto;

@Service
public interface ChatService {

	Client createChat(Long clientId, Long userId, String message);

	Boolean deleteChat(Long chatId, Long currentUser);

	Remark createRemarks(Long leadId,Long userId, String message, List<String> multipartFile,String type) ;

	List<Remark> getAllRemarks(Long leadId);


	Boolean updateRemark(UpdateRemarkDto updateRemarkDto);

	Boolean deleteRemark(Long remarkId, Long currentUser, Long leadId);
                     
}

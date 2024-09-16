package com.lead.dashboard.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.lead.dashboard.domain.*;
import com.lead.dashboard.dto.remarkDTO.UpdateRemarkRequest;
import com.lead.dashboard.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lead.dashboard.domain.lead.FileData;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.lead.Remark;
import com.lead.dashboard.service.ChatService;

/*
@Author :-Aryan Chaurasia
*/
@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	FileUploadServiceImpl fileUploadServiceImpl;

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	RemarkRepository remarkRepository;
	
	@Autowired
	FileDataRepository fileDataRepository;

	@Autowired
	CommunicationRepository communicationRepository;
	
	@Autowired
	LeadRepository leadRepository;

	@Autowired
	private LeadHistoryRepository leadHistoryRepository;

	@Override
	public Client createChat(Long clientId, Long userId, String message) {
		Optional<Client> clientOp = clientRepository.findById(clientId);
		Client client = clientOp.get();
		User user = userRepo.findById(userId).get();
		Communication communication = new Communication();
		communication.setMessage(message);
		communication.setType("chat");
		communication.setCreatedBy(user);

		communication.setChatTime(new Date());
		Communication com = communicationRepository.save(communication);
		List<Communication> comList = client.getCommunication();
		if(comList!=null) {
			comList.add(com);
			client.setCommunication(comList);
			clientRepository.save(client);
		}else {
			List<Communication> cList =new ArrayList<>();
			cList.add(com);
			client.setCommunication(cList);
			clientRepository.save(client);
		}
		return client;
	}

	@Override
	public Boolean deleteChat(Long chatId) {
        boolean flag=false;
		Optional<Communication> opChat = communicationRepository.findById(chatId);
		if(opChat!=null && opChat.get()!=null) {
			Communication chat = opChat.get();
			chat.setDeleted(true);
			communicationRepository.save(chat);
			flag=true;
		}
		return flag;
	}

	@Override
	public Remark createRemarks(Long leadId, Long userId, String message, List<String> images, String type) {

		User user = userRepo.findById(userId).get();
		Optional<Lead> opLead = leadRepository.findById(leadId);
		Remark remark = new Remark();

		if (opLead.isPresent()) {
			Lead lead = opLead.get();
			List<Remark> remarks = lead.getRemarks();

			if (images != null && !images.isEmpty()) {
				List<FileData> fileList = fileDataRepository.findByfilePathIn(images);
				remark.setImageList(fileList);
			}

			remark.setMessage(message);
			remark.setLatestUpdated(new Date());
			remark.setUpdatedBy(user);
			remark.setType(type);
			remarkRepository.save(remark);

			if (remarks != null) {
				remarks.add(remark);
			} else {
				List<Remark> remarkList = new ArrayList<>();
				remarkList.add(remark);
				lead.setRemarks(remarkList);
			}
			leadRepository.save(lead);

			saveLeadHistory(lead, user, "Remark Created", message);
		}
		return remark;
	}

	public void saveLeadHistory(Lead lead, User user, String eventType, String description) {
		LeadHistory leadHistory = new LeadHistory();
		leadHistory.setCreateDate(new Date());
		leadHistory.setCreatedBy(user);
		leadHistory.setPrevUser(null);
		leadHistory.setDescription(description);
		leadHistory.setEventType(eventType);
		leadHistory.setLeadId(lead.getId());
		leadHistoryRepository.save(leadHistory);
	}


	@Override
	public List<Remark> getAllRemarks(Long leadId) {
		// TODO Auto-generated method stub
		Optional<Lead> opLead = leadRepository.findById(leadId);
		List<Remark> remark = new ArrayList<>();

		if(opLead!=null && opLead.get()!=null) {
			remark=opLead.get().getRemarks();
		}
		return remark;
	}

	public Remark updateRemarks(UpdateRemarkRequest updateRemarkRequest) {

		Long userId = updateRemarkRequest.getUserId();
		Long remarkId = updateRemarkRequest.getRemarkId();
		String updatedMessage = updateRemarkRequest.getMessage();
		List<String> updatedImages = updateRemarkRequest.getFile();
		String type = updateRemarkRequest.getType();
		Long leadId = updateRemarkRequest.getLeadId();

		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		Remark remark = remarkRepository.findById(remarkId).orElseThrow(() -> new RuntimeException("Remark not found"));

		Lead lead = leadRepository.findById(leadId).orElseThrow(() -> new RuntimeException("Lead not found"));

		String previousMessage = remark.getMessage();
		List<FileData> previousImages = remark.getImageList();

		if (updatedImages != null && !updatedImages.isEmpty()) {
			List<FileData> fileList = fileDataRepository.findByfilePathIn(updatedImages);
			remark.setImageList(fileList);
		}

		remark.setMessage(updatedMessage);
		remark.setLatestUpdated(new Date());
		remark.setUpdatedBy(user);
		remark.setType(type);

		remarkRepository.save(remark);

		saveUpdateHistory(lead, user, "Remark updated", previousMessage, updatedMessage, previousImages, updatedImages);

		return remark;
	}

	public void saveUpdateHistory(Lead lead, User user, String eventType, String previousMessage, String updatedMessage,
								  List<FileData> previousImages, List<String> updatedImages) {

		String description = "Updated remark. ";

		if (!previousMessage.equals(updatedMessage)) {
			description += "Message updated from: \"" + previousMessage + "\" to \"" + updatedMessage + "\". ";
		}

		if (previousImages != null && !previousImages.isEmpty() && updatedImages != null && !updatedImages.isEmpty()) {
			description += "Images updated.";
		} else if (previousImages == null || previousImages.isEmpty()) {
			description += "Images added.";
		} else if (updatedImages == null || updatedImages.isEmpty()) {
			description += "Images removed.";
		}

		LeadHistory leadHistory = new LeadHistory();
		leadHistory.setCreateDate(new Date());
		leadHistory.setCreatedBy(user);
		leadHistory.setDescription(description);
		leadHistory.setEventType(eventType);
		leadHistory.setLeadId(lead.getId());

		leadHistoryRepository.save(leadHistory);
	}



}

package com.lead.dashboard.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.domain.Chats;
import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Communication;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.lead.Remark;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.repository.CommunicationRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.RemarkRepository;
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
	CommunicationRepository communicationRepository;
	
	@Autowired
	LeadRepository leadRepository;

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
	public Remark createRemarks(Long leadId,Long userId, String message, MultipartFile multipartFile) throws IOException {
		// TODO Auto-generated method stub
		
		User user = userRepo.findById(userId).get();
		Optional<Lead> opLead = leadRepository.findById(leadId);
		Remark remark = new Remark();

		if(opLead!=null && opLead.get()!=null) {
			Lead lead = opLead.get();
			List<Remark> remarks = lead.getRemarks();
			
			if(multipartFile!=null) {
				try {
					String file=fileUploadServiceImpl.uploadImageToFileData(multipartFile);
					remark.setImages(file);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if(remarks!=null && remarks.size()!=0) {
				remark.setMessage(message);
				remark.setLatestUpdated(new Date());
				remark.setUpdatedBy(user);
				remarkRepository.save(remark);
				remarks.add(remark);
				lead.setRemarks(remarks);
				leadRepository.save(lead);
			}else {
				List<Remark>remarkList = new ArrayList<>();
				remark.setMessage(message);
				remark.setLatestUpdated(new Date());
				remark.setUpdatedBy(user);
				remarkRepository.save(remark);
				remarkList.add(remark);
				lead.setRemarks(remarkList);
				leadRepository.save(lead);
			}
		}  
		return remark;
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

}

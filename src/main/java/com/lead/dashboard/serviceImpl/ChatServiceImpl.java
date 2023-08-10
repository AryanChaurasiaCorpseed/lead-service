package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.domain.Chats;
import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Communication;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.repository.CommunicationRepository;
import com.lead.dashboard.service.ChatService;

/*
@Author :-Aryan Chaurasia
*/
@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	UserRepo userRepo;

	@Autowired
	CommunicationRepository communicationRepository;

	@Override
	public Client createChat(Long clientId, Long userId, String message) {
		Optional<Client> clientOp = clientRepository.findById(userId);
		Client client = clientOp.get();
		User user = userRepo.findById(userId).get();
		Communication communication = new Communication();
		communication.setMessage(message);
		communication.setType("chat");
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

}

package com.lead.dashboard.serviceImpl.country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.State;
import com.lead.dashboard.repository.StateRepository;
import com.lead.dashboard.service.country.StateService;


@Service
public class StateServiceImpl implements StateService{
	
	@Autowired
	StateRepository stateRepository;

	@Override
	public Boolean createState(String name) {
		Boolean flag=false;
		State state = new State();
		state.setName(name);
		stateRepository.save(state);
		flag=true;
		return flag;
	}

	@Override
	public List<Map<String, Object>> getAllState() {
		List<State> stateList = stateRepository.findAll();
		List<Map<String, Object>>res=new ArrayList<>();
		for(State s:stateList) {
			Map<String, Object>map = new HashMap<>();
			map.put("id", s.getId());
			map.put("name", s.getName());
			res.add(map);
		}
		return res;
	}
	

}

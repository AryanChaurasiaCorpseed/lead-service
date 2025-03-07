package com.lead.dashboard.service.country;

import java.util.List;
import java.util.Map;

public interface StateService {

	Boolean createState(String name);

	List<Map<String, Object>> getAllState();

}

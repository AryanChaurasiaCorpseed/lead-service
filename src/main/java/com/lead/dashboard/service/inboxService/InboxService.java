package com.lead.dashboard.service.inboxService;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public interface InboxService {

	List<Map<String ,Object>> getAllInboxData();

	boolean editView();

}

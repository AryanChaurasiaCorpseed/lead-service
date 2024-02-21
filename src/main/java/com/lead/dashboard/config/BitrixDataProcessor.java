package com.lead.dashboard.config;

import org.springframework.batch.item.ItemProcessor;

import com.lead.dashboard.domain.BitrixLead;

public class BitrixDataProcessor implements ItemProcessor<BitrixLead, BitrixLead>{

	@Override
	public BitrixLead process(BitrixLead item) throws Exception {
		// we will write process test case
		// if we have not any data then we write this
		return item;
	}

}

package com.lead.dashboard.dto.mapper;

import java.util.Map;

import lombok.Data;
//@Data
public class Objects {
    private String callcenter_variables;
    private String destination;
    private int billed_pulses;
    private double coin_deducted;
    private int order_id;
    private String char_field_1;
    private String seconds_from_year_3000;
    private double legb_coin_deducted;
    private int duration;
    private int int_field_1;
    private String voiceid;
    private int user_plan_id;
    private String uniphore_status;
    private int id;
    private String callerph;
    private String callcenter_queue_id;
    private int int_field_3;
    private String extension;
    private int ruleid;
    private String with_recording;
    private String filepath;
    private int coins_deducted;
    private boolean is_outgoing;
    private String ftype;
    private String sr_number;
    public String getCallcenter_variables() {
		return callcenter_variables;
	}
	public String getDestination() {
		return destination;
	}
	public int getBilled_pulses() {
		return billed_pulses;
	}
	public double getCoin_deducted() {
		return coin_deducted;
	}
	public int getOrder_id() {
		return order_id;
	}
	public String getChar_field_1() {
		return char_field_1;
	}
	public String getSeconds_from_year_3000() {
		return seconds_from_year_3000;
	}
	public double getLegb_coin_deducted() {
		return legb_coin_deducted;
	}
	public int getDuration() {
		return duration;
	}
	public int getInt_field_1() {
		return int_field_1;
	}
	public String getVoiceid() {
		return voiceid;
	}
	public int getUser_plan_id() {
		return user_plan_id;
	}
	public String getUniphore_status() {
		return uniphore_status;
	}
	public int getId() {
		return id;
	}
	public String getCallerph() {
		return callerph;
	}
	public String getCallcenter_queue_id() {
		return callcenter_queue_id;
	}
	public int getInt_field_3() {
		return int_field_3;
	}
	public String getExtension() {
		return extension;
	}
	public int getRuleid() {
		return ruleid;
	}
	public String getWith_recording() {
		return with_recording;
	}
	public String getFilepath() {
		return filepath;
	}
	public int getCoins_deducted() {
		return coins_deducted;
	}

	public String getFtype() {
		return ftype;
	}
	public String getSr_number() {
		return sr_number;
	}
	public Map<String, Object> getReceiver_details() {
		return receiver_details;
	}
	public String getCallid() {
		return callid;
	}
	public String getStart_time() {
		return start_time;
	}
	public String getGen_queue() {
		return gen_queue;
	}
	public String getResource_uri() {
		return resource_uri;
	}
	public void setCallcenter_variables(String callcenter_variables) {
		this.callcenter_variables = callcenter_variables;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setBilled_pulses(int billed_pulses) {
		this.billed_pulses = billed_pulses;
	}
	public void setCoin_deducted(double coin_deducted) {
		this.coin_deducted = coin_deducted;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public void setChar_field_1(String char_field_1) {
		this.char_field_1 = char_field_1;
	}
	public void setSeconds_from_year_3000(String seconds_from_year_3000) {
		this.seconds_from_year_3000 = seconds_from_year_3000;
	}
	public void setLegb_coin_deducted(double legb_coin_deducted) {
		this.legb_coin_deducted = legb_coin_deducted;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setInt_field_1(int int_field_1) {
		this.int_field_1 = int_field_1;
	}
	public void setVoiceid(String voiceid) {
		this.voiceid = voiceid;
	}
	public void setUser_plan_id(int user_plan_id) {
		this.user_plan_id = user_plan_id;
	}
	public void setUniphore_status(String uniphore_status) {
		this.uniphore_status = uniphore_status;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCallerph(String callerph) {
		this.callerph = callerph;
	}
	public void setCallcenter_queue_id(String callcenter_queue_id) {
		this.callcenter_queue_id = callcenter_queue_id;
	}
	public void setInt_field_3(int int_field_3) {
		this.int_field_3 = int_field_3;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public void setRuleid(int ruleid) {
		this.ruleid = ruleid;
	}
	public void setWith_recording(String with_recording) {
		this.with_recording = with_recording;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public void setCoins_deducted(int coins_deducted) {
		this.coins_deducted = coins_deducted;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	public void setSr_number(String sr_number) {
		this.sr_number = sr_number;
	}
	public void setReceiver_details(Map<String, Object> receiver_details) {
		this.receiver_details = receiver_details;
	}
	public void setCallid(String callid) {
		this.callid = callid;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public void setGen_queue(String gen_queue) {
		this.gen_queue = gen_queue;
	}
	public void setResource_uri(String resource_uri) {
		this.resource_uri = resource_uri;
	}
	private Map<String, Object> receiver_details; // Assuming this is a dynamic map of objects
    private String callid;
    private String start_time;
    private String gen_queue;
    private String resource_uri;
	public boolean is_outgoing() {
		return is_outgoing;
	}
	public void setis_outgoing(boolean is_outgoing) {
		this.is_outgoing = is_outgoing;
	}
    
    
}

package com.lead.dashboard.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Lead;
import com.lead.dashboard.service.LeadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//@Tag(name = "Lead", description = "Lead management APIs")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LeadController {
  @Autowired
  LeadService leadservice;

	/*
	 * @Operation(summary = "Create a new Lead", tags = { "Leads", "post" })
	 * 
	 * @ApiResponses({
	 * 
	 * @ApiResponse(responseCode = "201", content = {
	 * 
	 * @Content(schema = @Schema(implementation = Lead.class), mediaType =
	 * "application/json") }),
	 * 
	 * @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema())
	 * }) })
	 * 
	 * @PostMapping("/Lead") public ResponseEntity<Lead> createLead(@RequestBody
	 * Lead Lead) { try { Lead _Lead = Leadservice .save(new Lead(Lead.getTitle(),
	 * Lead.getDescription(), Lead.isPublished())); return new
	 * ResponseEntity<>(_Lead, HttpStatus.CREATED); } catch (Exception e) { return
	 * new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); } }
	 * 
	 * @Operation(summary = "Retrieve all Leads", tags = { "Leads", "get", "filter"
	 * })
	 * 
	 * @ApiResponses({
	 * 
	 * @ApiResponse(responseCode = "200", content = {
	 * 
	 * @Content(schema = @Schema(implementation = Lead.class), mediaType =
	 * "application/json") }),
	 * 
	 * @ApiResponse(responseCode = "204", description = "There are no Leads",
	 * content = {
	 * 
	 * @Content(schema = @Schema()) }),
	 * 
	 * @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema())
	 * }) })
	 * 
	 * @GetMapping("/Leads") public ResponseEntity<List<Lead>>
	 * getAllLeads(@RequestParam(required = false) String title) { try { List<Lead>
	 * Leads = new ArrayList<Lead>();
	 * 
	 * if (title == null) Leadservice.findAll().forEach(Leads::add); else
	 * Leadservice.findByTitleContaining(title).forEach(Leads::add);
	 * 
	 * if (Leads.isEmpty()) { return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * 
	 * return new ResponseEntity<>(Leads, HttpStatus.OK); } catch (Exception e) {
	 * return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); } }
	 * 
	 * @Operation( summary = "Retrieve a Lead by Id", description =
	 * "Get a Lead object by specifying its id. The response is Lead object with id, title, description and published status."
	 * , tags = { "Leads", "get" })
	 * 
	 * @ApiResponses({
	 * 
	 * @ApiResponse(responseCode = "200", content = { @Content(schema
	 * = @Schema(implementation = Lead.class), mediaType = "application/json") }),
	 * 
	 * @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())
	 * }),
	 * 
	 * @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema())
	 * }) })
	 * 
	 * @GetMapping("/Leads/{id}") public ResponseEntity<Lead>
	 * getLeadById(@PathVariable("id") long id) { Lead Lead =
	 * Leadservice.findById(id);
	 * 
	 * if (Lead != null) { return new ResponseEntity<>(Lead, HttpStatus.OK); } else
	 * { return new ResponseEntity<>(HttpStatus.NOT_FOUND); } }
	 * 
	 * @Operation(summary = "Update a Lead by Id", tags = { "Leads", "put" })
	 * 
	 * @ApiResponses({
	 * 
	 * @ApiResponse(responseCode = "200", content = {
	 * 
	 * @Content(schema = @Schema(implementation = Lead.class), mediaType =
	 * "application/json") }),
	 * 
	 * @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema())
	 * }),
	 * 
	 * @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())
	 * }) })
	 * 
	 * @PutMapping("/Leads/{id}") public ResponseEntity<Lead>
	 * updateLead(@PathVariable("id") long id, @RequestBody Lead Lead) { Lead _Lead
	 * = Leadservice.findById(id);
	 * 
	 * if (_Lead != null) { _Lead.setTitle(Lead.getTitle());
	 * _Lead.setDescription(Lead.getDescription());
	 * _Lead.setPublished(Lead.isPublished()); return new
	 * ResponseEntity<>(Leadservice.save(_Lead), HttpStatus.OK); } else { return new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND); } }
	 * 
	 * @Operation(summary = "Delete a Lead by Id", tags = { "Leads", "delete" })
	 * 
	 * @ApiResponses({ @ApiResponse(responseCode = "204", content =
	 * { @Content(schema = @Schema()) }),
	 * 
	 * @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema())
	 * }) })
	 * 
	 * @DeleteMapping("/Leads/{id}") public ResponseEntity<HttpStatus>
	 * delete̥(@PathVariable("id") long id) { try { Leadservice.deleteById(id);
	 * return new ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception e) {
	 * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 * 
	 * @Operation(summary = "Delete all Leads", tags = { "Leads", "delete" })
	 * 
	 * @ApiResponses({ @ApiResponse(responseCode = "204", content =
	 * { @Content(schema = @Schema()) }),
	 * 
	 * @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema())
	 * }) })
	 * 
	 * @DeleteMapping("/Leads") public ResponseEntity<HttpStatus> deleteAllLeads() {
	 * try { Leadservice.deleteAll(); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 * 
	 * @Operation(summary = "Retrieve all published Leads", tags = { "Leads", "get",
	 * "filter" })
	 * 
	 * @GetMapping("/Leads/published") public ResponseEntity<List<Lead>>
	 * findByPublished() { try { List<Lead> Leads =
	 * Leadservice.findByPublished(true);
	 * 
	 * if (Leads.isEmpty()) { return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * return new ResponseEntity<>(Leads, HttpStatus.OK); } catch (Exception e) {
	 * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */
  
  @Operation(summary = "Create a new Lead", tags = { "Leads", "post" })
  @ApiResponses({
      @ApiResponse(responseCode = "201", content = {
          @Content(schema = @Schema(implementation = Lead.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @PostMapping("/Lead")
  public Lead createLead(@RequestParam Long LeadId) {
	  Lead  fb =leadservice.getAllData(LeadId);
	 System.out.println(fb!=null?fb.getName():"NA------");
	 return fb;
  }

}


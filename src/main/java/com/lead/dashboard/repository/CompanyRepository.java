package com.lead.dashboard.repository;

import java.util.List;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.lead.Lead;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query(value = "SELECT c.id FROM company c left join company_lead cl on c.id=cl.company_id where cl.company_lead_id=:leadId LIMIT 1", nativeQuery = true)
	Long findCompanyIdByLeadId(Long leadId);

	@Query(value = "SELECT * FROM company c where c.assignee_id in(:userList)", nativeQuery = true)
	List<Company> findAllByAssigneeIdIn(List<Long> userList);

	@Query(value = "SELECT count(id) FROM company c where c.assignee_id in(:userList)", nativeQuery = true)
	int findAllCountByAssigneeIdIn(List<Long> userList);

	@Query(value = "SELECT * FROM company c where c.assignee_id in(:userList)", nativeQuery = true)
	Page<Company> findAllByAssigneeIdIn(List<Long> userList,Pageable pageable);

	//temp
	@Query(value = "SELECT * FROM company c where c.temp_assignee_id in(:userList)", nativeQuery = true)
	Page<Company> findAllByTempAssigneeIdIn(List<Long> userList,Pageable pageable);	
	@Query(value = "SELECT count(id) FROM company c where c.temp_assignee_id in(:userList)", nativeQuery = true)
	int findAllCountByTempAssigneeIdIn(List<Long> userList);


	@Query(value = "SELECT * FROM company c where c.assignee_id=:userId", nativeQuery = true)
	Page<Company> findByAssigneeId(Long userId,Pageable pageable);

	//temp assignee
	@Query(value = "SELECT * FROM company c where c.temp_assignee_id=:userId", nativeQuery = true)
	Page<Company> findByTempAssigneeId(Long userId,Pageable pageable);
	@Query(value = "SELECT count(id) FROM company c where c.temp_assignee_id=:userId", nativeQuery = true)
	int findCountByTempAssigneeId(Long userId);


	@Query(value = "SELECT count(id) FROM company c where c.assignee_id=:userId", nativeQuery = true)
	int findCountByAssigneeId(Long userId);


	@Query(value = "SELECT * FROM company c where c.assignee_id=:userId", nativeQuery = true)
	List<Company> findByAssigneeId(Long userId);

	@Query(value = "SELECT * FROM company c where c.is_parent=false", nativeQuery = true)
	List<Company> findAllByIsParent();

	@Query(value = "SELECT * FROM company c where c.name=:name limit 1", nativeQuery = true)
	Company findByName(String name);

	@Query(value = "SELECT * FROM company c where c.id=:id || c.parent_id=:id", nativeQuery = true)
	List<Company> findAllCompanyUnitByCompanyId(Long id);


	@Query(value = "SELECT * FROM company c where c.gst_no=:gst", nativeQuery = true)
	List<Company> findByGst(String gst);

	@Query(value = "SELECT ct.emails FROM company c left join contact ct on ct.id=c.id", nativeQuery = true)
	List<String> findAllEmail();

	@Query(value = "SELECT c.id FROM company c left join contact ct on ct.id=c.primary_contact_id where ct.emails=:email and is_parent=true limit 1", nativeQuery = true)
	Long findByPrimaryEmails(String email);


	@Query(value = "SELECT * FROM company c WHERE c.name LIKE %:searchTerm% or c.gst_no like %:searchTerm%", nativeQuery = true)
	List<Company> findByNameOrGST(@Param("searchTerm") String searchTerm);

	@Query(value = "SELECT * FROM company c WHERE c.assignee_id = :userId AND (c.name LIKE %:searchNameAndGST% OR c.gst_no LIKE %:searchNameAndGST%)", nativeQuery = true)
	List<Company> findAllByCompanyNameAndGSTNumber(@Param("searchNameAndGST") String searchNameAndGST, @Param("userId") Long userId);

	@Query(value = "SELECT * FROM company c where c.id in(:companyId)", nativeQuery = true)
	List<Company> findByIdIn(List<Long> companyId);

	@Query(value = "SELECT * FROM company c where c.parent_id = :id", nativeQuery = true)
	List<Company> findAllByParentId(Long id);

	@Query(value = "SELECT count(*) FROM company c ", nativeQuery = true)
	long findAllCount();

	@Query(value = "SELECT c.id FROM company c left join company_lead cl on c.id=cl.company_id where cl.company_lead_id in(:leadIds) LIMIT 1", nativeQuery = true)
	Long findCompanyIdByLeadId(List<Long> leadIds);

	@Query(value = "SELECT * FROM company c where create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	List<Company> findByInBetweenCreateDate(String d1,String d2);



//	@Query(value = "SELECT * FROM company c WHERE c.name LIKE %:searchNameAndGST%", nativeQuery = true)
//	List<Company> findByNameLike(String searchNameAndGST);
//
//	@Query(value = "SELECT * FROM company c WHERE c.gst_no LIKE %:searchNameAndGSt%", nativeQuery = true)
//	List<Company> findByGstNumberLike(String searchNameAndGSt);
//
//	@Query(value = "SELECT c.id FROM company c left join contact pc on pc.id=c.primary_contact_id WHERE pc.contact_no like %:searchTerm%", nativeQuery = true)
//	List<Long> findByContactNoLike(String searchTerm);
//	@Query(value = "SELECT c.id FROM company c left join contact pc on pc.id=c.primary_contact_id WHERE pc.emails like %:searchTerm%", nativeQuery = true)
//	List<Long> findByEmailLike(@Param("searchTerm") String searchTerm);
	
	
	@Query(value = "SELECT c.id , c.name , u.id , u.full_name FROM company c left join user u on u.id=c.assignee_id WHERE c.name LIKE %:searchNameAndGST%", nativeQuery = true)
	List<Object[]> findByNameLike(String searchNameAndGST);

	@Query(value = "SELECT c.id , c.name , u.id , u.full_name FROM company c left join user u on u.id=c.assignee_id WHERE c.gst_no LIKE %:searchNameAndGSt%", nativeQuery = true)
	List<Object[]> findByGstNumberLike(String searchNameAndGSt);

	@Query(value = "SELECT c.id , c.name , u.id , u.full_name FROM company c left join contact pc on pc.id=c.primary_contact_id left join user u on u.id=c.assignee_id WHERE pc.contact_no like %:searchTerm%", nativeQuery = true)
	List<Object[]> findByContactNoLike(String searchTerm);
	@Query(value = "SELECT c.id ,c.name , u.id , u.full_name FROM company c left join contact pc on pc.id=c.primary_contact_id left join user u on u.id=c.assignee_id WHERE pc.emails like %:searchTerm%", nativeQuery = true)
	List<Object[]> findByEmailLike(@Param("searchTerm") String searchTerm);
	
	@Query(value = "SELECT c.id , c.name , u.id , u.full_name FROM company c left join user u on u.id=c.assignee_id WHERE c.name LIKE %:searchTerm% or c.gst_no like %:searchTerm%", nativeQuery = true)
	List<Object[]> findByNameOrGSTLike(@Param("searchTerm") String searchTerm);
	@Query(value = "SELECT c.id , c.name , u.email as assignee , c.gst_no ,cont.emails , cont.contact_no ,c.address,  c.city,c.state,c.country ,c.primary_pin_code ,c.s_address,c.s_city,c.s_state,c.s_country FROM finalerp.company c left join finalerp.user u on u.id=c.assignee_id left join finalerp.contact cont on cont.id=c.primary_contact_id", nativeQuery = true)
	List<Object[]> findAllData();
	
	@Query(value = "SELECT c.id , c.name , u.email as assignee , c.gst_no ,cont.emails , cont.contact_no ,c.address,  c.city,c.state,c.country ,c.primary_pin_code ,c.s_address,c.s_city,c.s_state,c.s_country FROM finalerp.company c left join finalerp.user u on u.id=c.assignee_id left join finalerp.contact cont on cont.id=c.primary_contact_id where c.assignee_id in(:userList)", nativeQuery = true)
	List<Object[]> findAllDataByAssigneeIdIn(List<Long> userList);
	
	@Query(value = "SELECT c.id , c.name , u.email as assignee , c.gst_no ,cont.emails , cont.contact_no ,c.address,  c.city,c.state,c.country ,c.primary_pin_code ,c.s_address,c.s_city,c.s_state,c.s_country FROM finalerp.company c left join finalerp.user u on u.id=c.assignee_id left join finalerp.contact cont on cont.id=c.primary_contact_id where c.assignee_id=:userId", nativeQuery = true)
	List<Object[]> findDataByAssigneeId(Long userId);
	
//	
//	@Query(value = "SELECT c.id , c.name , u.email as assignee , c.gst_no ,cont.emails , cont.contact_no ,c.address,  c.city,c.state,c.country ,c.primary_pin_code ,c.s_address,c.s_city,c.s_state,c.s_country FROM finalerp.company c left join finalerp.user u on u.id=c.assignee_id left join finalerp.contact cont on cont.id=c.primary_contact_id where c.assignee_id in(:userList)", nativeQuery = true)
//	List<Company> findAllDataByAssigneeIdIn(List<Long> userList);

}

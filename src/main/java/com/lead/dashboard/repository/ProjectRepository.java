package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Project;

@Repository
public interface ProjectRepository  extends JpaRepository<Project, Long> {

	@Query(value = "SELECT * FROM project p WHERE p.id in(:projectId)", nativeQuery = true)
	List<Project> findAllByIdIn(List<Long> projectId);

     @Query(value = "SELECT * FROM project p WHERE p.project_no =:projectNo", nativeQuery = true)
	List<Project> findAllByProjectNo(String projectNo);

     @Query(value = "SELECT * FROM project p WHERE p.assignee_id =:userId", nativeQuery = true)
	 List<Project> findAllByAssigneeId(Long userId);
     
     @Query(value = "SELECT * FROM project p WHERE p.assignee_id =:userId", nativeQuery = true)
	 List<Project> findAllByAssigneeId(Long userId,Pageable pageable);

     @Query(value = "SELECT * FROM project p WHERE p.assignee_id =:userId and name =:projectName", nativeQuery = true)
	 List<Project> findAllByAssigneeIdAndProjectName(Long userId, String projectName);

     @Query(value = "SELECT * FROM project p WHERE p.name =:projectName", nativeQuery = true)
	 List<Project> findAllByProjectName(String projectName);
     
     @Query(value = "SELECT * FROM project p WHERE p.assignee_id =:userId and name =:projectName and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	 List<Project> findAllByAssigneeIdAndProjectNameAndInBetweenDate(Long userId, String projectName,String d1,String d2);
	

     @Query(value = "SELECT * FROM project p WHERE p.assignee_id =:userId and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	List<Project> findAllByAssigneeIdAndInBetweenDate(Long userId,String d1,String d2);
     
     @Query(value = "SELECT * FROM project p WHERE p.name =:projectName and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	 List<Project> findAllByProjectNameAndInBetweenDate(String projectName,String d1,String d2);
     
     @Query(value = "SELECT * FROM project p WHERE create_date BETWEEN :d1 AND :d2", nativeQuery = true)
  	 List<Project> findAllInBetweenDate(String d1,String d2);
     
     @Query(value = "SELECT p.id ,cp.company_id , p.amount ,c.name FROM project p left join company_project cp on cp.company_project_id=p.id left join company c on c.id=cp.company_id where p.create_date BETWEEN :d1 AND :d2", nativeQuery = true)
  	 List<Object[]> findInBetweenDate(String d1,String d2);
  	 
     @Query(value = "SELECT * FROM project p where p.create_date BETWEEN :d1 AND :d2", nativeQuery = true)
   	 List<Project> findAllByInBetweenDate(String d1,String d2);
  	 
     //test
     @Query(value = "SELECT p.id,p.name FROM project p WHERE p.assignee_id =:userId and name =:projectName and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	 List<Object[]> findIdAndNameByAssigneeIdAndProjectNameAndInBetweenDate(Long userId, String projectName,String d1,String d2);
	 
     @Query(value = "SELECT p.id,p.name FROM project p WHERE p.assignee_id =:userId and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	List<Object[]> findIdAndNameByAssigneeIdAndInBetweenDate(Long userId,String d1,String d2);
	
    @Query(value = "SELECT  p.id,p.name FROM project p WHERE p.name =:projectName and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	 List<Object[]> findIdAndNameByProjectNameAndInBetweenDate(String projectName,String d1,String d2);
	 
     @Query(value = "SELECT p.id,p.name FROM project p WHERE create_date BETWEEN :d1 AND :d2", nativeQuery = true)
  	 List<Object[]> findIdAndNameByInBetweenDate(String d1,String d2);
  	 
     @Query(value = "SELECT p.id,p.name FROM project p WHERE p.assignee_id =:userId and name =:projectName", nativeQuery = true)
	 List<Object[]> findIdAndNameByAssigneeIdAndProjectName(Long userId, String projectName);
     
     @Query(value = "SELECT p.id,p.name FROM project p WHERE p.assignee_id =:userId", nativeQuery = true)
	 List<Object[]> findIdAndNameByAssigneeId(Long userId);
     
     @Query(value = "SELECT p.id,p.name FROM project p WHERE p.name =:projectName", nativeQuery = true)
	 List<Object[]> findIdAndNameByProjectName(String projectName);
	 
     @Query(value = "SELECT id,name FROM project", nativeQuery = true)
	 List<Object[]> findIdAndName();
	 
	 //amount
	 @Query(value = "SELECT p.id,p.name,p.amount FROM project p WHERE p.assignee_id =:userId and name =:projectName and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	 List<Object[]> findIdAndNameAndAmountByAssigneeIdAndProjectNameAndInBetweenDate(Long userId, String projectName,String d1,String d2);
	 
     @Query(value = "SELECT p.id,p.name,p.amount FROM project p WHERE p.assignee_id =:userId and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	List<Object[]> findIdAndNameAndAmountByAssigneeIdAndInBetweenDate(Long userId,String d1,String d2);
	
    @Query(value = "SELECT  p.id,p.name,p.amount FROM project p WHERE p.name =:projectName and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	 List<Object[]> findIdAndNameAndAmountByProjectNameAndInBetweenDate(String projectName,String d1,String d2);
	 
     @Query(value = "SELECT p.id,p.name,p.amount FROM project p WHERE create_date BETWEEN :d1 AND :d2", nativeQuery = true)
  	 List<Object[]> findIdAndNameAndAmountByInBetweenDate(String d1,String d2);
  	 
     @Query(value = "SELECT p.id,p.name,p.amount FROM project p WHERE p.assignee_id =:userId and name =:projectName", nativeQuery = true)
	 List<Object[]> findIdAndNameAndAmountByAssigneeIdAndProjectName(Long userId, String projectName);
     
     @Query(value = "SELECT p.id,p.name,p.amount FROM project p WHERE p.assignee_id =:userId", nativeQuery = true)
	 List<Object[]> findIdAndNameAndAmountByAssigneeId(Long userId);
     
     @Query(value = "SELECT p.id,p.name,p.amount FROM project p WHERE p.name =:projectName", nativeQuery = true)
	 List<Object[]> findIdAndNameAndAmountByProjectName(String projectName);
	 
     @Query(value = "SELECT id,name,amount FROM project", nativeQuery = true)
	 List<Object[]> findIdAndNameAndAmount();
	 
     @Query(value = "SELECT p.id ,cp.company_id , p.amount ,c.name FROM project p left join company_project cp on cp.company_project_id=p.id left join company c on c.id=cp.company_id", nativeQuery = true)
  	 List<Object[]> findCompanyIdAndAmount();
	 
     @Query(value = "SELECT * FROM project p", nativeQuery = true)
   	 List<Project> findAllProject();
     
     
}

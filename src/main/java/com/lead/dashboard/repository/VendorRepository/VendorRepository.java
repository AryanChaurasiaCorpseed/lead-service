package com.lead.dashboard.repository.VendorRepository;


import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.vendor.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface VendorRepository  extends JpaRepository<Vendor,Long> {


    @Query("SELECT v FROM Vendor v WHERE v.assignedUser.id = :userId AND v.lead.id = :leadId AND v.isDeleted = false")
    List<Vendor> findAllVendorRequestByAssignedUser(@Param("userId") Long userId, @Param("leadId") Long leadId);


    @Query("SELECT v FROM Vendor v WHERE v.lead.id = :leadId AND v.isDeleted = false")
    List<Vendor> findAllVendorRequests(@Param("leadId") Long leadId);


    @Query("SELECT v FROM Vendor v WHERE v.id = :vendorId")
    Vendor findVendorById(@Param("vendorId") Long vendorId);

    Page<Vendor> findAll(Pageable pageable);


    @Query("SELECT v FROM Vendor v JOIN v.user u WHERE u.id = :userId AND v.lead.id = :leadId AND v.isDeleted = false")
    List<Vendor> findVendorRequestsBySalesUserAndLead(@Param("userId") Long userId, @Param("leadId") Long leadId);

    Page<Vendor> findByAssignedUser(User assignedUser, Pageable pageable);

    Page<Vendor> findByUser(User user, Pageable pageable);

    @Query("SELECT v FROM Vendor v where v.isDeleted = false ORDER BY v.id DESC")
    Page<Vendor> findAllVendors(Pageable pageable);


    @Query("SELECT v FROM Vendor v WHERE v.assignedUser = :user ORDER BY v.id DESC")
    Page<Vendor> findVendorsByAssignedUser(@Param("user") User user, Pageable pageable);


    @Query("SELECT v FROM Vendor v WHERE v.assignedUser.id = :userId AND v.date BETWEEN :startDate AND :endDate")
    List<Vendor> findAllByAssignedUserAndDateRange(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT v FROM Vendor v WHERE " +
            "LOWER(v.clientCompanyName) LIKE LOWER(CONCAT('%', :searchInput, '%')) OR " +
            "LOWER(v.clientName) LIKE LOWER(CONCAT('%', :searchInput, '%')) OR " +
            "v.clientMobileNumber LIKE CONCAT('%', :searchInput, '%') OR " +
            "v.clientEmailId LIKE CONCAT('%', :searchInput, '%')")
    List<Vendor> searchVendors(@Param("searchInput") String searchInput);

    @Query("SELECT v FROM Vendor v " +
            "WHERE v.assignedUser = :userDetails " +
            "AND (LOWER(v.clientName) LIKE LOWER(CONCAT('%', :searchInput, '%')) " +
            "OR LOWER(v.clientCompanyName) LIKE LOWER(CONCAT('%', :searchInput, '%')) " +
            "OR LOWER(v.clientMobileNumber) LIKE (CONCAT('%', :searchInput, '%')) " +
            "OR LOWER(v.clientEmailId) LIKE LOWER(CONCAT('%', :searchInput, '%')))")
    List<Vendor> searchVendorsByUser(@Param("userDetails") User userDetails,
                                     @Param("searchInput") String searchInput);

    @Query("SELECT v FROM Vendor v " +
            "WHERE v.createDate BETWEEN :startDate AND :endDate " +
            "AND v.assignedUser.id IN :assignedUserIds")
    List<Vendor> findVendorsByDateAndAssignedUsers(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("assignedUserIds") List<Long> assignedUserIds
    );

    @Query("SELECT v FROM Vendor v " +
            "WHERE v.createDate BETWEEN :startDate AND :endDate")
    List<Vendor> findVendorsByDateOnly(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );


    @Query(value = "SELECT * FROM vendor v WHERE v.status in(:statuses)and create_date BETWEEN :startDateConverted AND :endDateConverted", nativeQuery = true)
    List<Vendor> findAllVendorRequestByDateAndStatusOnly(Date startDateConverted, Date endDateConverted, List<String> statuses);


    @Query(value = "SELECT * FROM vendor v WHERE v.status IN (:statuses) " +
            "AND v.assigned_user IN (:assignedUserIds) " +
            "AND v.create_date BETWEEN :startDateConverted AND :endDateConverted",
            nativeQuery = true)
    List<Vendor> findAllVendorRequestByDateAndStatus(
            @Param("startDateConverted") Date startDateConverted,
            @Param("endDateConverted") Date endDateConverted,
            @Param("statuses") List<String> statuses,
            @Param("assignedUserIds") List<Long> assignedUserIds
    );

}
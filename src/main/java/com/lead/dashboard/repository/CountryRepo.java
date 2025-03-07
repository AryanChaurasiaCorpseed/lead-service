package com.lead.dashboard.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Country;


@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {


}

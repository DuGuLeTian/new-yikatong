package com.liutianhong.yikatong.repository;

import com.liutianhong.yikatong.domain.Duguletian;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Duguletian entity.
 */
@SuppressWarnings("unused")
public interface DuguletianRepository extends JpaRepository<Duguletian,Long> {

}

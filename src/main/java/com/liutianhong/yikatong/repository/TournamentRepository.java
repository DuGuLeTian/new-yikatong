package com.liutianhong.yikatong.repository;

import com.liutianhong.yikatong.domain.Tournament;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Tournament entity.
 */
@SuppressWarnings("unused")
public interface TournamentRepository extends JpaRepository<Tournament,Long> {

}

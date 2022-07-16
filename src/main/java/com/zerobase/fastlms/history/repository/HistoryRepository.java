package com.zerobase.fastlms.history.repository;

import com.zerobase.fastlms.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, String> {

    Optional<List<History>> findByUserIdOrderByLoginDate(String userId);
}

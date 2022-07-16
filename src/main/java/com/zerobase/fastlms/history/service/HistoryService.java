package com.zerobase.fastlms.history.service;

import com.zerobase.fastlms.history.dto.HistoryDto;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryService {

    boolean addHistory(LocalDateTime loginDate, String clientIp, String userAgent, String userId);

    List<HistoryDto> list(String userId);
}

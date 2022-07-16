package com.zerobase.fastlms.history.service;

import com.zerobase.fastlms.history.dto.HistoryDto;
import com.zerobase.fastlms.history.entity.History;
import com.zerobase.fastlms.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    public boolean addHistory(LocalDateTime loginDate, String clientIp, String userAgent, String userId) {

        History history = History.builder()
                .userId(userId)
                .loginDate(loginDate)
                .accessIp(clientIp)
                .userAgent(userAgent)
                .build();

        historyRepository.save(history);

        return true;
    }

    @Override
    public List<HistoryDto> list(String userId) {

        Optional<List<History>> historyList = historyRepository.findByUserIdOrderByLoginDate(userId);
        if (historyList.isPresent()) {
            return HistoryDto.of(historyList.get());
        }
        return null;
    }
}

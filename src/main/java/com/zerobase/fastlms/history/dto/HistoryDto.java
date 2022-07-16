package com.zerobase.fastlms.history.dto;

import com.zerobase.fastlms.history.entity.History;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HistoryDto {
    int No;
    LocalDateTime loginDate;
    String accessIp;
    String userAgent;

    public static HistoryDto of(History history, int No) {

        return HistoryDto.builder()
                .No(No)
                .loginDate(history.getLoginDate())
                .accessIp(history.getAccessIp())
                .userAgent(history.getUserAgent())
                .build();
    }

    public static List<HistoryDto> of(List<History> histories) {
        if (histories == null) {
            return null;
        }

        List<HistoryDto> historyDtoList = new ArrayList<>();

        for (int i = histories.size(); i > 0; i--) {
            historyDtoList.add(HistoryDto.of(histories.get(i - 1), i));
        }

        return historyDtoList;
    }
}

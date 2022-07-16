package com.zerobase.fastlms.history.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class History {

    @Id
    @GeneratedValue
    private int id;
    private String userId;
    private LocalDateTime loginDate;
    private String accessIp;
    private String userAgent;
}

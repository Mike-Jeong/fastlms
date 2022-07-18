package com.zerobase.fastlms.banner.entity;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
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
public class Banner {

    @Id
    @GeneratedValue
    private long id;
    private String bannerName;
    private String imageDirectory;
    private String atext;
    private String address;
    private int orderId;
    private String openId;
    private boolean visible;
    private LocalDateTime regDt;

    public static Banner of(BannerDto bannerDto) {

        return Banner.builder()
                .id(bannerDto.getId())
                .bannerName(bannerDto.getBannerName())
                .imageDirectory(bannerDto.getImageDirectory())
                .atext(bannerDto.getBannerName())
                .address(bannerDto.getAddress())
                .orderId(bannerDto.getOrder())
                .openId(bannerDto.getOpenId())
                .visible(bannerDto.isVisible())
                .regDt(bannerDto.getRegDt())
                .build();
    }
}

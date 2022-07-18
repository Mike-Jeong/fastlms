package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BannerDto {
    private long id;
    private String bannerName;
    private String imageDirectory;
    private String address;
    private int order;
    private String openId;
    private boolean visible;
    private LocalDateTime regDt;

    long totalCount;
    long seq;

    public static BannerDto of(Banner banner) {

        return BannerDto.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .imageDirectory(banner.getImageDirectory())
                .address(banner.getAddress())
                .order(banner.getOrderId())
                .openId(banner.getOpenId())
                .visible(banner.isVisible())
                .regDt(banner.getRegDt())
                .build();
    }
}

package com.zerobase.fastlms.banner.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerInput {
    private String bannerName;
    private String address;
    private String imageDirectory;
    private int orderId;
    private String openId;
    private boolean visible;
}

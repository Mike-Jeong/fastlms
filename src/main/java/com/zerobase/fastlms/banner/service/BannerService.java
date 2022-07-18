package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;

import java.util.List;

public interface BannerService {

    List<BannerDto> list(BannerParam parameter);
    boolean delete(BannerParam bannerParam);

    BannerDto detail(String bannerId);

    boolean update(BannerInput bannerInput, long id);
    boolean add(BannerInput bannerInput);

    List<BannerDto> findVisible();

}

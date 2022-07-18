package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class BannerServiceImpl implements BannerService {

    private final BannerMapper bannerMapper;
    private final BannerRepository bannerRepository;

    @Override
    public List<BannerDto> list(BannerParam parameter) {

        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for(BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public boolean delete(BannerParam bannerParam) {

        for (String id : bannerParam.getIdList().split(",")) {
            bannerRepository.deleteById(Long.parseLong(id));
        }

        return true;
    }

    @Override
    public BannerDto detail(String bannerId) {
        Optional<Banner> optionalBanner = bannerRepository.findById(Long.parseLong(bannerId));

        if (!optionalBanner.isPresent()) {
            return null;
        }
        Banner banner = optionalBanner.get();

        return BannerDto.of(banner);
    }

    @Override
    public boolean update(BannerInput bannerInput, long id) {

        Optional<Banner> optionalBanner = bannerRepository.findById(id);
        if (!optionalBanner.isPresent()) {
            return false;
        }
        Banner banner = optionalBanner.get();
        File oldFile = new File(banner.getImageDirectory());
        oldFile.delete();

        banner.setBannerName(bannerInput.getBannerName());
        banner.setImageDirectory(bannerInput.getImageDirectory());
        banner.setAtext(bannerInput.getBannerName());
        banner.setAddress(bannerInput.getAddress());
        banner.setOpenId(bannerInput.getOpenId());
        banner.setOrderId(bannerInput.getOrderId());
        banner.setVisible(bannerInput.isVisible());
        banner.setRegDt(LocalDateTime.now());

        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean add(BannerInput bannerInput) {
        try {
            Banner banner = Banner.builder()
                    .bannerName(bannerInput.getBannerName())
                    .imageDirectory(bannerInput.getImageDirectory())
                    .atext(bannerInput.getBannerName())
                    .address(bannerInput.getAddress())
                    .orderId(bannerInput.getOrderId())
                    .openId(bannerInput.getOpenId())
                    .visible(bannerInput.isVisible())
                    .regDt(LocalDateTime.now())
                    .build();
            bannerRepository.save(banner);
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }

    @Override
    public List<BannerDto> findVisible() {
        return bannerMapper.findVisibleBanner();
    }

}

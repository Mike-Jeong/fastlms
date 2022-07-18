package com.zerobase.fastlms.admin.controller;


import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@Slf4j
public class AdminBannerController extends BaseController {
    
    private final BannerService bannerService;
    
    @GetMapping("/admin/banner/list.do")
    public String list(Model model, BannerParam parameter) {
        
        parameter.init();
        List<BannerDto> banners = bannerService.list(parameter);
        
        long totalCount = 0;
        if (banners != null && banners.size() > 0) {
            totalCount = banners.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
        
        model.addAttribute("list", banners);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);
        
        return "admin/banner/list";
    }

    @GetMapping("/admin/banner/add.do")
    public String add() {
        return "/admin/banner/add";
    }

    @PostMapping("/admin/banner/add.do")
    public String add(MultipartFile multipartFile, BannerInput bannerInput) {
        String IMAGE_LOCAL_DIRECTORY = "/Users/min/imageUpload";
        String IMAGE_SAVE_DIRECTORY = "/imageUpload";
        String uuid = UUID.randomUUID().toString();

        try {
            File newFile = new File(RequestUtils.saveFile(multipartFile.getOriginalFilename(), IMAGE_LOCAL_DIRECTORY, uuid));
            FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(newFile));
        } catch (IOException e) {
            log.info(e.getMessage());
            return "/admin/banner/add";
        }
        bannerInput.setImageDirectory(RequestUtils.saveFile(multipartFile.getOriginalFilename(), IMAGE_SAVE_DIRECTORY, uuid));
        bannerService.add(bannerInput);
        return "/admin/banner/add";
    }

    @GetMapping("/admin/banner/edit.do")
    public String update(Model model, BannerParam parameter) {
        parameter.init();

        String bannerId = parameter.getId();
        BannerDto bannerDto = bannerService.detail(bannerId);
        model.addAttribute("banner", bannerDto);
        return "/admin/banner/edit";
    }

    @PostMapping("/admin/banner/edit.do")
    public String update(MultipartFile multipartFile, BannerInput bannerInput, @RequestParam(name = "bannerid")long id) {
        String IMAGE_LOCAL_DIRECTORY = "/Users/min/imageUpload";
        String IMAGE_SAVE_DIRECTORY = "/imageUpload";
        String uuid = UUID.randomUUID().toString();

        try {

            File newFile = new File(RequestUtils.saveFile(multipartFile.getOriginalFilename(), IMAGE_LOCAL_DIRECTORY, uuid));
            FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(newFile));
        } catch (IOException e) {
            log.info(e.getMessage());
            return "/admin/banner/edit";
        }
        bannerInput.setImageDirectory(RequestUtils.saveFile(multipartFile.getOriginalFilename(), IMAGE_SAVE_DIRECTORY, uuid));
        bannerService.update(bannerInput, id);

        return "/admin/banner/edit";
    }



    @PostMapping("/admin/banner/delete.do")
    public String delete(BannerParam bannerParam) {
        bannerService.delete(bannerParam);
        return "redirect:/admin/banner/list.do";
    }
}

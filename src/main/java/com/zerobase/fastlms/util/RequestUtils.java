package com.zerobase.fastlms.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;

public class RequestUtils {

    public static String getUserAgent(HttpServletRequest request) {

        String userAgent = null;

        userAgent = request.getHeader("user-agent");

        return userAgent;

    }

    public static String getClientIP(HttpServletRequest request) {

        String ip = null;

        ip = request.getRemoteAddr();

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("Proxy-Client-IP");

        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("WL-Proxy-Client-IP");

        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("HTTP_CLIENT_IP");

        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("HTTP_X_FORWARDED_FOR");

        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("X-Real-IP");

        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("X-RealIP");

        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("REMOTE_ADDR");

        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getRemoteAddr();

        }

        return ip;

    }

    public static String saveFile(String originalFilename, String baseUrlPath, String bannerName) {
        LocalDate now = LocalDate.now();
        String urlDir = String.format("%s/%d/%02d/%02d/", baseUrlPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        String[] dirs = {
                String.format("%s/%d/", baseUrlPath, now.getYear()),
                String.format("%s/%d/%02d/", baseUrlPath, now.getYear(), now.getMonthValue()),
                String.format("%s/%d/%02d/%02d/", baseUrlPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth())};

        for (String dir : dirs) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.mkdir();
            }
        }

        String fileExtension = "";
        if (originalFilename != null) {
            int dotPos = originalFilename.lastIndexOf(".");
            if (dotPos > -1) {
                fileExtension = originalFilename.substring(dotPos + 1);
            }
        }

        String newUrlFilename = String.format("%s%s", urlDir, bannerName);

        if (fileExtension.length() > 0) {
            newUrlFilename += "." + fileExtension;
        }

        return newUrlFilename;
    }
}

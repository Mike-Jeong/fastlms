package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.history.service.HistoryService;
import com.zerobase.fastlms.member.service.MemberService;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberService memberService;
    private final HistoryService historyService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails)principal).getUsername();
        System.out.println(userId);

        LocalDateTime current = LocalDateTime.now();
        System.out.println(current);

        String userAgent = RequestUtils.getUserAgent(request);
        System.out.println(userAgent);
        String clientIp = RequestUtils.getClientIP(request);
        System.out.println(clientIp);
        memberService.updateLastAccessDate(userId, current);
        historyService.addHistory(current, clientIp, userAgent, userId);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}

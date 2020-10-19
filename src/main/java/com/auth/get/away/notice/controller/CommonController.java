package com.auth.get.away.notice.controller;

import com.auth.get.away.notice.service.dto.AccountDTO;
import com.boostor.framework.rest.ResposeStatus;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 公共管理
 * @author wxy
 *  2020 3-18
 */
@RestController
@RequestMapping("/common")
public class CommonController {



}
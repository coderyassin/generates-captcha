package org.yascode.generates_captcha.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.generates_captcha.model.CaptchaData;
import org.yascode.generates_captcha.service.CaptchaService;

import java.io.IOException;

@RestController
@RequestMapping("/api/captcha")
public class CaptchaController {
    private final CaptchaService captchaService;

    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @GetMapping
    public ResponseEntity<byte[]> getCaptcha(HttpServletRequest request) throws IOException {
        CaptchaData captchaData = captchaService.generateCaptcha();
        request.getSession().setAttribute("captcha", captchaData.getAnswer());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return ResponseEntity.ok().headers(headers).body(captchaData.getImage());
    }

    @GetMapping(value = "/recoverCaptcha")
    public ResponseEntity<?> recoverCaptcha(HttpServletRequest request) {
        return ResponseEntity.ok(request.getSession().getAttribute("captcha"));
    }

}

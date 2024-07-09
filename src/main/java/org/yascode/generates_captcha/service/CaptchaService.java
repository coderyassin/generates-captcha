package org.yascode.generates_captcha.service;

import org.yascode.generates_captcha.model.CaptchaData;

import java.io.IOException;

public interface CaptchaService {
    CaptchaData generateCaptcha() throws IOException;
}

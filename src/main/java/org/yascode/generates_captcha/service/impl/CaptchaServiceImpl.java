package org.yascode.generates_captcha.service.impl;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;
import org.yascode.generates_captcha.model.CaptchaData;
import org.yascode.generates_captcha.service.CaptchaService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Collections;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Override
    public CaptchaData generateCaptcha() throws IOException {
        Captcha captcha = new Captcha.Builder(200, 50)
                .addText(new DefaultTextProducer(), new DefaultWordRenderer(Collections.singletonList(Color.BLACK), Collections.singletonList(new Font("Arial", Font.BOLD, 40))))
                .addBackground(new GradiatedBackgroundProducer())
                .build();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(captcha.getImage(), "png", outputStream);

        return new CaptchaData(outputStream.toByteArray(), captcha.getAnswer());
    }
}
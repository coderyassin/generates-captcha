package org.yascode.generates_captcha.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaptchaData {
    private byte[] image;
    private String answer;
}

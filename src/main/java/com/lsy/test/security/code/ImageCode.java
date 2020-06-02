package com.lsy.test.security.code;

import lombok.Data;
import lombok.experimental.Accessors;

import java.awt.image.BufferedImage;

@Data
@Accessors(chain = true)
public class ImageCode extends ValidateCode{

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, Integer expiredMinute) {
        super(code, expiredMinute);
        this.image = image;
    }
}

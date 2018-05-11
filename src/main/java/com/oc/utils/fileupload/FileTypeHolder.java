package com.oc.utils.fileupload;

import com.oc.utils.SystemUtils;
import org.junit.Test;

public class FileTypeHolder {

    public enum FileType {

        image,

        other;

    }

    public enum Image {

        avatar,

        articleimg,

        background
    }

    public String getUploadPath(Image image) {
        StringBuilder pathBuilder = new StringBuilder(SystemUtils.getValue("static"));
        String imagePath = SystemUtils.getValue("imagePath");
        String s = imagePath.replaceAll("\\$\\{imageType}", image.toString());
        System.out.println(s);
        return null;
    }

    @Test
    public void test() {
        getUploadPath(Image.avatar);
    }

}
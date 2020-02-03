package controller;

import org.apache.commons.lang.xwork.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pojo.UploadImageFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {
    @RequestMapping("/uploadImage")
    public ModelAndView upload(HttpServletRequest request, UploadImageFile file) throws IOException {
        String name = RandomStringUtils.randomAlphanumeric(10);
        String newFileName = name + ".jpg";
        File newFile = new File(request.getServletContext().getRealPath("/image"), newFileName);
        System.out.println(request.getServletContext().getRealPath("/image"));
        System.out.println(newFile.getPath());
        newFile.getParentFile().mkdirs();
        file.getImage().transferTo(newFile); //复制文件

        ModelAndView mav = new ModelAndView("showUploadedFile");
        mav.addObject("imageName", newFileName);
        return mav;
    }
}

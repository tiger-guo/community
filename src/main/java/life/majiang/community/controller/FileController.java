package life.majiang.community.controller;

import life.majiang.community.dto.FileDTO;
import life.majiang.community.provider.UcloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/29 13:11
 */
@Controller
public class FileController {

    @Autowired
    private UcloudProvider ucloudProvider;
    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("editormd-image-file");

        try {
            String fileName = ucloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl(fileName);
            return fileDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

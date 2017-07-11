package com.zxh.controller;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhengxh on 2017/6/8.
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @RequestMapping(value="/up",method= RequestMethod.POST)
    public void test2(Model model, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String realDir = request.getSession().getServletContext().getRealPath("");
        try {
            String filePath = "uploadfiles";
            String realPath = realDir + "\\" + filePath;
            File dir = new File(realPath);
            if (!dir.isDirectory())
                dir.mkdir();
            if (ServletFileUpload.isMultipartContent(request)) {
                DiskFileItemFactory dff = new DiskFileItemFactory();
                dff.setRepository(dir);
                dff.setSizeThreshold(1024000);
                ServletFileUpload sfu = new ServletFileUpload(dff);
                FileItemIterator fii = null;
                try {
                    fii = sfu.getItemIterator(request);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String title = "";   //图片标题
                String url = "";    //图片地址
                String fileName = "";
                String state = "SUCCESS";
                String realFileName = "";
                while (fii.hasNext()) {
                    FileItemStream fis = fii.next();
                    try {
                        if (!fis.isFormField() && fis.getName().length() > 0) {
                            fileName = fis.getName();
                            Pattern reg = Pattern.compile("[.]jpg|png|jpeg|gif$");
                            Matcher matcher = reg.matcher(fileName);

                            realFileName = new Date().getTime() + fileName.substring(fileName.lastIndexOf("."), fileName.length());
                            url = realPath + "\\" + realFileName;
                            BufferedInputStream in = new BufferedInputStream(fis.openStream());//获得文件输入流
                            FileOutputStream a = new FileOutputStream(new File(url));
                            BufferedOutputStream output = new BufferedOutputStream(a);
                            Streams.copy(in, output, true);//开始把文件写到你指定的上传文件夹
                        } else {
                            String fname = fis.getFieldName();
                            if (fname.indexOf("pictitle") != -1) {
                                BufferedInputStream in = new BufferedInputStream(fis.openStream());
                                byte c[] = new byte[10];
                                int n = 0;
                                while ((n = in.read(c)) != -1) {
                                    title = new String(c, 0, n);
                                    break;
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

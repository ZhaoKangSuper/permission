package com.mmall.controller;

import com.mmall.dao.SysAclMapper;
import com.mmall.dao.SysRoleAclMapper;
import com.mmall.dao.SysRoleUserMapper;
import com.mmall.model.Record;
import com.mmall.model.SysAcl;
import com.mmall.model.SysUser;
import com.mmall.service.RecordService;
import com.mmall.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by ZHAOKANG on 2018/6/13.
 */
@RequestMapping(value = "/record")
@Controller
@Slf4j
public class RecordController {

    @Resource
    SysRoleUserMapper sysRoleUserMapper;

    @Resource
    SysRoleAclMapper sysRoleAclMapper;

    @Resource
    SysAclMapper sysAclMapper;

    @Autowired
    private RecordService recordService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() {
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String doUpload(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "title") String title,
                           @RequestParam(value = "description") String description,
                           @RequestParam(value = "imgFile") MultipartFile file) {
        int type = (Integer) request.getSession().getAttribute("flag");
        log.info("****************" + type);
        int action = 0;
//        String title = request.getParameter("title");
        log.info("-------------"+title);
//        String description = request.getParameter("description");
        log.info("-------------"+description);
        String username = (String)request.getSession().getAttribute("username");
        log.info(type + username + title + description + file + action);
        recordService.doUpload(type, username, title, description, file, action);
        log.info("------------------");
        return "upload";
    }

    @RequestMapping(value = "/check")
    public String check (Map<String,Object> map,HttpServletRequest request, HttpServletResponse response) {
        int type = (int)request.getSession().getAttribute("flag");
        log.info("????????????"+type);
 //       List<Record> recordList = recordService.selectAllRecord();
        List<Record> recordList = recordService.selectByType(type, 0);
        log.info("????????????"+type);
        map.put("records", recordList);
        return "check";
    }

    @RequestMapping(value ="/doCheck", method = RequestMethod.POST)
    public String doCheck(HttpServletRequest request, HttpServletResponse response) {
        String radio = request.getParameter("radio");
        String description2 = request.getParameter("description");
        String user2 = (String)request.getSession().getAttribute("username");
        log.info("----"+radio );
        // 剪出
        if(radio !=null ) {
            String s1 = radio.substring(0,1);
            String pic = radio.substring(1);
            log.info("-------"+s1);
            log.info(pic);
            // s1：1代表同意，s1:2不同意
            int action = Integer.parseInt(s1);
            //更新图片对应的记录
            recordService.updateRecord(action, user2, description2, pic);
        }
        return "redirect:/record/check";
    }

    //下载请求
    /**
     * 处理图片下载请求
     * @param fileName
     * @param response
     */
    @RequestMapping("/downloadPic/{fileName}.{suffix}")
    public void downloadPicture(@PathVariable("fileName") String fileName,
                                @PathVariable("suffix") String suffix,
                                HttpServletResponse response){
        // 设置下载的响应头信息
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + fileName + "." + suffix);
        File imgFile = new File(Constants.IMG_PATH + fileName + "." + suffix);
        responseFile(response, imgFile);
    }


    /**
     * 响应输出图片文件
     * @param response
     * @param imgFile
     */
    private void responseFile(HttpServletResponse response, File imgFile) {
        try(InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream();){
            byte [] buffer = new byte[1024]; // 图片文件流缓存池
            while(is.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    @RequestMapping(value = "/download")
    public String download (Map<String,Object> map, HttpServletRequest request, HttpServletResponse response) {
 //       List<Record> recordList = recordService.selectByAction();
        int type = (int)request.getSession().getAttribute("flag");
        List<Record> recordList = recordService.selectByAction(type);
        map.put("records", recordList);
        return "download";
    }

    @RequestMapping(value = "/login")
    public String login () {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login1(HttpServletRequest request, HttpServletResponse response, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password.equals("123456")) {
           request.getSession().setAttribute("user", username);
            model.addAttribute("user", username);
            if (username.equals("user1")) {
                return "redirect:/record/upload";
            } else if (username.equals("user2")) {
                return "redirect:/record/check";
            } else {
                return "redirect:/record/download";
            }
        }else {
                return "redirect:/record/upload";
            }
    }

    @RequestMapping("/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SysUser sysUser = (SysUser)request.getSession().getAttribute("user");
        List<SysAcl> aclList = aclController(sysUser);
        for (SysAcl sysAcl: aclList
                ) {
            if (sysAcl.getUrl().contains("register")) {
                request.getSession().setAttribute("flag", 1);
                if (sysAcl.getUrl().contains("upload")) {
                    response.sendRedirect("/record/upload");
                } else if (sysAcl.getUrl().contains("check")) {
                    response.sendRedirect("/record/check");
                } else {
                    response.sendRedirect("/record/download");
                }
            }
        }
        for (SysAcl sysAcl: aclList
                ) {
                if (sysAcl.getUrl().contains("upload")) {
                    response.sendRedirect("/record/upload");
                } else if (sysAcl.getUrl().contains("check")) {
                    response.sendRedirect("/record/check");
                } else {
                    response.sendRedirect("/record/download");
                }
        }
    }

    @RequestMapping("/grant")
    public void grant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SysUser sysUser = (SysUser)request.getSession().getAttribute("user");
        List<SysAcl> aclList = aclController(sysUser);

        for (SysAcl sysAcl: aclList
                ) {
            if (sysAcl.getUrl().contains("grant")) {
                request.getSession().setAttribute("flag", 0);
                if (sysAcl.getUrl().contains("upload")) {
                    response.sendRedirect("/record/upload");
                } else if (sysAcl.getUrl().contains("check")) {
                    response.sendRedirect("/record/check");
                } else {
                    response.sendRedirect("/record/download");
                }
            }
        }
        for (SysAcl sysAcl: aclList
                ) {

                if (sysAcl.getUrl().contains("upload")) {
                    response.sendRedirect("/record/upload");
                } else if (sysAcl.getUrl().contains("check")) {
                    response.sendRedirect("/record/check");
                } else {
                    response.sendRedirect("/record/download");
                }
        }
    }

    public List<SysAcl> aclController(SysUser sysUser) {
        // userId - roleId - aclId - acl
        //获取acl
        int userId = sysUser.getId();
        log.info("============="+userId);
        List<Integer> roleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
        log.info("============="+roleIdList.get(0));
        List<Integer> aclId = sysRoleAclMapper.getAclIdListByRoleIdList(roleIdList);
        List<SysAcl> aclList = sysAclMapper.getByIdList(aclId);
        return aclList;
    }
}

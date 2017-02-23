<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.json.simple.*" %>
<%@ page import="com.krt.smartcity.common.util.FastDFSClient" %>
<%
    //定义允许上传的文件扩展名
    HashMap<String, String> extMap = new HashMap<String, String>();
    extMap.put("image", "gif,jpg,jpeg,png,bmp");
    extMap.put("flash", "swf,flv");
    extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
    extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

    long maxSize_image = 5 * 1024 * 1024; //5M
    long maxSize_flash = 20 * 1024 * 1024; //20M
    long maxSize_media = 20 * 1024 * 1024; //20M
    long maxSize_file = 20 * 1024 * 1024; //20M

    response.setContentType("text/html; charset=UTF-8");

    if (!ServletFileUpload.isMultipartContent(request)) {
        out.println(getError("请选择文件。"));
        return;
    }
    String dirName = request.getParameter("dir");
    if (dirName == null) {
        dirName = "file";
    }
    FileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    upload.setHeaderEncoding("UTF-8");
    List items = upload.parseRequest(request);
    Iterator itr = items.iterator();
    while (itr.hasNext()) {
        FileItem item = (FileItem) itr.next();
        String fileName = item.getName();
        long fileSize = item.getSize();
        if (!item.isFormField()) {
            //检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                return;
            }
            //检查文件大小
            if (item.getSize() > maxSize_image
                    && dirName.equals("image")) {
                out.println(getError("上传文件大小超过限制。"));
                return;
            } else if (item.getSize() > maxSize_flash
                    && dirName.equals("flash")) {
                out.println(getError("上传文件大小超过限制。"));
                return;
            } else if (item.getSize() > maxSize_media
                    && dirName.equals("media")) {
                out.println(getError("上传文件大小超过限制。"));
                return;
            } else if (item.getSize() > maxSize_file
                    && dirName.equals("file")) {
                out.println(getError("上传文件大小超过限制。"));
                return;
            }
            try {
                //获取内存中当前文件输入流
                FastDFSClient client = new FastDFSClient();
                InputStream in = item.getInputStream();
                String url = client.uploadFileByStream(in, fileName, fileSize);
                if (url != null && !"".equals(url)) {
                    //成功
                    JSONObject obj = new JSONObject();
                    obj.put("error", 0);
                    obj.put("url", url);
                    out.println(obj.toJSONString());
                } else {
                    //失败
                    out.println(getError("上传文件失败。"));
                }
            } catch (Exception e) {
                out.println(getError("上传文件失败。"));
                return;
            }
        }
    }
%>
<%!
    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toJSONString();
    }
%>
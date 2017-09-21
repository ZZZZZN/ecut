package com.krt.ruanjian.course.service;

import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;
import com.krt.core.util.ExcelUtil;
import com.krt.ruanjian.course.entity.Title;
import com.krt.ruanjian.course.mapper.TitleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@Service
public class DataExportService extends BaseServiceImpl<Title> {

    @Resource
    private TitleMapper titleMapper;

    @Override
    public BaseMapper<Title> getMapper() {
        return titleMapper;
    }

    /**
     * excel导出通用方法
     */
    public void stuSelDataExport(HttpServletResponse response, String fileName,
                                 List<Map> newList, String[] keys, String[] columnNames) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(newList,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }
}

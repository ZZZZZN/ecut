package com.krt.core.util;

import com.krt.admin.system.entity.User;
import com.krt.ruanjian.course.mapper.MajorMapper;
import com.krt.ruanjian.course.service.MajorService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WangXin on 2017/9/8.
 */
public class ExcelUtil {

    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ExcelUtil(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;}
    //获取总列数
    public int getTotalCells() {  return totalCells;}
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }


    /**
     * 读取教师excel表的数据
     * @param wb
     * @return
     */
    public List<User> readTeacherExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<User> userList = new ArrayList<User>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            User user = new User();
            // 循环Excel的列
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String name = String.valueOf(cell.getNumericCellValue());
                            user.setName(name.substring(0, name.length()-2>0?name.length()-2:1));//名称
                        }else{
                            user.setName(cell.getStringCellValue());//名称
                        }
                    } else if (c == 1) {
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String username = String.valueOf(cell.getNumericCellValue());
                            user.setUsername(username.substring(0, username.length()-2>0?username.length()-2:1));
                        }else{
                            user.setUsername(cell.getStringCellValue());//用户名
                        }
                    } else if (c == 2){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String password = String.valueOf(cell.getNumericCellValue());
                            user.setPassword(password.substring(0, password.length()-2>0?password.length()-2:1));//密码
                        }else{
                            user.setPassword(cell.getStringCellValue());//密码
                        }
                    }else if (c == 3){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String title_level = String.valueOf(cell.getNumericCellValue());
                            user.setTitle_level(title_level.substring(0, title_level.length()-2>0?title_level.length()-2:1));//职称
                        }else{
                            user.setTitle_level(cell.getStringCellValue());//职称
                        }
                    }else if (c == 4){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String institute = String.valueOf(cell.getNumericCellValue());
                            user.setInstitute(institute.substring(0, institute.length()-2>0?institute.length()-2:1));//学院
                        }else{
                            user.setInstitute(cell.getStringCellValue());//学院
                        }
                    }else if (c == 5){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String department = String.valueOf(cell.getNumericCellValue());
                            user.setDepartment(department.substring(0, department.length()-2>0?department.length()-2:1));//所在系
                        }else{
                            user.setDepartment(cell.getStringCellValue());//所在系
                        }
                    }else if (c == 6){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String note = String.valueOf(cell.getNumericCellValue());
                            user.setNote(note.substring(0, note.length()-2>0?note.length()-2:1));//备注
                        }else{
                            user.setNote(cell.getStringCellValue());//备注
                        }
                    }
                }
            }
            // 添加到list
            userList.add(user);
        }
        return userList;
    }

    /**
     * 读取学生excel表的数据
     * @param wb
     * @return
     */
    public List<User> readStudentExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<User> userList = new ArrayList<User>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            User user = new User();
            // 循环Excel的列
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String name = String.valueOf(cell.getNumericCellValue());
                            user.setName(name.substring(0, name.length()-2>0?name.length()-2:1));//名称
                        }else{
                            user.setName(cell.getStringCellValue());//名称
                        }
                    } else if (c == 1) {
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String username = String.valueOf(cell.getNumericCellValue());
                            user.setUsername(username.substring(0, username.length()-2>0?username.length()-2:1));
                        }else{
                            user.setUsername(cell.getStringCellValue());//用户名
                        }
                    } else if (c == 2){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String stu_class = String.valueOf(cell.getNumericCellValue());
                            user.setStu_class(stu_class.substring(0, stu_class.length()-2>0?stu_class.length()-2:1));//班级
                        }else{
                            user.setStu_class(cell.getStringCellValue());//班级
                        }
                    }else if (c == 3){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String institute = String.valueOf(cell.getNumericCellValue());
                            user.setInstitute(institute.substring(0, institute.length()-2>0?institute.length()-2:1));//学院
                        }else{
                            user.setInstitute(cell.getStringCellValue());//学院
                        }
                    }else if (c == 4){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String major = String.valueOf(cell.getNumericCellValue());
                            user.setMajor(major.substring(0, major.length()-2>0?major.length()-2:1));//专业
                        }else{
                            user.setMajor(cell.getStringCellValue());//专业
                        }
                    }else if (c == 5){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String training_site = String.valueOf(cell.getNumericCellValue());
                            user.setTraining_site(training_site.substring(0, training_site.length()-2>0?training_site.length()-2:1));//实训地点
                        }else{
                            user.setTraining_site(cell.getStringCellValue());//实训地点
                        }
                    }else if (c == 6){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String company = String.valueOf(cell.getNumericCellValue());
                            user.setCompany(company.substring(0, company.length()-2>0?company.length()-2:1));//所在企业
                        }else{
                            user.setCompany(cell.getStringCellValue());//所在企业
                        }
                    }else if (c == 7){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String note = String.valueOf(cell.getNumericCellValue());
                            user.setNote(note.substring(0, note.length()-2>0?note.length()-2:1));//备注
                        }else{
                            user.setNote(cell.getStringCellValue());//备注
                        }
                    }
                }
            }
            // 添加到list
            userList.add(user);
        }
        return userList;
    }


    public List<User> createTeacherExcel(InputStream is, boolean isExcel2003) {
        List<User> userList = new ArrayList<User>();
        try{
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            userList = readTeacherExcelValue(wb);// 读取Excel里面的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<User> createStudentExcel(InputStream is, boolean isExcel2003) {
        List<User> userList = new ArrayList<User>();
        try{
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            userList = readStudentExcelValue(wb);// 读取Excel里面的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * 读Teacher EXCEL文件，获取信息集合
     * @return
     */
    public List<User> getTeacherExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();//获取文件名
        List<User> userList = new ArrayList<User>();
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            userList = createTeacherExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * 读Student EXCEL文件，获取信息集合
     * @return
     */
    public List<User> getStudentExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();//获取文件名
        List<User> userList = new ArrayList<User>();
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            userList = createStudentExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }



    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}

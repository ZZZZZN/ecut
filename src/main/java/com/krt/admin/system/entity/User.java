package com.krt.admin.system.entity;

import com.krt.core.base.BaseEntity;

/**
 * @Description：T_user实体类
 * @date：2016-08-30
 * @version 1.0
 */
@SuppressWarnings("serial")
public class User extends BaseEntity{

	private String username;
	private String password;
	private String name;
	private String status;
	private String roleCode;//角色id
	private String stu_class;//学生班级
	private String stu_no;//学生学号
	private Integer tel;//电话
	private Integer direction;//学生方向
	private String company;//企业（学生）
	private String title_level;//职称（教师）
	private Integer title_level_num;//职称对应可带人数
	private String training_site;//实训地点（校内、校外、其他）
	private String major;//专业（学生：软件工程、网络工程、物联网工程、数字媒体技术、计算机科学与技术、通信工程）
	private String note;//备注
	private String department;//所在系（教师：软件工程系、网络工程系、数字媒体系、计算机科学与技术系、通信工程系）
	private String institute;//学院（软件学院、信工学院）

	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return username;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}
	public void setRoleCode(String roleCode){
		this.roleCode=roleCode;
	}
	public String getRoleCode(){
		return roleCode;
	}

	public String getStu_class() {
		return stu_class;
	}

	public void setStu_class(String stu_class) {
		this.stu_class = stu_class;
	}

	public String getStu_no() {
		return stu_no;
	}

	public void setStu_no(String stu_no) {
		this.stu_no = stu_no;
	}

	public Integer getTel() {
		return tel;
	}

	public void setTel(Integer tel) {
		this.tel = tel;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTitle_level() {
		return title_level;
	}

	public void setTitle_level(String title_level) {
		this.title_level = title_level;
	}

	public String getTraining_site() {
		return training_site;
	}

	public void setTraining_site(String training_site) {
		this.training_site = training_site;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public Integer getTitle_level_num() {
		return title_level_num;
	}

	public void setTitle_level_num(Integer title_level_num) {
		this.title_level_num = title_level_num;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", status='" + status + '\'' +
				", roleCode='" + roleCode + '\'' +
				", stu_class='" + stu_class + '\'' +
				", stu_no='" + stu_no + '\'' +
				", tel=" + tel +
				", direction=" + direction +
				", company='" + company + '\'' +
				", title_level='" + title_level + '\'' +
				", title_level_num=" + title_level_num +
				", training_site='" + training_site + '\'' +
				", major='" + major + '\'' +
				", note='" + note + '\'' +
				", department='" + department + '\'' +
				", institute='" + institute + '\'' +
				'}';
	}
}

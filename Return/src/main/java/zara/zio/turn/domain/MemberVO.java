package zara.zio.turn.domain;

import java.sql.Timestamp;

public class MemberVO {

	private String user_id; // ȸ�� ���̵� 
	private String user_pass; // ȸ�� �н�����
	private String user_birth; // ȸ�� ������� 
	private int user_gender; // ȸ������
	private String user_phone; // ȸ�� ���ѹ�
	private String user_email; // ȸ�� �̸���
	private Timestamp user_date; // ȸ���������� 
	private String yyyy, mm, dd; //�������
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	public String getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}
	public int getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(int user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public Timestamp getUser_date() {
		return user_date;
	}
	public void setUser_date(Timestamp user_date) {
		this.user_date = user_date;
	}
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getDd() {
		return dd;
	}
	public void setDd(String dd) {
		this.dd = dd;
	}
	
	public String toString() {
		return "( ȸ�����̵� : " + user_id + 
				" ȸ���н����� : " + user_pass + 
				" ȸ������ : " + user_birth + 
				" ȸ������ : " + user_gender + 
				" ȸ�����ѹ� : " + user_phone + 
				" ȸ���̸��� : " + user_email + 
				" ȸ���������� : " + user_date + " )";
	}
	
}

package zara.zio.turn.domain;

import java.sql.Timestamp;

public class PlaceVO {
	
	// �ּ�����
	private int place_code;
	private String place_name; // ����̸�
	private Timestamp add_date; // �߰��ȳ�¥, �����ȳ�¥
	private String place_content; // ��ҳ���
	private String place_address; // ����ּ� 
	private double place_lat; // ��� ���� 
	private double place_lng; // ��� �浵 
	private String place_type; // ��� �з� 
	private int place_on; // ��� �� ���� enable, disable
	
	
	// �̹������� 
	private int img_code; // �̹�����������
	private String place_img; // ���ϰ��
	private String file_name; // �����̸�
	
	public int getImg_code() {
		return img_code;
	}
	public void setImg_code(int img_code) {
		this.img_code = img_code;
	}
	public String getPlace_img() {
		return place_img;
	}
	public void setPlace_img(String place_img) {
		this.place_img = place_img;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public int getPlace_code() {
		return place_code;
	}
	public void setPlace_code(int place_code) {
		this.place_code = place_code;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public Timestamp getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Timestamp add_date) {
		this.add_date = add_date;
	}
	public String getPlace_content() {
		return place_content;
	}
	public void setPlace_content(String place_content) {
		this.place_content = place_content;
	}
	public String getPlace_address() {
		return place_address;
	}
	public void setPlace_address(String place_address) {
		this.place_address = place_address;
	}
	public double getPlace_lat() {
		return place_lat;
	}
	public void setPlace_lat(double place_lat) {
		this.place_lat = place_lat;
	}
	public double getPlace_lng() {
		return place_lng;
	}
	public void setPlace_lng(double place_lng) {
		this.place_lng = place_lng;
	}
	public String getPlace_type() {
		return place_type;
	}
	public void setPlace_type(String place_type) {
		this.place_type = place_type;
	}
	public int getPlace_on() {
		return place_on;
	}
	public void setPlace_on(int place_on) {
		this.place_on = place_on;
	}
	
	
	
	
//	private int place_code;
//	private String place_name; // ����̸�
//	private Timestamp add_date; // �߰��ȳ�¥, �����ȳ�¥
//	private String place_content; // ��ҳ���
//	private String place_address; // ����ּ� 
//	private double place_lat; // ��� ���� 
//	private double place_lng; // ��� �浵 
//	private String place_type; // ��� �з� 
//	private int place_on; // ��� �� ���� enable, disable
	
	
	public String toString() {
		return "( �ڵ� : " + place_code + 
				" ��ҳ��� : " + place_content + 
				" ����ּ� : " + place_address + 
				" ���� : " + place_lat + 
				" �浵 : " + place_lng + 
				" ��Һз� : " + place_type + 
				" ��ҿ¿��� : " + place_on + " )";
	}
	
}

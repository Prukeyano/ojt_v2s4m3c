package dev.mvc.showppingbasket;

public class ShowppingbasketVO {

	/* showppingno number(10) not null, --��ٱ��� ��ȣ
	    productno number(10) not null, -- ��ǰ��ȣ
	    title varchar(100) not null, -- ��ǰ ����
	    cnt number(10) not null, -- ��ǰ ����
	    price number(10) not null, -- ��ǰ ����
	    memberid varchar(100) not null, -- ��ǰ ������ ���̵�
	    rdate date DEFAULT sysdate  not null, -- ��ٱ��� �������
	    primary key(showppingno), -- �⺻Ű
	    FOREIGN key(memberid) references mem(id) -- �ܷ�Ű*/
	private int showppingno;
	private int productno;
	private String title;
	private int cnt;
	private String memberid;
	private String rdate;
	private String uri;
	private String thumb;
	private int price;
	
	public int getShowppingno() {
		return showppingno;
	}
	public void setShowppingno(int showppingno) {
		this.showppingno = showppingno;
	}
	public int getProductno() {
		return productno;
	}
	public void setProductno(int productno) {
		this.productno = productno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ShowppingbasketVO [showppingno=" + showppingno + ", productno=" + productno + ", title=" + title
				+ ", cnt=" + cnt + ", memberid=" + memberid + ", rdate=" + rdate + ", uri=" + uri + ", thumb=" + thumb
				+ ", price=" + price + "]";
	}
	
}

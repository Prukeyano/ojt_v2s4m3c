package dev.mvc.categrp;

public class CategrpVO {

  /**
   * ī�װ� ��ȣ
   */
  private int categrpno;
  /**
   * ī�װ� �̸�
   */
  private String name;
  /**
   * ī�װ� ��� ����
   */
  private int seqno;
  /**
   * ī�װ� ��� ����
   */
  private String visible;
  /**
   * ī�װ� ��� ��¥
   */
  private String rdate;
  /**
   * ī�װ� ���� ������ ����
   */
  private int cnt;
  public int getCategrpno() {
    return categrpno;
  }
  public void setCategrpno(int categrpno) {
    this.categrpno = categrpno;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getSeqno() {
    return seqno;
  }
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
  public String getVisible() {
    return visible;
  }
  public void setVisible(String visible) {
    this.visible = visible;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  
  @Override
  public String toString() {
    return "CategrpVO [categrpno=" + categrpno + ", name=" + name + ", seqno=" + seqno + ", visible=" + visible
        + ", rdate=" + rdate + ", cnt=" + cnt + "]";
  }
  
}

package dev.mvc.attachfile;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class AttachfileVO {
  
  /** ���� ��ȣ */
  private int attachfileno;
  /** �� ��ȣ(FK) */
  private int iqynum;
  /** ���� ���ϸ� */
  private String fname;
  /** ���ε�� ���ϸ� */
  private String fupname;
  /** Thumb �̹��� */
  private String thumb;
  /** ���� ũ�� */
  private long fsize;
  /** ����� */
  private String fdate;
  
  /** Form�� ������ MultipartFile�� ��ȯ�Ͽ� List�� ����  */
  private List<MultipartFile> fnamesMF;
  
  // private MultipartFile fnamesMF;  // �ϳ��� ���� ó��
  /** ���� ���� ��� */
  private String flabel;   


  public int getAttachfileno() {
    return attachfileno;
  }

  public void setAttachfileno(int attachfileno) {
    this.attachfileno = attachfileno;
  }

  public int getIqynum() {
    return iqynum;
  }

  public void setIqynum(int iqynum) {
    this.iqynum = iqynum;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getFupname() {
    return fupname;
  }

  public void setFupname(String fupname) {
    this.fupname = fupname;
  }

  public String getThumb() {
    return thumb;
  }

  public void setThumb(String thumb) {
    this.thumb = thumb;
  }

  public long getFsize() {
    return fsize;
  }

  public void setFsize(long fsize) {
    this.fsize = fsize;
  }

  public String getFdate() {
    return fdate;
  }

  public void setFdate(String fdate) {
    this.fdate = fdate;
  }

  public List<MultipartFile> getFnamesMF() {
    return fnamesMF;
  }

  public void setFnamesMF(List<MultipartFile> fnamesMF) {
    this.fnamesMF = fnamesMF;
  }

  public String getFlabel() {
    return flabel;
  }

  public void setFlabel(String flabel) {
    this.flabel = flabel;
  }

  
}


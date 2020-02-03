package dev.mvc.mem;

public class PagingVO {
  String col;
  String word;
  int memgradeno;
  int recordCount;     // ��ȸ�� ��ü ���ڵ� ��
  int recordPerPage;  // �������� ���ڵ� ��
  int pagePerBlock;   // ���� ������ �� 
  int nowPage;         // ���� ������
  
  // ��ü ������
  int totalPage; // = (int)(Math.ceil((double)recordCount/recordPerPage));
  // ������ �׷�(��ü �׷�)
  int totalGrp; // = (int)(Math.ceil((double)totalPage/pagePerBlock));       
  // ���� �׷�
  int nowGrp; // = (int)(Math.ceil( (double)nowPage / pagePerBlock ));    
  
  int startPage; // = ((nowGrp - 1) * pagePerBlock) + 1;     // ������������ ���� �׷��� ���� ������
  int endPage; // = (nowGrp * pagePerBlock);                 // ������������ ���� �׷��� �� ������

  int forPage; //  = (nowGrp-1) * pagePerBlock;  // ���� �׷��� ������������ 
  int nextPage; // = (nowGrp) * pagePerBlock + 1;    // ���� �׷��� ������������
  int startNum;
  int endNum;
  
  
  public int getMemgradeno() {
    return memgradeno;
  }
  public void setMemgradeno(int memgradeno) {
    this.memgradeno = memgradeno;
  }
  public int getStartNum() {
    return startNum;
  }
  public void setStartNum(int startNum) {
    this.startNum = startNum;
  }
  public int getEndNum() {
    return endNum;
  }
  public void setEndNum(int endNum) {
    this.endNum = endNum;
  }
  public int getRecordCount() {
    return recordCount;
  }
  public void setRecordCount(int recordCount) {
    this.recordCount = recordCount;
  }
  public int getRecordPerPage() {
    return recordPerPage;
  }
  public void setRecordPerPage(int recordPerPage) {
    this.recordPerPage = recordPerPage;
  }
  public int getPagePerBlock() {
    return pagePerBlock;
  }
  public void setPagePerBlock(int pagePerBlock) {
    this.pagePerBlock = pagePerBlock;
  }
  public int getTotalPage() {
    return totalPage;
  }
  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }
  public int getTotalGrp() {
    return totalGrp;
  }
  public void setTotalGrp(int totalGrp) {
    this.totalGrp = totalGrp;
  }
  public int getNowGrp() {
    return nowGrp;
  }
  public void setNowGrp(int nowGrp) {
    this.nowGrp = nowGrp;
  }
  public int getStartPage() {
    return startPage;
  }
  public void setStartPage(int startPage) {
    this.startPage = startPage;
  }
  public String getCol() {
    return col;
  }
  public void setCol(String col) {
    this.col = col;
  }
  public String getWord() {
    return word;
  }
  public void setWord(String word) {
    this.word = word;
  }
  public int getNowPage() {
    return nowPage;
  }
  public void setNowPage(int nowPage) {
    this.nowPage = nowPage;
  }
  public int getEndPage() {
    return endPage;
  }
  public void setEndPage(int endPage) {
    this.endPage = endPage;
  }
  public int getForPage() {
    return forPage;
  }
  public void setForPage(int forPage) {
    this.forPage = forPage;
  }
  public int getNextPage() {
    return nextPage;
  }
  public void setNextPage(int nextPage) {
    this.nextPage = nextPage;
  }
  
}

package dev.mvc.mem;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.mem.MemProc")
public class MemProc implements MemProcInter {
  @Autowired
  private MemDAOInter memDAO;

  @Override
  public List<MemVO> list_all() {
    return memDAO.list_all();
  }

  @Override
  public List<MemVO> list_by_memgradeno(int memgradeno) {
    return memDAO.list_by_memgradeno(memgradeno);
  }

  @Override
  public int count_by_memgradeno(int memgradeno) {
    return memDAO.count_by_memgradeno(memgradeno);
  }

  @Override
  public int idcheck(String id) {
    return memDAO.idcheck(id);
  }

  @Override
  public int create(MemVO memVO) {
    return memDAO.create(memVO);
  }

  @Override
  public int update_by_memgradeno(HashMap<String, Integer> map) {
    return memDAO.update_by_memgradeno(map);
  }
  
  @Override
  public int count() {
    return memDAO.count();
  }

  @Override
  public int update_passwd(HashMap<Object, Object> map) {
    return memDAO.update_passwd(map);
  }

  @Override
  public int check_passwd(HashMap<Object, Object> map) {
    return memDAO.check_passwd(map);
  }

  @Override
  public MemVO read_by_id(String id) {
    return memDAO.read_by_id(id);
  }

  @Override
  public int login(HashMap<Object, Object> map) {
    return memDAO.login(map);
  }
  
  @Override
  public MemVO read(int memno) {
    return memDAO.read(memno);
  }

  @Override
  public int update_grade(HashMap<Object, Object> map) {
    return memDAO.update_grade(map);
  }

  @Override
  public int update(MemVO memVO) {
    return memDAO.update(memVO);
  }

  @Override
  public int delete(int memno) {
    return memDAO.delete(memno);
  }

  @Override
  public List<MemVO> search_paging(PagingVO pagingVO) {
    return memDAO.search_paging(pagingVO);
  }

  @Override
  public int total_recod(PagingVO pagingVO) {
    return memDAO.total_recod(pagingVO);
  }

  // �������� �޼ҵ�
  @Override
  public int send_mail(String subject, String content, String from, String to) {
    
    
    return 0;
  }

  @Override
  public PagingVO set_pagingVO(PagingVO pagingVO) {
    
    // ����¡ ����
    int pagePerBlock = 10;    // ���� ������ ��
    
    int recordPerPage = pagingVO.getRecordPerPage(); // �������� ���ڵ� ��
    int recordCount = pagingVO.getRecordCount(); // ��ȸ�� ��ü ���ڵ� ��
    int nowPage = pagingVO.getNowPage();               // ���� ������
    
    int totalPage = (int)(Math.ceil((double)recordCount/recordPerPage)); // ��ü ������
    int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));    // ������ �׷�(��ü �׷�)       
    int nowGrp = (int)(Math.ceil( (double)nowPage / pagePerBlock )); // ���� �׷�    
    
    int startPage = ((nowGrp - 1) * pagePerBlock) + 1;     // ������������ ���� �׷��� ���� ������
    int endPage = (nowGrp * pagePerBlock);                 // ������������ ���� �׷��� �� ������
    
    int forPage  = (nowGrp-1) * pagePerBlock;        // ���� �׷��� ������������ 
    int nextPage = (nowGrp) * pagePerBlock + 1;    // ���� �׷��� ������������
    
    int startNum = (pagingVO.getNowPage() - 1)* recordPerPage + 1;
    int endNum = pagingVO.getNowPage() * recordPerPage;
    
    pagingVO.setPagePerBlock(pagePerBlock);
    pagingVO.setStartPage(startPage);
    pagingVO.setEndPage(endPage);
    pagingVO.setTotalGrp(totalGrp);
    pagingVO.setTotalPage(totalPage);
    pagingVO.setForPage(forPage);
    pagingVO.setNextPage(nextPage);
    pagingVO.setRecordCount(recordCount);
    pagingVO.setNowGrp(nowGrp);
    pagingVO.setStartNum(startNum);
    pagingVO.setEndNum(endNum);
    return pagingVO;
  }

  @Override
  public MemVO read_by_email(String email) {
    return memDAO.read_by_email(email);
  }

  @Override
  public int total_recod_by_memgradeno(PagingVO pagingVO) {
    return memDAO.total_recod_by_memgradeno(pagingVO);
  }

  @Override
  public List<MemVO> search_paging_by_memgradeno(PagingVO pagingVO) {
    return memDAO.search_paging_by_memgradeno(pagingVO);
  }
  
}

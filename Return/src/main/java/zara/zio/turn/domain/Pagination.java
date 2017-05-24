package zara.zio.turn.domain;

public class Pagination {
	private int page = 1; // ������ 
	private int recordPage = 20; 
	
	public final static int DISPLAY_PAGE_NUM = 10;
	private int startPage; // ����������
	private int endPage; // ����������
	private boolean prev; 
	private boolean next; 
	private int totalCount;
	

	public int getTotalCount() { // ��üī��Ʈ
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calculate();
	}
	
	
	
	private void calculate() {
		// TODO Auto-generated method stub
		/*
		 *  startPage, endPage, prev, next �������  
		 *  
		 */
		// ���� ������ ����
		endPage = (int)Math.ceil(page/(double)DISPLAY_PAGE_NUM)*DISPLAY_PAGE_NUM;
			
		// ��ŸƮ ������ ����
		startPage = endPage-DISPLAY_PAGE_NUM + 1;
		
		// �������� ���
		int tempEndPage = (int)Math.ceil((double)totalCount/recordPage);
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		// <, > ��ŸƮ, ���� ������ ������
		prev = startPage > 1 ? true:false;
		next = endPage*recordPage < totalCount ? true:false;
	}

	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public boolean isNext() {
		return next;
	}
	
	// ======================================================= //
	
	public int getStartRecord() { // ������ ��ȣ�� ���� ����ؼ� �ѱ��. 
		/* page�� 1�̸� 0*10 ����
		 * page�� 2�̸� 1*10 ����
		 * page�� 3�̸� 2*10 ����
		 * page�� 4�̸� 3*10 ����
		 * 
		 */	
		return (page-1)*recordPage;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page <= 0)
			this.page = 1;
		else 
			this.page = page;
	}
	public int getRecordPage() {
		return recordPage;
	}
	public void setRecordPage(int recordPage) {
		if(recordPage <= 0 || recordPage > 100)
			this.recordPage = 20;
		else 
			this.recordPage = recordPage;
	}
	
	
	@Override
	public String toString() {
		return "Pagenation [page="+ page + ", " + "recordPage=" + recordPage + "]";
		
	}
	
}

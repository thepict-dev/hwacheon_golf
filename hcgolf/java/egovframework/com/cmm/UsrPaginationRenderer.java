package egovframework.com.cmm;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import java.text.MessageFormat;


/**
 * ImagePaginationRenderer.java 클래스
 *
 * @author 서준식
 * @since 2011. 9. 16.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    -------------    ----------------------
 *   2011. 9. 16.   서준식       이미지 경로에 ContextPath추가
 *   2016. 6. 17.   장동한       표준프레임워크 v3.6 리뉴얼
 * </pre>
 */
public class UsrPaginationRenderer extends AbstractPaginationRenderer{

	public UsrPaginationRenderer() {
		firstPageLabel    = "<a href=\"#lnk\" onclick=\"{0}({1});return false; \" class=\"pager-first\" title=\"처음으로\"><span class=\"screen-out\">처음</span></a>";
        previousPageLabel = "<a href=\"#lnk\" onclick=\"{0}({1});return false; \" class=\"pager-prev\" title=\"이전\"><span class=\"screen-out\">이전</span></a>";
        currentPageLabel  = "<a href=\"#lnk\" class=\"pager-num active\"><span class=\"screen-out\">현재 페이지</span>{0}</a>";
        otherPageLabel    = "<a href=\"#lnk\" onclick=\"{0}({1});return false; \" class=\"pager-num\">{2}</a>";
        nextPageLabel     = "<a href=\"#lnk\" onclick=\"{0}({1});return false; \" class=\"pager-next\" title=\"다음\"><span class=\"screen-out\">다음</span></a>";
        lastPageLabel     = "<a href=\"#lnk\" onclick=\"{0}({1});return false; \" class=\"pager-last\" title=\"마지막으로\"><span class=\"screen-out\">마지막</span></a>";
	}

	public String renderPagination(PaginationInfo paginationInfo, String jsFunction) {
		StringBuffer strBuff = new StringBuffer();

		int firstPageNo = paginationInfo.getFirstPageNo();
		int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();
		int totalPageCount = paginationInfo.getTotalPageCount();
		int pageSize = paginationInfo.getPageSize();
		int lastPageNoOnPageList = paginationInfo.getLastPageNoOnPageList();
		int currentPageNo = paginationInfo.getCurrentPageNo();
		int lastPageNo = paginationInfo.getLastPageNo();

		if (totalPageCount > pageSize) {
			if (firstPageNoOnPageList > pageSize) {
				strBuff.append(MessageFormat.format(firstPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNo) }));
				strBuff.append(MessageFormat.format(previousPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNoOnPageList - 1) }));
			} else {
				strBuff.append(MessageFormat.format(firstPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNo) }));
				strBuff.append(MessageFormat.format(previousPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNo) }));
			}
		}

		strBuff.append("<span class=\"pager-numlist\">");
		for (int i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
			if (i == currentPageNo) {
				strBuff.append(MessageFormat.format(currentPageLabel, new Object[] { Integer.toString(i) }));
			} else {
				strBuff.append(MessageFormat.format(otherPageLabel, new Object[] { jsFunction, Integer.toString(i), Integer.toString(i) }));
			}
		}
		strBuff.append("</span>");

		if (totalPageCount > pageSize) {
			if (lastPageNoOnPageList < totalPageCount) {
				strBuff.append(MessageFormat.format(nextPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNoOnPageList + pageSize) }));
				strBuff.append(MessageFormat.format(lastPageLabel, new Object[] { jsFunction, Integer.toString(lastPageNo) }));
			} else {
				strBuff.append(MessageFormat.format(nextPageLabel, new Object[] { jsFunction, Integer.toString(lastPageNo) }));
				strBuff.append(MessageFormat.format(lastPageLabel, new Object[] { jsFunction, Integer.toString(lastPageNo) }));
			}
		}
		return strBuff.toString();
	}
}

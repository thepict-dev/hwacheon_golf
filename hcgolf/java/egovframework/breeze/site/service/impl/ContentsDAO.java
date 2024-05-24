package egovframework.breeze.site.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.breeze.site.service.ContentsVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("contentsDAO")
public class ContentsDAO extends EgovComAbstractDAO {

	public List<?> selectContentsList(ContentsVO contentsVO) {
		return list("contentsMapper.selectContentsList", contentsVO);
	}

	public int selectContentsListCnt(ContentsVO contentsVO) {
		return (Integer)selectOne("contentsMapper.selectContentsListCnt", contentsVO);
	}

	public ContentsVO selectContentsView(ContentsVO contentsVO) {
		return (ContentsVO)selectOne("contentsMapper.selectContentsView", contentsVO);
	}

	public void contentsInsert(ContentsVO contentsVO) {
		insert("contentsMapper.contentsInsert", contentsVO);
	}

	public void contentsUpdate(ContentsVO contentsVO) {
		update("contentsMapper.contentsUpdate", contentsVO);
	}

	public void contentsDelete(ContentsVO contentsVO) {
		update("contentsMapper.contentsDelete", contentsVO);
	}

	public void contentsBakInsert(ContentsVO contentsVO) {
		insert("contentsMapper.contentsBakInsert", contentsVO);
	}
	
	public List<?> selectContentsBakList(ContentsVO contentsVO) {
		return list("contentsMapper.selectContentsBakList", contentsVO);
	}

	public int selectContentsListBakCnt(ContentsVO contentsVO) {
		return (Integer)selectOne("contentsMapper.selectContentsBakListCnt", contentsVO);
	}

	public ContentsVO selectContentsBakView(ContentsVO contentsVO) {
		return (ContentsVO)selectOne("contentsMapper.selectContentsBakView", contentsVO);
	}
}

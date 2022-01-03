package co.kr.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import co.kr.vo.BoardVO;
import co.kr.vo.Criteria;
import co.kr.vo.SearchCriteria;

@Repository
public class BoardDAOimpi implements BoardDAO {

	@Inject
	private SqlSession ss;

	// 게시글 작성
	@Override
	public void write(BoardVO boardVO) throws Exception {
		ss.insert("boardMapper.insert", boardVO);
	}

	// 게시물 목록 조회
	@Override
	public List<BoardVO> list(SearchCriteria scri) throws Exception {
		
		System.out.println(scri.getRowStart()+"로우스타트.");
		System.out.println(scri.getRowEnd()+"로우앤드입니다.");
		System.out.println(scri.getKeyword()+"키워드입니다.");
		System.out.println(scri.getSearchType()+"옵션입니다.");
		return ss.selectList("boardMapper.listPage", scri);
	}
	
	//게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return ss.selectOne("boardMapper.listCount", scri);
	}

	// 게시물 조회
	@Override
	public BoardVO read(int bno) throws Exception {
		return ss.selectOne("boardMapper.read", bno);
	}

	// 게시물 수정
	@Override
	public void update(BoardVO boardVO) throws Exception {
		ss.update("boardMapper.update", boardVO);
	}

	// 게시물 삭제
	@Override
	public void delete(int bno) throws Exception {
		ss.delete("boardMapper.delete", bno);
	}
}

package co.kr.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import co.kr.vo.BoardVO;

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
	public List<BoardVO> list() throws Exception {
		return ss.selectList("boardMapper.list");
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
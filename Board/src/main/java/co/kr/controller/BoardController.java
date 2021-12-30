package co.kr.controller;

import javax.inject.Inject;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.kr.service.BoardService;
import co.kr.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	BoardService service;

	@RequestMapping("/board/writeView")
	public String writeView() throws Exception {
		logger.info("writeView");
		return "../board/writeView";
	}

	@RequestMapping("/board/write")
	public String write(BoardVO bvo) throws Exception {
		logger.info("write");
		service.write(bvo);

		return "redirect:/board/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception {
		logger.info("list");
		model.addAttribute("list", service.list());
		return "../board/list";
	}

	// 게시판 조회
	@RequestMapping(value = "/readView", method = RequestMethod.GET)
	public String read(BoardVO boardVO, Model model) throws Exception {
		logger.info("read");
		model.addAttribute("read", service.read(boardVO.getBno()));
		return "../board/readView";
	}

	// 게시판 수정뷰
	@RequestMapping(value = "/updateView", method = RequestMethod.GET)
	public String updateView(BoardVO boardVO, Model model) throws Exception {
		logger.info("updateView");
		model.addAttribute("update", service.read(boardVO.getBno()));
		return "../board/updateView";
	}

	// 게시판 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardVO boardVO) throws Exception {
		logger.info("update");
		service.update(boardVO);
		return "redirect:/board/list";
	}

	// 게시판 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(BoardVO boardVO) throws Exception {
		logger.info("delete");
		service.delete(boardVO.getBno());
		return "redirect:/board/list";
	}
}

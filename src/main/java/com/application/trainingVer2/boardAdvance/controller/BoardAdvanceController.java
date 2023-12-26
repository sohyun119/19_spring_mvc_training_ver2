package com.application.trainingVer2.boardAdvance.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.application.trainingVer2.boardAdvance.dto.MainBoardDTO;
import com.application.trainingVer2.boardAdvance.dto.ReplyDTO;
import com.application.trainingVer2.boardAdvance.service.BoardAdvanceService;


@Controller
@RequestMapping("/boardAdvance")
public class BoardAdvanceController {

	@Autowired								
	private BoardAdvanceService boardAdvanceService;		
	
	
	@GetMapping("/boardList")
	public ModelAndView boardList(@RequestParam(name="searchKeyword" , defaultValue = "total") String searchKeyword,
								  @RequestParam(name="searchWord" , defaultValue = "") String searchWord,
								  @RequestParam(name="onePageViewCnt" , defaultValue = "10")  int onePageViewCnt,
								  @RequestParam(name="currentPageNumber" , defaultValue = "1") int currentPageNumber) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardAdvance/board/boardList");
		
		
		Map<String, String> searchCntMap = new HashMap<String, String>();
		searchCntMap.put("searchKeyword", searchKeyword);
		searchCntMap.put("searchWord", searchWord);
		
		
		int allBoardCnt = boardAdvanceService.getAllBoardCnt(searchCntMap);
		
		int allPageCnt = allBoardCnt / onePageViewCnt + 1;
		
		if (allBoardCnt % onePageViewCnt == 0) allPageCnt--;
		
		int startPage = (currentPageNumber - 1)  / 10  * 10 + 1;
		if (startPage == 0) {
			startPage = 1;
		}
		
		int endPage = startPage + 9;
		
		if (endPage > allPageCnt) endPage = allPageCnt;
		
		
		int startBoardIdx = (currentPageNumber - 1) * onePageViewCnt;
		
		mv.addObject("startPage"         , startPage);
		mv.addObject("endPage"           , endPage);
		mv.addObject("allBoardCnt"   	 , allBoardCnt);
		mv.addObject("allPageCnt"   	 , allPageCnt);
		mv.addObject("onePageViewCnt"    , onePageViewCnt);
		mv.addObject("currentPageNumber" , currentPageNumber);
		mv.addObject("startBoardIdx"     , startBoardIdx);
		mv.addObject("searchKeyword"     , searchKeyword);
		mv.addObject("searchWord"        , searchWord);
		
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("onePageViewCnt" , onePageViewCnt);
		searchMap.put("startBoardIdx"  , startBoardIdx);
		searchMap.put("searchKeyword"  , searchKeyword);
		searchMap.put("searchWord"     , searchWord);
		mv.addObject("boardList"      ,  boardAdvanceService.getBoardList(searchMap));		
		
		
		return mv;
		
	}
	
	
	@GetMapping("/addBoard")
	public String addBoard(){
		return "boardAdvance/board/addBoard";
	}
	
	
	@PostMapping("/addBoard")
	public String addBoard(MainBoardDTO mainBoardDTO){
		
		boardAdvanceService.addBoard(mainBoardDTO);
		return "redirect:/boardAdvance/boardList";
		
	}
	
	
	@GetMapping("/boardDetail")
	public ModelAndView boardDetail(@RequestParam("boardId") long boardId){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardAdvance/board/boardDetail");
		mv.addObject("mainBoardDTO" , boardAdvanceService.getBoardDetail(boardId , true));
		mv.addObject("allReplyCnt" , boardAdvanceService.getAllReplyCnt(boardId));
		mv.addObject("replyList" , boardAdvanceService.getReplyList(boardId));
		
		return mv;
		
	}
	
	
	@GetMapping("/boardAuthentication")
	public ModelAndView boardAuthentication(@RequestParam("menu") String menu ,
											@RequestParam("boardId") long boardId){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardAdvance/board/boardAuthentication");
		mv.addObject("mainBoardDTO" , boardAdvanceService.getBoardDetail(boardId , true));
		mv.addObject("menu" , menu);
		
		return mv;
		
	}
	
	@PostMapping("/boardAuthentication")
	@ResponseBody
	public String boardAuthentication(@RequestParam("menu") String menu ,
									  @ModelAttribute MainBoardDTO mainBoardDTO) {
		
		String jsScript = "";
		if (boardAdvanceService.checkAuthorizedUser(mainBoardDTO)) {
			
			if (menu.equals("update")) {
				jsScript = "<script>";
				jsScript += "location.href='/boardAdvance/modifyBoard?boardId=" + mainBoardDTO.getBoardId() + "';";
				jsScript += "</script>";
			}
			else if (menu.equals("delete")) {
				jsScript = "<script>";
				jsScript += "location.href='/boardAdvance/removeBoard?boardId=" +  mainBoardDTO.getBoardId() + "';";
				jsScript += "</script>";
			}
			
		}
		else {
			 jsScript = "<script>";
			 jsScript += "alert('Check Id or Password');";
			 jsScript += "history.go(-1);";
			 jsScript += "</script>";
		}
		
		return jsScript;
	
	}
	
	
	@GetMapping("/modifyBoard")
	public ModelAndView modifyBoard(@RequestParam("boardId") long boardId){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardAdvance/board/modifyBoard");
		mv.addObject("mainBoardDTO" , boardAdvanceService.getBoardDetail(boardId , false));
		
		return mv;
		
	}
	
	
	@PostMapping("/modifyBoard")
	public String modifyBoard(MainBoardDTO mainBoardDTO) {
		boardAdvanceService.modifyBoard(mainBoardDTO);
		return "redirect:/boardAdvance/boardList";
	}
	
	
	@GetMapping("/removeBoard")
	public ModelAndView removeBoard(@RequestParam("boardId") long boardId) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardAdvance/board/removeBoard");
		mv.addObject("boardId" , boardId);
		
		return mv;
		
	}
	
	
	@PostMapping("/removeBoard")
	public String postRemoveBoard(@RequestParam("boardId") long boardId) {
		boardAdvanceService.removeBoard(boardId);
		return "redirect:/boardAdvance/boardList";
	}
	
	
	@GetMapping("/addBoardDummy")
	public String addBoardDummy(){
		boardAdvanceService.addBoardDummy();
		return "redirect:/boardAdvance/boardList";
	}
	
	
	@GetMapping("/addReply")
	public ModelAndView addReply(@RequestParam("boardId") long boardId) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardAdvance/reply/addReply");
		mv.addObject("boardId" , boardId);
		return mv;
		
	}
	
	
	@PostMapping("/addReply")
	public String addReply(@ModelAttribute ReplyDTO replyDTO){
		boardAdvanceService.addReply(replyDTO);
		return "redirect:/boardAdvance/boardDetail?boardId=" + replyDTO.getBoardId();
	}
	
	
	@GetMapping("/modifyReply")
	public ModelAndView modifyReply(@RequestParam("replyId") long replyId){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardAdvance/reply/modifyReply");
		mv.addObject("replyDTO" , boardAdvanceService.getReplyDetail(replyId));
		
		return mv;
		
	}
	
	
	@PostMapping("/modifyReply")
	@ResponseBody
	public String modifyReply(ReplyDTO replyDTO){
		
		String jsScript = "";
		if (boardAdvanceService.modifyReply(replyDTO)) {
			jsScript += "<script>";
			jsScript += "location.href='/boardAdvance/boardDetail?boardId=" + replyDTO.getBoardId() + "';";
			jsScript += "</script>";

		}
		else {
		   jsScript ="<script>"; 
		   jsScript += "alert('check your passowrd');";
		   jsScript += "history.go(-1);";
		   jsScript += "</script>";
		}
		
		return jsScript;
		
	}
	
	
	@GetMapping("/removeReply")
	public ModelAndView removeReply(@RequestParam("replyId") long replyId) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardAdvance/reply/removeReply");
		mv.addObject("replyDTO" , boardAdvanceService.getReplyDetail(replyId));
		
		return mv;
		
	}
	
	
	@PostMapping("/removeReply")
	@ResponseBody
	public String removeReply(ReplyDTO replyDTO) {
		
		String jsScript = "";
		if (boardAdvanceService.removeReply(replyDTO)) {
			jsScript += "<script>";
			jsScript += "location.href='/boardAdvance/boardDetail?boardId=" + replyDTO.getBoardId() + "';";
			jsScript += "</script>";
		}
		else {
		   jsScript ="<script>"; 
		   jsScript += "alert('check your password');";
		   jsScript += "history.go(-1);";
		   jsScript += "</script>";
		}
		
		return jsScript;
		
	}
	
}

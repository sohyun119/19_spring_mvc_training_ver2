package com.application.trainingVer2.boardAdvance.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.trainingVer2.boardAdvance.dao.BoardAdvanceDAO;
import com.application.trainingVer2.boardAdvance.dto.MainBoardDTO;
import com.application.trainingVer2.boardAdvance.dto.ReplyDTO;

@Service		
public class BoardAdvanceServiceImpl implements BoardAdvanceService {

	@Autowired				
	private BoardAdvanceDAO boardAdvanceDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static Logger logger = LoggerFactory.getLogger(BoardAdvanceServiceImpl.class);
	
	@Override
	public List<MainBoardDTO> getBoardList(Map<String, Object> searchMap){
		return boardAdvanceDAO.getBoardList(searchMap);
	}
	 
	@Override
	public int getAllBoardCnt(Map<String, String> searchCntMap) {
		return boardAdvanceDAO.getAllBoardCnt(searchCntMap);
	}

	@Override
	@Transactional
	public MainBoardDTO getBoardDetail(long boardId, boolean isIncreaseReadCnt) {
		if(isIncreaseReadCnt) {
			boardAdvanceDAO.updateReadCnt(boardId);
		}
		return  boardAdvanceDAO.getBoardDetail(boardId);
	}	
	
	@Override
	public boolean checkAuthorizedUser(MainBoardDTO mainBoardDTO) {
		boolean isAuthorizedUser = false;
		if (passwordEncoder.matches(mainBoardDTO.getPasswd(), boardAdvanceDAO.getPasswd(mainBoardDTO.getBoardId()))){
			isAuthorizedUser = true; 
		}
		return isAuthorizedUser;
	}
	
	
	@Override
	public void addBoard(MainBoardDTO mainBoardDTO) {
		mainBoardDTO.setPasswd(passwordEncoder.encode(mainBoardDTO.getPasswd()));
		boardAdvanceDAO.insertBoard(mainBoardDTO);
	}
	
	@Override
	public void modifyBoard(MainBoardDTO mainBoardDTO) {
		boardAdvanceDAO.updateBoard(mainBoardDTO);
	}

	@Override
	public void removeBoard(long boardId) {
		boardAdvanceDAO.deleteBoard(boardId);
	}

	@Override
	public void addBoardDummy() {
		
		Random ran = new Random();
		
		List<MainBoardDTO> dummyBoardList = new ArrayList<MainBoardDTO>();
		
		String[] word = {"가","나","다","라","마","바","사","아","자","차","카","타","파","하"};
		
		for (int i = 1001; i < 1301; i++) {
			
			String writer    = "";
			String subject   = "";
			String content   = "";
			for (int j = 0; j < 7; j++) {
				writer  += word[ran.nextInt(word.length)];
				subject += word[ran.nextInt(word.length)];
				content += word[ran.nextInt(word.length)];
			}
			
			MainBoardDTO temp = new MainBoardDTO();
			temp.setBoardId(i);		
			temp.setWriter(writer);
			temp.setSubject(subject);
			temp.setPasswd(passwordEncoder.encode("1111"));
			temp.setContent(content);
			
			dummyBoardList.add(temp);
			
		}
		
		boardAdvanceDAO.insertBoardDummy(dummyBoardList);
		
	}
	
	@Override
	public List<ReplyDTO> getReplyList(long boardId) {
		return boardAdvanceDAO.getReplyList(boardId);
	}
	
	@Override
	public int getAllReplyCnt(long boardId) {
		return boardAdvanceDAO.selectOneAllReplyCnt(boardId);
	}
	
	@Override
	public ReplyDTO getReplyDetail(long replyId) {
		return boardAdvanceDAO.getReplyDetail(replyId);
	}
	
	@Override
	public void addReply(ReplyDTO replyDTO) {
		replyDTO.setPasswd(passwordEncoder.encode(replyDTO.getPasswd()));
		boardAdvanceDAO.insertReply(replyDTO);
	}

	@Override
	public boolean modifyReply(ReplyDTO replyDTO) {
		if (passwordEncoder.matches(replyDTO.getPasswd() , boardAdvanceDAO.validateReplyUserCheck(replyDTO.getReplyId()))) {
			boardAdvanceDAO.updateReply(replyDTO);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeReply(ReplyDTO replyDTO) {
		if (passwordEncoder.matches(replyDTO.getPasswd() , boardAdvanceDAO.validateReplyUserCheck(replyDTO.getReplyId()))) {
			boardAdvanceDAO.deleteReply(replyDTO.getReplyId());
			return true;
		}
		return false;
	}

	@Override
	@Scheduled(cron="59 59 23 * * *")
	public void getTodayEnrolledBoardAndReplyCnt() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());
		
		logger.info("(" + today + ") 오늘의 게시글 등록수 : " + boardAdvanceDAO.getTodayEnrolledBoardCnt(today));
		logger.info("(" + today + ") 오늘의 댓글 등록수 : " + boardAdvanceDAO.getTodayEnrolledReplyCnt(today));
		
	}

	
}

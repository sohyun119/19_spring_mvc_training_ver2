package com.application.trainingVer2.boardAdvance.service;

import java.util.List;
import java.util.Map;

import com.application.trainingVer2.boardAdvance.dto.MainBoardDTO;
import com.application.trainingVer2.boardAdvance.dto.ReplyDTO;

public interface BoardAdvanceService {
	
	public List<MainBoardDTO> getBoardList(Map<String, Object> searchMap);
	public int getAllBoardCnt(Map<String, String> searchCntMap);
	public MainBoardDTO getBoardDetail(long boardId , boolean isIncreaseReadCnt);
	public void addBoard(MainBoardDTO mainBoardDTO);
	public boolean checkAuthorizedUser(MainBoardDTO mainBoardDTO);
	public void modifyBoard(MainBoardDTO mainBoardDTO);
	public void removeBoard(long boardId);
	public void addBoardDummy();
	
	public List<ReplyDTO> getReplyList(long boardId);
	public int getAllReplyCnt(long boardId);
	public ReplyDTO getReplyDetail(long replyId);
	public void addReply(ReplyDTO replyDTO);
	public boolean modifyReply(ReplyDTO replyDTO);
	public boolean removeReply(ReplyDTO replyDTO);
	
	public void getTodayEnrolledBoardAndReplyCnt();
	
}

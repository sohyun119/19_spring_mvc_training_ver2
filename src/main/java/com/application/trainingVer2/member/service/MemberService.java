package com.application.trainingVer2.member.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.application.trainingVer2.member.dto.MemberDTO;

public interface MemberService {

	public void addMember(MultipartFile uploadProfile , MemberDTO memberDTO) throws IllegalStateException, IOException;	
	public String checkValidId(String memberId) ;
	public boolean loginMember(MemberDTO memberDTO) ;
	public MemberDTO getMemberDetail(String memberId) ;
	public void modifyMember(MultipartFile uploadProfile , MemberDTO memberDTO) throws IllegalStateException, IOException;
	public void modifyInactiveMember(String memberId) ;
	
	public List<MemberDTO> getMemberList() ;
	public List<MemberDTO> getMemberSearchList(Map<String,String> searchMap) ;
	
	public void getTodayNewMemberCnt();
	public void deleteMemberScheduler();
	
}

package com.application.trainingVer2.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.application.trainingVer2.member.dto.MemberDTO;

@Mapper
public interface MemberDAO {

	public void insertMember(MemberDTO memberDTO);
	public String selectOneCheckValidId(String memberId);
	public MemberDTO selectOneloginMember(String string);
	public MemberDTO selectOneMember(String memberId);
	public void updateMember(MemberDTO memberDTO);
	public void updateInactiveMember(String memberId);
	
	public List<MemberDTO> selectListMember();
	public List<MemberDTO> selectListSearchMember(Map<String,String> searchMap);
	
	public int selectOneTodayNewMemberCnt(String today);
	public List<MemberDTO> selectListInActiveMember();
	public void deleteMember(String memberId);
	
}

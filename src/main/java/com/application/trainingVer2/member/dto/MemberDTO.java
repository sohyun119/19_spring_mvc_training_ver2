package com.application.trainingVer2.member.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class MemberDTO {

	private String memberId;
	private String passwd;
	private String profileOriginalName;
	private String profileUUID;
	private String memberNm;
	private String sex;
	private String birthAt;
	private String hp;
	private String smsstsYn;
	private String email;
	private String emailstsYn;
	private String zipcode;
	private String roadAddress;
	private String jibunAddress;
	private String namujiAddress;
	private String etc;
	private String activeYn;
	private Date inactiveAt;
	private Date joinAt;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getProfileOriginalName() {
		return profileOriginalName;
	}
	public void setProfileOriginalName(String profileOriginalName) {
		this.profileOriginalName = profileOriginalName;
	}
	public String getProfileUUID() {
		return profileUUID;
	}
	public void setProfileUUID(String profileUUID) {
		this.profileUUID = profileUUID;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthAt() {
		return birthAt;
	}
	public void setBirthAt(String birthAt) {
		this.birthAt = birthAt;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getSmsstsYn() {
		return smsstsYn;
	}
	public void setSmsstsYn(String smsstsYn) {
		this.smsstsYn = smsstsYn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailstsYn() {
		return emailstsYn;
	}
	public void setEmailstsYn(String emailstsYn) {
		this.emailstsYn = emailstsYn;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getRoadAddress() {
		return roadAddress;
	}
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}
	public String getJibunAddress() {
		return jibunAddress;
	}
	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
	}
	public String getNamujiAddress() {
		return namujiAddress;
	}
	public void setNamujiAddress(String namujiAddress) {
		this.namujiAddress = namujiAddress;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	public Date getInactiveAt() {
		return inactiveAt;
	}
	public void setInactiveAt(Date inactiveAt) {
		this.inactiveAt = inactiveAt;
	}
	public Date getJoinAt() {
		return joinAt;
	}
	public void setJoinAt(Date joinAt) {
		this.joinAt = joinAt;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [memberId=" + memberId + ", passwd=" + passwd + ", profileOriginalName=" + profileOriginalName
				+ ", profileUUID=" + profileUUID + ", memberNm=" + memberNm + ", sex=" + sex + ", birthAt=" + birthAt
				+ ", hp=" + hp + ", smsstsYn=" + smsstsYn + ", email=" + email + ", emailstsYn=" + emailstsYn
				+ ", zipcode=" + zipcode + ", roadAddress=" + roadAddress + ", jibunAddress=" + jibunAddress
				+ ", namujiAddress=" + namujiAddress + ", etc=" + etc + ", activeYn=" + activeYn + ", inactiveAt="
				+ inactiveAt + ", joinAt=" + joinAt + "]";
	}
	
}

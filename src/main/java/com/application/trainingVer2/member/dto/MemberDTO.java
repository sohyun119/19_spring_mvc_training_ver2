package com.application.trainingVer2.member.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/*

	# 롬복(lombok)
	
	- 롬복은 반복적이고 번거로운 코드를 줄이고, 개발자가 간편하게 자바 빈(Java Bean) 클래스를 작성할 수 있도록 도와주는 라이브러리입니다.
	- 롬복은 주로 어노테이션(Annotation)을 활용하여 코드를 자동으로 생성해주는 기능을 제공합니다. 
	- @Data
		@Data 어노테이션 사용시 
		Getter와 , Setter , toString() , 기본 생성자 , equals() , hashCode()가 모두 추가되어
		코드의 가독성을 높이고 유지보수를 용이하게 만들 수 있습니다.
	
	- 구현방법
	
		 1) build.gradle파일에 lombok 의존성 추가(프로젝트 생성시 선택가능)
		    implementation 'org.projectlombok:lombok'
		 
		 @) 구현이 안될 경우
			Help menu > Install new software > (Add) https://projectlombok.org/p2를 설치하면 롬복을 구현할 수 있습니다.
		
*/
@Data
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
	
}

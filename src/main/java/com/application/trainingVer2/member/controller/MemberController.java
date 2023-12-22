package com.application.trainingVer2.member.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.application.trainingVer2.member.dto.MemberDTO;
import com.application.trainingVer2.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/member")
public class MemberController {

	@Value("${file.repo.path}")
    private String fileRepositoryPath;
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/main")
	public ModelAndView main()  {
		return new ModelAndView("member/main");
	}
	
	
	@GetMapping("/register")
	public ModelAndView register() {
		return new ModelAndView("member/register");
	}
	
	
	@PostMapping("/register")
	public String register(@RequestParam("uploadProfile") MultipartFile uploadProfile , @ModelAttribute MemberDTO memberDTO) throws IllegalStateException, IOException  {
		memberService.addMember(uploadProfile, memberDTO);
		return "redirect:main";
	}
	
	
	@PostMapping("/validId")
	@ResponseBody
	public String validId(@RequestParam("memberId") String memberId) {
		return memberService.checkValidId(memberId);
	}
	
	
	@GetMapping("/login")
	public ModelAndView loginMember()  {
		return new ModelAndView("member/login");
	}
	
	
	@PostMapping("/login")
	@ResponseBody
	public String loginMember(MemberDTO memberDTO , HttpServletRequest request) {
		
		String isValidMember = "n";
		if (memberService.loginMember(memberDTO)) {
			
			HttpSession session = request.getSession();
			session.setAttribute("memberId", memberDTO.getMemberId());
			
			isValidMember = "y";
			
		} 
		
		return isValidMember;
		
	}
	
	
	@GetMapping("/logout")
	public String logoutMember(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:main";
		
	}
	
	
	@GetMapping("/modify")
	public ModelAndView modify(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/modify");
		mv.addObject("memberDTO" , memberService.getMemberDetail((String)session.getAttribute("memberId")));
		
		return mv;
		
	}	
	
	
	@GetMapping("/thumbnails")
    @ResponseBody
    public Resource thumbnails(@RequestParam("fileName") String fileName) throws IOException{
        return new UrlResource("file:" + fileRepositoryPath + fileName);
    }
	
	
	@PostMapping("/modify")
	public String modify(@RequestParam("uploadProfile") MultipartFile uploadProfile , @ModelAttribute MemberDTO memberDTO) throws IllegalStateException, IOException  {
		
		if (memberDTO.getSmsstsYn() == null)   memberDTO.setSmsstsYn("n");
		if (memberDTO.getEmailstsYn() == null) memberDTO.setEmailstsYn("n");
		
		memberService.modifyMember(uploadProfile , memberDTO);
		
		return "redirect:main";
		
	}
	
	
	@GetMapping("/remove")
	public ModelAndView remove() {
		return new ModelAndView("member/remove");
	}
	
	
	@PostMapping("/remove")
	public String remove(HttpServletRequest request)  {
		
		HttpSession session = request.getSession();
		memberService.modifyInactiveMember((String)session.getAttribute("memberId"));
		
		session.invalidate();
		
		return "redirect:main";
		
	}
	
	
	@GetMapping("/admin")
	public ModelAndView memberList()  {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("member/admin");
		mv.addObject("memberList" , memberService.getMemberList());
		
		return mv;
	
	}	
	
	
	// 엑셀 export 컨트롤러
	@GetMapping("/excelExport")
	public void memberExcelExport(HttpServletResponse response , @RequestParam Map<String,String> searchMap) {
		  
		try {
			 
			//파일명
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
			String fileName = sdf.format(new Date()) + "_memberList.xls";
			
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("회원리스트");
			
			// 헤더
			String[] header = {"회원아이디", "회원이름", "가입일", "활성화", "문자수신동의" , "메일수신동의"};
			Row row = sheet.createRow(0);
			for (int i = 0; i < header.length; i++) {
			    Cell cell = row.createCell(i);
			    cell.setCellValue(header[i]);
			}

			List<MemberDTO> memberList = memberService.getMemberSearchList(searchMap);
			
			int rowIdx = 1;
			for (MemberDTO memberDTO : memberList) {
				
				row = sheet.createRow(rowIdx++);  

                Cell cell = null;
                cell = row.createCell(0);
                cell.setCellValue(memberDTO.getMemberId());

                cell = row.createCell(1);
                cell.setCellValue(memberDTO.getMemberNm());

                cell = row.createCell(2);
                cell.setCellValue(memberDTO.getJoinAt());

                cell = row.createCell(3);
                cell.setCellValue(memberDTO.getActiveYn());

                cell = row.createCell(4);
                cell.setCellValue(memberDTO.getSmsstsYn());
                
                cell = row.createCell(5);
                cell.setCellValue(memberDTO.getEmailstsYn());
                
			}

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8")+".xlsx");
			
			workbook.write(response.getOutputStream());
			workbook.close();
		
		} catch(IOException e) {
		    e.printStackTrace();
		}
		 
		
	}
	
	
	@GetMapping("/searchMemberList")
	@ResponseBody 
	public List<MemberDTO> searchMemberList(@RequestParam Map<String,String> searchMap) {
		return memberService.getMemberSearchList(searchMap);
	}
	
}

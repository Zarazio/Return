package zara.zio.turn;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils; // ���� �����͸� �д� ���̺귯�� 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import zara.zio.turn.domain.Pagination;
import zara.zio.turn.domain.PlaceVO;
import zara.zio.turn.persistence.PlaceService;
import zara.zio.turn.util.MediaUtils;
import zara.zio.turn.util.UploadFileUtils;

@Controller
public class AdminController {
	
	private static final Logger logger =
			LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	private PlaceService service;
	
	// ��� ���� path
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping(value="/uploadSet", method=RequestMethod.POST) 
	public String uploadSet(@RequestParam(value="no", defaultValue="-1") int no, String [] cookieFile, PlaceVO placeVO, 
		@ModelAttribute Pagination pagination, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String [] maps = request.getParameterValues("files");
		
		String text = placeVO.getPlace_content().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		placeVO.setPlace_content(text);
				
		if(cookieFile != null) {
			service.pimg_delete(no); // db���� ����
			for(int i=0; i<cookieFile.length; i++) {
				deleteFile(cookieFile[i]); // ���ε� ��λ��� 
			}
		}
		
		service.place_update(placeVO, no); // ����������Ʈ
		System.out.println(placeVO);
		
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		int subr = 0;
		
		for(int i=0; i<maps.length; i++) {
			PlaceVO place = new PlaceVO();
			subr = maps[i].indexOf("_") + 1; // ���ڿ��ڸ���
			
			place.setPlace_code(no);
			place.setImg_code(i);
			place.setPlace_img(maps[i]);
			place.setFile_name(maps[i].substring(subr));
			System.out.println("���� : "+ maps[i].substring(subr));
			
			list.add(place);
		}
		service.pimg_delete(no); // db���� ����
		service.img_insert(list); // �̹��� ������ 
		
		// �ۼ��� ��� �̵� 
		PlaceVO place = service.read(no); // �ڹٺ� ��ü�� ��ȯ
		list = service.readimg(no);
		model.addAttribute("place", place); // �ش�������ȯ 
		model.addAttribute("list", list);// �̹��� ����Ʈ ��ȯ
		
		return "adminPage/uploadRead";
	}
	
	@RequestMapping(value="/uploadSet", method=RequestMethod.GET)
	public String uploadSet(@RequestParam(value="no", defaultValue="-1") int no, @ModelAttribute Pagination pagination, Model model) throws Exception {
		
		PlaceVO place = service.read(no); // �ڹٺ� ��ü�� ��ȯ
		List<PlaceVO> list = service.readimg(no);
		model.addAttribute("place", place);
		model.addAttribute("list", list);
		
		return "adminPage/uploadSet";
	}
	
	@RequestMapping(value="/uploadDel", method=RequestMethod.GET)
	public String uploadDel(String [] imgDel, int no) throws Exception{
		
		if(imgDel != null) { // �̹������� ������ // if������ ������ exception����    
			for(int i=0; i<imgDel.length; i++) { 
				deleteFile(imgDel[i]); // �������� �̹����� ����
			}
		} // �̹������� ������ �ǳʶ� 
		// �̹��� ������ ������ ������� ����
		service.placeAll_delete(no);
		
		return "redirect:uploadList";
	}
	
	@RequestMapping(value="/uploadRead", method=RequestMethod.GET)
	public String uploadRead(@RequestParam(value="no", defaultValue="-1") int no, @ModelAttribute Pagination pagination, Model model) throws Exception {
		// (read?bno=?? ��� �ּҷ� �����Ѵ�.) 
		PlaceVO place = service.read(no); // �ڹٺ� ��ü�� ��ȯ
		List<PlaceVO> list = service.readimg(no);
		model.addAttribute("place", place); // �ش�������ȯ 
		model.addAttribute("list", list);// �̹��� ����Ʈ ��ȯ
		return "adminPage/uploadRead";
		
	}
	
	// ���ε�� ������ ����Ʈ ����
	@RequestMapping(value="/uploadList", method=RequestMethod.GET)
	public String uploadList(Model model, Pagination pagination) throws Exception {
		
		System.out.println(pagination);
		
		List<PlaceVO> list = service.listPage(pagination);
		
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		
		int totalCount = service.getTotalCount();
		
		pagination.setTotalCount(totalCount); // pagination ���
		
		return "adminPage/uploadList";
	}
	
	// Ajax���ε� ������ ����
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public String uploadAjax() {
		return "adminPage/upload";
//		return "adminPage/handlebarTemple";
	}
	
	// Ajax���ε� ó�� ����
    // ������ �ѱ�ó�� : produces="text/plain;charset=utf-8"
	@RequestMapping(value="/upload", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		
		logger.info("OriginalName : " + file.getOriginalFilename()); // �����̸� 
//		logger.info("Size : " + file.getSize()); // ���ϻ����� 
//		logger.info("ContentType : " + file.getContentType()); // ����Ÿ�� jpg, png
		
		return new ResponseEntity<String>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
	}
	
	
	// �̹��� ǥ�� ����
	@ResponseBody
	@RequestMapping("/displayFile") 
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		// ������ ������ �ٿ�ε��ϱ� ���� ��Ʈ��
		InputStream in = null; // java.io
		ResponseEntity<byte[]> entity = null;
		
		logger.info("Display FILE NAME : " + fileName);
		
		try {
			// Ȯ���ڸ� �����Ͽ� formatName�� ����
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			
			// ������ Ȯ���ڸ� MediaUtilsŬ��������  �̹������Ͽ��θ� �˻��ϰ� ���Ϲ޾� mType�� ����
			MediaType mType = MediaUtils.getMediaType(formatName);
			
			// ��� ���� ��ü(�ܺο��� �����͸� �ְ���� ������ header�� body�� �����ؾ��ϱ� ������)
			HttpHeaders headers = new HttpHeaders();
			
			 // InputStream ����
			in = new FileInputStream(uploadPath+fileName);
			
			if(mType != null) { // �̹��� �����϶� 
				headers.setContentType(mType);
			} else { // �̹��������� �ƴҶ�
				fileName = fileName.substring(fileName.indexOf("_")+1);
				
				// �ٿ�ε�� ����Ʈ Ÿ������ application/octet-stream 
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				
				// ����Ʈ�迭�� ��Ʈ������ : 
				// new String(fileName.getBytes("utf-8"),"iso-8859-1") * iso-8859-1 ���������, ū ����ǥ ���ο�  " \" ���� \" "
                // ������ �ѱ� ���� ����
				headers.add("Content-Disposition", "attachment; filename=\"" + 
					new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\""); 
				//headers.add("Content-Disposition", "attachment; filename='"+fileName+"'");
			}
			
			// ����Ʈ �迭, ���, HTTP �����ڵ� 
			// ������Ͽ��� �����͸� �о�� IOUtils�� toByteArray()�޼ҵ� 
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED); 
				
		} catch(Exception e) {
			e.printStackTrace();
			
			// HTTP���� �ڵ�()
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	// ÷������ ���� ���� 
	@ResponseBody
	@RequestMapping(value="/deleteFile", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {
		
		logger.info("delete file : " + fileName);
		
		// ������ Ȯ���� ����
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		// �̹��� ���� ���� �˻�
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		// �̹����� ���(����� + �������� ����), �̹����� �ƴϸ� �������ϸ� ����
        // �̹��� �����̸�
		if(mType != null) {
			String che = "/" + fileName.substring(3);
			// ����� �̹��� ����
			
			new File(uploadPath + (che).replace('/', File.separatorChar)).delete();
		} 
		// ���� ���� ����

		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		// �����Ϳ� http ���� �ڵ� ����
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/uploadForm" , method=RequestMethod.POST) 
	public String uploadForm(PlaceVO placeVO, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String [] maps = request.getParameterValues("files");
		
		String text = placeVO.getPlace_content().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		placeVO.setPlace_content(text);
		
		service.place_insert(placeVO); // ���� ����    
		int max = service.place_max();// �ƽ����� �޾ƿ�
		
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		int subr = 0;
		
		for(int i=0; i<maps.length; i++) {
			PlaceVO place = new PlaceVO();
			subr = maps[i].indexOf("_") + 1; // ���ڿ��ڸ���
			
			place.setPlace_code(max);
			place.setImg_code(i);
			place.setPlace_img(maps[i]);
			place.setFile_name(maps[i].substring(subr));
			System.out.println("���� : "+ maps[i].substring(subr));
			
			list.add(place);
		}
		service.img_insert(list); // �̹��� ���� 
		
		
		return "redirect:uploadList"; // ����Ʈ�� ����.
	}

}

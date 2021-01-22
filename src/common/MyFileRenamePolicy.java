package common;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originFile) {	// 저장할 파일
		
		long currentTime = System.currentTimeMillis();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		int ranNum = (int)(Math.random()*100000);
		
		String name = originFile.getName();
		String ext = null;
		int dot = name.lastIndexOf(".");
		if(dot != -1) {
			ext = name.substring(dot);
		} else { 
			ext = "";
		}
		
		String fileName = sdf.format(new Date(currentTime)) + ranNum + "TCHD" + ext;
		File newFile = new File(originFile.getParent(), fileName);	// 원래 파일명에 바뀐 파일명 넣어줌
		
		return newFile;
	}

} // class end

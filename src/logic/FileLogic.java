package logic;

import javax.servlet.http.Part;

public class FileLogic {
	private String absolute;
	private String relative;
	private String fileName;

	public String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }


	public String setAbsolutePass(String fileName,int familyId) {
		String absoluteBase = "C:/pleiades/workspace/TestWeb01/WebContent/upload/family_"+familyId+"/";

		TimeLogic time = new TimeLogic();
		String now = time.nowCalendar();
		String url = absoluteBase + now +"-"+ fileName;


		return url;
	}
	public String setRelativePath (String fileName,int familyId) {
		String relativeBase = "upload/family_"+familyId+"/";

		TimeLogic time = new TimeLogic();
		String now = time.nowCalendar();
		String url = relativeBase + now +"-"+ fileName;


		return url;
	}
	public String getAbsolute() {
		return absolute;
	}

	public void setAbsolute(String absolute) {
		this.absolute = absolute;
	}

	public String getRelative() {
		return relative;
	}

	public void setRelative(String relative) {
		this.relative = relative;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}

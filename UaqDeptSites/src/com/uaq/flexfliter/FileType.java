package com.uaq.flexfliter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FileType {
	private static final Map<String,String> file2mt;
	private String filetype;
	private String mimetype;
	private String filename;
	private String fileext;

	public FileType(String inval) {
		setFilename(inval);
		setFiletype(getFileType(inval));
		setFileext(inval);
		setMimetype(getMime(getExt(inval)));
	}

	public String getFiletype() {
		return this.filetype;
	}

	private void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getFilename() {
		return this.filename;
	}

	private void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileext() {
		return this.fileext;
	}

	private void setFileext(String fileext) {
		this.fileext = getExt(fileext);
	}

	public String getMimetype() {
		return this.mimetype;
	}

	private void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	private String getFileType(String s1) {
		int i = s1.length();
		int j = s1.lastIndexOf(".") + 1;
		String s2 = s1.substring(j, i);
		String s3;
		if (s2.equalsIgnoreCase("DOC")) {
			s3 = "Word Document";
		} else {
			if (s2.equalsIgnoreCase("xls")) {
				s3 = "Excel Spreadsheet";
			} else {
				if (s2.equalsIgnoreCase("pdf")) {
					s3 = "PDF";
				} else {
					if (s2.equalsIgnoreCase("HTML")) {
						s3 = "HTML";
					} else {
						if (s2.equalsIgnoreCase("gif")) {
							s3 = "GIF";
						} else {
							if (s2.equalsIgnoreCase("swf")) {
								s3 = "Flash";
							} else {
								if (s2.equalsIgnoreCase("jpg")) {
									s3 = "JPG";
								} else {
									if (s2.equalsIgnoreCase("psd")) {
										s3 = "Photoshop";
									} else {
										if (s2.equalsIgnoreCase("ppt")) {
											s3 = "PowerPoint Presentation";
										} else {
											if (s2.equalsIgnoreCase("mov"))
												s3 = "Quicktime Movie";
											else
												s3 = "Unknown filetype";
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return s3;
	}

	private String getExt(String s1) {
		int i = s1.length();
		int j = s1.lastIndexOf(".") + 1;
		return s1.substring(j, i);
	}

	private String getMime(String x) {
		String mapped = (String) file2mt.get(x.toLowerCase());
		return mapped;
	}

	static {
		Map<String,String> m = new HashMap<String,String>();
		m.put("pdf", "application/pdf");
		m.put("latex", "application/x-latex");
		m.put("ltx", "application/x-latex");
		m.put("tcl", "application/x-tcl");
		m.put("tex", "application/x-tex");
		m.put("man", "application/x-troff-man");
		m.put("mif", "application/x-framemaker");
		m.put("pgp", "application/pgp");
		m.put("asc", "application/pgp");
		m.put("hqx", "application/mac");
		m.put("sit", "application/x-stuffit");
		m.put("ps", "application/postscript");
		m.put("eps", "application/postscript");
		m.put("ai", "application/postscript");
		m.put("exe", "application/x-msdownload");
		m.put("dot", "application/msword");
		m.put("wiz", "application/msword");
		m.put("wzs", "application/msword");
		m.put("doc", "application/msword");
		m.put("rtf", "application/rtf");
		m.put("rtx", "text/richtext");
		m.put("xl", "application/vnd.ms-excel");
		m.put("xla", "application/vnd.ms-excel");
		m.put("xlb", "application/vnd.ms-excel");
		m.put("xlc", "application/vnd.ms-excel");
		m.put("xld", "application/vnd.ms-excel");
		m.put("xlk", "application/vnd.ms-excel");
		m.put("xll", "application/vnd.ms-excel");
		m.put("xlm", "application/vnd.ms-excel");
		m.put("xls", "application/vnd.ms-excel");
		m.put("xlt", "application/vnd.ms-excel");
		m.put("xlv", "application/vnd.ms-excel");
		m.put("xlw", "application/vnd.ms-excel");
		m.put("xls", "application/vnd.ms-excel");
		m.put("pot", "application/vnd.ms-powerpoint");
		m.put("ppa", "application/vnd.ms-powerpoint");
		m.put("pps", "application/vnd.ms-powerpoint");
		m.put("pwz", "application/vnd.ms-powerpoint");
		m.put("ppt", "application/vnd.ms-powerpoint");
		m.put("mpp", "application/vnd.ms-project");
		m.put("wcp", "application/vnd.ms-works");
		m.put("wdb", "application/vnd.ms-works");
		m.put("wks", "application/vnd.ms-works");
		m.put("wps", "application/vnd.ms-works");
		m.put("hlp", "application/winhlp");
		m.put("mdb", "application/x-msaccess");
		m.put("crd", "application/x-mscardfile");
		m.put("clp", "application/x-msclip");
		m.put("m13", "application/x-msmediaview");
		m.put("m14", "application/x-msmediaview");
		m.put("mvb", "application/x-msmediaview");
		m.put("wmf", "application/x-msmetafile");
		m.put("mny", "application/x-msmoney");
		m.put("pub", "application/x-mspublisher");
		m.put("scd", "application/x-msschedule");
		m.put("trm", "application/x-msterminal");
		m.put("wri", "application/x-mswrite");
		m.put("smi", "application/smil");
		m.put("smil", "application/smil");
		m.put("wp4", "application/wordperfect5.1");
		m.put("wp5", "application/wordperfect5.1");
		m.put("wp6", "application/wordperfect5.1");
		m.put("wp", "application/wordperfect5.1");
		m.put("wkb", "application/wordperfect5.1");
		m.put("wpd", "application/wordperfect5.1");
		m.put("htm", "text/html");
		m.put("txt", "text/plain");
		m.put("css", "text/css");
		m.put("xml", "text/xml");
		m.put("html", "text/html");
		m.put("js", "application/x-javascript");
		m.put("sgml", "text/sgml");
		m.put("sgm", "text/sgml");
		m.put("jpg", "image/jpeg");
		m.put("jpeg", "image/jpe");
		m.put("jpe", "image/jpe");
		m.put("bmp", "image/x-windows-bm");
		m.put("png", "image/png");
		m.put("gif", "image/gif");
		m.put("tif", "image/tiff");
		m.put("tiff", "image/tiff");
		m.put("wav", "audio.wav");
		m.put("avi", "video/x-msvideo");
		m.put("mpeg", "video/mpeg");
		m.put("mpg", "video/mpeg");
		m.put("mpe", "video/mpeg");
		m.put("m2v", "video/mpeg");
		m.put("m1v", "video/mpeg");
		m.put("mpa", "video/mpeg");
		m.put("mp3", "audio/mpeg");
		m.put("mp2", "audio/mpeg");
		m.put("mpga", "audio/mpeg");
		m.put("au", "audio/basic");
		m.put("mov", "video/quicktime");
		m.put("dcr", "application/x-director");
		m.put("dir", "application/x-director");
		m.put("dxr", "application/x-director");
		m.put("swf", "application/x-shockwave-flash");
		m.put("swf", "application/futuresplash");
		m.put("ram", "audio/x-pn-realaudio");
		m.put("rmm", "audio/x-pn-realaudio");
		m.put("arj", "application/x-arj");
		m.put("rar", "application/x-rar");
		m.put("zip", "application/zip");
		m.put("z", "application/x-compress");
		m.put("gtar", "application/x-gtar");
		m.put("gz", "application/x-gzip");
		m.put("gzip", "application/x-gzip");
		m.put("tgz", "application/x-gzip");
		m.put("tar", "application/x-tar");
		m.put("bz2", "application/bzip2");
		m.put("uu", "text/x-uuencode");
		m.put("uue", "text/x-uuencode");
		file2mt = Collections.unmodifiableMap(m);
	}
}
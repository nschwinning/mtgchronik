package mtgchronik.entities;

import javax.persistence.Entity;

@Entity
public class Resource extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1718864956058672883L;
	private String fileName;
	private byte[] content;
	
	public Resource(){}
	
	public Resource(String fileName, byte[] content){
		this.fileName=fileName;
		this.content=content;
	}
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	
	
}

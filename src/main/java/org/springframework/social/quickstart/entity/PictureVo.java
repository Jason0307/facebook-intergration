package org.springframework.social.quickstart.entity;

public class PictureVo {
	private IconVo data;

	public IconVo getData() {
		return data;
	}

	public void setData(IconVo data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PictureVo [data=" + data + "]";
	}
	
	
}

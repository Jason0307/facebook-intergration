package org.springframework.social.quickstart.entity;

public class IconVo {

	private boolean is_silhouette;
	private String url;
	public boolean isIs_silhouette() {
		return is_silhouette;
	}
	public void setIs_silhouette(boolean is_silhouette) {
		this.is_silhouette = is_silhouette;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "IconVo [is_silhouette=" + is_silhouette + ", url=" + url + "]";
	}
	
	
}

package org.springframework.social.quickstart.entity;

public class FriendVo {

	private String id;
	private String name;
	private PictureVo picture;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PictureVo getPicture() {
		return picture;
	}
	public void setPicture(PictureVo picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "FriendVo [id=" + id + ", name=" + name + ", picture=" + picture
				+ "]";
	}
	
	
}

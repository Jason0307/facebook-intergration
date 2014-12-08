package org.springframework.social.quickstart.entity;

import java.util.List;

public class FriendDetailVo {

	private List<FriendVo> data;
	private PagingVo paging;

	public List<FriendVo> getData() {
		return data;
	}

	public void setData(List<FriendVo> data) {
		this.data = data;
	}

	public PagingVo getPaging() {
		return paging;
	}

	public void setPaging(PagingVo paging) {
		this.paging = paging;
	}

	@Override
	public String toString() {
		return "FriendDetailVo [data=" + data + ", paging=" + paging + "]";
	}

}


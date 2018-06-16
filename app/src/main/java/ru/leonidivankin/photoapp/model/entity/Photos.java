package ru.leonidivankin.photoapp.model.entity;

import java.util.List;

public class Photos {
	private String totalHits;
	private List<Hits> hits;

	public Photos(String totalHits) {
		this.totalHits = totalHits;
	}

	public String getTotalHits() {
		return totalHits;
	}

	public List<Hits> getHits() {
		return hits;
	}

	public void setHits(List<Hits> hits) {
		this.hits = hits;
	}


}

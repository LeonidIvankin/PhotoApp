package ru.leonidivankin.photovkapp.model.entity;

import java.util.List;

public class Photos {
	String totalHits;
	List<Hits> hits;

	public String getTotalHits() {
		return totalHits;
	}

	public List<Hits> getHits() {
		return hits;
	}

	public class Hits{
		String largeImageURL;
		String previewURL;

		public String getPreviewURL() {
			return previewURL;
		}

		public String getLargeImageURL() {
			return largeImageURL;
		}

	}
}

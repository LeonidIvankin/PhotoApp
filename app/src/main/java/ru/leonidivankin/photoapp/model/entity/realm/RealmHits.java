package ru.leonidivankin.photoapp.model.entity.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmHits extends RealmObject {

	@PrimaryKey
	String previewURL;
	String tags;

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getPreviewURL() {
		return previewURL;
	}


}

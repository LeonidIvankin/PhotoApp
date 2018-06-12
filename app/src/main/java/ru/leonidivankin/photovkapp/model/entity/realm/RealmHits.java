package ru.leonidivankin.photovkapp.model.entity.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmHits extends RealmObject {

	@PrimaryKey
	String previewURL;

	public void setPreviewURL(String previewURL) {
		this.previewURL = previewURL;
	}

	public String getPreviewURL() {
		return previewURL;
	}


}

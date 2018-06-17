package ru.leonidivankin.photoapp.app;

public interface Constant {

	//http request
	//http://pixabay.com/api/?key=9250926-552b631cddef606bad3e807d2
	String BASE_URL = "http://pixabay.com";
	String TOKEN_KEY = "9250926-552b631cddef606bad3e807d2";

	//directory
	String IMAGE_FOLDER_NAME_PREVIEW_URL = "imagePreviewUrl";

	//error
	String FAILED_TO_CREATE_DIRECTORY = "Failed to create directory: ";
	String FAILED_TO_SAVE_IMAGE = "Failed to save image";
	String ERROR_GETTING_PHOTOS_FROM_REALM = "error getting photos from realm";
	String IMAGE_LOAD_FAILED = "Image load failed";

	//send intent
	String SEND_INTENT_FROM_MAINACTIVITY_TO_PHOTOACTIVITY = "sendIntentFromMainactivityToPhotoactivity";

}

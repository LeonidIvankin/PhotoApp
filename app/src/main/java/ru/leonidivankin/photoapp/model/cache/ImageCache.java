package ru.leonidivankin.photoapp.model.cache;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.realm.Realm;
import ru.leonidivankin.photoapp.app.App;
import ru.leonidivankin.photoapp.model.entity.realm.CachedImage;
import timber.log.Timber;

public class ImageCache {

	private static final String IMAGE_FOLDER_NAME = "image";
	private static File imageDir;

	//сохраняем картинку
	public static File save(String url, Bitmap bitmap) {

		//если папка существует(!) или не получилось создать, выбрасываем ошибку
		if (!getImageDir().exists() && !getImageDir().mkdirs()){
			throw new RuntimeException("Failed to create directory: " + getImageDir().toString());
		}

		final String fileFormat = url.contains(".jpg") ? ".jpg" : ".png";

		final File imageFile = new File(getImageDir(), md5(url) + fileFormat);

		//сохраняем картинку на карту памяти
		try(FileOutputStream fos = new FileOutputStream(imageFile)){
			bitmap.compress(fileFormat.equals("jpg") ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();
		}catch(Exception e){
			Timber.d("Failed to save image");
			return null;
		}

		//сохраняем путь и url картинки в Realm.
		Realm.getDefaultInstance().executeTransaction(realm ->{
			CachedImage cachedImage = new CachedImage();
			cachedImage.setUrl(url);
			cachedImage.setPath(imageFile.toString());
			realm.copyToRealm(cachedImage);
		});

		//возращаем путь к файлу
		return imageFile;
	}

	//возращаем вместо url hash
	private static String md5(String s) {
		MessageDigest m = null;

		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		m.update(s.getBytes(), 0, s.length());
		String hash = new BigInteger(1, m.digest()).toString(16);
		return hash;
	}

	//получаем путь к файлу по url из Realm
	public static File getFile(String url) {
		CachedImage cachedImage = Realm
				.getDefaultInstance()
				.where(CachedImage.class)
				.equalTo("url", url)
				.findFirst();

		if(cachedImage != null){
			return new File(cachedImage.getPath());
		}

		return null;

	}

	//проверяем, есть ли данный путь в Realm
	public static boolean contains(String url) {
		return Realm
				.getDefaultInstance()
				.where(CachedImage.class)
				.equalTo("url", url)
				.count() > 0;

	}

	//получаем путь к папке для сохранения фото
	public static File getImageDir() {
		return new File(App.getInstance().getExternalFilesDir(null) + "/" + IMAGE_FOLDER_NAME);
	}
}

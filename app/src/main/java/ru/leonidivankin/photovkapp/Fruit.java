package ru.leonidivankin.photovkapp;

class Fruit {
	public static final Fruit[] fruits = {
			new Fruit("apple", R.drawable.apple),
			new Fruit("pear", R.drawable.pear),
			new Fruit("orange", R.drawable.orange),
			new Fruit("watermelon", R.drawable.watermelon),
			new Fruit("cherry", R.drawable.cherry),
			new Fruit("strawberry", R.drawable.strawberry)
	};
	private String name;
	private int image;

	public Fruit(String name, int image) {
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}
}

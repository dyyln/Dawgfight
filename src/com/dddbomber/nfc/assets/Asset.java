package com.dddbomber.nfc.assets;

public class Asset {
	public static final Bitmap plane = AssetLoader.loadBitmap("/plane.png");
	public static Bitmap tiles = AssetLoader.loadBitmap("/tiles.png");
	public static Bitmap bullet = AssetLoader.loadBitmap("/bullet.png");
	public static Bitmap score = AssetLoader.loadBitmap("/score.png");
	public static Bitmap missile = AssetLoader.loadBitmap("/missile.png");
	public static Bitmap cloud = AssetLoader.loadBitmap("/cloud.png");
	public static Bitmap exp_ground = AssetLoader.loadBitmap("/exp_ground.png");
	public static Bitmap exp_air = AssetLoader.loadBitmap("/exp_air.png");
	public static Bitmap avatar = AssetLoader.loadBitmap("/avatar.png");
	public static Bitmap title = AssetLoader.loadBitmap("/title.png");
	public static Bitmap aagun = AssetLoader.loadBitmap("/aagun.png");
	public static Bitmap mission = AssetLoader.loadBitmap("/mission.png");
	
	public static double lerpDegrees(double start, double end, double amount) {
        double difference = Math.abs(end - start);
        if (difference > 180) {
            if (end > start) {
                start += 360;
            } else {
                end += 360;
            }
        }
        double value = (start + ((end - start) * amount));
        double rangeZero = 360;
        if (value >= 0 && value <= 360) {
            return value;
        }
        return (value % rangeZero);
    }
}

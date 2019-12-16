import javafx.scene.paint.Color;

//信息类存储排序过程中的位置变化以及要显示的颜色变化
public class Information {
	private Color color;
	private int fx;
	private int sx;
	
	public Information() {
		
	}
	
	public Information(int fx, int sx) {
		this.fx = fx;
		this.sx = sx;
	}
	
	public Information(int fx, Color color) {
		this.fx = fx;
		this.sx = -1;
		this.color = color;
	}
	
	
	public int getFx() {
		return fx;
	}
	
	public int getSx() {
		return sx;				
	}
	
	public Color getColor() {
		return color;
	}
}

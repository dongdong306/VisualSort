import java.util.ArrayList;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DataGraph {
	public static final double DEFAULT_HEIGHT_BORDER = 60;
	public static final double DEFAULT_WIDTH = 30;
	public static final double MAX_HEIGHT = 400;
	public static final double DEFAULT_SPACING = 5;
	public static final double DEFAULT_BORDER_SPACING = 45;
	public static final int DEFAULT_FONT_SIZE = 16;
	private static double HEIGHT_EPSILON = 3;
	private static double SPACING = 5;
	private static double BORDER_SPACING = 45;
	private static double WIDTH = 30;
	private static double DELTA = 0;
	private static int FONT_SIZE = 16;
	private Label label;
	
	public DataGraph() {
		
	}
	
	//初始化柱形图
	public DataGraph(int x, int n) {
		Rectangle rect = new Rectangle(WIDTH, (n + DELTA) * HEIGHT_EPSILON);
		rect.setFill(ConstantData.BLUE);
		label = new Label("" + n, rect);
		label.setTranslateX(BORDER_SPACING + x * (WIDTH + SPACING));
		label.setTranslateY(MAX_HEIGHT + DEFAULT_HEIGHT_BORDER - (n * HEIGHT_EPSILON));
		label.setContentDisplay(ContentDisplay.BOTTOM);
		label.setFont(Font.font(null, FontWeight.BOLD, FONT_SIZE));
		//System.out.println(WIDTH);
	}
	
	public static void graphAdapter(ArrayList<Integer> array) {
		double n = array.size();
		double max,min;
		max = min = 0;
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				max = min = array.get(0);
			}
			if (array.get(i) > max) {
				max = array.get(i);
			}
			
			if (array.get(i) < min) {
				min = array.get(i);
			}
		}
		
		if (n < 20) {
			SPACING = DEFAULT_SPACING;
			WIDTH = DEFAULT_WIDTH;
			FONT_SIZE = DEFAULT_FONT_SIZE;
		}
		else {
			BORDER_SPACING = DEFAULT_BORDER_SPACING;
			//计算各个柱形图的宽度
			double temp = 700 / n;
			SPACING = temp / 7;
			WIDTH = temp - SPACING;
			if (n < 100) {
				FONT_SIZE = (int) (DEFAULT_FONT_SIZE * (WIDTH / DEFAULT_WIDTH));
			}
			else {
				FONT_SIZE = 0;
			}
		}
		BORDER_SPACING = (790 - n * (WIDTH + SPACING)) / 2;
		
		//计算柱形图的高度系数和基数
		if (min < 0) {
			DELTA = (min * -1) + 1;
			HEIGHT_EPSILON = MAX_HEIGHT / (max - min);
		}
		else {
			DELTA = 0;
			HEIGHT_EPSILON = MAX_HEIGHT / max;
		}
	}
	
	public void setFill(Color color) {
		Rectangle rect = (Rectangle) label.getGraphic();
		rect.setFill(color);
	}
	
	
	public Rectangle getRect() {
		return (Rectangle) label.getGraphic();
	}
	
	
	public Color getColor() {
		return (Color) getRect().getFill();
	}
	
	
	public Label getLabel() {
		return label;
	}
	
	
	public double getTranslateX() {
		return label.getTranslateX();
	}
	
	//目前没用
	public void setTranslateX(double x) {
		label.setTranslateX(x);
	}
		
	
	
	public void added(Pane pane) {
		pane.getChildren().addAll(label);
	}
	
	
}

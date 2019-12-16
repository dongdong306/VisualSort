import java.util.ArrayList;

import javafx.animation.Animation.Status;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class DrawPane extends Pane {
	private ArrayList<DataGraph> rects;
	private Timeline animation;					//主要时间线
	private int duration = 200;					//时间线默认播放时间
	private Status status = Status.STOPPED;		//时间线状态
	private ProgressBar progressBar;			//进度条
	private Label label;						//进度条的sorting标签
	
	//构造方法
	public DrawPane() {
		rects = new ArrayList<>();
	}
	
	//初始化进度条的相关内容
	public void initProgressBar() {
		progressBar = new ProgressBar();
		label = new Label("Sorting...");
		label.setLayoutX(375);
		label.setLayoutY(520);
		label.setFont(Font.font(null, FontWeight.BOLD, 14));
		progressBar.setLayoutX(195);
		progressBar.setLayoutY(550);
		progressBar.setPrefWidth(400);
		progressBar.setProgress(0);
		super.getChildren().addAll(label, progressBar);
	}
	
	//设置进度条进度
	public void setProgressBar(double n) {
		progressBar.setProgress(n);
	}
	
	//设置时间线的播放速率
	public void setDuration(SnapShot snapShot, int duration) {
		this.duration = duration;
		pause();
		animation = new Timeline(new KeyFrame(Duration.millis(duration), e -> run(snapShot)));
		animation.setCycleCount(Timeline.INDEFINITE);
		
		//如果快照为空则还未进行排序，将状态设置为停止，否则设为暂停
		if (snapShot.isEmpty()) {
			status = Status.STOPPED;
		}
		else {
			status = Status.PAUSED;
		}
	}
	
	//开始播放时间线已经解析快照队列生成动画
	public void run(SnapShot snapShot) {
		//按步解析队列
		setProgressBar((snapShot.getMaxQueueSize() - snapShot.size()) / snapShot.getMaxQueueSize());
		if (!snapShot.isEmpty()) {
			Information temp = snapShot.dequeue();
			//交换位置
			if (temp.getSx() != -1) {
				TranslateTransition tt1 = new TranslateTransition(
						Duration.millis(duration - duration / 8), 
						rects.get(temp.getFx()).getLabel()
						);
				tt1.setFromX(rects.get(temp.getFx()).getTranslateX());
				tt1.setToX(rects.get(temp.getSx()).getTranslateX());
				tt1.playFromStart();
				
				TranslateTransition tt2 = new TranslateTransition(
						Duration.millis(duration - duration / 8), 
						rects.get(temp.getSx()).getLabel()
						);
				tt2.setFromX(rects.get(temp.getSx()).getTranslateX());
				tt2.setToX(rects.get(temp.getFx()).getTranslateX());
				tt2.playFromStart();
				
				DataGraph t = rects.get(temp.getFx());
				rects.set(temp.getFx(), rects.get(temp.getSx()));
				rects.set(temp.getSx(), t);
			}
			//变换颜色
			else {
				FillTransition ft = new FillTransition(
						Duration.millis(duration - duration / 9), 
						rects.get(temp.getFx()).getRect(), 
						rects.get(temp.getFx()).getColor(),
						temp.getColor()
						);
				ft.play();
			}
			
		}
	}
	
	//未排序的原始数据的初始化生成
	public void draw(ArrayList<Integer> array) {
		DataGraph.graphAdapter(array);
		rects.clear();
		super.getChildren().clear();
		for (int i = 0; i < array.size(); i++) {
			DataGraph rect = new DataGraph(i, array.get(i));
			rect.added(this);
			rects.add(rect);
		}
		initProgressBar();
	}
	
	//时间线的相应操作的封装
	public void play(SnapShot snapShot, int duration) {
		if (animation == null) {
			animation = new Timeline(new KeyFrame(Duration.millis(duration), e -> run(snapShot)));
			animation.setCycleCount(Timeline.INDEFINITE);
		}
		status = Status.RUNNING;
		animation.play();
	}
	
	public void pause() {
		if (animation != null) {
			animation.pause();
		}
		status = Status.PAUSED;
	}
	
	public void stop() {
		if (animation != null) {
			animation.stop();
		}
		status = Status.STOPPED;
	}
	
	public  Status getStatus() {
		return status;
	}
	
	
}

import java.util.LinkedList;

import javafx.scene.paint.Color;

public class SnapShot {
	private LinkedList<Information> queue;				//队列存储快照
	private int timeOfSwap;								//排序过程中的元素交换次数
	private double maxQueueSize;						//数据量的记录
	
	public SnapShot() {
		queue = new LinkedList<>();
		timeOfSwap = 0;
	}
	
	public void enqueue(Information temp) {
		queue.add(temp);
	}
	
	//交换信息进入队
	public void enqueue(int fx, int dx) {
		queue.add(new Information(fx, dx));
		maxQueueSize = queue.size();
		//交换次数加一
		timeOfSwap++;
	}
	
	//颜色变化信息入队
	public void enqueue(int fx, Color color) {
		queue.add(new Information(fx, color));
		maxQueueSize = queue.size();
	}
	
	//基本方法
	public Information dequeue() {
		return queue.removeFirst();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public Information getLast() {
		return queue.getLast();
	}
	
	public void clear() {
		maxQueueSize = 0;
		timeOfSwap = 0;
		queue.clear();
	}
	
	public int getTimeOfSwap() {
		return timeOfSwap;
	}
	
	public double getMaxQueueSize() {
		return maxQueueSize;
	}
	
	public int size() {
		return queue.size();
	}
}

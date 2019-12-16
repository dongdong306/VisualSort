import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.Animation.Status;

public class Agent {
	private Sort sort;						//排序接口
	private SnapShot snapShot;				//快照队列类
	private ArrayList<Integer> rawArray;	//原始数据
	private ArrayList<Integer> array;		//排序操作数据
	private FileOperation FO;				//文件操作类
	private DrawPane drawPane;				//动画控制面板类
	private int duration = 200;				//基础动画时间
	private int randomDataSize = 20;		//基础随机数据量范围
	
	public Agent() {
		
	}
	
	//构造
	public Agent(DrawPane drawPane) {
		FO = new FileOperation();
		array = new ArrayList<>();
		rawArray = new ArrayList<>();
		snapShot = new SnapShot();
		this.drawPane = drawPane;
		
	}
	
	//设置排序方式
	public void setWayOfSort(String way) {
		if (way.equals("冒泡排序")) {
			sort = new BubbleSort();
		}
		else if (way.equals("插入排序")) {
			sort = new InsertSort();
		}
		else if (way.equals("选择排序")) {
			sort = new SelectionSort();
		}
		else if (way.equals("快速排序")) {
			sort = new QuickSort();
		}
	}
	
	//恢复操作
	public void recover() {
		array = (ArrayList<Integer>) rawArray.clone();
	}
	
	public void sort() {
		sort.sort(0, array.size() - 1, array, snapShot);
		//draw();
	}
	
	public void save() throws IOException {
		FO.writeFile(rawArray);
	}
	
	public void randomData() {
		rawArray.clear();
		array.clear();
		for (int i = 0; i < randomDataSize; i++) {
			int temp = (int)(Math.random() * 100 + 1);
			array.add(temp);
			rawArray.add(temp);
		}
	}
	
	public void play() {
		if (drawPane.getStatus() == Status.STOPPED && !array.isEmpty()) {
			sort();
		}
		
		drawPane.play(snapShot, duration);
	}
	
	public void pause() {
		drawPane.pause();
	}
	
	public void draw() {
		drawPane.draw(rawArray);
	}
	
	public void stop() {
		drawPane.stop();
		snapShot.clear();
	}
	
	public void textInput(String[] temp) {
		array.clear();
		rawArray.clear();
		try {
			if (temp != null) {
				for (int i = 0; i < temp.length; i++) {
					int t = Integer.valueOf(temp[i]);
					array.add(t);
					rawArray.add(t);
				}
			}
		} catch (NumberFormatException e) {
			//System.out.println("111");
			array.clear();
			rawArray.clear();
		}
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
		drawPane.setDuration(snapShot, duration);
	}
	
	public void setRandomDataSize(int randomDataSize)  {
		this.randomDataSize = randomDataSize;
	}
	
}

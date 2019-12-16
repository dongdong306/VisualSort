import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileOperation {
	
	//写入文件
	public void writeFile(ArrayList<Integer> rawArray) throws IOException {
		ArrayList<Integer> array;
		SnapShot snapShot = new SnapShot();
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-HH-mm-ss");
		
		PrintWriter output = new PrintWriter(new File(df.format(new Date()) + ".txt"));
		
		//四种排序算法的基本信息和交换次数显示
		ArrayList<Sort> sort = new ArrayList<>();
		sort.add(new BubbleSort());
		sort.add(new InsertSort());
		sort.add(new SelectionSort());
		sort.add(new QuickSort());
		array = (ArrayList<Integer>) rawArray.clone();
		for (int i = 0; i < sort.size(); i++) {
			snapShot.clear();
			array = (ArrayList<Integer>) rawArray.clone();
			sort.get(i).sort(0, array.size() - 1, array, snapShot);
			output.println(sort.get(i).toString());
			output.println("交换次数：" + snapShot.getTimeOfSwap());
		}
		
		output.println("数据量：" +  rawArray.size());
		
		//原始数据
		output.println("原始数据：");
		for (int i = 0; i < rawArray.size(); i++) {
			output.print(rawArray.get(i) + " ");
		}
		output.println("");
		
		//已排序数据
		output.println("已排序数据：");
		for (int i = 0; i < array.size(); i++) {
			output.print(array.get(i) + " ");
		}
		output.println("");
		output.println("ps:选择排序因每次遍历整个数组只是找出最大值，交换发生在遍历完成后，所以每次遍历只交换一次，所以交换次数较少");
		
		output.close();
	}

}

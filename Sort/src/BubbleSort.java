import java.util.ArrayList;

public class BubbleSort implements Sort {
	
	@Override
	public void sort(int left, int right, ArrayList<Integer> array, SnapShot snapShot) {
		int temp;
		
		for (int i = 0; i <= right; i++) {
			snapShot.enqueue(0, ConstantData.GREEN);
			for (int j = 1; j <= right - i; j++) {
				//进行比较的两个数据填充为绿色
				snapShot.enqueue(j, ConstantData.GREEN);
				if (array.get(j) < array.get(j - 1)) {
					//进行位置的变换
					snapShot.enqueue(j , j - 1);
					temp = array.get(j - 1);
					array.set(j - 1, array.get(j));
					array.set(j, temp);
				}
				//将前一个数据恢复为正常颜色
				snapShot.enqueue(j - 1, ConstantData.BLUE);
			}
			//完成排序的部分填充为橘色
			snapShot.enqueue(right - i, ConstantData.ORANGE);
		}
		
	}
	
	@Override
	public String toString() {
		return "冒泡排序: \r\n平均算法复杂度:O(n2)";
		
	}

}

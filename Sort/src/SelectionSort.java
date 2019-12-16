import java.util.ArrayList;

public class SelectionSort implements Sort {

	@Override
	public void sort(int left, int right, ArrayList<Integer> array, SnapShot snapShot) {
		int max = 0;
		int i,j;
		for (i = 0; i <= right; i++) {
			max = 0;
			for (j = 0; j <= right - i; j++) {
				//将遍历的哨兵位置填充为绿色，将目前最大值填充为红色
				snapShot.enqueue(j , ConstantData.GREEN);
				snapShot.enqueue(max , ConstantData.RED);
				if (array.get(max) < array.get(j)) {
					//若发现比最大值大的数据将原来的最大值柱子填充回默认的蓝色，将新的最大值填充为红色
					snapShot.enqueue(max , ConstantData.BLUE);
					max = j;
					snapShot.enqueue(max , ConstantData.RED);
				}
				else if (max != j){
					//如果未发生当前最大值的变化则将哨兵遍历后的位置填充回默认的蓝色
					snapShot.enqueue(j , ConstantData.BLUE);
				}
			}
			snapShot.enqueue(right - i , ConstantData.RED);
			snapShot.enqueue(max, right - i);
			int temp = array.get(max);
			array.set(max, array.get(right - i));
			array.set(right - i, temp);
			//将完成排序的部分填充为橘色
			snapShot.enqueue(max , ConstantData.BLUE);
			snapShot.enqueue(right - i, ConstantData.ORANGE);
		}
		
	}
	
	@Override
	public String toString() {
		return "选择排序: \r\n平均算法复杂度:O(n2)";
		
	}

}

import java.util.ArrayList;

public class QuickSort implements Sort {
	@Override
	public void sort(int left, int right, ArrayList<Integer> array, SnapShot snapShot) {
		int i,j,t,temp;
		
		if (right < left) {
			return;
		}
		
		temp = array.get(left);
		snapShot.enqueue(left, ConstantData.YELLOW);
		i = left;
		j = right;
		while (i != j) {
			while (array.get(j) >= temp && i < j) {
				snapShot.enqueue(j, ConstantData.BLUE);
				j--;
				snapShot.enqueue(j, ConstantData.RED);
			}
			while (array.get(i) <= temp && i < j) {
				if (i != left) {
					snapShot.enqueue(i, ConstantData.BLUE);
				}
				
				i++;
				snapShot.enqueue(i, ConstantData.PURPLE);
			}
			if (i < j) {
				snapShot.enqueue(i, ConstantData.GREEN);
				snapShot.enqueue(j, ConstantData.GREEN);
				snapShot.enqueue(i, j);
				t = array.get(i);
				array.set(i, array.get(j));
				array.set(j, t);
				snapShot.enqueue(i, ConstantData.RED);
				snapShot.enqueue(j, ConstantData.PURPLE);
			}
		}
		snapShot.enqueue(i, left);
		array.set(left, array.get(i));
		array.set(i, temp);
		snapShot.enqueue(j, ConstantData.ORANGE);
		
		sort(left, i - 1, array, snapShot);
		sort(i + 1, right ,array, snapShot);
		
	}
	
	@Override
	public String toString() {
		return "快速排序: \r\n平均算法复杂度:O(nlogn)";
		
	}
}

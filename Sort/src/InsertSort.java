import java.util.ArrayList;

public class InsertSort implements Sort {

	@Override
	public void sort(int left, int right, ArrayList<Integer> array, SnapShot snapShot) {
		int temp;
		int j;
		for (int i = 0; i <= right; i++) {
            for (j = i; j > 0 ; j--) {
            	//将待交换位置的柱子填充为红色，与其比较的柱子填充为绿色
            	snapShot.enqueue(j, ConstantData.RED);
            	snapShot.enqueue(j - 1, ConstantData.GREEN);
            	
            	if (array.get(j) < array.get(j - 1)) {
            		snapShot.enqueue(j - 1, j);
                	temp = array.get(j - 1);
    				array.set(j - 1, array.get(j));
    				array.set(j, temp);
    				//交换位置后将绿色柱子填充为橘色
    				snapShot.enqueue(j, ConstantData.ORANGE);
            	}
            	else {
            		//若未交换位置则已经有序，将原来绿色的待比较柱子填充为完成排序的橘色
            		snapShot.enqueue(j - 1, ConstantData.ORANGE);
            		break;
            	}
            }
            //若交换的位置则将红色柱子填充为橘色，若未交换位置则将绿色柱子填充为橘色
            snapShot.enqueue(j, ConstantData.ORANGE);
        }
		
	}
	
	@Override
	public String toString() {
		return "插入排序: \r\n平均算法复杂度:O(n2)";
		
	}

}

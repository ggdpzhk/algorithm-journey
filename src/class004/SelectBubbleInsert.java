package class004;

public class SelectBubbleInsert {

	// 数组中交换i和j位置的数
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// 选择排序
	//选择待排数据中最小的，与数组最左侧的数据进行交换
	public static void selectionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int minIndex, i = 0; i < arr.length - 1; i++) {
			minIndex = i;//因为每一次都会排好前面的位置，所以每次 都要重新给minIndex赋值； 然后数组剩余数字进行遍历，找出最小值然后交换
			for (int j = i + 1; j < arr.length; j++) {//当 j = i 时，会无意义地比较 arr[i] 和 arr[minIndex]（此时 minIndex = i），即 arr[i] 和自己比。
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			swap(arr, i, minIndex);
		}
	}

	// 冒泡排序 
	// 从第一个开始，两个两个比较，比较完第一轮，最大的位置会出现在最后，
	// 从第一个开始，                     二  ，次大的位置会出现在倒数第二个
	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int end = arr.length - 1; end > 0; end--) {
			for (int i = 0; i < end; i++) {
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i + 1);
				}
			}
		}
	}

	// 插入排序 
	// 像打扑克一样，一张一张地拿，从后往前比较，放在合适的位置，再拿下一张
	public static void insertionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {//j+1是新加入的牌，j+i 最多= i，i < arr.length,因此j绝对不会越界
				swap(arr, j, j + 1);
			}
		}
	}

}

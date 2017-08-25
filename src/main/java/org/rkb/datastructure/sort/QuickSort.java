package org.rkb.datastructure.sort;

public class QuickSort {
	public static void main(String... args) {
		QuickSort qs = new QuickSort();
		int data[] = { 10,14,91,18,72,551,82749,23,55,26 };
		System.out.println(data.length - 1);
		qs.quickSort(data, 0, data.length - 1);
		for (int i : data) {
			System.out.print(i + " ");
		}
	}

	public void quickSort(int data[], int low, int high) {
		int i = low;
		int j = high;

		int pivot = data[low + (high - low) / 2];

		while (i <= j) {
			while (data[i] < pivot)
				i++;
			while (data[j] > pivot)
				j--;
			if (i <= j) {
				int tmp = data[i];
				data[i] = data[j];
				data[j] = tmp;
				i++;
				j--;
			}
		}
		if (low < j)
			quickSort(data, low, j);
		if (high > i)
			quickSort(data, i, high);
	}

}

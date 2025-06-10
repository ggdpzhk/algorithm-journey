package class021;

// 归并排序，acm练习风格
// 测试链接 : https://www.luogu.com.cn/problem/P1177
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code01_MergeSort {

	public static int MAXN = 100001;

	public static int[] arr = new int[MAXN];

	public static int[] help = new int[MAXN];//merge过程中的辅助数组
	//arr[]和help[]是全局静态static变量，所以所有的方法都不用放参数位置。参数位没有也能直接访问到。
	//使用全局静态变量能少些好多东西
	public static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		n = (int) in.nval;
		for (int i = 0; i < n; i++) {
			in.nextToken();//不断都数据，
			arr[i] = (int) in.nval;//并放入arr数组
		}
		// mergeSort1(0, n - 1);
		mergeSort2();
		for (int i = 0; i < n - 1; i++) {
			out.print(arr[i] + " ");
		}
		out.println(arr[n - 1]);
		out.flush();
		out.close();
		br.close();
	}

	// 归并排序递归版
	// 假设l...r一共n个数
	// T(n) = 2 * T(n/2) + O(n)
	// a = 2, b = 2, c = 1
	// 根据master公式，时间复杂度O(n * logn)
	// 空间复杂度O(n)
	public static void mergeSort1(int l, int r) {
		if (l == r) {
			return;
		}
		int m = (l + r) / 2;
		mergeSort1(l, m);
		mergeSort1(m + 1, r);
		merge(l, m, r);
	}

	// 归并排序非递归版
	// 时间复杂度O(n * logn)
	// 空间复杂度O(n)
	public static void mergeSort2() {
		// 一共发生O(logn)次
		for (int l, m, r, step = 1; step < n; step <<= 1) {
			// 内部分组merge，时间复杂度O(n)
			l = 0;
			while (l < n) {
				m = l + step - 1;
				if (m + 1 >= n) {
					break;
				}
				r = Math.min(l + (step << 1) - 1, n - 1);
				merge(l, m, r);
				l = r + 1;
			}
		}
	}

	// l....r 一共有n个数
	// O(n)
	//不是递归方法 
	public static void merge(int l, int m, int r) {
		int i = l;//辅助数组help[]被填到了什么位置 
		int a = l;//a是左边有序数组的指针
		int b = m + 1;//b是右边有序数组的指针
		while (a <= m && b <= r) {//如果左右两侧都没有耗尽的话
			help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];//最经典的过程，谁小拷贝谁
		}
		// 左侧指针、右侧指针，必有一个越界、另一个不越界（所以下面两个while只会命中一个）
		while (a <= m) {
			help[i++] = arr[a++];
		}
		while (b <= r) {
			help[i++] = arr[b++];
		}
		//至此，help[]的数字全部经过比较填写完成，限下面将其刷进原数组arr[]
		for (i = l; i <= r; i++) {
			arr[i] = help[i];
		}
	}

}

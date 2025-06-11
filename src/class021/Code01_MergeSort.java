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
	public static int n;//通过下面输入流的读入方式，n是arr的长度

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//创建BufferedReader，从标准输入流读取数据
		StreamTokenizer in = new StreamTokenizer(br);//创建StreamTokenizer，解析数据
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));//创建PrintWriter,从高效输出到标准输出
		//一般第一个数都是大小，知道了大小然后下面循环读取具体的内容。（我就说为什么循环读了，为啥外面还要这么多余再来一次）
		in.nextToken();//读取下一个标记（通常是数字或字符串）
		n = (int) in.nval;// 将读取的数字赋值给变量n（表示数据个数）。  
		for (int i = 0; i < n; i++) {// 循环读取n个数据
			in.nextToken();//不断读数据，
			arr[i] = (int) in.nval;//并放入arr数组
		}
		// mergeSort1(0, n - 1);
		mergeSort2();
		for (int i = 0; i < n - 1; i++) {
			out.print(arr[i] + " ");
		}
		out.println(arr[n - 1]);//输出到标准输出（即输出到控制台）（不是文件）
		out.flush();//刷新输出缓冲区，确保所有内容都被写出
		out.close();//关闭输出流
		br.close();//关闭输入流
	}

	// 归并排序递归版
	// 假设l...r一共n个数
	// T(n) = 2 * T(n/2) + O(n)
	// a = 2, b = 2, c = 1
	// 根据master公式，时间复杂度O(n * logn)
	// 空间复杂度O(n) (需要额外的辅助数组)
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
	public static void mergeSort2() {//从左到右，按步长=1，2，4……排完序以后再merge，不是递归，是从头一遍又一遍。
					//所以为什么不用传参数呢？数组是static，全局变量，那么直接方法里定义l，r，m即可
		// 一共发生O(logn)次
		for (int l, m, r, step = 1; step < n; step <<= 1) { //step是步长，这里的位运算表示每次都乘2，写成乘2也行，但是位运算比乘法运算快一点，其实秀技
			// 内部分组merge，时间复杂度O(n)
			l = 0;
			while (l < n) {
				m = l + step - 1;//0，，1，，2 这个步长是3	
				if (m + 1 >= n) {//发现右边界的初始越界就break，就不用merge了
					break;
				}
				//说明有右侧
				//接下来求右侧的右边界，左边界是m+1
				//左部分是的右边界是l+x-1,右部分和左部分是相等的长度，所以右部分的右边界是（拿l算为啥不拿m+1作为起始算？一样的，我的是思考的直接的方法（相比较多一步计算），这个是数学上简化代码上更优雅的方法）l + 2x - 1，即l + (step << 1) - 1。但是呢有可能数组没有这么长了，所以两者取min为右边界值
				r = Math.min(l + (step << 1) - 1, n - 1);
				merge(l, m, r);
				l = r + 1;
			}
		}
	}

	// l....r 一共有n个数
	// O(n)
	//merge不是递归方法 
	public static void merge(int l, int m, int r) {
		int i = l;//辅助数组help[]被填到了什么位置 
		int a = l;//a是左边有序数组的指针
		int b = m + 1;//b是右边有序数组的指针
		while (a <= m && b <= r) {//如果左右两侧都没有耗尽的话
			help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];//最经典的过程，谁小拷贝谁
		}
		// 左侧指针、右侧指针，必有一个越界、另一个不越界（所以下面两个while只会命中一个）
		//逻辑：哪边没耗尽就继续拷贝
		while (a <= m) {
			help[i++] = arr[a++];
		}
		while (b <= r) {
			help[i++] = arr[b++];
		}
		/*意义相同，下面是我写的，逻辑：哪边耗尽了就拷贝另一边
	        while(a > m){
  	          help[i++] = arr[b++];
	        }
	        while (b > r){
	            help[i++] = arr[a++];
        }*/


		
		//至此，help[]的数字全部经过比较填写完成，限下面将其刷进原数组arr[]
		for (i = l; i <= r; i++) {
			arr[i] = help[i];
		}
	}

}

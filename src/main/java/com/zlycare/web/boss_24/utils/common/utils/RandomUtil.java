package com.zlycare.web.boss_24.utils.common.utils;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/**
 * Description:
 * @author yz.wu
 */
public class RandomUtil {
	
	public static Random ranTmp = new Random();

	public static int getNum(int min, int max) {
		return ranTmp.nextInt(max-min+1)+min;
	}
	
	/**
	 * 获取指定长度的随机数字字符串
	 * @param length
	 * @return String
	 */
	public static String getRandomInt(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			switch (i) {
			case 0:
				sb.append(getNum(1, 9));
				break;
			default:
				sb.append(getNum(0, 9));
				break;
			}
		}
		
		return sb.toString();
	}
	
	public static int[] getRandomInt(int min, int max, int count) {
		boolean check =  count >= (max - min + 1);
		if (check) {
			count = max - min + 1;
		}
		
		int[] array = new int[count];
		List<Integer> list = Lists.newArrayList();
		for (int i=min,j=0;i<max+1;i++) {
			if (check) {
				array[j++] = i;
			} else {
				list.add(i);
			}	
		}
		
		if (!check) {
			int size = 0;
			for (int i=0;i<count;i++) {
				if (i > max-min) {
					break;
				}
				size = list.size();
				array[i] = list.remove(ranTmp.nextInt(size));	
			}
		}
		
		return array;
	}

    public static void main(String[] args) {
        for (int i=0;i<1000;i++) {
            System.out.println(getNum(1, 2));
        }
    }
}

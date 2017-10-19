package com.zlycare.web.boss_24.utils.common.utils;

import java.util.UUID;

/**
 * UUID生成器<br>
 * 生成32位的uuid
 * 
 * @author lixk
 * @date 2010-6-4 上午09:36:20
 */
public class UUIDGenerator {

	public static String generate() {
		UUID uuid = UUID.randomUUID();
		long mostSigBits = uuid.getMostSignificantBits();
		long leastSigBits = uuid.getLeastSignificantBits();
		return digits(mostSigBits >> 32, 8) + "" + digits(mostSigBits >> 16, 4)
				+ "" + digits(mostSigBits, 4) + ""
				+ digits(leastSigBits >> 48, 4) + "" + digits(leastSigBits, 12);

	}

	private static String digits(long val, int digits) {
		long hi = 1L << (digits * 4);
		return Long.toHexString(hi | (val & (hi - 1))).substring(1);
	}
}

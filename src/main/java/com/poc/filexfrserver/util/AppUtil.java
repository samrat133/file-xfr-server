package com.poc.filexfrserver.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class AppUtil {

	public static String getFileName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return sdf.format(timestamp);
	}

}

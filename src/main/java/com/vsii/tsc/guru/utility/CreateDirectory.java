package com.vsii.tsc.guru.utility;

import java.io.File;

import com.vsii.tsc.guru.testbase.TestBase;

public class CreateDirectory {
	
	public static void createDir(String dir){
		String directoryName = dir;
		File theDir = new File(directoryName);
		if(!theDir.exists())
		{
			theDir.mkdir();
		}
	}

}

package com.daw.contafin;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImageService {
	
	public byte[] uploadImage(MultipartFile file) throws IOException {
		
		//file to bytes
		byte[] bytes = file.getBytes();
		//Return an InputStream to read the contents of the file from.
		InputStream inputStream= file.getInputStream();
		//Read stream data into "bytes"
		inputStream.read(bytes);
		
		return bytes;
	}
	
}

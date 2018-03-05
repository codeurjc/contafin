package com.daw.contafin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseRepository;


@Service
public class ImageService {
	
	@Autowired
	ExerciseRepository exerciseRepository;
	
	public byte[] uploadImage(MultipartFile file) throws IOException {
		
		//file to bytes
		byte[] bytes = file.getBytes();
		//Return an InputStream to read the contents of the file from.
		InputStream inputStream= file.getInputStream();
		//Read stream data into "bytes"
		inputStream.read(bytes);
		
		return bytes;
	}
	
	//Store exercises images located in the "static/img" folder in the database
	public void saveImages(Exercise exercise, Path route1, Path route2, Path route3) throws IOException {
		byte []image = Files.readAllBytes(route1);
		exercise.setImage1(image);
		image = Files.readAllBytes(route2);
		exercise.setImage2(image);
		image = Files.readAllBytes(route3);
		exercise.setImage3(image);
		exerciseRepository.save(exercise);	
	}
	
}

package com.daw.contafin.controller;


import javax.transaction.Transactional;

import com.daw.contafin.dto.JwtResponse;
import com.daw.contafin.dto.UserConfigDto;
import com.daw.contafin.dto.UserDto;
import com.daw.contafin.service.UserService;
import com.daw.contafin.config.jwt.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@Slf4j
@Transactional
@RestController
@RequestMapping ("/api/User")
public class UserRestController {


	@Autowired
	UserService userService;
	
	@DeleteMapping(value = "/{id}")
	@ResponseBody
	@Operation(summary = "Elimina un usuario dado su id, la función utilizada es deleteUnit(long)")
	public ResponseEntity<Boolean> deleteAccount(@PathVariable Long id) {
		log.info("Se ha recibido una peticion para borrar al usuario con id: {}",id);
		ResponseEntity<Boolean> response;
		try{
			userService.deleteAccount(id);
			response = new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido borrar el usuario";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}


	@GetMapping(value = "/{id}")
	@ResponseBody
	@Operation(summary = "Devuelve la información del perfil del usuario dado su id, la función utilizada es profile(long)")
	public ResponseEntity<UserDto> profile(@PathVariable Long id) {
		log.info("Se ha recibido una peticion para cargar el usuario con id: {}",id);
		ResponseEntity<UserDto> response;
		try{
			UserDto userDto = userService.getProfile(id);
			response = new ResponseEntity<>(userDto,HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha cargar el usuario";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}


	@PutMapping(value = "/")
	@ResponseBody
	@Operation(summary = "Actualiza estadisticas del usuario dado un UserDto, la función utilizada es profile(UserDto)")
	public ResponseEntity<UserDto> updateUserData(@RequestBody UserDto updatedUser) {
		log.info("Se ha recibido una peticion para actualizar el usuario con id: {}");
		ResponseEntity<UserDto> response;
		try{
			UserDto userDto = userService.updateUser(updatedUser);
			response = new ResponseEntity<>(userDto,HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido actualizar el usuario";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@PostMapping (value = "/Validation/{id}")
	@ResponseBody
	@Operation(summary = "Perimite cambiar información sensible del usuario dado el id del usuario y un UserConfigDto, la función utilizada es validation(Long, UserDto)")
	public ResponseEntity<JwtResponse> validation(@PathVariable Long id, @RequestBody UserConfigDto userConfigDto){
		log.info("Se ha recibido una peticion para comprobar el usuario con id: {}",id);
		ResponseEntity<JwtResponse> response;
		try{
			String finalpass = userConfigDto.getOldpass();
			UserDto userDto = userService.checkPass(id,userConfigDto.getOldpass(), userConfigDto.getPass(), userConfigDto.getName(), userConfigDto.getEmail(), userConfigDto.getFile());

			if(userDto ==null ){
				return response = ResponseEntity.ok(null);
			}else{
				if(!userConfigDto.getPass().isEmpty()){
					finalpass = userConfigDto.getPass();
				}

				Map<String,Object> responseObjects = userService.createAuthentication(userConfigDto.getName(), finalpass);

				UserDetailsImpl userDetails = (UserDetailsImpl) responseObjects.get("userDetails");
				return ResponseEntity.ok(new JwtResponse((String) responseObjects.get("token"),
						userDetails.getUser(),
						userDetails.getId(),
						userDetails.getUsername(),
						userDetails.getEmail(),
						(List<String>) responseObjects.get("roles")));
			}

		}catch (Exception e){
			String error = "No se ha comprobar el usuario";
			log.warn(error,e);
			return response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}/Progress")
	@ResponseBody
	@Operation(summary = "Actualiza y devuelve la información del progreso del usuario de los ultimos 7 dias dado su id, la función utilizada es progress(Long)")
	public ResponseEntity<int[]> progress(@PathVariable Long id) {
		log.info("Se ha recibido una peticion para actualizar el progreso del usuario con id: {}",id);
		ResponseEntity<int[]> response;
		try{
			int[] i = userService.progress(id);
			response = new ResponseEntity<>(i,HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha actualizar el progreso del usuario";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@PostMapping(value = "/{idlesson}/Completed/{points}")
	@ResponseBody
	public ResponseEntity<UserDto> completedLesson(@PathVariable Long idlesson, @PathVariable int points) {
		log.info("Se ha recibido una solicitud para poner si una leccion esta completa");
		ResponseEntity<UserDto> response;
		try{
			UserDto user = userService.lessonComplete(idlesson, points);
			response = new ResponseEntity<>(user, HttpStatus.OK);
		}catch (Exception e){
			String error = "No se ha podido comprobar la leccion";
			log.warn(error,e);
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
}

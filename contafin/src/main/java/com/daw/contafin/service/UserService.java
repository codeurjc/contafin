package com.daw.contafin.service;


import java.util.*;
import java.util.stream.Collectors;

import com.daw.contafin.config.jwt.JwtUtils;
import com.daw.contafin.dto.CompletedLessonDto;
import com.daw.contafin.dto.LessonDto;
import com.daw.contafin.dto.UserDto;
import com.daw.contafin.entity.User;
import com.daw.contafin.mapper.UserMapper;
import com.daw.contafin.repository.UserRepository;
import com.daw.contafin.config.jwt.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	CompletedLessonService completedLessonService;

	@Resource
	UserMapper userMapper;

	@Autowired
	LessonService lessonService;

	@Autowired
	UnitService unitService;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	AuthenticationManager authenticationManager;

	public UserDto findById(long id) {
		log.info("Busqueda de usuario por id: {}", id);
		UserDto userDto;
		try{
			User user = userRepository.findById(id);
			userDto = userMapper.UserToUserDto(user);
		}catch (Exception e){
			log.warn("Error al buscar el usuario");
			userDto = null;
		}
		return userDto;
	}
	
	public UserDto findByEmail (String email) {
		log.info("Busqueda de usuario por email: {}", email);
		UserDto userDto;
		try{
			User user = userRepository.findByEmail(email);
			userDto = userMapper.UserToUserDto(user);
		}catch (Exception e){
			log.warn("Error al buscar el usuario");
			userDto = null;
		}
		return userDto;
	}
	
	public List<UserDto> getUsers () {
		log.info("Busqueda del listado de usuarios");
		List<UserDto> userDtos;
		try{
			List<User> user = userRepository.findAll();
			userDtos = userMapper.UsersToUsersDto(user);
		}catch (Exception e){
			log.warn("Error al buscar los usuarios");
			userDtos = null;
		}
		return userDtos;
	}
	
	public void save(UserDto userDto) {
		log.info("Guardado del usuario: {}", userDto);
		try{
			User user = userMapper.UserDtoToUser(userDto);
			userRepository.save(user);
		}catch (Exception e){
			log.warn("Error al guardar del usuario");
		}
	}
	
	public UserDto updateUser(UserDto userDtoUpdate) {
		log.info("Actualizacion del usuario: {}", userDtoUpdate);
		try{
			userDtoUpdate.setLastConnection(userDtoUpdate.newConnection());
			User user = userRepository.findById(userDtoUpdate.getId());
			if(user != null){
				user = userMapper.UserDtoToUser(userDtoUpdate);
				userRepository.save(user);
			}
		}catch (Exception e){
			log.warn("Error al actualizar el usuario");
			userDtoUpdate = null;
		}
		return userDtoUpdate;
	}

	public UserDto checkPass(Long id, String oldpass, String pass,String name, String email, byte[] file){
		log.info("Comprobar contraseña para el usuario con id: {}", id);
		UserDto userDto = null;
		try{
			userDto = findById(id);
			if(userDto != null){
				if(name != null && !name.isEmpty()){
					userDto.setName(name);
				}
				if(email != null && !email.isEmpty()){
					userDto.setEmail(email);
				}

				if(file != null){
					userDto.setImage(file);
				}

				if (new BCryptPasswordEncoder().matches(oldpass, userDto.getPasswordHash())) {
					if(pass != null && !pass.isEmpty()){
						userDto.setPasswordHash(new BCryptPasswordEncoder().encode(pass));
					}
					save(userDto);
				}else{
					userDto = null;
				}
			}
		}catch (Exception e){
			log.warn("Error al comprobar la contraseña");
		}
		return userDto;
	}

	public void deleteAccount(Long id) {
		log.info("Borrado del usuario con id: {}", id);
		try{
			UserDto userDto = findById(id);
			if(userDto != null){
				User user = userMapper.UserDtoToUser(userDto);
				userRepository.delete(user);
			}
		}catch (Exception e){
			log.warn("Error al borrar el usuario");
		}
	}

	public UserDto getProfile(Long id) {
		log.info("Carga actualizada de datos para el usuario con id: {}", id);
		UserDto userDto;
		try{
			userDto = findById(id);
			if(userDto != null) {
				updateUserData(userDto, false, 0 );
			}
		}catch (Exception e){
			log.warn("Error al cargar el usuario");
			userDto = null;
		}
		return userDto;
	}


	public int [] progress(Long id) {
		log.info("Devuelve el progreso semanal del usuario con id: {}", id);
		int [] progress = new int[7];
		try{
			UserDto userDto = findById(id);
			if(userDto != null){
				//User user = userComponent.getLoggedUser();
				//Get the current date and set first day of week Monday
				Calendar calendar = Calendar.getInstance();
				//Convert java.util.Date to java.sql.Date
				Date date = calendar.getTime();
				//Get the current day
				//Store weekly progress
				for (int i = 6; i>0; i-- ) {
					progress[i]=completedLessonService.getCompletedLessons(userDto, date);
					calendar.add(Calendar.DATE, -1);
					date = calendar.getTime();
				}
			}
		}catch (Exception e){
			log.warn("Error al calcular el progreso");
			progress = null;
		}
		return progress;
	}


	public int getFluency() {
		log.info("Calcular el porcentaje completado de lecciones");
		int percentage;
		try{
			List<LessonDto> lessonDtos = lessonService.findAll();
			int totalNumberOfCompletedLesson = completedLessonService.totalNumberOfCompletedLesson();
			percentage = Math.round((float)totalNumberOfCompletedLesson / lessonDtos.size() * 100);
		}catch (Exception e){
			log.warn("Error al calcular el porcentaje");
			percentage = -1;
		}

		return percentage;
	}

	
	public int getRemainingGoals(UserDto userDto) {
		log.info("Calculo de las metas que le quedan al usuario:{}", userDto);
		int goalsAct;
		try{
			Calendar date = Calendar.getInstance();
			java.util.Date sqlDate =  new java.util.Date((date.getTime()).getTime());
			int nCompleteLesson = completedLessonService.getCompletedLessons(userDto, sqlDate);
			if ( nCompleteLesson >= userDto.getDailyGoal()) {
				goalsAct = 0;
			} else {
				goalsAct = (userDto.getDailyGoal() - nCompleteLesson);
			}
		}catch (Exception e){
			log.warn("Error al calcular las metas");
			goalsAct = -1;
		}

		return goalsAct;


	}

	public void completedLesson(UserDto userDto, Long idlesson, int point) {
		log.info("Poner una leccion como completada");
		try{
			LessonDto lessonDto = lessonService.findById(idlesson);
			Calendar dateC = Calendar.getInstance();
			java.sql.Date date = new java.sql.Date((dateC.getTime()).getTime());
			if (!SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().contains("ROLE_ANONYMOUS")) {
				// If lesson is not completed yet and you do all exercise set the Lesson to done
				CompletedLessonDto completedLessonDtoNew = new CompletedLessonDto(userDto, lessonDto, date);
				completedLessonService.save(completedLessonDtoNew);
				updateUserData(userDto, true, point);

			}

		}catch (Exception e){
			log.warn("Error al poner una leccion como completada");
		}
	}

	public void updateUserData(UserDto userDto, boolean points, int point) {//Poner boolean para ver si sumar exp y nivel
		log.info("Modificar la puntuacion, la ultima unidad y leccion realizada");
		Calendar dateC = Calendar.getInstance();
		java.util.Date date = dateC.getTime();
		try{
			//General
			userDto.setProgress(progress(userDto.getId()));
			userDto.setFluency(getFluency());
			List<CompletedLessonDto> completedLessonDtos = completedLessonService.findByUserOrderByDateDesc(userDto);
			if(completedLessonDtos != null && !completedLessonDtos.isEmpty()){
				CompletedLessonDto completedLessonDto = completedLessonService.findByUserOrderByDateDesc(userDto).get(0);
				userDto.setLastLesson(Long.bitCount(completedLessonDto.getLesson().getId()));
				userDto.setLastUnit((int) unitService.findByLessonsId(completedLessonDto.getLesson().getId()).getId());
			}
			userDto.setRemainingGoals(getRemainingGoals(userDto));
			updateStreak(userDto, completedLessonService.getCompletedLessons(userDto, date));

			if(points){
				userDto.setExp(userDto.getExp() + 10);
				userDto.upLevel();
				userDto.setPoints(updatePoints(userDto.getPoints(), point));
			}
			updateUser(userDto);
		}catch (Exception e){
			log.warn("Error al actualizar la informacion");
		}
	}

	public int updatePoints(int pointsUser, int points) {
		if(pointsUser+points >=0) {
			return pointsUser+points;
		}
		else {
			return 0;
		}
	}

	public UserDto lessonComplete(Long idLesson, int points){
		log.info("Se va a comprobar si la leccion esta completa");
		UserDto user = null;
		try{
			user = findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
			completedLesson(user, idLesson, points);
			user = findById(user.getId());
		}catch (Exception e){
			log.warn("Error al comprobar si la leccion esta completa");
		}
		return user;
	}

	public void updateStreak(UserDto userdto, int completedLessons) {
		boolean hasCompletedLesson = true;
		int streak = 0;
		if(completedLessons >= userdto.getDailyGoal()) {
			Calendar c = Calendar.getInstance();

			while(hasCompletedLesson){
				streak++;
				c.add(Calendar.DAY_OF_MONTH, -1);
				hasCompletedLesson = completedLessonService.getCompletedLessons(userdto, c.getTime()) >= userdto.getDailyGoal();
			}
		}
		userdto.setStreak(streak);
	}

	public Map<String, Object> createAuthentication (String email, String pass){
		Map<String, Object> resultMap = new HashMap<>();
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, pass));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		resultMap.put("token", jwt);
		resultMap.put("userDetails", userDetails);
		resultMap.put("roles", roles);

		return resultMap;
	}

}

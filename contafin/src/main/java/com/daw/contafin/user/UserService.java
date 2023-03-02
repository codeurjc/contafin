package com.daw.contafin.user;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.daw.contafin.ImageService;
import com.daw.contafin.completedLesson.CompletedLessonDto;
import com.daw.contafin.lesson.LessonDto;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.unit.UnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.daw.contafin.completedLesson.CompletedLesson;
import com.daw.contafin.completedLesson.CompletedLessonService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserComponent userComponent;

	@Autowired
	CompletedLessonService completedLessonService;

	@Resource
	UserMapper userMapper;

	@Autowired
	LessonService lessonService;

	@Autowired
	ImageService imageService;

	@Autowired
	UnitService unitService;

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
			User user = userRepository.findById(userDtoUpdate.getId());
			if(user != null){
				userRepository.save(user);
				userComponent.setLoggedUser(userDtoUpdate);
			}
		}catch (Exception e){
			log.warn("Error al actualizar el usuario");
			userDtoUpdate = null;
		}
		return userDtoUpdate;
	}

	public Boolean checkPass(Long id, String pass){
		log.info("Comprobar contraseña para el usuario con id: {}", id);
		Boolean b = false;
		try{
			UserDto userDto = findById(id);
			if(userDto != null){
				if (new BCryptPasswordEncoder().matches(pass, userDto.getPasswordHash())) {
					b = true;
				} else {
					b = false;
				}
			}
		}catch (Exception e){
			log.warn("Error al comprobar la contraseña");
			b = null;
		}
		return b;
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
				int[] progress = progress(id);
				userDto.setProgress(progress);
				userComponent.setLoggedUser(userDto);
			}
		}catch (Exception e){
			log.warn("Error al cargar el usuario");
			userDto = null;
		}
		return userDto;
	}

	public Boolean updatePhoto (Long id, MultipartFile file){
		log.info("Carga actualizada de datos para el usuario con id: {}", id);
		 Boolean b = false;
		try{
			if (!file.isEmpty()) {
				UserDto userDto = findById(id);
				if(userDto != null){
					byte[] bytes = imageService.uploadImage(file);
					// Update the user's data
					userDto.setImage(bytes);
					updateUser(userDto);
					userComponent.setLoggedUser(userDto);
					b = true;
				}
			}
		}catch (Exception e){
			log.warn("Error al cargar el usuario");
			b = null;
		}
		return b;

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
				calendar.setFirstDayOfWeek(Calendar.MONDAY);
				//Convert java.util.Date to java.sql.Date
				Date date = calendar.getTime();
				java.util.Date sqlDate = new java.util.Date(date.getTime());
				//Get the current day
				int day = calendar.get(Calendar.DAY_OF_WEEK);
				//Store weekly progress
				progress[day-1] = completedLessonService.getCompletedLessons(userDto, sqlDate);
				for (int i = day-1; i>0; i-- ) {
					calendar.add(Calendar.DATE, -1);
					date = calendar.getTime();
					sqlDate = new java.util.Date(date.getTime());
					progress[i-1]=completedLessonService.getCompletedLessons(userDto, sqlDate);
				}
				userDto.setProgress(progress);
				updateUser(userDto);
			}
		}catch (Exception e){
			log.warn("Error al calcular el progreso");
			progress = null;
		}
		return progress;
	}


	public int getFluency(UserDto userDto) {
		log.info("Calcular el porcentaje completado de lecciones");
		int percentage;
		try{
			List<LessonDto> lessonDtos = lessonService.findAll();
			List<CompletedLessonDto> lessonCompletedDtos = completedLessonService.findByUser(userDto);
			percentage = lessonCompletedDtos.size() / lessonDtos.size() * 100;
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

	public void completedLesson(UserDto userDto, Long idlesson) {
		log.info("Poner una leccion como completada");
		try{
			LessonDto lessonDto = lessonService.findById(idlesson);
			Calendar dateC = Calendar.getInstance();
			java.sql.Date date = new java.sql.Date((dateC.getTime()).getTime());

			// If lesson is not completed yet and you do all exercise set the Lesson to done
			CompletedLessonDto completedLessonDtoNew = new CompletedLessonDto(userDto, lessonDto, date);
			completedLessonService.save(completedLessonDtoNew);
			if (userComponent.isLoggedUser()) {
				userDto.setFluency(getFluency(userDto));
				updateUserData(userDto, date);
				userComponent.setLoggedUser(userDto);
			}

		}catch (Exception e){
			log.warn("Error al poner una leccion como completada");
		}
	}

	public void updateUserData(UserDto userDto, java.util.Date date) {
		log.info("Modificar la puntuacion, la ultima unidad y leccion realizada");
		try{
			CompletedLessonDto completedLessonDto = completedLessonService.findByUserOrderByDateDesc(userDto).get(0);
			userDto.setFluency(getFluency(userDto));
			userDto.setExp(userDto.getExp() + 10);
			userDto.upLevel();
			userDto.updateStreak(userDto, completedLessonService.getCompletedLessons(userDto, date));
			userDto.setRemainingGoals(getRemainingGoals(userDto));
			userDto.setLastLesson((int) completedLessonDto.getLesson().getId());
			userDto.setLastUnit((int) unitService.findByLessonsId(completedLessonDto.getLesson().getId()).getId());
			updateUser(userDto);
		}catch (Exception e){
			log.warn("Error al actualizar la informacion");
		}
	}
	
}

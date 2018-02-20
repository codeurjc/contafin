package com.daw.contafin.exercise;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseRepository;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonRepository;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;

@Controller
public class ExerciseController {
	
	@Autowired
	private ExerciseService exerciseService;
	
	@Autowired
	private LessonRepository lessonRepository; 
	
	@Autowired
	UserComponent userComponent;
	
	List<String> texts;
	
	@PostConstruct
	public void init () {
		
	}
	
	//Cambiar html lección 
	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/1/{numExercise}")
    public String exercise1(Model model,@PathVariable int id, @PathVariable int numLesson,@PathVariable int numExercise) {
		
		//Si no funciona copiar el codigo del ejercicio 2
		Lesson lesson = lessonRepository.findById(numLesson);
		
		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);
		
		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise+1);
		
		
		model.addAttribute("Image1", exercise.getRuteImages().get(0));
		model.addAttribute("Image2", exercise.getRuteImages().get(1));
		model.addAttribute("Image3", exercise.getRuteImages().get(2));
		model.addAttribute("Text1", exercise.getTexts().get(0));
		model.addAttribute("Text2", exercise.getTexts().get(1));
		model.addAttribute("Text3", exercise.getTexts().get(2));
		model.addAttribute("Statement", exercise.getStatement());
		

		if (nextExercise==null) {
			model.addAttribute("next",false);
			model.addAttribute("end",true);
		}
		else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();
			
			model.addAttribute("next",true);
			model.addAttribute("end",false);
			model.addAttribute("idunit",id);
			model.addAttribute("nextNumExercise",nextNumExercise);
			model.addAttribute("nextType",typeNext);
		}
		model.addAttribute("idlesson",numLesson);

		
    	return "exerciseType1";
    }
	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/2/{numExercise}")
    public String exercise2(Model model,@PathVariable int id, @PathVariable int numLesson,@PathVariable int numExercise) {
		
		Lesson lesson = lessonRepository.findById(numLesson);
		
		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);
		
		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise+1);
		
		
		model.addAttribute("Statement", exercise.getStatement());
		
		if (nextExercise==null) {
			model.addAttribute("next",false);
			model.addAttribute("end",true);
		}
		else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();
			
			model.addAttribute("next",true);
			model.addAttribute("end",false);
			model.addAttribute("idunit",id);
			model.addAttribute("nextNumExercise",nextNumExercise);
			model.addAttribute("nextType",typeNext);
		}
		model.addAttribute("idlesson",numLesson);
		
    	return "exerciseType2";
    }
	
	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/3/{numExercise}")
    public String exercise3(Model model,@PathVariable int id, @PathVariable int numLesson,@PathVariable int numExercise) {
		
		Lesson lesson = lessonRepository.findById(numLesson);
		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);
		
		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise+1);
		texts = exerciseService.findById(numExercise).getTexts();
		
		
		model.addAttribute("Statement", exercise.getStatement());
		model.addAttribute("texts", texts);
		
		if (nextExercise==null) {
			model.addAttribute("next",false);
			model.addAttribute("end",true);
		}
		else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();
			
			model.addAttribute("next",true);
			model.addAttribute("end",false);
			model.addAttribute("idunit",id);
			model.addAttribute("nextNumExercise",nextNumExercise);
			model.addAttribute("nextType",typeNext);
		}
		model.addAttribute("idlesson",numLesson);
		
    	return "exerciseType3";
    }
	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/4/{numExercise}")
    public String exercise4(Model model,@PathVariable int id, @PathVariable int numLesson,@PathVariable int numExercise) {
		
		Lesson lesson = lessonRepository.findById(numLesson);
		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);
		
		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise+1);
		texts = exerciseService.findById(numExercise).getTexts();
		
		model.addAttribute("Statement", exercise.getStatement());
		model.addAttribute("texts", texts);
		
		if (nextExercise==null) {
			model.addAttribute("next",false);
			model.addAttribute("end",true);
		}
		else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();
			
			model.addAttribute("next",true);
			model.addAttribute("end",false);
			model.addAttribute("idunit",id);
			model.addAttribute("nextNumExercise",nextNumExercise);
			model.addAttribute("nextType",typeNext);
		}
		model.addAttribute("idlesson",numLesson);
		
    	return "exerciseType4";
    }
	
	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/5/{numExercise}")
    public String exercise5(Model model,@PathVariable int id, @PathVariable int numLesson, @PathVariable int numExercise) {
		
		Lesson lesson = lessonRepository.findById(numLesson);
		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);
		
		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise+1);
		texts = exerciseService.findById(numExercise).getTexts();
		
		model.addAttribute("Statement", exercise.getStatement());
		model.addAttribute("texts", texts);
		
		if (nextExercise==null) {
			model.addAttribute("next",false);
			model.addAttribute("end",true);
		}
		else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();
			
			model.addAttribute("next",true);
			model.addAttribute("end",false);
			model.addAttribute("idunit",id);
			model.addAttribute("nextNumExercise",nextNumExercise);
			model.addAttribute("nextType",typeNext);
		}
		model.addAttribute("idlesson",numLesson);
		
		
    	return "exerciseType5";
    }
	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/6/{numExercise}")
    public String exercise6(Model model,@PathVariable int id, @PathVariable int numLesson, @PathVariable int numExercise) {
		
		Lesson lesson = lessonRepository.findById(numLesson);
		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);
		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise+1);
		
		String texts1 = exercise.getTexts().get(0);
		String texts2 = exercise.getTexts().get(1);
		String texts3 = exercise.getTexts().get(2);
		String texts4 = exercise.getTexts().get(3);
		
		model.addAttribute("Statement", exercise.getStatement());
		model.addAttribute("texts1", texts1);
		model.addAttribute("texts2", texts2);
		model.addAttribute("texts3", texts3);
		model.addAttribute("texts4", texts4);
		
		if (nextExercise==null) {
			model.addAttribute("next",false);
			model.addAttribute("end",true);
		}
		else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();
			
			model.addAttribute("next",true);
			model.addAttribute("end",false);
			model.addAttribute("idunit",id);
			model.addAttribute("nextNumExercise",nextNumExercise);
			model.addAttribute("nextType",typeNext);
		}
		model.addAttribute("idlesson",numLesson);
		
    	return "exerciseType6";
    }
	
	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/7/{numExercise}")
    public String exercise7(Model model,@PathVariable int id, @PathVariable int numLesson, @PathVariable int numExercise) {
		
		Lesson lesson = lessonRepository.findById(numLesson);
		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);
		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise+1);
		texts = exerciseService.findById(numExercise).getTexts();
		
		model.addAttribute("Statement", exercise.getStatement());
		model.addAttribute("texts", texts);
		
		if (nextExercise==null) {
			model.addAttribute("next",false);
			model.addAttribute("end",true);
		}
		else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();
			
			model.addAttribute("next",true);
			model.addAttribute("end",false);
			model.addAttribute("idunit",id);
			model.addAttribute("nextNumExercise",nextNumExercise);
			model.addAttribute("nextType",typeNext);
		}
		model.addAttribute("idlesson",numLesson);
		
    	return "exerciseType7";
	}
	
	@RequestMapping("/lesson/{idLesson}/lessonCompleted/") 
     public String completedLesson(Model model,@PathVariable int idLesson) {
		
		
		Lesson lesson = lessonRepository.findById(idLesson);
		List <Exercise> listExercises = exerciseService.findByLesson(lesson);
		
		//Te devuelve el usuario loggeado
		User user = userComponent.getLoggedUser();
		user.setLevel(user.getExp()+10);
		user.upLevel(); 
    	return "completedlesson";
		
		/*Lesson lesson = lessonRepository.findById(numLesson);
		Exercise exercise = exerciseRepository.findByLessonAndKind(lesson,7);
		
		model.addAttribute("idunit",id);
		model.addAttribute("idlesson",numLesson);
		
		model.addAttribute("Statement", exercise.getStatement());
		model.addAttribute("texts", texts);
		
    	return "exerciseType7";*/
	}
	
	@RequestMapping("/continueLesson")
    public String continueLesson(Model model) {
		//obtener el numero de ejercicios completados y saber su porcentaje del total y pasarselo.
		model.addAttribute("percentage", "31");
    	return "continueLesson";
    }
}

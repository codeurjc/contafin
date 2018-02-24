package com.daw.contafin.exercise;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daw.contafin.completedExercise.CompletedExercise;
import com.daw.contafin.completedExercise.CompletedExerciseService;
import com.daw.contafin.completedLesson.CompletedLesson;
import com.daw.contafin.completedLesson.CompletedLessonRepository;
import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonService;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;

@Controller
public class ExerciseController {

	@Autowired
	private ExerciseService exerciseService;

	@Autowired
	private LessonService lessonService;

	@Autowired
	private CompletedExerciseService completedExerciseService;

	@Autowired
	UserComponent userComponent;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private CompletedLessonRepository completedLessonRepository;

	List<String> texts;
	User user;

	@PostConstruct
	public void init() {

	}

	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/1/{numExercise}/Completed")
	public String exerciseCompleted1(Model model, @PathVariable int id, @PathVariable int numLesson,
			@PathVariable int numExercise, @RequestParam String solution) {
		
		user = userComponent.getLoggedUser();

		Lesson lesson = lessonService.findById(numLesson + (3 * (id - 1)));

		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);

		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise + 1);

		String answer = exercise.getAnswer().getResult();

		if (solution.equals(answer)) {
			if (solution.equals("uno")) {
				model.addAttribute("exercise1", "exercise1Good");
				model.addAttribute("exercise2", "exercise1");
				model.addAttribute("exercise3", "exercise1");
				completedExerciseService.save(new CompletedExercise(user,exercise, 0));
			} else if (solution.equals("dos")) {
				model.addAttribute("exercise1", "exercise1");
				model.addAttribute("exercise2", "exercise1Good");
				model.addAttribute("exercise3", "exercise1");
				completedExerciseService.save(new CompletedExercise(user,exercise, 0));
			} else {
				model.addAttribute("exercise1", "exercise1");
				model.addAttribute("exercise2", "exercise1");
				model.addAttribute("exercise3", "exercise1Good");
				completedExerciseService.save(new CompletedExercise(user,exercise, 0));
			}
		} else {
			if (solution.equals("uno")) {
				model.addAttribute("exercise1", "exercise1Bad");
				model.addAttribute("exercise2", "exercise1");
				model.addAttribute("exercise3", "exercise1");
			} else if (solution.equals("dos")) {
				model.addAttribute("exercise1", "exercise1");
				model.addAttribute("exercise2", "exercise1Bad");
				model.addAttribute("exercise3", "exercise1");
			} else {
				model.addAttribute("exercise1", "exercise1");
				model.addAttribute("exercise2", "exercise1");
				model.addAttribute("exercise3", "exercise1Bad");
			}
		}

		model.addAttribute("image1", exercise.getRuteImages().get(0));
		model.addAttribute("image2", exercise.getRuteImages().get(1));
		model.addAttribute("image3", exercise.getRuteImages().get(2));
		model.addAttribute("text1", exercise.getTexts().get(0));
		model.addAttribute("text2", exercise.getTexts().get(1));
		model.addAttribute("text3", exercise.getTexts().get(2));
		model.addAttribute("statement", exercise.getStatement());

		if (nextExercise == null) {
			model.addAttribute("next", false);
			model.addAttribute("end", true);
			model.addAttribute("correct", false);
			model.addAttribute("tocorrectend", false);
		} else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();
			model.addAttribute("next", false);
			model.addAttribute("correct", true);
			model.addAttribute("end", false);
			model.addAttribute("tocorrectend", false);
			model.addAttribute("idunit", id);
			model.addAttribute("nextNumExercise", nextNumExercise);
			model.addAttribute("nextType", typeNext);
		}
		
		model.addAttribute("idunit", id);
		model.addAttribute("idlesson", numLesson);
		model.addAttribute("thisExercise", numExercise);

		return "exerciseType1";
	}

	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/1/{numExercise}")
	public String exercise1(Model model, @PathVariable int id, @PathVariable int numLesson,
			@PathVariable int numExercise) {

		model.addAttribute("exercise1", "exercise1");
		model.addAttribute("exercise2", "exercise1");
		model.addAttribute("exercise3", "exercise1");

		Lesson lesson = lessonService.findById(numLesson + (3 * (id - 1)));

		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);

		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise + 1);

		model.addAttribute("image1", exercise.getRuteImages().get(0));
		model.addAttribute("image2", exercise.getRuteImages().get(1));
		model.addAttribute("image3", exercise.getRuteImages().get(2));
		model.addAttribute("text1", exercise.getTexts().get(0));
		model.addAttribute("text2", exercise.getTexts().get(1));
		model.addAttribute("text3", exercise.getTexts().get(2));
		model.addAttribute("statement", exercise.getStatement());

		if (nextExercise == null) {
			model.addAttribute("next", false);
			model.addAttribute("correct", false);
			model.addAttribute("end", false);
			model.addAttribute("tocorrectend", true);
		} 
		else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();

			model.addAttribute("next", true);
			model.addAttribute("correct", false);
			model.addAttribute("end", false);
			model.addAttribute("tocorrectend", false);
			model.addAttribute("nextNumExercise", nextNumExercise);
			model.addAttribute("nextType", typeNext);

		}
		model.addAttribute("idunit", id);
		model.addAttribute("idlesson", numLesson);
		model.addAttribute("thisExercise", numExercise);

		return "exerciseType1";
	}

	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/2/{numExercise}/Completed")
	public String exerciseCompleted2(Model model, @PathVariable int id, @PathVariable int numLesson,
			@PathVariable int numExercise, @RequestParam String solution) {

		Lesson lesson = lessonService.findById(numLesson + (3 * (id - 1)));

		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);

		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise + 1);

		String answerCorrect = exercise.getAnswer().getResult();

		String[] answer = answerCorrect.split(" ");
		String[] solutionparts = solution.split(" ");
		int counter = 0;

		for (int i = 0; i < solutionparts.length; i++) {
			for (int j = 0; j < answer.length; j++) {
				if (solutionparts[i].equals(answer[j])) {
					counter++;
				}
			}
		}
		if (counter >= 3) {
			model.addAttribute("color", "exercise1Good");
			completedExerciseService.save(new CompletedExercise(user,exercise, 0));
		} else {
			model.addAttribute("color", "exercise1Bad");
		}

		model.addAttribute("statement", exercise.getStatement());

		if (nextExercise == null) {
			model.addAttribute("next", false);
			model.addAttribute("end", true);
			model.addAttribute("correct", false);
			model.addAttribute("tocorrectend", false);
		} else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();
			model.addAttribute("next", false);
			model.addAttribute("correct", true);
			model.addAttribute("end", false);
			model.addAttribute("tocorrectend", false);
			model.addAttribute("idunit", id);
			model.addAttribute("nextNumExercise", nextNumExercise);
			model.addAttribute("nextType", typeNext);
		}
		
		model.addAttribute("idunit", id);
		model.addAttribute("idlesson", numLesson);
		model.addAttribute("thisExercise", numExercise);

		return "exerciseType2";
	}

	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/2/{numExercise}")
	public String exercise2(Model model, @PathVariable int id, @PathVariable int numLesson,
			@PathVariable int numExercise) {

		model.addAttribute("color", "exercise2");

		Lesson lesson = lessonService.findById(numLesson + (3 * (id - 1)));

		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);

		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise + 1);

		model.addAttribute("statement", exercise.getStatement());

		if (nextExercise == null) {
			model.addAttribute("next", false);
			model.addAttribute("correct", false);
			model.addAttribute("end", false);
			model.addAttribute("tocorrectend", true);
		} 
		else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();

			model.addAttribute("next", true);
			model.addAttribute("correct", false);
			model.addAttribute("end", false);
			model.addAttribute("tocorrectend", false);
			model.addAttribute("nextNumExercise", nextNumExercise);
			model.addAttribute("nextType", typeNext);

		}
		model.addAttribute("idunit", id);
		model.addAttribute("idlesson", numLesson);
		model.addAttribute("thisExercise", numExercise);

		return "exerciseType2";
	}

	/*
	 * @RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/3/{numExercise}")
	 * public String exercise3(Model model,@PathVariable int id, @PathVariable int
	 * numLesson,@PathVariable int numExercise) {
	 * 
	 * Lesson lesson = lessonRepository.findById(numLesson+(3*(id-1))); Exercise
	 * exercise = exerciseService.findByLessonAndId(lesson, numExercise);
	 * 
	 * Exercise nextExercise = exerciseService.findByLessonAndId(lesson,
	 * numExercise+1); texts = exerciseService.findById(numExercise).getTexts();
	 * 
	 * 
	 * model.addAttribute("Statement", exercise.getStatement());
	 * model.addAttribute("texts", texts);
	 * 
	 * if (nextExercise==null) { model.addAttribute("next",false);
	 * model.addAttribute("end",true); } else { int typeNext =
	 * nextExercise.getKind(); long nextNumExercise = nextExercise.getId();
	 * 
	 * model.addAttribute("next",true); model.addAttribute("end",false);
	 * model.addAttribute("idunit",id);
	 * model.addAttribute("nextNumExercise",nextNumExercise);
	 * model.addAttribute("nextType",typeNext); }
	 * model.addAttribute("idlesson",numLesson);
	 * 
	 * return "exerciseType3"; }
	 * 
	 * @RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/4/{numExercise}")
	 * public String exercise4(Model model,@PathVariable int id, @PathVariable int
	 * numLesson,@PathVariable int numExercise) {
	 * 
	 * Lesson lesson = lessonRepository.findById(numLesson+(3*(id-1))); Exercise
	 * exercise = exerciseService.findByLessonAndId(lesson, numExercise);
	 * 
	 * Exercise nextExercise = exerciseService.findByLessonAndId(lesson,
	 * numExercise+1); texts = exerciseService.findById(numExercise).getTexts();
	 * 
	 * model.addAttribute("Statement", exercise.getStatement());
	 * model.addAttribute("texts", texts);
	 * 
	 * if (nextExercise==null) { model.addAttribute("next",false);
	 * model.addAttribute("end",true); } else { int typeNext =
	 * nextExercise.getKind(); long nextNumExercise = nextExercise.getId();
	 * 
	 * model.addAttribute("next",true); model.addAttribute("end",false);
	 * model.addAttribute("idunit",id);
	 * model.addAttribute("nextNumExercise",nextNumExercise);
	 * model.addAttribute("nextType",typeNext); }
	 * model.addAttribute("idlesson",numLesson);
	 * 
	 * return "exerciseType4"; }
	 */

	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/5/{numExercise}/Completed")
	public String exerciseCompleted5(Model model, @PathVariable int id, @PathVariable int numLesson,
			@PathVariable int numExercise, @RequestParam String solution) {

		Lesson lesson = lessonService.findById(numLesson + (3 * (id - 1)));

		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);

		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise + 1);

		String answer = exercise.getAnswer().getResult();

		if (solution.equals(answer)) {
			if (solution.equals("uno")) {
				model.addAttribute("exercise1", "exercise1Good");
				model.addAttribute("exercise2", "exercise1");
				model.addAttribute("exercise3", "exercise1");
				completedExerciseService.save(new CompletedExercise(user,exercise, 0));
			} else if (solution.equals("dos")) {
				model.addAttribute("exercise1", "exercise1");
				model.addAttribute("exercise2", "exercise1Good");
				model.addAttribute("exercise3", "exercise1");
				completedExerciseService.save(new CompletedExercise(user,exercise, 0));
			} else {
				model.addAttribute("exercise1", "exercise1");
				model.addAttribute("exercise2", "exercise1");
				model.addAttribute("exercise3", "exercise1Good");
				completedExerciseService.save(new CompletedExercise(user,exercise, 0));
			}
		} else {
			if (solution.equals("uno")) {
				model.addAttribute("exercise1", "exercise1Bad");
				model.addAttribute("exercise2", "exercise1");
				model.addAttribute("exercise3", "exercise1");
			} else if (solution.equals("dos")) {
				model.addAttribute("exercise1", "exercise1");
				model.addAttribute("exercise2", "exercise1Bad");
				model.addAttribute("exercise3", "exercise1");
			} else {
				model.addAttribute("exercise1", "exercise1");
				model.addAttribute("exercise2", "exercise1");
				model.addAttribute("exercise3", "exercise1Bad");
			}
		}

		texts = exerciseService.findById(numExercise).getTexts();

		model.addAttribute("text1", texts.get(0));
		model.addAttribute("text2", texts.get(1));
		model.addAttribute("text3", texts.get(2));
		model.addAttribute("statement", exercise.getStatement());

		if (nextExercise == null) {
			model.addAttribute("next", false);
			model.addAttribute("end", true);
			model.addAttribute("correct", false);
			model.addAttribute("tocorrectend", false);
		} else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();
			model.addAttribute("next", false);
			model.addAttribute("correct", true);
			model.addAttribute("end", false);
			model.addAttribute("tocorrectend", false);
			model.addAttribute("idunit", id);
			model.addAttribute("nextNumExercise", nextNumExercise);
			model.addAttribute("nextType", typeNext);
		}
		model.addAttribute("idunit", id);
		model.addAttribute("idlesson", numLesson);
		model.addAttribute("thisExercise", numExercise);

		return "exerciseType5";
	}

	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/5/{numExercise}")
	public String exercise5(Model model, @PathVariable int id, @PathVariable int numLesson,
			@PathVariable int numExercise) {

		model.addAttribute("exercise1", "exercise1");
		model.addAttribute("exercise2", "exercise1");
		model.addAttribute("exercise3", "exercise1");

		Lesson lesson = lessonService.findById(numLesson + (3 * (id - 1)));
		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);

		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise + 1);
		texts = exerciseService.findById(numExercise).getTexts();

		model.addAttribute("text1", texts.get(0));
		model.addAttribute("text2", texts.get(1));
		model.addAttribute("text3", texts.get(2));
		model.addAttribute("statement", exercise.getStatement());

		if (nextExercise == null) {
			model.addAttribute("next", false);
			model.addAttribute("correct", false);
			model.addAttribute("end", false);
			model.addAttribute("tocorrectend", true);
		} 
		else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();

			model.addAttribute("next", true);
			model.addAttribute("correct", false);
			model.addAttribute("end", false);
			model.addAttribute("tocorrectend", false);
			model.addAttribute("nextNumExercise", nextNumExercise);
			model.addAttribute("nextType", typeNext);

		}
		model.addAttribute("idunit", id);
		model.addAttribute("idlesson", numLesson);
		model.addAttribute("thisExercise", numExercise);

		return "exerciseType5";
	}
	/*
	 * @RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/6/{numExercise}")
	 * public String exercise6(Model model,@PathVariable int id, @PathVariable int
	 * numLesson, @PathVariable int numExercise) {
	 * 
	 * Lesson lesson = lessonRepository.findById(numLesson+(3*(id-1))); Exercise
	 * exercise = exerciseService.findByLessonAndId(lesson, numExercise); Exercise
	 * nextExercise = exerciseService.findByLessonAndId(lesson, numExercise+1);
	 * 
	 * String texts1 = exercise.getTexts().get(0); String texts2 =
	 * exercise.getTexts().get(1); String texts3 = exercise.getTexts().get(2);
	 * String texts4 = exercise.getTexts().get(3);
	 * 
	 * model.addAttribute("Statement", exercise.getStatement());
	 * model.addAttribute("texts1", texts1); model.addAttribute("texts2", texts2);
	 * model.addAttribute("texts3", texts3); model.addAttribute("texts4", texts4);
	 * 
	 * if (nextExercise==null) { model.addAttribute("next",false);
	 * model.addAttribute("end",true); } else { int typeNext =
	 * nextExercise.getKind(); long nextNumExercise = nextExercise.getId();
	 * 
	 * model.addAttribute("next",true); model.addAttribute("end",false);
	 * model.addAttribute("idunit",id);
	 * model.addAttribute("nextNumExercise",nextNumExercise);
	 * model.addAttribute("nextType",typeNext); }
	 * model.addAttribute("idlesson",numLesson);
	 * 
	 * return "exerciseType6"; }
	 */

	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/7/{numExercise}/Completed")
	public String exerciseCompleted7(Model model, @PathVariable int id, @PathVariable int numLesson,
			@PathVariable int numExercise, @RequestParam String solution) {

		Lesson lesson = lessonService.findById(numLesson + (3 * (id - 1)));

		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);

		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise + 1);

		String answer = exercise.getAnswer().getResult();

		if (solution.equals(answer)) {
			if (solution.equals("uno")) {
				model.addAttribute("exercise1", "exercise1Good");
				model.addAttribute("exercise2", "exercise1");
				model.addAttribute("exercise3", "exercise1");
				completedExerciseService.save(new CompletedExercise(user,exercise, 0));
			} else if (solution.equals("dos")) {
				model.addAttribute("exercise1", "exercise1");
				model.addAttribute("exercise2", "exercise1Good");
				model.addAttribute("exercise3", "exercise1");
				completedExerciseService.save(new CompletedExercise(user,exercise, 0));
			} else {
				model.addAttribute("exercise1", "exercise1");
				model.addAttribute("exercise2", "exercise1");
				model.addAttribute("exercise3", "exercise1Good");
				completedExerciseService.save(new CompletedExercise(user,exercise, 0));
			}
		} else {
			if (solution.equals("uno")) {
				model.addAttribute("exercise1", "exercise1Bad");
				model.addAttribute("exercise2", "exercise1");
				model.addAttribute("exercise3", "exercise1");
			} else if (solution.equals("dos")) {
				model.addAttribute("exercise1", "exercise1");
				model.addAttribute("exercise2", "exercise1Bad");
				model.addAttribute("exercise3", "exercise1");
			} else {
				model.addAttribute("exercise1", "exercise1");
				model.addAttribute("exercise2", "exercise1");
				model.addAttribute("exercise3", "exercise1Bad");
			}
		}

		texts = exerciseService.findById(numExercise).getTexts();

		model.addAttribute("text1", texts.get(0));
		model.addAttribute("text2", texts.get(1));
		model.addAttribute("text3", texts.get(2));
		model.addAttribute("statement", exercise.getStatement());

		if (nextExercise == null) {
			model.addAttribute("next", false);
			model.addAttribute("end", true);
			model.addAttribute("correct", false);
			model.addAttribute("tocorrectend", false);
		} else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();
			model.addAttribute("next", false);
			model.addAttribute("correct", true);
			model.addAttribute("end", false);
			model.addAttribute("tocorrectend", false);
			model.addAttribute("idunit", id);
			model.addAttribute("nextNumExercise", nextNumExercise);
			model.addAttribute("nextType", typeNext);
		}
		model.addAttribute("idunit", id);
		model.addAttribute("idlesson", numLesson);
		model.addAttribute("thisExercise", numExercise);

		return "exerciseType7";
	}

	@RequestMapping("/Unit/{id}/lessons/{numLesson}/Exercise/7/{numExercise}")
	public String exercise7(Model model, @PathVariable int id, @PathVariable int numLesson,
			@PathVariable int numExercise) {

		model.addAttribute("exercise1", "exercise1");
		model.addAttribute("exercise2", "exercise1");
		model.addAttribute("exercise3", "exercise1");

		Lesson lesson = lessonService.findById(numLesson + (3 * (id - 1)));

		Exercise exercise = exerciseService.findByLessonAndId(lesson, numExercise);

		Exercise nextExercise = exerciseService.findByLessonAndId(lesson, numExercise + 1);

		model.addAttribute("text1", exercise.getTexts().get(0));
		model.addAttribute("text2", exercise.getTexts().get(1));
		model.addAttribute("text3", exercise.getTexts().get(2));
		model.addAttribute("statement", exercise.getStatement());

		if (nextExercise == null) {
			model.addAttribute("next", false);
			model.addAttribute("correct", false);
			model.addAttribute("end", false);
			model.addAttribute("tocorrectend", true);
		} 
		else {
			int typeNext = nextExercise.getKind();
			long nextNumExercise = nextExercise.getId();

			model.addAttribute("next", true);
			model.addAttribute("correct", false);
			model.addAttribute("end", false);
			model.addAttribute("tocorrectend", false);
			model.addAttribute("nextNumExercise", nextNumExercise);
			model.addAttribute("nextType", typeNext);

		}
		model.addAttribute("idunit", id);
		model.addAttribute("idlesson", numLesson);
		model.addAttribute("thisExercise", numExercise);

		return "exerciseType7";
	}

	@RequestMapping("/lesson/{idlesson}/lessonCompleted/")
	public String completedLesson(Model model, @PathVariable int idlesson) {

		Lesson lesson = lessonService.findById(idlesson);
		List<Exercise> listExercises = exerciseService.findByLesson(lesson);

		user = userComponent.getLoggedUser();
		
		// Añadir la lección al respositorio de lecciones completadas si no estaba ya.

		Date date = new Date(0); 
		CompletedLesson completedLessonS = completedLessonRepository.findByUserAndLesson(user,lesson);
		if(completedLessonS == null) {
			CompletedLesson completedLesson = new CompletedLesson(user, lesson, date);
			completedLessonRepository.save(completedLesson);
			if(userComponent.getLoggedUser().getRoles().contains("ROLE_USER")) {
				user.setExp(user.getExp() + 10);
				user.upLevel();
				userService.updateUserData(user);
				userComponent.setLoggedUser(user);
			}
		}
		
		return "completedLesson";	
	}

	@RequestMapping("/continueLesson")
	public String continueLesson(Model model) {
		User user = userComponent.getLoggedUser();
		
		List<Lesson> lessons = lessonService.getAll();
		List<CompletedLesson> lessonsCompleted = completedLessonRepository.findByUser(user);
		
		int numLessons = lessons.size();
		int numLessonsCompleted = lessonsCompleted.size();
		double percentageD = (double)numLessonsCompleted / numLessons * 100;
		int percentage = (int)percentageD;
		model.addAttribute("percentage", percentage);
		return "continueLesson";
	}
}

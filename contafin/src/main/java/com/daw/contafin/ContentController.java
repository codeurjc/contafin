package com.daw.contafin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.daw.contafin.completedLesson.CompletedLesson;
import com.daw.contafin.completedLesson.CompletedLessonRepository;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.unit.Unit;
import com.daw.contafin.unit.UnitService;
import com.daw.contafin.unit.UnitsComplete;
import com.daw.contafin.user.User;
import com.daw.contafin.user.UserComponent;
import com.daw.contafin.user.UserService;

@Controller
public class ContentController {

	@Autowired
	UserComponent userComponent;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UnitService unitService;
	
	@Autowired
	private CompletedLessonRepository completedLessonRepository;
	
	public void loadNavbar(Model model) {
	
		if (userComponent.isLoggedUser()) {
			model.addAttribute("name", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getName());
			model.addAttribute("level", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getLevel());
			model.addAttribute("points", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getPoints());
			model.addAttribute("streak", userService.findByEmail(userComponent.getLoggedUser().getEmail()).getStreak());
		}
	}
	
	public void loadUnits(Model model) {

		User user = userComponent.getLoggedUser();
		
		List<Unit> unit = unitService.findAll();
		
		List<UnitsComplete> unitsCompletes = new ArrayList<>();
		
		for (int i = 0; i < unit.size(); i++) {
			unitsCompletes.add(new UnitsComplete(unit.get(i).getName()));
			
			List<Lesson> lessons = unit.get(i).getLessons();
			
			for(int j = 0; j < lessons.size(); j++) {
				
				int done = 0;
				
				CompletedLesson completedLesson1 = completedLessonRepository.findByUserAndLesson(user, lessons.get(0));
				if(completedLesson1 != null) {
					done++;
					}
				CompletedLesson completedLesson2 = completedLessonRepository.findByUserAndLesson(user, lessons.get(1));
				if(completedLesson2 != null) {
					done++;
					}
				CompletedLesson completedLesson3 = completedLessonRepository.findByUserAndLesson(user, lessons.get(2));
				if(completedLesson3 != null) {
					done++;
					}
				unitsCompletes.get(i).setLessonsDone(done);
				if(done == 3) {
					unitsCompletes.get(i).setDone(true);
				}
			}
			
		}
		model.addAttribute("unitsCompletes", unitsCompletes);
	}
}

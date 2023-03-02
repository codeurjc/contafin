package com.daw.contafin.unit;

import com.daw.contafin.ImageService;
import com.daw.contafin.answer.AnswerService;
import com.daw.contafin.exercise.ExerciseDto;
import com.daw.contafin.lesson.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UnitServiceTests {

    @InjectMocks
    @Spy
    UnitService unitService;

    @Mock
    UnitRepository unitRepository;

    @Mock
    AnswerService answerService;

    @Mock
    UnitMapper unitMapper;


    @Test
    public void findById(){
        //GIVEN
        Long id = 2L;
        UnitDto unitDto = new UnitDto();
        Unit unit = new Unit();

        //THEN
        when(unitRepository.findById(id)).thenReturn(Optional.of(unit));
        when(unitMapper.UnitToUnitDto(unit)).thenReturn(unitDto);

        unitService.findById(id);

    }

    @Test
    public void findByIdError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(unitRepository.findById(id)).thenReturn(null);
        when(unitMapper.UnitToUnitDto(null)).thenThrow(npe);

        unitService.findById(id);
    }

    @Test
    public void findByLessonsId(){
        //GIVEN
        Long id = 2L;
        UnitDto unitDto = new UnitDto();
        Unit unit = new Unit();

        //THEN
        when(unitRepository.findByLessonsId(id)).thenReturn(unit);
        when(unitMapper.UnitToUnitDto(unit)).thenReturn(unitDto);

        unitService.findByLessonsId(id);

    }

    @Test
    public void findByLessonsIdError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(unitRepository.findByLessonsId(id)).thenReturn(null);
        when(unitMapper.UnitToUnitDto(null)).thenThrow(npe);

        unitService.findByLessonsId(id);
    }

    @Test
    public void findAll(){
        //GIVEN
        List<Unit> unitList = new ArrayList<>();
        List<UnitDto> unitDtoList = new ArrayList<>();

        //THEN
        when(unitRepository.findAll()).thenReturn(unitList);
        when(unitMapper.UnitsToUnitsDto(unitList)).thenReturn(unitDtoList);

        unitService.findAll();

    }

    @Test
    public void findAllError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        when(unitRepository.findAll()).thenThrow(npe);

        unitService.findAll();

    }

    @Test
    public void save(){
        //GIVEN
        UnitDto unitDto = new UnitDto();
        Unit unit = new Unit();

        //THEN
        when(unitMapper.UnitDtoToUnit(unitDto)).thenReturn(unit);
        when(unitRepository.save(unit)).thenReturn(unit);

        unitService.save(unitDto);

    }

    @Test
    public void saveError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(unitMapper.UnitDtoToUnit(null)).thenThrow(npe);

        unitService.save(null);

    }


    @Test
    public void saveUnitComplete(){
        //GIVEN
        UnitDto unitDto = new UnitDto();
        List<LessonDto> lessonDtoList = new ArrayList<>();
        LessonDto lessonDto = new LessonDto();
        List<ExerciseDto> exerciseDtoList = new ArrayList<>();
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDtoList.add(exerciseDto);
        lessonDto.setExercises(exerciseDtoList);
        lessonDtoList.add(lessonDto);
        unitDto.setLessons(lessonDtoList);


        //THEN
        when(answerService.save(exerciseDto.getAnswer())).thenReturn(exerciseDto.getAnswer());
        Mockito.doNothing().when(unitService).save(unitDto);

        unitService.saveUnitComplete(unitDto);

    }

    @Test
    public void saveUnitCompleteError(){
        Exception npe = new NullPointerException();
        UnitDto unitDto = new UnitDto();
        List<LessonDto> lessonDtoList = new ArrayList<>();
        LessonDto lessonDto = new LessonDto();
        List<ExerciseDto> exerciseDtoList = new ArrayList<>();
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDtoList.add(exerciseDto);
        lessonDto.setExercises(exerciseDtoList);
        lessonDtoList.add(lessonDto);
        unitDto.setLessons(lessonDtoList);

        when(answerService.save(exerciseDto.getAnswer())).thenThrow(npe);

        unitService.saveUnitComplete(unitDto);
    }




}

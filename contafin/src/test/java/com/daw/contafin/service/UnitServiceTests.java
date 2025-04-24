package com.daw.contafin.service;

import com.daw.contafin.entity.Exercise;
import com.daw.contafin.entity.Lesson;
import com.daw.contafin.repository.UnitRepository;
import com.daw.contafin.dto.LessonDto;
import com.daw.contafin.dto.UnitDto;
import com.daw.contafin.entity.Unit;
import com.daw.contafin.dto.ExerciseDto;
import com.daw.contafin.mapper.UnitMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UnitServiceTests {

    @InjectMocks
    @Spy
    UnitService unitService;

    @Mock
    UnitRepository unitRepository;

    @Mock
    LessonService lessonService;

    @Mock
    ExerciseService exerciseService;

    @Mock
    UnitMapper unitMapper;

    @Test
    public void findById(){
        //GIVEN
        long id = 2L;
        UnitDto unitDto = new UnitDto();
        Unit unit = new Unit();

        //THEN
        when(unitRepository.findById(id)).thenReturn(unit);
        when(unitMapper.UnitToUnitDto(unit)).thenReturn(unitDto);

        assertEquals(unitService.findById(id), unitDto);

    }

    @Test
    public void findByIdError(){
        //GIVEN
        long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(unitRepository.findById(id)).thenReturn(null);
        when(unitMapper.UnitToUnitDto(null)).thenThrow(npe);

        assertEquals(unitService.findById(id), null);
    }

    @Test
    public void findAll(){
        //GIVEN
        List<Unit> unitList = new ArrayList<>();
        List<UnitDto> unitDtoList = new ArrayList<>();

        //THEN
        when(unitRepository.findAll()).thenReturn(unitList);
        when(unitMapper.UnitsToUnitsDto(unitList)).thenReturn(unitDtoList);

        assertEquals(unitService.findAll(), unitDtoList);

    }

    @Test
    public void findAllError(){
        //GIVEN
        Exception npe = new NullPointerException();

        //THEN
        when(unitRepository.findAll()).thenThrow(npe);

        assertEquals(unitService.findAll(), null);

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
        verify(unitRepository).save(same(unit));

    }

    @Test
    public void saveError(){
        //GIVEN
        Exception npe = new NullPointerException();
        //THEN
        when(unitMapper.UnitDtoToUnit(null)).thenThrow(npe);

        unitService.save(null);
        verifyNoInteractions(unitRepository);

    }

    @Test
    public void findByLessonsId(){
        //GIVEN
        long id = 2L;
        UnitDto unitDto = new UnitDto();
        Unit unit = new Unit();

        //THEN
        when(unitRepository.findByLessonsId(id)).thenReturn(unit);
        when(unitMapper.UnitToUnitDto(unit)).thenReturn(unitDto);

        assertEquals(unitService.findByLessonsId(id), unitDto);

    }

    @Test
    public void findByLessonsIdError(){
        //GIVEN
        Long id = 2L;
        Exception npe = new NullPointerException();

        //THEN
        when(unitRepository.findByLessonsId(id)).thenReturn(null);
        when(unitMapper.UnitToUnitDto(null)).thenThrow(npe);

        assertEquals(unitService.findByLessonsId(id), null);
    }


    //delete
    @Test
    public void delete(){
        //GIVEN
        Long id = 1L;

        //THEN
        Mockito.doNothing().when(unitRepository).deleteById(id);

        unitService.delete(id);
        verify(unitRepository).deleteById(same(id));

    }

    @Test
    public void deleteError(){
        //GIVEN
        Long id = 1L;
        Exception npe = new NullPointerException();
        //THEN
        Mockito.doThrow(npe).when(unitRepository).deleteById(id);

        unitService.delete(id);

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

        Unit unit = new Unit();
        List<Lesson> lessonList = new ArrayList<>();
        Lesson lesson = new Lesson();
        List<Exercise> exerciseList = new ArrayList<>();
        Exercise exercise = new Exercise();
        exerciseList.add(exercise);
        lesson.setExercises(exerciseList);
        lessonList.add(lesson);
        unit.setLessons(lessonList);


        //THEN
        Mockito.doNothing().when(unitService).save(unitDto);
        Mockito.doNothing().when(exerciseService).delete(0L);
        Mockito.doNothing().when(lessonService).delete(0L);
        when(unitRepository.findById(unitDto.getId())).thenReturn(unit);

        assertEquals(unitService.saveUnitComplete(unitDto), unitDto);

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

        when(unitRepository.findById(unitDto.getId())).thenThrow(npe);

        assertEquals(unitService.saveUnitComplete(unitDto), null);
    }

    @Test
    void testInit() throws IOException {
        // GIVEN
        when(unitService.findAll()).thenReturn(Collections.emptyList());
        doNothing().when(unitService).save(any(UnitDto.class));

        // WHEN
        unitService.init();

        // THEN
        verify(unitService, atLeastOnce()).findAll();
        verify(unitService, atLeastOnce()).save(any(UnitDto.class));
    }


}

package com.daw.contafin.unit;

import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Mapper( componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UnitMapper {

    UnitDto UnitToUnitDto(Unit unit);
    Unit UnitDtoToUnit(UnitDto unitDto);

    List<UnitDto> UnitsToUnitsDto(Collection<Unit> units);
    List<Unit> UnitsDtoToUnits(Collection<UnitDto> unitsDto);
}
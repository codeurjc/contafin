package com.daw.contafin.mapper;

import com.daw.contafin.dto.UnitDto;
import com.daw.contafin.entity.Unit;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper( componentModel = "spring", builder = @Builder(disableBuilder = true),uses = LessonMapper.class)
public interface UnitMapper {

    UnitDto UnitToUnitDto(Unit unit);
    Unit UnitDtoToUnit(UnitDto unitDto);

    List<UnitDto> UnitsToUnitsDto(Collection<Unit> units);
}

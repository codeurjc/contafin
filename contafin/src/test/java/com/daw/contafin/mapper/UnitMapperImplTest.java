package com.daw.contafin.mapper;

import com.daw.contafin.dto.CompletedLessonDto;
import com.daw.contafin.dto.UnitDto;
import org.junit.jupiter.api.BeforeEach;
import com.daw.contafin.entity.Unit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UnitMapperImplTest {

    @Autowired
    private UnitMapper unitMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUnitToUnitDto() {
        Unit unit = new Unit();
        unit.setId(1L);
        unit.setName("Unit Name");
        // Mock other fields if necessary

        UnitDto unitDto = unitMapper.UnitToUnitDto(unit);

        assertNotNull(unitDto);
        assertEquals(unit.getId(), unitDto.getId());
        assertEquals(unit.getName(), unitDto.getName());
        // Add assertions for other fields if necessary
    }

    @Test
    public void testUnitDtoToUnit() {
        UnitDto unitDto = new UnitDto();
        unitDto.setId(1L);
        unitDto.setName("Unit Name");
        // Mock other fields if necessary

        Unit unit = unitMapper.UnitDtoToUnit(unitDto);

        assertNotNull(unit);
        assertEquals(unitDto.getId(), unit.getId());
        assertEquals(unitDto.getName(), unit.getName());
        // Add assertions for other fields if necessary
    }

    @Test
    public void testUnitsToUnitsDto() {
        Unit unit1 = new Unit();
        unit1.setId(1L);
        Unit unit2 = new Unit();
        unit2.setId(2L);
        List<Unit> units = Arrays.asList(unit1, unit2);

        List<UnitDto> unitDtos = unitMapper.UnitsToUnitsDto(units);

        assertNotNull(unitDtos);
        assertEquals(2, unitDtos.size());
        assertEquals(unit1.getId(), unitDtos.get(0).getId());
        assertEquals(unit2.getId(), unitDtos.get(1).getId());
    }
}
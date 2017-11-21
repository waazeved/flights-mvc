package br.com.embraer.flights.business.service.impl.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.embraer.flights.business.dto.AirplaneDto;
import br.com.embraer.flights.business.dto.convert.AirplaneDtoConverter;
import br.com.embraer.flights.business.exception.FlightException;
import br.com.embraer.flights.business.exception.NameIsAlreadyInUseException;
import br.com.embraer.flights.business.repository.AirplaneRepository;
import br.com.embraer.flights.business.service.impl.AirplaneService;
import br.com.embraer.flights.model.entities.Airplane;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AirplaneServiceTest {

	@InjectMocks
	private AirplaneService service;

	@Mock
	private AirplaneRepository repository;

	@Mock
	private AirplaneDtoConverter converter;

	private Airplane airplaneMock;

	private AirplaneDto airplaneDtoMock;

	@Before
	public void setUp() throws Exception {
		airplaneMock = new Airplane();
		airplaneMock.setId(1l);
		airplaneMock.setName("PTAAA");

		airplaneDtoMock = new AirplaneDto();
		airplaneDtoMock.setId(1l);
		airplaneDtoMock.setName("PTAAA");

		Mockito.doReturn(airplaneMock).when(repository).save(Mockito.any(Airplane.class));
		Mockito.doReturn(airplaneMock).when(converter).toAirplane(Mockito.any(AirplaneDto.class));
		Mockito.doReturn(mockListDto()).when(converter).toDtoList(Mockito.anyListOf(Airplane.class));
	}

	@Test
	public void save() throws FlightException, NameIsAlreadyInUseException{
		Mockito.doReturn(airplaneMock).when(repository).save(Mockito.any(Airplane.class));
		AirplaneDto dto = new AirplaneDto();
		dto.setName("John Galt");
		Assert.assertEquals(airplaneMock, service.save(dto));
	}

	@Test(expected=FlightException.class)
	public void saveNullNameShouldThrowsException() throws FlightException, NameIsAlreadyInUseException {
		AirplaneDto dto = new AirplaneDto();
		dto.setName(null);
		service.save(dto);
	}

	@Test(expected=FlightException.class)
	public void saveEmptyNameShouldThrowsException() throws FlightException, NameIsAlreadyInUseException {
		AirplaneDto dto = new AirplaneDto();
		dto.setName("");
		service.save(dto);
	}

	@Test(expected=FlightException.class)
	public void saveSpaceNameShouldThrowsException() throws FlightException, NameIsAlreadyInUseException {
		AirplaneDto dto = new AirplaneDto();
		dto.setName("    ");
		service.save(dto);
	}

	@Test
	public void shouldReturnAllAirplanes() {
		when(repository.findAll()).thenReturn(mockList());
		List<AirplaneDto> list = service.findAllDto();
		assertEquals(1, list.size());
		long id = list.get(0).getId();
		assertEquals(1l, id);
	}

	private List<Airplane> mockList() {
		List<Airplane> list = new ArrayList<>();
		list.add(airplaneMock);
		return list;
	}

	private List<AirplaneDto> mockListDto() {
		List<AirplaneDto> list = new ArrayList<>();
		list.add(airplaneDtoMock);
		return list;
	}

}

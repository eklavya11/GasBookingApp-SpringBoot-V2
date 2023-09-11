package com.egasbooking.app.service;
//package com.cg.onlinegasbooking.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.cg.nutritions.entity.Gender;
//import com.cg.nutritions.entity.Role;
//import com.cg.nutritions.entity.UserTable;
//import com.cg.nutritions.repository.UserRepository;
//import com.cg.nutritions.services.UserService;
//import com.cg.nutritions.services.UserServiceImpl;
//import com.cg.onlinegasbooking.entity.Cylinder;
//import com.cg.onlinegasbooking.exceptions.ResourceNotFoundException;
//import com.cg.onlinegasbooking.repository.CylinderRepository;
//
//@SpringBootTest
//public class CylinderServiceTest {
//	@Mock
//	CylinderRepository cylinderRepository; // mock implemented object for IProductDao interface
//	
//	@InjectMocks
//	CylinderService service = new CylinderServiceImpl(); // injecting productDao object into service
//	
//	List<Cylinder> cylinderlist;
//	Cylinder c;
//	
//	@BeforeEach
//	public void beforeTest() {
//	cylinderlist = new ArrayList<>();
//	c = new Cylinder();
//	c.setCylinderId(10043);
//	c.setType("ABC");
//	c.setWeight(14.5f);
//	c.setPrice(900.5f);
//	c.setStrapColor("RED");
//	cylinderlist.add(c);
//}
//	@Test
//	@Order(1)
//	void testinsertCylinder() {
//		System.out.println("Testing Inserting product");
//
//		when(cylinderRepository.save(c)).thenReturn(c);
//		when(cylinderRepository.findAll()).thenReturn(cylinderlist);
//		List<Cylinder> l1 = service.saveUser(c);
//
//		assertTrue(l1.size() > 0);
//		assertEquals(1, l1.size());
//		assertEquals("Akash", l1.get(0).getUserName());
//
//		verify(userRepository, times(1)).save(u);
//		verify(userRepository, times(1)).findAll();
//
//	}
//
//	}
//
//	void testInsertCylinder() throws ResourceNotFoundException {
//	Cylinder cylinder = new Cylinder(100145, "HP", 16.7f, "RED", 950);
//	cylinderRepository.save(cylinder);
//	}
//
//	@Test
//	void testUpdateCylinder() throws ResourceNotFoundException {
//	Cylinder cylinder = new Cylinder(101112, "HP", 16.7f, "RED", 980);
//	cylinderRepository.save(cylinder);
//	}
//
//	@Test
//	void testDeleteCylinder() throws ResourceNotFoundException {
//	Cylinder cylinder = new Cylinder(101112, "HP", 16.7f, "RED", 980);
//	cylinderRepository.delete(cylinder);
//	}
//
//	@Test
//	void testViewCylinderByType() throws ResourceNotFoundException {
//		Cylinder cylinder = new Cylinder(100012, "LPG", 16.7f, "RED", 1000);
//		cylinderRepository.save(cylinder);
//		assertNotNull(cylinderService.viewCylinderByType(cylinder.getType()));
////		cylinderRepository.delete(cylinder);
//	}
//	@Test
//	void testViewCylinderByWeight() throws ResourceNotFoundException {
//		Cylinder cylinder = new Cylinder(101312, "ABC", 16.7f, "ORANGE", 1100);
//		cylinderRepository.save(cylinder);
//		assertNotNull(cylinderService.viewCylinderByWeight(cylinder.getWeight()));
////		cylinderRepository.delete(cylinder);
//	}
//	@Test
//	void testViewCylinderByStrapColor() throws ResourceNotFoundException {
//		Cylinder cylinder = new Cylinder(101315, "ABC", 16.7f, "RED", 1100);
//		cylinderRepository.save(cylinder);
//		assertNotNull(cylinderService.viewCylinderByStrapColor(cylinder.getStrapColor()));
////		cylinderRepository.delete(cylinder);
//	}
//
//}

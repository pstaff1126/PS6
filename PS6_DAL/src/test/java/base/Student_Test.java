package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import domain.StudentDomainModel;

public class Student_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse("1993-11-26");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		StudentDomainModel per1 = new StudentDomainModel();
		per1.setFirstName("Pat");
		per1.setLastName("Stafford");
		per1.setDOB(dDate);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void AddStudentTest()
	{	
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse("1972-07-31");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		StudentDomainModel per1 = new StudentDomainModel();
		per1.setFirstName("Pat");
		per1.setLastName("Stafford");
		per1.setDOB(dDate);
		
		StudentDomainModel per;		
		per = StudentDAL.getStudent(per1.getStudentID());		
		assertNull("The Student shouldn't have been in the database",per);		
		StudentDAL.addStudent(per1);	
		
		per = StudentDAL.getStudent(per1.getStudentID());
		System.out.println(per1.getStudentID() + " found");
		assertNotNull("The Student should have been added to the database",per);
	}
	@Test
	public void UpdateStudentTest()
	{		
		StudentDomainModel per;
		final String C_LASTNAME = "Smith";
		
		per = StudentDAL.getStudent(per1.getStudentID());		
		assertNull("The Student shouldn't have been in the database",per);		
		StudentDAL.addStudent(per1);	
		
		per1.setLastName(C_LASTNAME);
		StudentDAL.updateStudent(per1);
		
		per = StudentDAL.getStudent(per1.getStudentID());

		assertTrue("Name Didn't Change",per1.getLastName() == C_LASTNAME);
	}
	@Test
	public void DeleteStudentTest()
	{		
		StudentDomainModel per;		
		per = StudentDAL.getStudent(per1.getStudentID());		
		assertNull("The Student shouldn't have been in the database",per);	
		
		StudentDAL.addStudent(per1);			
		per = StudentDAL.getStudent(per1.getStudentID());
		System.out.println(per1.getStudentID() + " found");
		assertNotNull("The Student should have been added to the database",per);
		
		StudentDAL.deleteStudent(per1.getStudentID());
		per = StudentDAL.getStudent(per1.getStudentID());		
		assertNull("The Student shouldn't have been in the database",per);	
		
	}

}

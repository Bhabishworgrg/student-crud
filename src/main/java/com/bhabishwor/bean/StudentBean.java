package com.bhabishwor.bean;

import com.bhabishwor.model.Student;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@ManagedBean
@ViewScoped
public class StudentBean implements Serializable {
	private List<Student> students;
	private List<String> classes;
	private String selectedClass;
	private List<String> subjects;

	private Student student = new Student();
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentPU");

	@PostConstruct
	public void init() {
		classes = Arrays.asList(
			"Nursery",
			"LKG",
			"UKG",
			"Class 1",
			"Class 2",
			"Class 3",
			"Class 4",
			"Class 5"
		);

		students = loadStudents();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStudents() {
		return students;
	}

	public List<String> getClasses() {
		return classes;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public String getSelectedClass() {
		return selectedClass;
	}

	public void setSelectedClass(String selectedClass) {
		this.selectedClass = selectedClass;
	}

	public void saveStudent() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			student.setStudentClass(selectedClass);

			entityManager.getTransaction().begin();
			entityManager.persist(student);
			entityManager.getTransaction().commit();

			students = loadStudents();
			student = new Student();
		} finally {
			entityManager.close();
		}
	}

	public void updateStudent(Student student) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.merge(student);
			entityManager.getTransaction().commit();

			students = loadStudents();
			student = new Student();
		} finally {
			entityManager.close();
		}
	}

	public void deleteStudent(Student student) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			Student toRemove = entityManager.find(Student.class, student.getId());

			if (toRemove == null) {
				return;
			}

			entityManager.getTransaction().begin();
			entityManager.remove(toRemove);
			entityManager.getTransaction().commit();

			students = loadStudents();
		} finally {
			entityManager.close();
		}
	}

	public void onClassChange() {
		switch (selectedClass) {
			case "Nursery":
				subjects = Arrays.asList(
					"English",
					"Maths",
					"Nepali",
					"Rhymes"
				);
				break;
			case "LKG":
				subjects = Arrays.asList(
					"English",
					"Maths",
					"Nepali",
					"Science",
					"Art"
				);
				break;
			case "UKG":
				subjects = Arrays.asList(
					"English",
					"Maths",
					"Nepali",
					"Science",
					"Art",
					"Social Studies"
				);
				break;
			case "Class 1":
				subjects = Arrays.asList(
					"English",
					"Maths",
					"Nepali",
					"Science",
					"Social Studies"
				);
				break;
			case "Class 2":
				subjects = Arrays.asList(
					"English",
					"Maths",
					"Nepali",
					"Science",
					"Social Studies",
					"Computer"
				);
				break;
			case "Class 3":
				subjects = Arrays.asList(
					"English",
					"Maths",
					"Nepali",
					"Science",
					"Social Studies",
					"Computer",
					"HPE"
				);
				break;
			case "Class 4":
				subjects = Arrays.asList(
					"English",
					"Maths",
					"Nepali",
					"Science",
					"Social Studies",
					"Computer",
					"HPE",
					"Moral Education"
				);
				break;
			case "Class 5":
				subjects = Arrays.asList(
					"English",
					"Maths",
					"Nepali",
					"Science",
					"Social Studies",
					"Computer",
					"HPE",
					"Agriculture"
				);
				break;
			default:
				subjects = new ArrayList<>();
		}
	}

	private List<Student> loadStudents() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			TypedQuery<Student> query = entityManager.createQuery(
				"SELECT student FROM Student student",
				Student.class
			);

			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}
}

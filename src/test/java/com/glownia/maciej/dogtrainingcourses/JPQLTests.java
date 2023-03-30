package com.glownia.maciej.dogtrainingcourses;

import com.glownia.maciej.dogtrainingcourses.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest
class JPQLTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager entityManager;

	@Test
	void testJpqlBasicScenario() {
		List resultList = entityManager.createQuery("Select c From Course c").getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}

	/**
	 * Example of @NamedQuery is set in Course class
 	 */
	@Test
	void testJpqlUsingNamedQuery() {
		TypedQuery<Course> query =
				entityManager.createNamedQuery(
						"query_get_all-courses", // this is set in Course class
						Course.class);

		List<Course> resultList = query.getResultList();

		logger.info("Select c From Course c -> {}", resultList);
	}

	@Test
	void testJpqlWithWhereQuery() {
		TypedQuery<Course> query = entityManager.createQuery("Select c From Course c where name like '%100'", Course.class);

		List<Course> resultList = query.getResultList();

		logger.info("Select c From Course c where name like '%100", resultList);
	}

}

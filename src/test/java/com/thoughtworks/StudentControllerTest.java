package com.thoughtworks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.bean.ThirdBean;
import com.thoughtworks.domain.Student;
import com.thoughtworks.domain.UniClass;
import com.thoughtworks.repository.StudentStorage;
import com.thoughtworks.repository.UniClassStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StudentControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @BeforeEach
    void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
        UniClassStorage.clear();
        StudentStorage.clear();
    }
    @AfterEach
    void teardown() {
        UniClassStorage.clear();
        StudentStorage.clear();
    }

    @Test
    void should_get_classes() throws Exception{
        UniClassStorage.putClass(new UniClass(1, "1601"));
        mockMvc.perform(get("/api/classes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void should_post_student_to_class() throws Exception {
        UniClassStorage.putClass(new UniClass(1, "3"));
        Student student = new Student(1, "liu yanping", 18, 1);
        mockMvc.perform(post("/api/classes/1/students")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("liu yanping"))
                .andExpect(jsonPath("$.age").value(18));

        assertThat(StudentStorage.getStudents().size()).isEqualTo(1);
    }

    @Test
    void should_get_students_of_class() throws Exception{
        UniClassStorage.putClass(new UniClass(1, "5"));
        Student student1 = new Student(1, "liu yanping", 18, 1);
        Student student2 = new Student(2, "xu ya", 16, 1);
        Student student3 = new Student(3, "sun ming", 17, 1);
        Student student4 = new Student(4, "huang lizhen", 17, 2);
        StudentStorage.putStudents(student1, student2, student3, student4);
        mockMvc.perform(get("/api/classes/1/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        assertThat(StudentStorage.getStudentsByClassId(1).size()).isEqualTo(3);

    }

    @Test
    void should_query_student_age_greater_than_given_age() throws Exception{
        UniClassStorage.putClass(new UniClass(1, "5"));
        UniClassStorage.putClass(new UniClass(2, "2"));
        Student student1 = new Student(1, "liu yanping", 22, 1);
        Student student2 = new Student(2, "xu ya", 16, 1);
        Student student3 = new Student(3, "sun ming", 17, 2);
        Student student4 = new Student(4, "huang lizhen", 20, 2);
        StudentStorage.putStudents(student1, student2, student3, student4);
        mockMvc.perform(get("/api/classes/1/students?age_gt=20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("liu yanping"));
    }

    @Test
    void should_query_student_by_age_condition() throws Exception{
        UniClassStorage.putClass(new UniClass(1, "5"));
        UniClassStorage.putClass(new UniClass(2, "2"));
        Student student1 = new Student(1, "liu yanping", 22, 1);
        Student student2 = new Student(2, "xu ya", 16, 1);
        Student student3 = new Student(3, "sun ming", 17, 1);
        Student student4 = new Student(4, "huang lizhen", 20, 1);
        StudentStorage.putStudents(student1, student2, student3, student4);
        mockMvc.perform(get("/api/classes/1/students?age=20"))
                .andExpect(status().isOk());
    }

    @Autowired
    ThirdBean thirdBean;
    @Test
    void should_get_bean_message() {
        assertThat(thirdBean.getBeanMessage().size()).isEqualTo(3);
    }
}

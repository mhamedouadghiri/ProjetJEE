package com.mhamed.ProjetJEE.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mhamed.ProjetJEE.data.*;
import com.mhamed.ProjetJEE.model.*;

import java.util.List;

public class StudentUtils {

    private static final StudentDAO studentDatasource = new StudentDatasource();

    private static final EducationDAO educationDatasource = new EducationDatasource();
    private static final ExperienceDAO experienceDatasource = new ExperienceDatasource();
    private static final LanguageDAO languageDatasource = new LanguageDatasource();
    private static final SkillDAO skillDatasource = new SkillDatasource();

    public static ObjectNode studentInfoAsObjectNode(Long studentId) {
        Student student = (Student) studentDatasource.get(studentId);
        List<Education> educations = educationDatasource.getEducationsByStudentId(studentId);
        List<Experience> experiences = experienceDatasource.getExperiencesByStudentId(studentId);
        List<Language> languages = languageDatasource.getLanguagesByStudentId(studentId);
        List<Skill> skills = skillDatasource.getSkillsByStudentId(studentId);
        return StudentUtils.studentInfoAsObjectNode(student, educations, experiences, languages, skills);
    }

    public static ObjectNode studentInfoAsObjectNode(Student student,
                                                     List<Education> educations,
                                                     List<Experience> experiences,
                                                     List<Language> languages,
                                                     List<Skill> skills) {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.convertValue(student, JsonNode.class);

        ObjectNode studentObjectNode = jsonNode.deepCopy();

        ArrayNode educationsNode = mapper.createArrayNode();
        for (Education education : educations) {
            ObjectNode node = mapper.createObjectNode();
            node.put("id", education.getId());
            node.put("startDate", education.getStartDate() == null ? null : education.getStartDate().toString());
            node.put("endDate", education.getEndDate() == null ? null : education.getEndDate().toString());
            node.put("name", education.getName());
            node.put("level", education.getLevel());
            node.put("studentId", education.getStudentId());
            educationsNode.add(node);
        }
        studentObjectNode.set("educations", educationsNode);

        ArrayNode experiencesNode = mapper.createArrayNode();
        for (Experience experience : experiences) {
            ObjectNode node = mapper.createObjectNode();
            node.put("id", experience.getId());
            node.put("endDate", experience.getEndDate() == null ? null : experience.getEndDate().toString());
            node.put("startDate", experience.getStartDate() == null ? null : experience.getStartDate().toString());
            node.put("description", experience.getDescription());
            node.put("studentId", experience.getStudentId());
            experiencesNode.add(node);
        }
        studentObjectNode.set("experiences", experiencesNode);

        ArrayNode languagesNode = mapper.createArrayNode();
        for (Language language : languages) {
            ObjectNode node = mapper.createObjectNode();
            node.put("id", language.getId());
            node.put("name", language.getName());
            node.put("level", language.getLevel());
            node.put("studentId", language.getStudentId());
            languagesNode.add(node);
        }
        studentObjectNode.set("languages", languagesNode);

        ArrayNode skillsNode = mapper.createArrayNode();
        for (Skill skill : skills) {
            ObjectNode node = mapper.createObjectNode();
            node.put("id", skill.getId());
            node.put("level", skill.getLevel());
            node.put("name", skill.getName());
            node.put("studentId", skill.getStudentId());
            skillsNode.add(node);
        }
        studentObjectNode.set("skills", skillsNode);

        return studentObjectNode;
    }
}

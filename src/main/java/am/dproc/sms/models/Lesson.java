package am.dproc.sms.models;

import java.util.List;

public class Lesson {

    private Integer id;
    private Integer courseId;
    private String name;
    private String content;
    private List<Topic> listOfTopics;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Topic> getListOfTopics() {
        return listOfTopics;
    }

    public void setListOfTopics(List<Topic> listOfTopics) {
        this.listOfTopics = listOfTopics;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", listOfTopics=" + listOfTopics +
                '}';
    }
}

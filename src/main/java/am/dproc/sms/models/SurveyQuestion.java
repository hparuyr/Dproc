package am.dproc.sms.models;

public class SurveyQuestion {

    private Integer id;
    private String content;
    private String inputName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }
}


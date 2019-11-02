package am.dproc.sms.enums;

public enum ClassroomType {
	
	FOR_SEMINAR (1),
	FOR_LECTURE (2),
	GENERAL_PURPOSE(3);

    private final Integer index;

    ClassroomType(Integer index) {
        this.index = index;
    }

    public Integer index() { 
        return index; 
    }

}

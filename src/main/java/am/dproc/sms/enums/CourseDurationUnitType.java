package am.dproc.sms.enums;

public enum CourseDurationUnitType {
	
	DAYS (1),
	WEEKS (2),
	MONTHS (3);

    private final Integer index;

    CourseDurationUnitType(Integer index) {
        this.index = index;
    }

    public Integer index() { 
        return index; 
    }

}

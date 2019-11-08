package am.dproc.sms.models;

public enum StudentStatus {

	PENDING(1),
	SUCCESS(2),
	FAILED(3);

	private final Integer index;

	StudentStatus(Integer index) {
		this.index = index;
	}

	public Integer index() {
		return index;
	}

}

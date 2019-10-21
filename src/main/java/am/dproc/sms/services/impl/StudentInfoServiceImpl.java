package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.StudentInfoDAO;
import am.dproc.sms.models.StudentInfo;
import am.dproc.sms.services.interfaces.StudentInfoService;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {

	@Autowired
	StudentInfoDAO studentInfo;

	@Override
	public Integer addStudentInfo(StudentInfo studentInfo) {
		return this.studentInfo.addStudentInfo(studentInfo);
	}

	@Override
	public int[] addStudentInfos(List<StudentInfo> infos) {
		return studentInfo.addStudentInfos(infos);
	}

	@Override
	public StudentInfo getStudentInfoByStudentId(Integer studentId) {
		return this.studentInfo.getStudentInfo(studentId);
	}

	@Override
	public Integer updateStudentInfo(StudentInfo studentInfo) {
		if (studentInfo.getPassportId() != null) {
			return this.studentInfo.updateStudentInfoPassportId(studentInfo.getUserId(),
					studentInfo.getPassportId());
		} else if (studentInfo.getSocialCardId() != null) {
			return this.studentInfo.updateStudentInfoSocialCardId(studentInfo.getUserId(),
					studentInfo.getSocialCardId());
		} else if (studentInfo.getBirthDate() != null) {
			return this.studentInfo.updateStudentInfoBirthDate(studentInfo.getUserId(), studentInfo.getBirthDate());
		} else if (studentInfo.getImageUrl() != null) {
			return this.studentInfo.updateStudentInfoImageUrl(studentInfo.getUserId(), studentInfo.getImageUrl());
		}
		return 0;
	}

	@Override
	public Integer deleteStudentInfo(Integer studentId) {
		return this.studentInfo.deleteStudentInfo(studentId);
	}

}

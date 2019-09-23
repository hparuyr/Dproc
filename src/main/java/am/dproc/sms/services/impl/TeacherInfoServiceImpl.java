package am.dproc.sms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.TeacherInfoDAO;
import am.dproc.sms.models.TeacherInfo;
import am.dproc.sms.services.interfaces.TeacherInfoService;
@Service
public class TeacherInfoServiceImpl implements TeacherInfoService {

	@Autowired
	TeacherInfoDAO teacherInfo;

	@Override
	public Integer addTeacherInfo(TeacherInfo teacherInfo) {
		return this.teacherInfo.addTeacherInfo(teacherInfo);
	}

	@Override
	public TeacherInfo getTeacherInfoByTeacherId(Integer teacherId) {
		return this.teacherInfo.getTeacherInfo(teacherId);
	}

	@Override
	public Integer updateTeacherInfo(TeacherInfo teacherInfo) {
		if (teacherInfo.getPassportId() != null) {
			return this.teacherInfo.updateTeacherInfoPassportId(teacherInfo.getTeacherId(),
					teacherInfo.getPassportId());
		} else if (teacherInfo.getSocialCardId() != null) {
			return this.teacherInfo.updateTeacherInfoSocialCardId(teacherInfo.getTeacherId(),
					teacherInfo.getSocialCardId());
		} else if (teacherInfo.getBirthDate() != null) {
			return this.teacherInfo.updateTeacherInfoBirthDate(teacherInfo.getTeacherId(), teacherInfo.getBirthDate());
		} else if (teacherInfo.getImageUrl() != null) {
			return this.teacherInfo.updateTeacherInfoImageUrl(teacherInfo.getTeacherId(), teacherInfo.getImageUrl());
		}
		return 0;
	}

	@Override
	public Integer deleteTeacherInfo(Integer teacherId) {
		return this.teacherInfo.deleteTeacherInfo(teacherId);
	}

}

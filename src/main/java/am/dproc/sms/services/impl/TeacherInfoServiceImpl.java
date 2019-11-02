package am.dproc.sms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.TeacherInfoDAO;
import am.dproc.sms.models.TeacherInfo;
import am.dproc.sms.services.interfaces.TeacherInfoService;

@Service
public class TeacherInfoServiceImpl  implements TeacherInfoService{
	@Autowired
	TeacherInfoDAO teacherInfoDao;

	@Override
	public Integer addTeacherInfo(TeacherInfo teacherInfo) {
		return teacherInfoDao.addTeacherInfo(teacherInfo);
	}

	@Override
	public TeacherInfo getTeacherInfoByTeacherId(Integer teacherId) {
		return teacherInfoDao.getTeacherInfo(teacherId);
	}

	@Override
	public Integer updateTeacherInfo(TeacherInfo teacherInfo) {
		if (teacherInfo.getPassportId() != null) {
			return this.teacherInfoDao.updateTeacherInfoPassportId(teacherInfo.getUserId(),
					teacherInfo.getPassportId());
		} else if (teacherInfo.getSocialCardId() != null) {
			return this.teacherInfoDao.updateTeacherInfoSocialCardId(teacherInfo.getUserId(),
					teacherInfo.getSocialCardId());
		} else if (teacherInfo.getBirthDate() != null) {
			return this.teacherInfoDao.updateTeacherInfoBirthDate(teacherInfo.getUserId(), teacherInfo.getBirthDate());
		} else if (teacherInfo.getImageUrl() != null) {
			return this.teacherInfoDao.updateTeacherInfoImageUrl(teacherInfo.getUserId(), teacherInfo.getImageUrl());
		}
		return 0;
	}

	@Override
	public Integer deleteTeacherInfo(Integer teacherId) {
		return this.teacherInfoDao.deleteTeacherInfo(teacherId);
	}

}

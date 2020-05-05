package cai.tm.hotel.user.feedback.service.impl;

import io.jboot.aop.annotation.Bean;
import com.jfinal.plugin.activerecord.Page;

import org.tm.common.base.service.impl.BaseServiceImpl;
import org.tm.common.base.model.ICondition;
import cai.tm.hotel.user.feedback.service.UserFeedbackService;
import cai.tm.hotel.user.feedback.model.UserFeedback;

@Bean
public class UserFeedbackServiceImpl extends BaseServiceImpl<UserFeedback> implements UserFeedbackService {

    @Override
    public Page<UserFeedback> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }
}
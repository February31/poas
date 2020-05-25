package com.wenjun.poas.service.impl;

import com.wenjun.poas.config.system.WarningConfig;
import com.wenjun.poas.entity.Event;
import com.wenjun.poas.entity.Text;
import com.wenjun.poas.entity.User;
import com.wenjun.poas.entity.Warning;
import com.wenjun.poas.mapper.IEventMapper;
import com.wenjun.poas.mapper.IUserMapper;
import com.wenjun.poas.mapper.IWarningMapper;
import com.wenjun.poas.service.IWarningService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/5/4
 */
@Service
public class WarningService implements IWarningService {
    private Integer size = 0;
    @Resource
    IWarningMapper warningMapper;
    @Resource
    JavaMailSender mailSender;
    @Resource
    IUserMapper userMapper;
    @Resource
    IEventMapper eventMapper;
    @Resource
    WarningConfig warningConfig;

    @Override
    public void addWarning(Warning warning) {
        warningMapper.insertWarning(warning);
    }

    @Override
    public void deleteWarning(Warning warning) {
        warningMapper.deleteWarning(warning);
    }

    @Override
    public Warning findWarning(String eventId) {
        return warningMapper.findWarning(eventId);
    }

    @Override
    public List<Warning> findByUser(String userId) {
        return warningMapper.findByUser(userId);
    }

    @Override
    public List<Warning> findByUserAll(String userId) {
        return warningMapper.findByUserAll(userId);
    }

    @Override
    public Boolean fit(String eventId, List<Text> list) {
        if (list.size() <= 0) {
            return false;
        }
        Warning warning = warningMapper.findWarning(eventId);
        if (warning == null) {
            return false;
        }
        int i = 0;
        for (Text text : list) {
            if ("刚刚".equals(text.getCreatedAt()) || text.getCreatedAt().contains("分钟")) {
                i++;
            }
        }
        size = i;
        return Integer.parseInt(warning.getMax()) <= i;
    }

    @Override
    public void sendEmail(String eventId) {
        Warning warning = warningMapper.findWarning(eventId);
        User user = userMapper.findById(warning.getUserId());
        String address = user.getEmail();
        //        String address = "358190915@qq.com";
        //第一步，拿到用户，然后拿到邮箱。（两个办法，一是在预警中保留邮箱，二是通过预警查用户，然后拿邮箱）
        //拿到舆情条数，开始组装邮件内容（这部分应该是可配置的）
        //发邮件。
        Event event = eventMapper.findById(eventId);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("1453470090@qq.com");
        mailMessage.setTo(address);
//        邮件标题
        mailMessage.setSubject(warningConfig.title);
        String content = event.getName() + warningConfig.content + warning.getTime() + warningConfig.content2 + warning.getSize() + warningConfig.content3;
        mailMessage.setText(content);
        mailSender.send(mailMessage);

    }

    @Override
    public void updateTimeAndSize(Warning warning) {
        warningMapper.updateTimeAndSize(warning);
    }

    @Override
    public void updateWarning(Warning warning) {
        warningMapper.updateWarning(warning);
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public void handledWarning(String eventId) {
        warningMapper.handledWarning(eventId);
    }

}
